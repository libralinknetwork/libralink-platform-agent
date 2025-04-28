package io.libralink.platform.agent.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.libralink.client.payment.proto.Libralink;
import io.libralink.client.payment.proto.builder.echeck.ECheckBuilder;
import io.libralink.client.payment.proto.builder.echeck.ECheckSplitBuilder;
import io.libralink.client.payment.proto.builder.envelope.EnvelopeBuilder;
import io.libralink.client.payment.proto.builder.envelope.EnvelopeContentBuilder;
import io.libralink.client.payment.proto.builder.exception.BuilderException;
import io.libralink.client.payment.signature.SignatureHelper;
import io.libralink.client.payment.util.JsonUtils;
import io.libralink.platform.agent.api.protocol.ECheckController;
import io.libralink.platform.agent.services.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.web3j.crypto.Credentials;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ECheckController.class)
@ContextConfiguration(classes = { ApiTestConfiguration.class })
public class ECheckControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ECheckControllerTest.class);

    /* Payer Credentials */
    final private String PAYER_PK = "7af8df13f6aebcbd9edd369bb5f67bf7523517685491fea776bb547910ff5673";
    final private Credentials PAYER_CRED = Credentials.create(PAYER_PK);

    /* PAYEE */
    final private String PAYEE_PK = "64496cc969654b231087af38ce654aa8d539fea0970d90366e42a5e39761f3f5";
    final private Credentials PAYEE_CRED = Credentials.create(PAYEE_PK);

    /* Processor Credentials */
    private String PROCESSOR_PK = "d601b629b288ce5ab659b4782e7f34cc2279ac729485302fdcc19d0fccb78b0d";
    final private Credentials PROCESSOR_CRED = Credentials.create(PROCESSOR_PK);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AgentService agentService;

    @MockBean
    private EnvelopeService envelopeService;

    @MockBean
    private ProcessorFeeService processorFeeService;

    @MockBean
    private ECheckIssueService eCheckIssueService;

    @MockBean
    private ECheckDepositService eCheckDepositService;

    @MockBean
    private ProcessorService processorService;

    LocalDateTime now = LocalDateTime.now();

    private Libralink.ECheck eCheck = ECheckBuilder.newBuilder()
            .addFrom(PAYER_CRED.getAddress())
            .addFromProc(PROCESSOR_CRED.getAddress())
            .addTo(PAYEE_CRED.getAddress())
            .addToProc(PROCESSOR_CRED.getAddress())
            .addCurrency("USDC")
            .addFaceAmount(BigDecimal.valueOf(150))
            .addCreatedAt(now.toEpochSecond(ZoneOffset.UTC))
            .addExpiresAt(now.plusYears(10).toEpochSecond(ZoneOffset.UTC))
            .addNote("")
            .addCorrelationId(UUID.randomUUID())
            .addSplits(List.of(ECheckSplitBuilder.newBuilder()
                    .addTo(PAYEE_CRED.getAddress())
                    .addToProc(PROCESSOR_CRED.getAddress())
                    .addAmount(BigDecimal.valueOf(150))
                .build()
            )).build();

    private Libralink.Envelope unsignedEnvelope = EnvelopeBuilder.newBuilder()
            .addId(UUID.randomUUID())
            .addContent(EnvelopeContentBuilder.newBuilder()
                    .addECheck(eCheck)
                    .build())
            .build();

    @Test
    public void test_pre_process_echeck_signed_by_payer() throws Exception {

        when(processorFeeService.preProcess(any())).thenReturn(unsignedEnvelope);
        Libralink.Envelope signedEnvelope = SignatureHelper.sign(unsignedEnvelope, PAYER_CRED, Libralink.SignatureReason.IDENTITY);
        String base64Body = Base64.getEncoder().encodeToString(signedEnvelope.toByteArray());
        LOG.info("Base64 - " + base64Body);
        LOG.info("Json - " + JsonUtils.toJson(signedEnvelope));

        mockMvc.perform(post("/protocol/echeck/pre-issue")
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .content(base64Body))
                .andExpect(status().isOk());
    }

    @Test
    public void test_pre_process_echeck_signed_by_payee() throws Exception {

        when(processorFeeService.preProcess(any())).thenReturn(unsignedEnvelope);
        Libralink.Envelope signedEnvelope = SignatureHelper.sign(unsignedEnvelope, PAYEE_CRED, Libralink.SignatureReason.IDENTITY);
        String base64Body = Base64.getEncoder().encodeToString(signedEnvelope.toByteArray());
        LOG.info("Base64 - " + base64Body);
        LOG.info("Json - " + JsonUtils.toJson(signedEnvelope));

        mockMvc.perform(post("/protocol/echeck/pre-issue")
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .content(base64Body))
                .andExpect(status().isOk());
    }

    @Test
    public void test_issue_echeck_signed_by_payer() throws Exception {

        final String feeLockEnvelopeString = "CiQ0ZjRhOTUzYi05ZDAzLTQ3MDItYWU2Yy0wZDMxYTYwM2RhZTASgwUKKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNhoJU0VDUDI1NksxIAI6xwQKB3BlcmNlbnQSATEiuAQKJGJjMTBkNzY0LTgzOTUtNDM4My1hMWUwLTE5MGE0ZTg0ZTkyOBKIAwoqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlGglTRUNQMjU2SzEgAVLMAgoDMTUwEgRVU0RDGioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEiKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNioqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlMioweDE4NWNkNDU5NzU3YTYzZWQ3M2YyMTAwZjcwZDMxMTk4M2IzN2JjYTY6XQoDMTUwEioweDhmMzNkY2VlZWRmY2Y3MTg1YWE0ODBlZTE2ZGI5YjliYjc0NTc1NmUaKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNkDZmbrABkjZ5fTWB1okZmMyZDhhYmYtZDc0YS00NDMyLWJlYzgtOWU5OTU2NzA2OGVhGoQBMHg1ZGUyYjQ1YTI2N2FiZDdjZTdjZWZjN2RmY2NmMWRjOTQwZTRkMzVlODZkMTUxNDIwMGVkNDk2MGY1YjE2MmRlNzFiMjZhZTEwOTE0YWUyNzUwZWIzYWQwMWNhNzEyZDhhOTkyOGM3YTg0ZTMyMDZlZDkwNmM0NmE2OTcwN2U5MjFiGoQBMHhmYzBlOGZiMDYyNzEyYzU1YWJlZjA1NDM5ZmU2YTEyOTRiNzcxNDc1NjJjOTEyMDBkMzZjMjRlZGMxOGU2NjA2MGY1NTYxMDQzZmQ2NWZmYzY1NTFiMGYyZDJmYzM1Yzc2OThjYjFjNzgzM2Y2ZDJlYjkzNmEzOThkODVlYjc3ZjFj";
        byte[] decodedBytes = Base64.getDecoder().decode(feeLockEnvelopeString);
        Libralink.Envelope feeLockEnvelope = Libralink.Envelope.parseFrom(decodedBytes);

        Libralink.Envelope unsignedPayerEnvelope = EnvelopeBuilder.newBuilder()
                .addId(UUID.randomUUID())
                .addContent(EnvelopeContentBuilder.newBuilder()
                        .addEnvelope(feeLockEnvelope)
                        .build())
                .build();

        Libralink.Envelope signedByPayerEnvelope = SignatureHelper.sign(unsignedPayerEnvelope, PAYER_CRED, Libralink.SignatureReason.CONFIRM);
        String base64Body = Base64.getEncoder().encodeToString(signedByPayerEnvelope.toByteArray());
        LOG.info("Base64 - " + base64Body);
        LOG.info("Json - " + JsonUtils.toJson(signedByPayerEnvelope));

        when(eCheckIssueService.issue(any())).thenReturn(signedByPayerEnvelope);
        mockMvc.perform(post("/protocol/echeck/issue")
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .content(base64Body))
                .andExpect(status().isOk());
    }

    public ECheckControllerTest() throws BuilderException, BuilderException {
    }
}

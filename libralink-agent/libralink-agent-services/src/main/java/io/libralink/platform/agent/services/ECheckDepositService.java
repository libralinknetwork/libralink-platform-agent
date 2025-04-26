package io.libralink.platform.agent.services;

import io.libralink.client.payment.proto.Libralink;
import io.libralink.client.payment.proto.builder.api.DepositRequestBuilder;
import io.libralink.client.payment.proto.builder.api.DepositResponseBuilder;
import io.libralink.client.payment.proto.builder.envelope.EnvelopeBuilder;
import io.libralink.client.payment.proto.builder.envelope.EnvelopeContentBuilder;
import io.libralink.client.payment.signature.SignatureHelper;
import io.libralink.client.payment.util.EnvelopeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ECheckDepositService {

    private final Credentials processorCredentials;

    public ECheckDepositService(@Value("${libralink.processor.key.private}") String processorPrivateKey) {
        processorCredentials = Credentials.create(processorPrivateKey);
    }

    public Libralink.Envelope deposit(Libralink.Envelope envelope) throws Exception {

        /* TODO: heavy logic here */

        Libralink.DepositRequest depositRequest = EnvelopeUtils.findEntityByType(envelope, Libralink.DepositRequest.class).get();
        Libralink.Envelope eCheckEnvelope = depositRequest.getCheckEnvelope();
        List<Libralink.Envelope> requestEnvelopes = depositRequest.getRequestEnvelopesList();

        Libralink.DepositResponse response = DepositResponseBuilder.newBuilder()
                .addCheckEnvelopeId(UUID.fromString(eCheckEnvelope.getId()))
                .addRequestEnvelopeIds(requestEnvelopes.stream().map(env -> UUID.fromString(env.getId()))
                        .collect(Collectors.toList()))
            .build();

        Libralink.EnvelopeContent responseEnvelopeContent = EnvelopeContentBuilder.newBuilder()
                .addDepositResponse(response)
                .build();

        Libralink.Envelope responseEnvelope = EnvelopeBuilder.newBuilder()
                .addId(UUID.randomUUID())
                .addContent(responseEnvelopeContent).build();

        return SignatureHelper.sign(responseEnvelope, processorCredentials, Libralink.SignatureReason.CONFIRM);
    }
}

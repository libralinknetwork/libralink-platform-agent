# Libralink Agent

[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/3mRSbP89jqQQqkK78hQhCE/LEaH5aYP5LmW33fLeNb1JH/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/circleci/3mRSbP89jqQQqkK78hQhCE/LEaH5aYP5LmW33fLeNb1JH/tree/main)

![CodeQL](https://github.com/libralinknetwork/libralink-platform-agent/actions/workflows/codeql.yml/badge.svg)

## Run Postgres

```
docker-compose -f docker-compose.yml up -d postgres
```

## Data Migration
```
cd ./libralink-agent/migration
mvn clean install liquibase:update -N -DabsolutePath=`pwd`/src/main/resources
```

## Swagger
```
http://localhost:8082/swagger-ui.html
```

## Testing
### Register an Agent

The request being sent is
```
{
  "id": "d496a30a-699f-4152-8541-16b5d162e586",
  "content": {
    "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
    "algorithm": "SECP256K1",
    "reason": "IDENTITY",
    "registerKeyRequest": {
      "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
      "algorithm": "secp256k1",
      "confirmationId": "075e5892-0e32-4a0f-aeb3-e25394a51a7c",
      "hash": "dhFhkEjUVq4jI0d7BBcDWA\u003d\u003d"
    }
  },
  "sig": "0x29312058f361eb4389871bb13c860da7180c9a2d27fd518e21a72a67414abcc74a56b9d19518379392a545585fb4fdae49f50801d7c8a899ccd8f22fafb6dea41c"
}
```

Json Response
```
curl --header "Content-Type: text/plain" --header "Accept: */*" --request POST \
    --data 'CiRkNDk2YTMwYS02OTlmLTQxNTItODU0MS0xNmI1ZDE2MmU1ODYSswEKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJU0VDUDI1NksxIAGKAXcKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJc2VjcDI1NmsxIiQwNzVlNTg5Mi0wZTMyLTRhMGYtYWViMy1lMjUzOTRhNTFhN2MqGGRoRmhrRWpVVnE0akkwZDdCQmNEV0E9PRqEATB4MjkzMTIwNThmMzYxZWI0Mzg5ODcxYmIxM2M4NjBkYTcxODBjOWEyZDI3ZmQ1MThlMjFhNzJhNjc0MTRhYmNjNzRhNTZiOWQxOTUxODM3OTM5MmE1NDU1ODVmYjRmZGFlNDlmNTA4MDFkN2M4YTg5OWNjZDhmMjJmYWZiNmRlYTQxYw==' \
    http://localhost:8082/protocol/agent/register

# Response
{
    "id": "87d46dff-52c9-4a5f-aefd-3069ae515c52",
    "content": {
        "errorResponse": {
            "code": 999,
            "message": "Agent with address 0xf39902b133fbdcf926c1f48665c98d1b028d905a already registered"
        }
    }
}
```

Base64 Response
```
curl --header "Content-Type: text/plain" --header "Accept: text/plain" --request POST \
    --data 'CiRkNDk2YTMwYS02OTlmLTQxNTItODU0MS0xNmI1ZDE2MmU1ODYSswEKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJU0VDUDI1NksxIAGKAXcKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJc2VjcDI1NmsxIiQwNzVlNTg5Mi0wZTMyLTRhMGYtYWViMy1lMjUzOTRhNTFhN2MqGGRoRmhrRWpVVnE0akkwZDdCQmNEV0E9PRqEATB4MjkzMTIwNThmMzYxZWI0Mzg5ODcxYmIxM2M4NjBkYTcxODBjOWEyZDI3ZmQ1MThlMjFhNzJhNjc0MTRhYmNjNzRhNTZiOWQxOTUxODM3OTM5MmE1NDU1ODVmYjRmZGFlNDlmNTA4MDFkN2M4YTg5OWNjZDhmMjJmYWZiNmRlYTQxYw==' \
    http://localhost:8082/protocol/agent/register
    
# Response
CiQ3Y2RkMzc5Zi1lZjcwLTRmYWUtYmU3Ny0zYzU5MjZmMGM1NjMSVzJVCOcHElBBZ2VudCB3aXRoIGFkZHJlc3MgMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhIGFscmVhZHkgcmVnaXN0ZXJlZA==
```

### Get Balance
The request being sent is
```
{
  "id": "8711b0d6-1018-423b-9c76-3946d17a0964",
  "content": {
    "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
    "algorithm": "SECP256K1",
    "reason": "IDENTITY",
    "getBalanceRequest": {
      "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a"
    }
  },
  "sig": "0x07a5e58e78236a328d8eb4ab56e7525742a9968ad96748612fd9e231cda960601e95b23664b10ac4886b71eb0ac48ea36dd704bf95aae4daf7c58468cccbf9de1b"
}
```

Json Response
```
curl --header "Content-Type: text/plain" --header "Accept: */*" --request POST \
    --data 'CiQ4NzExYjBkNi0xMDE4LTQyM2ItOWM3Ni0zOTQ2ZDE3YTA5NjQSZwoqMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhGglTRUNQMjU2SzEgAVosCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEahAEweDA3YTVlNThlNzgyMzZhMzI4ZDhlYjRhYjU2ZTc1MjU3NDJhOTk2OGFkOTY3NDg2MTJmZDllMjMxY2RhOTYwNjAxZTk1YjIzNjY0YjEwYWM0ODg2YjcxZWIwYWM0OGVhMzZkZDcwNGJmOTVhYWU0ZGFmN2M1ODQ2OGNjY2JmOWRlMWI=' \
    http://localhost:8082/protocol/agent/balance

# Response
{
    "id": "5e0c9cda-5a24-404d-bf0f-99ddc0fb4741",
    "content": {
        "getBalanceResponse": {
            "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
            "available": "0",
            "pending": "0"
        }
    }
}
```

Base64 Response
```
curl --header "Content-Type: text/plain" --header "Accept: text/plain" --request POST \
    --data 'CiQ4NzExYjBkNi0xMDE4LTQyM2ItOWM3Ni0zOTQ2ZDE3YTA5NjQSZwoqMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhGglTRUNQMjU2SzEgAVosCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEahAEweDA3YTVlNThlNzgyMzZhMzI4ZDhlYjRhYjU2ZTc1MjU3NDJhOTk2OGFkOTY3NDg2MTJmZDllMjMxY2RhOTYwNjAxZTk1YjIzNjY0YjEwYWM0ODg2YjcxZWIwYWM0OGVhMzZkZDcwNGJmOTVhYWU0ZGFmN2M1ODQ2OGNjY2JmOWRlMWI=' \
    http://localhost:8082/protocol/agent/balance
    
# Response
CiQ1ODNkYjQyMC02NDAwLTRjNWItOTI2Yy03NDU4ODU3YTE2NGMSNGIyCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWESATAaATA=
```








### Deposit
The request being sent is
```
{
  "id": "3b83e7cb-ceb1-481d-9a9d-b52e78626f27",
  "content": {
    "address": "0x8f33dceeedfcf7185aa480ee16db9b9bb745756e",
    "algorithm": "SECP256K1",
    "reason": "IDENTITY",
    "depositRequest": {
      "checkEnvelope": {
        "id": "c6461d4a-0c27-4c7a-9879-c861e53d4356",
        "content": {
          "address": "0x185cd459757a63ed73f2100f70d311983b37bca6",
          "algorithm": "SECP256K1",
          "reason": "CONFIRM",
          "envelope": {
            "id": "16e9e78c-5e3f-413d-b5ba-d5b35c40fead",
            "content": {
              "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
              "algorithm": "SECP256K1",
              "reason": "CONFIRM",
              "envelope": {
                "id": "d7de9c1a-4f58-48b8-bbcb-ea1eeb1cbf19",
                "content": {
                  "address": "0x185cd459757a63ed73f2100f70d311983b37bca6",
                  "algorithm": "SECP256K1",
                  "reason": "FEE_LOCK",
                  "processingFee": {
                    "feeType": "percent",
                    "amount": "1",
                    "envelope": {
                      "id": "80dc8de2-fdfc-4b4f-9add-b5b2d14c93ec",
                      "content": {
                        "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
                        "algorithm": "SECP256K1",
                        "reason": "IDENTITY",
                        "check": {
                          "faceAmount": "150",
                          "currency": "USDC",
                          "from": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
                          "fromProc": "0x185cd459757a63ed73f2100f70d311983b37bca6",
                          "to": "0x8f33dceeedfcf7185aa480ee16db9b9bb745756e",
                          "toProc": "0x185cd459757a63ed73f2100f70d311983b37bca6",
                          "splits": [{
                            "amount": "150",
                            "to": "0x8f33dceeedfcf7185aa480ee16db9b9bb745756e",
                            "toProc": "0x185cd459757a63ed73f2100f70d311983b37bca6"
                          }],
                          "createdAt": "1745784129",
                          "expiresAt": "1746043329",
                          "correlationId": "523ef80d-9df6-4b16-8d01-348ecf7b6a49"
                        }
                      },
                      "sig": "0x84791338246de7c27d237c7d11532bbdfd31e2c5292dcd517b6d25ab6bcda90d2b21e594ebdcd48ca7408f0b3ba0630b5c08e9833773779f81d57636511a91071b"
                    }
                  }
                },
                "sig": "0x84eb53c8945d59e866dab55d28a4af1ab416c1bd1a8fe06a43704ccb540b1be118b83bbc196c7d7265062a6b209d12577d503ad4c6c4bfdd99866405dcb0f2ff1b"
              }
            },
            "sig": "0xf62f2c8171babaa8d483f542203955eeb5fedb17d5e24641654fd2c8cfcb68bc674fe6fbfb37ebef7ae004565aeae5830ab096822f5078ddd7c4e415e97815031c"
          }
        },
        "sig": "0x2236535bc758a710ef204193a5df88a676c43e76c6fe10dad0913faf160587e37a615e472c55df940ace97da24fdab33864abc1956429dc0beb67396845a1c421b"
      },
      "requestEnvelopes": [{
        "id": "70e9bf44-ecf8-4cd5-a402-5aec7b91e507",
        "content": {
          "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
          "algorithm": "SECP256K1",
          "reason": "CONFIRM",
          "envelope": {
            "id": "8d43485e-2298-45c1-bfa6-69aca41991dd",
            "content": {
              "address": "0x8f33dceeedfcf7185aa480ee16db9b9bb745756e",
              "algorithm": "SECP256K1",
              "reason": "CONFIRM",
              "paymentRequest": {
                "amount": "100",
                "from": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
                "fromProc": "fake",
                "to": "0x8f33dceeedfcf7185aa480ee16db9b9bb745756e",
                "toProc": "fake",
                "currency": "USDC",
                "createdAt": "1745784129",
                "correlationId": "523ef80d-9df6-4b16-8d01-348ecf7b6a49"
              }
            },
            "sig": "0x20cea085d663024574b7b597e1c9007b18b4e96dd3ea4582f1691943ce91234221505450035c8a2029bf04a8dc9b4ae66eeac3276a02a0497f167a53ff3eb6ee1b"
          }
        },
        "sig": "0xc1823897c7ce9435fa4cc36ecc7a79900ae7b5187e83403cd0011e016dcec194044b49d5a4938c2aac4bb2c91b5bfcab7b6006d7d87e6f05d7ab21777f59f2091c"
      }]
    }
  },
  "sig": "0x78aa6bfa5128cc8792da14f0df507ba3519df6f84d8195857ddefb1de1a1b1fe395682c720377d6f641ff6c5b2312a2d0954e70431e1b07533fcfecef75619881c"
}
```

Json Response
```
curl --header "Content-Type: text/plain" --header "Accept: */*" --request POST \
    --data 'CiQzYjgzZTdjYi1jZWIxLTQ4MWQtOWE5ZC1iNTJlNzg2MjZmMjcSwA8KKjB4OGYzM2RjZWVlZGZjZjcxODVhYTQ4MGVlMTZkYjliOWJiNzQ1NzU2ZRoJU0VDUDI1NksxIAFqhA8KiwoKJGM2NDYxZDRhLTBjMjctNGM3YS05ODc5LWM4NjFlNTNkNDM1NhLbCAoqMHgxODVjZDQ1OTc1N2E2M2VkNzNmMjEwMGY3MGQzMTE5ODNiMzdiY2E2GglTRUNQMjU2SzEgAyqfCAokMTZlOWU3OGMtNWUzZi00MTNkLWI1YmEtZDViMzVjNDBmZWFkEu8GCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEaCVNFQ1AyNTZLMSADKrMGCiRkN2RlOWMxYS00ZjU4LTQ4YjgtYmJjYi1lYTFlZWIxY2JmMTkSgwUKKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNhoJU0VDUDI1NksxIAI6xwQKB3BlcmNlbnQSATEiuAQKJDgwZGM4ZGUyLWZkZmMtNGI0Zi05YWRkLWI1YjJkMTRjOTNlYxKIAwoqMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhGglTRUNQMjU2SzEgAVLMAgoDMTUwEgRVU0RDGioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEiKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNioqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlMioweDE4NWNkNDU5NzU3YTYzZWQ3M2YyMTAwZjcwZDMxMTk4M2IzN2JjYTY6XQoDMTUwEioweDhmMzNkY2VlZWRmY2Y3MTg1YWE0ODBlZTE2ZGI5YjliYjc0NTc1NmUaKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNkDBmrrABkjBg8rABlokNTIzZWY4MGQtOWRmNi00YjE2LThkMDEtMzQ4ZWNmN2I2YTQ5GoQBMHg4NDc5MTMzODI0NmRlN2MyN2QyMzdjN2QxMTUzMmJiZGZkMzFlMmM1MjkyZGNkNTE3YjZkMjVhYjZiY2RhOTBkMmIyMWU1OTRlYmRjZDQ4Y2E3NDA4ZjBiM2JhMDYzMGI1YzA4ZTk4MzM3NzM3NzlmODFkNTc2MzY1MTFhOTEwNzFiGoQBMHg4NGViNTNjODk0NWQ1OWU4NjZkYWI1NWQyOGE0YWYxYWI0MTZjMWJkMWE4ZmUwNmE0MzcwNGNjYjU0MGIxYmUxMThiODNiYmMxOTZjN2Q3MjY1MDYyYTZiMjA5ZDEyNTc3ZDUwM2FkNGM2YzRiZmRkOTk4NjY0MDVkY2IwZjJmZjFiGoQBMHhmNjJmMmM4MTcxYmFiYWE4ZDQ4M2Y1NDIyMDM5NTVlZWI1ZmVkYjE3ZDVlMjQ2NDE2NTRmZDJjOGNmY2I2OGJjNjc0ZmU2ZmJmYjM3ZWJlZjdhZTAwNDU2NWFlYWU1ODMwYWIwOTY4MjJmNTA3OGRkZDdjNGU0MTVlOTc4MTUwMzFjGoQBMHgyMjM2NTM1YmM3NThhNzEwZWYyMDQxOTNhNWRmODhhNjc2YzQzZTc2YzZmZTEwZGFkMDkxM2ZhZjE2MDU4N2UzN2E2MTVlNDcyYzU1ZGY5NDBhY2U5N2RhMjRmZGFiMzM4NjRhYmMxOTU2NDI5ZGMwYmViNjczOTY4NDVhMWM0MjFiEvMECiQ3MGU5YmY0NC1lY2Y4LTRjZDUtYTQwMi01YWVjN2I5MWU1MDcSwwMKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJU0VDUDI1NksxIAMqhwMKJDhkNDM0ODVlLTIyOTgtNDVjMS1iZmE2LTY5YWNhNDE5OTFkZBLXAQoqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlGglTRUNQMjU2SzEgA0qbAQoDMTAwEioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEaBGZha2UiKjB4OGYzM2RjZWVlZGZjZjcxODVhYTQ4MGVlMTZkYjliOWJiNzQ1NzU2ZSoEZmFrZTIEVVNEQzjBmrrABkokNTIzZWY4MGQtOWRmNi00YjE2LThkMDEtMzQ4ZWNmN2I2YTQ5GoQBMHgyMGNlYTA4NWQ2NjMwMjQ1NzRiN2I1OTdlMWM5MDA3YjE4YjRlOTZkZDNlYTQ1ODJmMTY5MTk0M2NlOTEyMzQyMjE1MDU0NTAwMzVjOGEyMDI5YmYwNGE4ZGM5YjRhZTY2ZWVhYzMyNzZhMDJhMDQ5N2YxNjdhNTNmZjNlYjZlZTFiGoQBMHhjMTgyMzg5N2M3Y2U5NDM1ZmE0Y2MzNmVjYzdhNzk5MDBhZTdiNTE4N2U4MzQwM2NkMDAxMWUwMTZkY2VjMTk0MDQ0YjQ5ZDVhNDkzOGMyYWFjNGJiMmM5MWI1YmZjYWI3YjYwMDZkN2Q4N2U2ZjA1ZDdhYjIxNzc3ZjU5ZjIwOTFjGoQBMHg3OGFhNmJmYTUxMjhjYzg3OTJkYTE0ZjBkZjUwN2JhMzUxOWRmNmY4NGQ4MTk1ODU3ZGRlZmIxZGUxYTFiMWZlMzk1NjgyYzcyMDM3N2Q2ZjY0MWZmNmM1YjIzMTJhMmQwOTU0ZTcwNDMxZTFiMDc1MzNmY2ZlY2VmNzU2MTk4ODFj' \
    http://localhost:8082/protocol/echeck/deposit

# Response
{
    "id": "593dca57-fbf7-413f-9200-42571fc61007",
    "content": {
        "address": "0x185cd459757a63ed73f2100f70d311983b37bca6",
        "algorithm": "SECP256K1",
        "reason": "CONFIRM",
        "depositResponse": {
            "checkEnvelopeId": "c6461d4a-0c27-4c7a-9879-c861e53d4356",
            "requestEnvelopeIds": [
                "70e9bf44-ecf8-4cd5-a402-5aec7b91e507"
            ]
        }
    },
    "sig": "0x52e4a920e19ad2fe3f2cbc59cbcf97a9e3a1f485201364b362d4916a855628f21fb3b9fa335f1b96c983c2316a3cdb8eccb89ce1a106d1ba5fb06842e4e79df61b"
}
```

Base64 Response
```
curl --header "Content-Type: text/plain" --header "Accept: text/plain" --request POST \
    --data 'CiQzYjgzZTdjYi1jZWIxLTQ4MWQtOWE5ZC1iNTJlNzg2MjZmMjcSwA8KKjB4OGYzM2RjZWVlZGZjZjcxODVhYTQ4MGVlMTZkYjliOWJiNzQ1NzU2ZRoJU0VDUDI1NksxIAFqhA8KiwoKJGM2NDYxZDRhLTBjMjctNGM3YS05ODc5LWM4NjFlNTNkNDM1NhLbCAoqMHgxODVjZDQ1OTc1N2E2M2VkNzNmMjEwMGY3MGQzMTE5ODNiMzdiY2E2GglTRUNQMjU2SzEgAyqfCAokMTZlOWU3OGMtNWUzZi00MTNkLWI1YmEtZDViMzVjNDBmZWFkEu8GCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEaCVNFQ1AyNTZLMSADKrMGCiRkN2RlOWMxYS00ZjU4LTQ4YjgtYmJjYi1lYTFlZWIxY2JmMTkSgwUKKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNhoJU0VDUDI1NksxIAI6xwQKB3BlcmNlbnQSATEiuAQKJDgwZGM4ZGUyLWZkZmMtNGI0Zi05YWRkLWI1YjJkMTRjOTNlYxKIAwoqMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhGglTRUNQMjU2SzEgAVLMAgoDMTUwEgRVU0RDGioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEiKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNioqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlMioweDE4NWNkNDU5NzU3YTYzZWQ3M2YyMTAwZjcwZDMxMTk4M2IzN2JjYTY6XQoDMTUwEioweDhmMzNkY2VlZWRmY2Y3MTg1YWE0ODBlZTE2ZGI5YjliYjc0NTc1NmUaKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNkDBmrrABkjBg8rABlokNTIzZWY4MGQtOWRmNi00YjE2LThkMDEtMzQ4ZWNmN2I2YTQ5GoQBMHg4NDc5MTMzODI0NmRlN2MyN2QyMzdjN2QxMTUzMmJiZGZkMzFlMmM1MjkyZGNkNTE3YjZkMjVhYjZiY2RhOTBkMmIyMWU1OTRlYmRjZDQ4Y2E3NDA4ZjBiM2JhMDYzMGI1YzA4ZTk4MzM3NzM3NzlmODFkNTc2MzY1MTFhOTEwNzFiGoQBMHg4NGViNTNjODk0NWQ1OWU4NjZkYWI1NWQyOGE0YWYxYWI0MTZjMWJkMWE4ZmUwNmE0MzcwNGNjYjU0MGIxYmUxMThiODNiYmMxOTZjN2Q3MjY1MDYyYTZiMjA5ZDEyNTc3ZDUwM2FkNGM2YzRiZmRkOTk4NjY0MDVkY2IwZjJmZjFiGoQBMHhmNjJmMmM4MTcxYmFiYWE4ZDQ4M2Y1NDIyMDM5NTVlZWI1ZmVkYjE3ZDVlMjQ2NDE2NTRmZDJjOGNmY2I2OGJjNjc0ZmU2ZmJmYjM3ZWJlZjdhZTAwNDU2NWFlYWU1ODMwYWIwOTY4MjJmNTA3OGRkZDdjNGU0MTVlOTc4MTUwMzFjGoQBMHgyMjM2NTM1YmM3NThhNzEwZWYyMDQxOTNhNWRmODhhNjc2YzQzZTc2YzZmZTEwZGFkMDkxM2ZhZjE2MDU4N2UzN2E2MTVlNDcyYzU1ZGY5NDBhY2U5N2RhMjRmZGFiMzM4NjRhYmMxOTU2NDI5ZGMwYmViNjczOTY4NDVhMWM0MjFiEvMECiQ3MGU5YmY0NC1lY2Y4LTRjZDUtYTQwMi01YWVjN2I5MWU1MDcSwwMKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJU0VDUDI1NksxIAMqhwMKJDhkNDM0ODVlLTIyOTgtNDVjMS1iZmE2LTY5YWNhNDE5OTFkZBLXAQoqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlGglTRUNQMjU2SzEgA0qbAQoDMTAwEioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEaBGZha2UiKjB4OGYzM2RjZWVlZGZjZjcxODVhYTQ4MGVlMTZkYjliOWJiNzQ1NzU2ZSoEZmFrZTIEVVNEQzjBmrrABkokNTIzZWY4MGQtOWRmNi00YjE2LThkMDEtMzQ4ZWNmN2I2YTQ5GoQBMHgyMGNlYTA4NWQ2NjMwMjQ1NzRiN2I1OTdlMWM5MDA3YjE4YjRlOTZkZDNlYTQ1ODJmMTY5MTk0M2NlOTEyMzQyMjE1MDU0NTAwMzVjOGEyMDI5YmYwNGE4ZGM5YjRhZTY2ZWVhYzMyNzZhMDJhMDQ5N2YxNjdhNTNmZjNlYjZlZTFiGoQBMHhjMTgyMzg5N2M3Y2U5NDM1ZmE0Y2MzNmVjYzdhNzk5MDBhZTdiNTE4N2U4MzQwM2NkMDAxMWUwMTZkY2VjMTk0MDQ0YjQ5ZDVhNDkzOGMyYWFjNGJiMmM5MWI1YmZjYWI3YjYwMDZkN2Q4N2U2ZjA1ZDdhYjIxNzc3ZjU5ZjIwOTFjGoQBMHg3OGFhNmJmYTUxMjhjYzg3OTJkYTE0ZjBkZjUwN2JhMzUxOWRmNmY4NGQ4MTk1ODU3ZGRlZmIxZGUxYTFiMWZlMzk1NjgyYzcyMDM3N2Q2ZjY0MWZmNmM1YjIzMTJhMmQwOTU0ZTcwNDMxZTFiMDc1MzNmY2ZlY2VmNzU2MTk4ODFj' \
    http://localhost:8082/protocol/echeck/deposit

# Response
CiRkOTA4ODg2ZC1iNmIwLTQwMzItYmU0ZS1lYTQ1MWMxNDhhYTUShwEKKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNhoJU0VDUDI1NksxIANyTAokYzY0NjFkNGEtMGMyNy00YzdhLTk4NzktYzg2MWU1M2Q0MzU2EiQ3MGU5YmY0NC1lY2Y4LTRjZDUtYTQwMi01YWVjN2I5MWU1MDcahAEweDUyZTRhOTIwZTE5YWQyZmUzZjJjYmM1OWNiY2Y5N2E5ZTNhMWY0ODUyMDEzNjRiMzYyZDQ5MTZhODU1NjI4ZjIxZmIzYjlmYTMzNWYxYjk2Yzk4M2MyMzE2YTNjZGI4ZWNjYjg5Y2UxYTEwNmQxYmE1ZmIwNjg0MmU0ZTc5ZGY2MWI=
```

### Trigger Error Response
Json Response
```
curl --header "Content-Type: text/plain" --header "Accept: */*" --request POST \
    --data 'invalid' \
    http://localhost:8082/protocol/agent/balance

# Response
{
    "id": "2aac9d01-6e6e-4cab-8dfe-5793aee89582",
    "content": {
        "errorResponse": {
            "code": 999,
            "message": "Unable to parse request"
        }
    }
}
```

Base64 Response
```
curl --header "Content-Type: text/plain" --header "Accept: text/plain" --request POST \
    --data 'invalid' \
    http://localhost:8082/protocol/agent/balance

# Response
CiRiZDYzZGRjOC00MDA5LTRkNTEtYTYzNC1iYzMwOTRhMDQzMTYSHjIcCOcHEhdVbmFibGUgdG8gcGFyc2UgcmVxdWVzdA==
```
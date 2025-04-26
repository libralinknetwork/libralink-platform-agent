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
  "id": "d63ffd01-b465-4707-80d3-8ebdc71230a8",
  "content": {
    "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
    "algorithm": "SECP256K1",
    "reason": "IDENTITY",
    "registerKeyRequest": {
      "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
      "algorithm": "secp256k1",
      "confirmationId": "075e5892-0e32-4a0f-aeb3-e25394a51a7c",
      "hash": "dhFhkEjUVq4jI0d7BBcDWA=="
    }
  },
  "sig": "0x8d94faad1832023c5b474ecf58df3987a7e494dca9ce4cb5852a7f62ee6ebe6e766f178ccef1c4b10e6f32084973484c6ca2107d928bee96b067aede0be9ccfb1b"
}
```

Json Response
```
curl --header "Content-Type: text/plain" --header "Accept: */*" --request POST \
    --data 'CiRhZDZkZmYzMi1jMjhjLTRmZjEtYmFhMi02YTQ1MDdlNmYzMGESswEKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJU0VDUDI1NksxIAGCAXcKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJc2VjcDI1NmsxIiQwNzVlNTg5Mi0wZTMyLTRhMGYtYWViMy1lMjUzOTRhNTFhN2MqGGRoRmhrRWpVVnE0akkwZDdCQmNEV0E9PRqEATB4OGQ5NGZhYWQxODMyMDIzYzViNDc0ZWNmNThkZjM5ODdhN2U0OTRkY2E5Y2U0Y2I1ODUyYTdmNjJlZTZlYmU2ZTc2NmYxNzhjY2VmMWM0YjEwZTZmMzIwODQ5NzM0ODRjNmNhMjEwN2Q5MjhiZWU5NmIwNjdhZWRlMGJlOWNjZmIxYg==' \
    http://localhost:8082/protocol/agent/register

# Response
{
  "id": "69d09e81-6797-4a02-a823-cfe3ddb8c8e3",
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
    --data 'CiRhZDZkZmYzMi1jMjhjLTRmZjEtYmFhMi02YTQ1MDdlNmYzMGESswEKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJU0VDUDI1NksxIAGCAXcKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJc2VjcDI1NmsxIiQwNzVlNTg5Mi0wZTMyLTRhMGYtYWViMy1lMjUzOTRhNTFhN2MqGGRoRmhrRWpVVnE0akkwZDdCQmNEV0E9PRqEATB4OGQ5NGZhYWQxODMyMDIzYzViNDc0ZWNmNThkZjM5ODdhN2U0OTRkY2E5Y2U0Y2I1ODUyYTdmNjJlZTZlYmU2ZTc2NmYxNzhjY2VmMWM0YjEwZTZmMzIwODQ5NzM0ODRjNmNhMjEwN2Q5MjhiZWU5NmIwNjdhZWRlMGJlOWNjZmIxYg==' \
    http://localhost:8082/protocol/agent/register
    
# Response
CiQ2NjYyOTI0Yi0xNWU2LTQ0OWQtODAwZi03ODZmMWU3ODFmZWISWJIBVQjnBxJQQWdlbnQgd2l0aCBhZGRyZXNzIDB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YSBhbHJlYWR5IHJlZ2lzdGVyZWQ=
```

### Get Balance
The request being sent is
```
{
  "id": "41e4c030-e7c8-4d67-8016-ddb7459db60b",
  "content": {
    "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
    "algorithm": "SECP256K1",
    "reason": "IDENTITY",
    "getBalanceRequest": {
      "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a"
    }
  },
  "sig": "0x8020d572658a66da2aaf2b78b7d9fe86500e83fe0cb9bb00f46e778e93c2a15c56530e7ae93139ce7f7362c131ab21bd29887ee71aead17087c20c94bc409bf31c"
}
```

Json Response
```
curl --header "Content-Type: text/plain" --header "Accept: */*" --request POST \
    --data 'CiQwMzM1YjM3Yy1hMmQ5LTRlM2EtYTAxMC0xNWM3YzE0NDc4NzgSZwoqMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhGglTRUNQMjU2SzEgAVIsCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEahAEweDgwMjBkNTcyNjU4YTY2ZGEyYWFmMmI3OGI3ZDlmZTg2NTAwZTgzZmUwY2I5YmIwMGY0NmU3NzhlOTNjMmExNWM1NjUzMGU3YWU5MzEzOWNlN2Y3MzYyYzEzMWFiMjFiZDI5ODg3ZWU3MWFlYWQxNzA4N2MyMGM5NGJjNDA5YmYzMWM=' \
    http://localhost:8082/protocol/agent/balance

# Response
{
  "id": "b1e2ed3d-f2d8-4a0c-9e10-1ad0c6118582",
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
    --data 'CiQwMzM1YjM3Yy1hMmQ5LTRlM2EtYTAxMC0xNWM3YzE0NDc4NzgSZwoqMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhGglTRUNQMjU2SzEgAVIsCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEahAEweDgwMjBkNTcyNjU4YTY2ZGEyYWFmMmI3OGI3ZDlmZTg2NTAwZTgzZmUwY2I5YmIwMGY0NmU3NzhlOTNjMmExNWM1NjUzMGU3YWU5MzEzOWNlN2Y3MzYyYzEzMWFiMjFiZDI5ODg3ZWU3MWFlYWQxNzA4N2MyMGM5NGJjNDA5YmYzMWM=' \
    http://localhost:8082/protocol/agent/balance
    
# Response
CiQ3ZDhjNmRmMi1jYTZhLTQxMGEtYmMwZi00M2FiODIxZjI1MDcSNFoyCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWESATAaATA=
```

### Deposit
The request being sent is
```
{
  "id": "7b8a41bf-db06-4a4c-b098-bc80627be823",
  "content": {
    "address": "0x8f33dceeedfcf7185aa480ee16db9b9bb745756e",
    "algorithm": "SECP256K1",
    "reason": "IDENTITY",
    "depositRequest": {
      "checkEnvelope": {
        "id": "1856b5c9-67a6-4df2-8c8f-4495466ba4ec",
        "content": {
          "address": "0x185cd459757a63ed73f2100f70d311983b37bca6",
          "algorithm": "SECP256K1",
          "reason": "CONFIRM",
          "envelope": {
            "id": "19b53532-2ff7-4269-9c6e-a26bc15e3584",
            "content": {
              "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
              "algorithm": "SECP256K1",
              "reason": "CONFIRM",
              "envelope": {
                "id": "0cac10bb-7460-472c-8d2b-e1ad06992402",
                "content": {
                  "address": "0x185cd459757a63ed73f2100f70d311983b37bca6",
                  "algorithm": "SECP256K1",
                  "reason": "FEE_LOCK",
                  "processingFee": {
                    "feeType": "percent",
                    "amount": "1",
                    "envelope": {
                      "id": "c727be44-33be-4674-bf68-8b4944f8169d",
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
                          "createdAt": "1745585306",
                          "expiresAt": "1745844506",
                          "correlationId": "59266c5d-a2c4-4521-81dd-fb52b34e8cd5"
                        }
                      },
                      "sig": "0x2eb51fc42343b4a7dcf45d7ed2bdf168de8bac509a403cee63dfc107312c19c863f0f2f5aa4bb2b35a8eb430cd1820a3d8c8e471666655a74968879b7981cdc61b"
                    }
                  }
                },
                "sig": "0x4db4bca86c30580378467f1e49ca699157ee52f03d31d781d1dc35feddba9a13123e66d9a67f986645fc452b0bb2401b0fe8622a307db08443eb17a0395651671c"
              }
            },
            "sig": "0x30282805e4d2cc66b4bff1bcba381e7c4eb15980e64af1b0fc47fd5471fb1dfc3fb0292b96d24c701b1b2495f94985c7a42de90902a782deafa13cdb05fddcd31c"
          }
        },
        "sig": "0x80d9d8f8ec5c71f1b32c0777cf3d043082af0d81b403d6a7dcf644d994a273e506e454b6fdc151e94a64fbfacf5c4e085fb9c71e3903706acf421fa790360bef1c"
      },
      "requestEnvelopes": [{
        "id": "9068f6bb-1ad4-454b-9e7e-37812c34a90c",
        "content": {
          "address": "0xf39902b133fbdcf926c1f48665c98d1b028d905a",
          "algorithm": "SECP256K1",
          "reason": "CONFIRM",
          "envelope": {
            "id": "b616dad7-6fc9-44be-ba49-b0aa79089d47",
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
                "createdAt": "1745585306",
                "correlationId": "59266c5d-a2c4-4521-81dd-fb52b34e8cd5"
              }
            },
            "sig": "0xc7ab73f565bd1598ba96951b507741b1a217bc6ab13c41a5f987c4d485a09d2e09102ef47f085000529571ebb332258fd34b556b5b25a837d5de837735704df51c"
          }
        },
        "sig": "0x4fe511126ab7f6b97757ea4ceacc531ad958accac476c07eec271a956e1486c735b2c46e21d1c1fa96356ea2340f6642767b338e594c30dd894982e1e1e199621b"
      }]
    }
  },
  "sig": "0xbfeff4aa6ce3b0b42416e2b33881a0a037c194250a5402424f2e58032af6e73e57e8cd074881dcd2c93eecad8fe6efe6ca9b161c8622d84b69d69735e690eac01c"
}
```

Json Response
```
curl --header "Content-Type: text/plain" --header "Accept: */*" --request POST \
    --data 'CiQzMWUyMTgyYS0wY2FhLTRjYTgtOWVkZi0xNTBkNDQyOWNiNWMSwA8KKjB4OGYzM2RjZWVlZGZjZjcxODVhYTQ4MGVlMTZkYjliOWJiNzQ1NzU2ZRoJU0VDUDI1NksxIAFihA8KiwoKJGQwYjMwYTVjLTM5NjAtNDk5Ni1hN2VmLTIyNWIyYmQ2NDE5MhLbCAoqMHgxODVjZDQ1OTc1N2E2M2VkNzNmMjEwMGY3MGQzMTE5ODNiMzdiY2E2GglTRUNQMjU2SzEgAyqfCAokNWNmNDU5MzktMDJiNy00MWEyLWI2ODEtNTNlYjJiY2Q3MjEyEu8GCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEaCVNFQ1AyNTZLMSADKrMGCiRkZTAwYTBlZS01NTZiLTQ3NTUtODM0Ny01ZGYyZWM3NzNlZjESgwUKKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNhoJU0VDUDI1NksxIAIyxwQKB3BlcmNlbnQSATEiuAQKJGFhOWZiZDgxLTdlNzktNDU4MC05YTRkLWEyNzRkYzgwZDQxNxKIAwoqMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhGglTRUNQMjU2SzEgAUrMAgoDMTUwEgRVU0RDGioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEiKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNioqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlMioweDE4NWNkNDU5NzU3YTYzZWQ3M2YyMTAwZjcwZDMxMTk4M2IzN2JjYTY6XQoDMTUwEioweDhmMzNkY2VlZWRmY2Y3MTg1YWE0ODBlZTE2ZGI5YjliYjc0NTc1NmUaKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNkDkga7ABkjk6r3ABlokMTU5MDRiZWYtYjJjMi00YmVjLWE3NGEtM2IyZWU0MjI3OTVmGoQBMHhiYzg4MTllY2I4MmZiMWUxNjBhYTlhYWI1NjdmNmM3YWI5Y2Y1ZjFjYzNiOGZmYWExMmE4YThiMjg5M2U5ODYyMzI0YzcxY2U2NGEwZTE0NDgyNGIxNTRjZWViYWQ5M2U2YTE0ZDNiYjc4MTZlMWUyODM2MTZkMjRmN2UxMzRmODFjGoQBMHhjZDhhYmMyNDdhNGM2ZTM3MGE2MTJjMzI1MWI1Mzg3MGQzOGFkNTg0MTI0MmZhOTRiNGM0ZTE5OTRkODE1ODg4MGRhNTgyZDQwNTgzN2MyOGJmNjA1ZjhhYzI2NjRhMjE5YjcyMTliNGI4YjYyMjcyNjViYzI5ZDE4YTE2NzRlZDFiGoQBMHgxMTM1MDhmZDcyY2NmZjk0NTIzZjkwOTg3YTQzMWNkNjQ5YzFiYzc5MzVlYzY2YjhjOWZmNTg1ZDZiZjY1YjA3NzRlZWE1NWM1MDIyOGViN2I5NTIzZTJjNWFhM2NiNmNmNmEwYjMzYWRjMmUyYWQ3MWVlMDVjYTY1ZGJlNDM5ODFjGoQBMHg3MjA0NGIwN2Q5MDI4YmZlNDFkNmM4MDc1ZGFhODYwMmYyODI0ZDk5ZTIzN2MzNDI4NzQ0ODdkODg0YTI4OWRjMTViYzI4ODc5MWE3OTEyOThlMjEzMDM3MjhiNDA4YTBjMTc3ZWNkOGMzYmY1OGQxNTNjNjU4YTc1YjQ1MmRkNDFjEvMECiRmMzU1OWVhMy03ZjIyLTQ3MmUtOGVhMy0xZmU1NWQ4OGIyNWYSwwMKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJU0VDUDI1NksxIAMqhwMKJDc0NDQ5ZDQ3LTY5YWMtNGY3Ny05MGQ3LWI2MGVlMmY4ZmU3MRLXAQoqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlGglTRUNQMjU2SzEgA0KbAQoDMTAwEioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEaBGZha2UiKjB4OGYzM2RjZWVlZGZjZjcxODVhYTQ4MGVlMTZkYjliOWJiNzQ1NzU2ZSoEZmFrZTIEVVNEQzjkga7ABkokMTU5MDRiZWYtYjJjMi00YmVjLWE3NGEtM2IyZWU0MjI3OTVmGoQBMHgxMzFlMmQzMTU4NjFmNzE2ODY0OTQ3ZWE4YjVmYzg1NGY5OTJhOGUwMDA2NTk1ZjQ5OTJiNjI1OGMxMmQ4MmNjM2M2ZTA5NjFlODZiN2IzOThhNDQyZTU1MTk4YWQzNDg5NjY4MTU4YTE2NzVkMTE3OTczOGYyNmM3YTQzYjljNzFiGoQBMHg0OGY1MWM2M2ZmY2Q4MzEwZDJkNGExYmRmM2VmMTU5MjliNThlOTM0OWJhZmNhMTZiZDczYjI2ZjM4ZjkwMmNiNzg2NGRmZGFhNGMwMWU1OGQxNGI3OWEwMjAyM2M4ZGQ0NDBlYWQwODJiMjRiZDkxYTFjYzJjZTYyN2M4YWQwNDFiGoQBMHg1ODMyYTI3MWM0MzlmZWQ5MjczZDRlNmYyOGMzZGM5Y2ZjYzU1OGNlZTY3YzA1NGY2NDg2MjFmN2E2MzNiZjNiNGU0YmU1YjRhYmM4YWMxMGFkYWNmMjQ2ZjgzNWE3NjQ0NGM3YjdkOWFiZDJhYjAwMDRjYzg5MzdmNWQ2MGY2NDFj' \
    http://localhost:8082/protocol/echeck/deposit

# Response
{
  "id": "e5d1c201-96f8-406b-88d2-744a2b8f37b3",
  "content": {
    "address": "0x185cd459757a63ed73f2100f70d311983b37bca6",
    "algorithm": "SECP256K1",
    "reason": "CONFIRM",
    "depositResponse": {
      "checkEnvelopeId": "d0b30a5c-3960-4996-a7ef-225b2bd64192",
      "requestEnvelopeIds": ["f3559ea3-7f22-472e-8ea3-1fe55d88b25f"]
    }
  },
  "sig": "0x7f391d600123443c53d8ad1bd180b8ec944f4d461e7d5c10dd6ce886e0471a3003f9f189a89215a6583ab09f9e969b0868009ed7d0f96c73bc6f118e98846a8c1b"
}
```

Base64 Response
```
curl --header "Content-Type: text/plain" --header "Accept: text/plain" --request POST \
    --data 'CiQzMWUyMTgyYS0wY2FhLTRjYTgtOWVkZi0xNTBkNDQyOWNiNWMSwA8KKjB4OGYzM2RjZWVlZGZjZjcxODVhYTQ4MGVlMTZkYjliOWJiNzQ1NzU2ZRoJU0VDUDI1NksxIAFihA8KiwoKJGQwYjMwYTVjLTM5NjAtNDk5Ni1hN2VmLTIyNWIyYmQ2NDE5MhLbCAoqMHgxODVjZDQ1OTc1N2E2M2VkNzNmMjEwMGY3MGQzMTE5ODNiMzdiY2E2GglTRUNQMjU2SzEgAyqfCAokNWNmNDU5MzktMDJiNy00MWEyLWI2ODEtNTNlYjJiY2Q3MjEyEu8GCioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEaCVNFQ1AyNTZLMSADKrMGCiRkZTAwYTBlZS01NTZiLTQ3NTUtODM0Ny01ZGYyZWM3NzNlZjESgwUKKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNhoJU0VDUDI1NksxIAIyxwQKB3BlcmNlbnQSATEiuAQKJGFhOWZiZDgxLTdlNzktNDU4MC05YTRkLWEyNzRkYzgwZDQxNxKIAwoqMHhmMzk5MDJiMTMzZmJkY2Y5MjZjMWY0ODY2NWM5OGQxYjAyOGQ5MDVhGglTRUNQMjU2SzEgAUrMAgoDMTUwEgRVU0RDGioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEiKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNioqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlMioweDE4NWNkNDU5NzU3YTYzZWQ3M2YyMTAwZjcwZDMxMTk4M2IzN2JjYTY6XQoDMTUwEioweDhmMzNkY2VlZWRmY2Y3MTg1YWE0ODBlZTE2ZGI5YjliYjc0NTc1NmUaKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNkDkga7ABkjk6r3ABlokMTU5MDRiZWYtYjJjMi00YmVjLWE3NGEtM2IyZWU0MjI3OTVmGoQBMHhiYzg4MTllY2I4MmZiMWUxNjBhYTlhYWI1NjdmNmM3YWI5Y2Y1ZjFjYzNiOGZmYWExMmE4YThiMjg5M2U5ODYyMzI0YzcxY2U2NGEwZTE0NDgyNGIxNTRjZWViYWQ5M2U2YTE0ZDNiYjc4MTZlMWUyODM2MTZkMjRmN2UxMzRmODFjGoQBMHhjZDhhYmMyNDdhNGM2ZTM3MGE2MTJjMzI1MWI1Mzg3MGQzOGFkNTg0MTI0MmZhOTRiNGM0ZTE5OTRkODE1ODg4MGRhNTgyZDQwNTgzN2MyOGJmNjA1ZjhhYzI2NjRhMjE5YjcyMTliNGI4YjYyMjcyNjViYzI5ZDE4YTE2NzRlZDFiGoQBMHgxMTM1MDhmZDcyY2NmZjk0NTIzZjkwOTg3YTQzMWNkNjQ5YzFiYzc5MzVlYzY2YjhjOWZmNTg1ZDZiZjY1YjA3NzRlZWE1NWM1MDIyOGViN2I5NTIzZTJjNWFhM2NiNmNmNmEwYjMzYWRjMmUyYWQ3MWVlMDVjYTY1ZGJlNDM5ODFjGoQBMHg3MjA0NGIwN2Q5MDI4YmZlNDFkNmM4MDc1ZGFhODYwMmYyODI0ZDk5ZTIzN2MzNDI4NzQ0ODdkODg0YTI4OWRjMTViYzI4ODc5MWE3OTEyOThlMjEzMDM3MjhiNDA4YTBjMTc3ZWNkOGMzYmY1OGQxNTNjNjU4YTc1YjQ1MmRkNDFjEvMECiRmMzU1OWVhMy03ZjIyLTQ3MmUtOGVhMy0xZmU1NWQ4OGIyNWYSwwMKKjB4ZjM5OTAyYjEzM2ZiZGNmOTI2YzFmNDg2NjVjOThkMWIwMjhkOTA1YRoJU0VDUDI1NksxIAMqhwMKJDc0NDQ5ZDQ3LTY5YWMtNGY3Ny05MGQ3LWI2MGVlMmY4ZmU3MRLXAQoqMHg4ZjMzZGNlZWVkZmNmNzE4NWFhNDgwZWUxNmRiOWI5YmI3NDU3NTZlGglTRUNQMjU2SzEgA0KbAQoDMTAwEioweGYzOTkwMmIxMzNmYmRjZjkyNmMxZjQ4NjY1Yzk4ZDFiMDI4ZDkwNWEaBGZha2UiKjB4OGYzM2RjZWVlZGZjZjcxODVhYTQ4MGVlMTZkYjliOWJiNzQ1NzU2ZSoEZmFrZTIEVVNEQzjkga7ABkokMTU5MDRiZWYtYjJjMi00YmVjLWE3NGEtM2IyZWU0MjI3OTVmGoQBMHgxMzFlMmQzMTU4NjFmNzE2ODY0OTQ3ZWE4YjVmYzg1NGY5OTJhOGUwMDA2NTk1ZjQ5OTJiNjI1OGMxMmQ4MmNjM2M2ZTA5NjFlODZiN2IzOThhNDQyZTU1MTk4YWQzNDg5NjY4MTU4YTE2NzVkMTE3OTczOGYyNmM3YTQzYjljNzFiGoQBMHg0OGY1MWM2M2ZmY2Q4MzEwZDJkNGExYmRmM2VmMTU5MjliNThlOTM0OWJhZmNhMTZiZDczYjI2ZjM4ZjkwMmNiNzg2NGRmZGFhNGMwMWU1OGQxNGI3OWEwMjAyM2M4ZGQ0NDBlYWQwODJiMjRiZDkxYTFjYzJjZTYyN2M4YWQwNDFiGoQBMHg1ODMyYTI3MWM0MzlmZWQ5MjczZDRlNmYyOGMzZGM5Y2ZjYzU1OGNlZTY3YzA1NGY2NDg2MjFmN2E2MzNiZjNiNGU0YmU1YjRhYmM4YWMxMGFkYWNmMjQ2ZjgzNWE3NjQ0NGM3YjdkOWFiZDJhYjAwMDRjYzg5MzdmNWQ2MGY2NDFj' \
    http://localhost:8082/protocol/echeck/deposit

# Response
CiRiNTFmMTU2OC1mMzViLTQ0YzQtOGMwNC0xOThmMzNhOWM3YmIShwEKKjB4MTg1Y2Q0NTk3NTdhNjNlZDczZjIxMDBmNzBkMzExOTgzYjM3YmNhNhoJU0VDUDI1NksxIANqTAokZDBiMzBhNWMtMzk2MC00OTk2LWE3ZWYtMjI1YjJiZDY0MTkyEiRmMzU1OWVhMy03ZjIyLTQ3MmUtOGVhMy0xZmU1NWQ4OGIyNWYahAEweDdmMzkxZDYwMDEyMzQ0M2M1M2Q4YWQxYmQxODBiOGVjOTQ0ZjRkNDYxZTdkNWMxMGRkNmNlODg2ZTA0NzFhMzAwM2Y5ZjE4OWE4OTIxNWE2NTgzYWIwOWY5ZTk2OWIwODY4MDA5ZWQ3ZDBmOTZjNzNiYzZmMTE4ZTk4ODQ2YThjMWI=
```

### Trigger Error Response
Json Response
```
curl --header "Content-Type: text/plain" --header "Accept: */*" --request POST \
    --data 'invalid' \
    http://localhost:8082/protocol/agent/balance

# Response
{
  "id": "6c233e88-e7ad-49bc-8826-3fa405d28916",
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
CiRkOWQyZGQyZi05MDRlLTQ2OGYtYWE5Ni0yNDIwNDdiNTVlY2USH5IBHAjnBxIXVW5hYmxlIHRvIHBhcnNlIHJlcXVlc3Q=
```
FROM --platform=linux/amd64 openjdk:11
WORKDIR /suite

COPY target/suite-*.jar /libralink/suite.jar

ENV JAVA_OPTIONS=""
CMD java ${JAVA_OPTIONS} \
    -Dspring.datasource.url="${DATASOURCE_URL}" \
    -Dspring.datasource.username="${DATASOURCE_USERNAME}" \
    -Dspring.datasource.password="${DATASOURCE_PASSWORD}" \
    -jar /libralink/suite.jar

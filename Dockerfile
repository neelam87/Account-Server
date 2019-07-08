FROM openjdk:10-jre-slim
COPY ./target/account-service.jar /opt/lib/
COPY ./src/main/resources/account.sh /opt/bin/account.sh
RUN chmod 755 /opt/bin/account.sh
EXPOSE 8061
# Dockerfile for gym-server
FROM container-registry.oracle.com/java/jdk-no-fee-term:21.0.1

WORKDIR /app

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY ./target/gymadmin-0.0.1-SNAPSHOT.jar /app/gymadmin.jar

ENTRYPOINT ["java", "-jar", "/app/gymadmin.jar"]

EXPOSE 8080
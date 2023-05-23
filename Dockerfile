# 基础镜像
FROM harbor-test.ebonex.io/opensource/openjdk:8u322-jdk

RUN mkdir /app
WORKDIR /app

COPY target/salt-*.jar /app/salt.jar

# 暴露端口
EXPOSE 8080

CMD ["java", "-jar", "salt.jar"]

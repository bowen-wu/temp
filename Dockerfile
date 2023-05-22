# 基础镜像
FROM openjdk:8u322-jdk

RUN mkdir /app
WORKDIR /app

COPY wechat-shop-order/target/salt-0.0.1-SNAPSHOT.jar /app

# 暴露端口
EXPOSE 8080

CMD ["java", "-jar", "salt-0.0.1-SNAPSHOT.jar"]

#FROM 147642600964.dkr.ecr.ap-south-1.amazonaws.com/azul/zulu-openjdk-alpine:17-userupdate as builder
FROM azul/zulu-openjdk-alpine:17 as builder
ARG JAR_FILE=target/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

#FROM 147642600964.dkr.ecr.ap-south-1.amazonaws.com/azul/zulu-openjdk-alpine:17.0.3-jre-userupdate
FROM azul/zulu-openjdk-alpine:17
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
EXPOSE 9091
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

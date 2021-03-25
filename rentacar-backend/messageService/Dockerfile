FROM openjdk:8-jdk-alpine
EXPOSE 8082
COPY entrypoint.sh /entrypoint.sh
COPY target/messageService-0.0.1-SNAPSHOT.jar messageservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]

FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY entrypoint.sh /entrypoint.sh
COPY target/adService-0.0.1-SNAPSHOT.jar adservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]

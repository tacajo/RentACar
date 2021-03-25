FROM openjdk:8-jdk-alpine
EXPOSE 8083
COPY entrypoint.sh /entrypoint.sh
COPY target/rentACarService-0.0.1-SNAPSHOT.jar rentACarservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]

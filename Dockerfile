FROM maven as builder
WORKDIR /source
COPY . /source
RUN mvn clean package -U

FROM maven
WORKDIR /app
COPY --from=builder /source/target/pizzeriataskmain-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD java -jar pizzeriataskmain-0.0.1-SNAPSHOT.jar
FROM maven:3.8-openjdk-8

# Copy source folder 
COPY src /home/app/src

# copy pom.xml to app folder
COPY pom.xml /home/app

# package with mvn 
RUN mvn -f /home/app/pom.xml clean package -DskipTests


EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/target/demo-0.0.1-SNAPSHOT.jar"]
FROM maven:3.8-openjdk-8

# COPY mvnw .
# COPY .mvn .mvn
# COPY pom.xml .
# COPY src src

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# RUN mvn package -DskipTests 


# ARG JAR_FILE=target/*.jar
# ADD target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
# COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

# RUN mvn clean

EXPOSE 8080
# CMD ["mvn", "package", "-DskipTests"]
ENTRYPOINT ["java", "-jar", "/home/app/target/demo-0.0.1-SNAPSHOT.jar"]
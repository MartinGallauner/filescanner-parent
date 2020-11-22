FROM adoptopenjdk/openjdk15
VOLUME /filescanner
COPY filescanner-rest/target/filescanner-*.jar filescanner-rest.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Duser.timezone=Europe/Vienna", "-jar","/filescanner-rest.jar"]

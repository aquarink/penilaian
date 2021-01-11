FROM openjdk:11
VOLUME [ "/tmp" ]
ADD target/penilaian.jar penilaian.jar
ENTRYPOINT [ "java" , "-jar" , "/penilaian.jar"]
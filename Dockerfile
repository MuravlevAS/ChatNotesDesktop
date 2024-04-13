FROM openjdk:17-jdk-slim

WORKDIR /opt/chatnotes-desktop

COPY . .
RUN apt update \
    && apt-get -y install binutils \
    && apt-get -y install fakeroot \
    && apt-get clean

RUN ./gradlew clean :jpackage

ENTRYPOINT [ "/bin/sh" ]
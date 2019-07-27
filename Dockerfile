FROM openjdk:8-jre-alpine

MAINTAINER Faisal Alnahhas <falnahhas@headstorm.com>

ADD ./ /app

VOLUME [ "/app" ]

WORKDIR /app

EXPOSE 80

ENTRYPOINT start.sh
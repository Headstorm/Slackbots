FROM openjdk:8-jre-alpine
MAINTAINER Faisal Alnahhas <falnahhas@headstorm.com>

ADD slack-bot-project/ /app

VOLUME [ "/app" ]
WORKDIR /app



FROM williamyeh/java7
MAINTAINER Faisal Alnahhas <falnahhas@headstorm.com>

ENV SBT_VERSION 1.2.7
ENV SBT_JAR https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/$SBT_VERSION/sbt-launch.jar

ADD  $SBT_JAR  /usr/local/bin/sbt-launch.jar
COPY sbt.sh    /usr/local/bin/sbt

RUN echo "==> building sbt project"       && \
    echo "==> [CAUTION] this takes a while!"  && \
    sbt

VOLUME [ "/app" ]
WORKDIR /app

# Define default command.
ENTRYPOINT ["sbt"]
CMD ["clean run"]


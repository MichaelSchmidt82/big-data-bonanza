FROM hseeberger/scala-sbt:11.0.14.1-oraclelinux8_1.6.2_2.13.8

WORKDIR /prj_root

ADD src/main/scala/Main.scala src/main/scala/Main.scala
ADD build.sbt build.sbt
ADD project/build.properties project/build.properties

RUN sbt package

# use build, not here
# CMD ["sh", "-c", "bash"]
ENTRYPOINT ["sbt", "run"]
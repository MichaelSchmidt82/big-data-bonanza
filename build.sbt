ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "spark-test"
  )


val SparkVersion = "3.4.0"
//fork / run := true
fork := true

libraryDependencies +=  "org.apache.hadoop" % "hadoop-client" % "3.3.2"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % SparkVersion,
  "org.apache.spark" %% "spark-sql" % SparkVersion
)


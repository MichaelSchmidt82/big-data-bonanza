ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "big-data-bonanza"
  )


val SparkVersion = "3.4.0"
//fork / run := true
fork := true

libraryDependencies += "org.apache.commons" % "commons-compress" % "1.21"
libraryDependencies +=  "org.apache.hadoop" % "hadoop-client" % "3.3.2"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % SparkVersion,
  "org.apache.spark" %% "spark-sql" % SparkVersion
)

libraryDependencies += "com.hierynomus" % "smbj" % "0.11.5"


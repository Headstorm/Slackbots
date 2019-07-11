name := "slack-bot-project"

scalaVersion := "2.11.6"

version := "0.1"

resolvers += "scalac repo" at "https://raw.githubusercontent.com/ScalaConsultants/mvn-repo/master/"

val circeVersion = "0.11.1"
val sparkVersion = "1.6.1"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "com.github.slack-scala-client" %% "slack-scala-client" % "0.2.6",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic"% circeVersion,
  "io.circe" %% "circe-parser"% circeVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-twitter" % sparkVersion,
  "org.apache.httpcomponents" % "httpclient" % "4.5.9",
  "com.google.code.gson" % "gson" % "2.2.4"
)
//libraryDependencies += "com.github.slack-scala-client" %% "slack-scala-client" % "0.2.6"
name := "scalaExample"

organization := "com.tf"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.2"

resolvers ++= Seq(
  "spray repo" % "http://repo.spray.io",
  "Akka Repository" % "http://repo.akka.io/releases/",
  "opennlp sourceforge repo" % "http://opennlp.sourceforge.net/maven2"
  "Sonatype Snapshots" % "https://oss.sonatype.org/content/repositories/releases/"
)

scalacOptions += "-target:jvm-1.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"     % "2.2.0-RC1" % "compile",
  "io.spray"          %  "spray-caching"  % "1.2-M8"    % "compile",
  "io.spray"          %  "spray-can"      % "1.2-M8"    % "compile",
  "io.spray"          %  "spray-httpx"    % "1.2-M8"    % "compile",
  "io.spray"          %% "spray-json"     % "1.2.5"     % "compile",
  "io.spray"          %  "spray-routing"  % "1.2-M8"    % "compile",
  "org.pegdown"       %  "pegdown"        % "1.4.0"     % "compile",
  "org.scala-lang"    %  "scala-compiler" % "2.10.2"    % "compile",
  "org.webjars"       %  "bootstrap"      % "2.3.2"     % "runtime",
  "org.webjars"       %  "codemirror"     % "3.14"      % "runtime",
  "org.webjars"       %  "html5shiv"      % "3.6.2"     % "runtime",
  "org.webjars"       %  "jquery"         % "2.0.2"     % "runtime",
  "com.typesafe.akka" %% "akka-testkit"   % "2.2.0-RC1" % "test",
  "io.spray"          %  "spray-testkit"  % "1.2-M8"    % "test",
  "org.scalatest"     %% "scalatest"      % "1.9.1"     % "test"
)

fork := true

seq(Revolver.settings: _*)

seq(Twirl.settings: _*)

seq(com.typesafe.sbt.SbtStartScript.startScriptForClassesSettings: _*)

Twirl.twirlImports := Seq("com.twitter.scaffold.Document", "Document._")

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.1.0-beta1" withSources() withJavadoc()

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.0"

libraryDependencies +=  "org.scalatest" % "scalatest_2.10.0-M4" % "1.9-2.10.0-M4-B1"

libraryDependencies +=  "junit" % "junit" % "4.8.1" % "test"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.1.0"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.1.0"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.1.0"

libraryDependencies += "org.apache.spark" %% "spark-streaming-twitter" % "1.1.0"

libraryDependencies += "com.google.code.gson" % "gson" % "2.3"

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "3.0.3"

libraryDependencies += "commons-cli" % "commons-cli" % "1.2"

libraryDependencies += "org.apache.ctakes" % "ctakes-core" % "3.2.0"

libraryDependencies += "org.apache.ctakes" % "ctakes-core-res" % "3.2.0"

libraryDependencies += "org.apache.ctakes" % "ctakes-constituency-parser" % "3.2.0"

libraryDependencies += "org.apache.ctakes" % "ctakes-clinical-pipeline" % "3.2.0"


name := "scaffold"

organization := "com.twitter"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.2"

resolvers += "spray repo" at "http://repo.spray.io"

resolvers +=   "Akka Repository" at "http://repo.akka.io/releases/"

resolvers +=   "opennlp sourceforge repo" at "http://opennlp.sourceforge.net/maven2"

resolvers +=   "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/releases/"

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
  "org.scalatest" % "scalatest_2.10.0-M4" % "1.9-2.10.0-M4-B1"     % "test",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.1.0-beta1"      % "runtime",
  "org.apache.spark" %% "spark-core" % "1.1.0"     % "runtime",
  "org.apache.spark" %% "spark-mllib" % "1.1.0"     % "runtime",
  "org.apache.spark" %% "spark-sql" % "1.1.0"     % "runtime",
  "org.apache.spark" %% "spark-streaming" % "1.1.0"     % "runtime",
  "org.apache.spark" %% "spark-streaming-twitter" % "1.1.0"     % "runtime",
  "org.apache.ctakes" % "ctakes-core" % "3.2.0"     % "runtime",
  "org.apache.ctakes" % "ctakes-core-res" % "3.2.0"     % "runtime",
  "org.apache.ctakes" % "ctakes-constituency-parser" % "3.2.0"     % "runtime",
  "org.apache.ctakes" % "ctakes-clinical-pipeline" % "3.2.0"     % "runtime",
  "com.google.code.gson" % "gson" % "2.3"     % "runtime",
  "com.google.guava"      % "guava" % "11.0.1"        % "test",
  "org.specs2"                 % "specs2_2.9.2"        %"1.12.3"       % "test",
  "org.twitter4j" % "twitter4j-core" % "3.0.3"     % "runtime",
  "commons-cli" % "commons-cli" % "1.2"     % "runtime",
  "junit" % "junit" % "4.8.1" % "test"
)

fork := true

seq(Revolver.settings: _*)

seq(Twirl.settings: _*)

seq(com.typesafe.sbt.SbtStartScript.startScriptForClassesSettings: _*)

Twirl.twirlImports := Seq("com.twitter.scaffold.Document", "Document._")

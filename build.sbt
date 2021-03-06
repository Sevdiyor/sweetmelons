import _root_.play.PlayImport._
import _root_.play.PlayScala
import _root_.sbt.Keys._
import _root_.sbt._

name := "sweetmelons"

version := "1.0"

lazy val `sweetmelons` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.slick" %% "slick" % "2.1.0" withJavadoc() withSources(),
  "com.typesafe.play" %% "play-slick" % "0.8.0" withJavadoc() withSources(),
  "com.typesafe.slick" % "slick_2.11" % "2.1.0",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
// Auth
"jp.t2v" %% "play2-auth" % "0.12.0" withSources() withJavadoc(),
"jp.t2v" %% "play2-auth-test" % "0.12.0" % "test" withSources() withJavadoc(),
"jp.t2v" %% "stackable-controller" % "0.4.0" withSources() withJavadoc()
)



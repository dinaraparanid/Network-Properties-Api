ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "networkPropertiesCalculator",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect"         % "3.5.4",
      "org.http4s"    %% "http4s-dsl"          % "0.23.26",
      "org.http4s"    %% "http4s-ember-server" % "0.23.26",
      "org.http4s"    %% "http4s-circe"        % "0.23.26",
      "io.circe"      %% "circe-generic"       % "0.14.6",
      "io.circe"      %% "circe-literal"       % "0.14.6",
      "org.scalatest" %% "scalatest"           % "3.2.17" % Test,
    )
  )

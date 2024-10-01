val scala3Version = "3.5.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "chesso",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.16" % Test
    )
  )

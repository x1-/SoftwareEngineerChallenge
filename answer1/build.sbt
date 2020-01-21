name := "answer1"
version := "0.1"
scalaVersion := "2.13.1"

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:higherKinds",
  "-Xlint",
)
libraryDependencies ++= Seq(
  "org.scala-lang"      % "scala-reflect" % "2.13.1" % "provided",
  "org.wvlet.airframe" %% "airspec"       % "20.1.1" % "test",
  "org.scalacheck"     %% "scalacheck"    % "1.14.1" % "test"
)

testFrameworks += new TestFramework("wvlet.airspec.Framework")

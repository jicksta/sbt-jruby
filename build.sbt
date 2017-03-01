name := "scala-jruby-spark"
version := "0.1.0"
scalaVersion := "2.11.8"

mainClass := Some("dummy.Runme")

libraryDependencies ++= Seq(
  "org.jruby" % "jruby" % "9.1.+",
  "org.apache.spark" %% "spark-core"      % "2.1.+",
  "org.apache.spark" %% "spark-sql"       % "2.1.+"
)
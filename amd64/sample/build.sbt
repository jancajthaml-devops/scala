organization := "jancajthaml"
name := "sample"
version := "1.0.0"

scalaVersion := sys.env.get("SCALA_VERSION").get

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "3.2.10" % Test
)
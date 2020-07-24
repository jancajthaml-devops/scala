organization := "jancajthaml"
name := "sample"
version := "1.0.0"

scalaVersion := sys.env.get("SCALA_VERSION").get

crossScalaVersions := Seq("2.17.2", scalaVersion)

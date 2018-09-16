import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.0.1"
    )),
    name := "generic-gis-bounding-box-creator",
    libraryDependencies ++= Seq(
			"com.github.tototoshi" %% "scala-csv" % "1.3.5",
			"com.github.shafiquejamal" %% "gis-util" % "0.0.16",
			scalaTest % Test,
		)
  )

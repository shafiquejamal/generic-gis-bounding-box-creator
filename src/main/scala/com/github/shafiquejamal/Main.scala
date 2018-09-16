package com.github.shafiquejamal

import com.github.tototoshi.csv._
import java.io.File

import com.github.shafiquejamal.gisutil.location.{BoundingBox, GPSCoordinate, Lat, Lng}

import scala.util.Try

object Main extends App {

  val inputPath = args(0)
  val latitudeField = args(1)
  val longitudeField = args(2)
  val kmEdgeLength = args(3)
  val outputPath = args(4)

  val outputFile = new File(outputPath)
  val writer = CSVWriter.open(outputFile)
  val inputFile = new File(inputPath)
  val header =
    (firstLine(inputFile).getOrElse("") + ",nWLat,nWLng,sWLat,sWLng,nELat,nELng,sELat,sELng").split(",").map(_.trim)

  val reader = CSVReader.open(inputPath)
  writer writeRow header

  val iterator = reader.iteratorWithHeaders

  while (iterator.hasNext) {
    val row = iterator.next()
    val maybeLatitude = row.get(latitudeField).flatMap(s => Try(s.toDouble).toOption).map { lat => Lat(lat) }
    val maybeLongitude = row.get(longitudeField).flatMap(s => Try(s.toDouble).toOption).map { lat => Lng(lat) }
    val maybekmEdgeLength = Try(kmEdgeLength.toDouble).toOption
    val maybeBoundingBox = for {
      latitude <- maybeLatitude
      longitude <- maybeLongitude
      km <- maybekmEdgeLength
    } yield {
      BoundingBox.from(GPSCoordinate(latitude, longitude), km)
    }
    maybeBoundingBox.foreach{ bb =>
      val elementsToWrite = row.values.toList ++ List(
        bb.nW.lat.value.toString,
        bb.nW.lng.value.toString,
        bb.sW.lat.value.toString,
        bb.sW.lng.value.toString,
        bb.nE.lat.value.toString,
        bb.nE.lng.value.toString,
        bb.sE.lat.value.toString,
        bb.sE.lng.value.toString
      )
      writer writeRow elementsToWrite
    }
  }

  writer.close()

  // From https://stackoverflow.com/questions/8865434/how-to-get-first-line-from-file-in-scala
  private def firstLine(f: java.io.File): Option[String] = {
    val src = io.Source.fromFile(f)
    try {
      src.getLines.find(_ => true)
    } finally {
      src.close()
    }
  }

}

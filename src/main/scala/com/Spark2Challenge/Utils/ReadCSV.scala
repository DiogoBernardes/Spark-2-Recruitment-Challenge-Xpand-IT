package com.Spark2Challenge.Utils

import org.apache.spark.sql.{DataFrame, SparkSession}

object ReadCSV {
  def readCSV(sparkSession: SparkSession, filePath: String): DataFrame = {
    sparkSession.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(filePath)
  }
}

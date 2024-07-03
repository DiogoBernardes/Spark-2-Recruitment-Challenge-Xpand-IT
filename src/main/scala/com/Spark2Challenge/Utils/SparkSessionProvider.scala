package com.Spark2Challenge.Utils

import org.apache.spark.sql.SparkSession

object SparkSessionProvider {

  def getSession: SparkSession = {
    SparkSession.builder()
      .appName("Spark2 Recruitment Challenge Xpand IT")
      .master("local[*]")
      .getOrCreate()
  }
}

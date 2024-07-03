package com.Spark2Challenge

import com.Spark2Challenge.Utils.ReadCSV
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object TopRatedApps {
  def run(sparkSession: SparkSession): Unit = {

    // Read the CSV file
    val appsDataFrame = ReadCSV.readCSV(sparkSession, "src/main/resources/googleplaystore.csv")

    // Filter out the apps with rating greater or equal to 4.0 and without NaN or null values and order it by descending order
    val bestAppsDF = appsDataFrame.filter(col("Rating") >= 4.0 && !col("Rating").isNaN && col("Rating").isNotNull)
      .orderBy(desc("Rating"))

    // Write the previously made dataframe to CSV and delimit it by §
    bestAppsDF
      .coalesce(1)
      .write
      .option("header", "true")
      .option("delimiter", "§")
      .mode("overwrite")
      .csv("src/main/resources/best_apps.csv")

  }
}

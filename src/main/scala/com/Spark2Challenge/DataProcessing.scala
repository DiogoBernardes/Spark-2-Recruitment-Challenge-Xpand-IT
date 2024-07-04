package com.Spark2Challenge

import com.Spark2Challenge.Utils.ReadCSV
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

object DataProcessing {
  def run(sparkSession: SparkSession): DataFrame = {
    //Is used to define the parsing policy for dates and timestamps in SQL contexts and DataFrames as of Spark 3.0
    sparkSession.conf.set("spark.sql.legacy.timeParserPolicy", "LEGACY")

    // Read the CSV file
    val appsDataFrame = ReadCSV.readCSV(sparkSession, "src/main/resources/googleplaystore.csv")

    // Create row_number column which will assign a sequential number to the app partitions and order it by reviews in descending order.
    // After that, we keep only the first row of each partition and we drop the column.
    val uniqueAppsDF = appsDataFrame
      .withColumn("row_number", row_number.over(Window.partitionBy("App").orderBy(col("reviews").desc)))
      .filter(col("row_number") === 1)
      .drop("row_number")

    // Group categories by app and collect them in an array, and rename the column to Categories
    val categoriesDF = appsDataFrame
      .groupBy("App")
      .agg(collect_set("Category").alias("Categories"))


    val df_3 = uniqueAppsDF.join(categoriesDF, Seq("App"), "left")
      .select(
        col("App"),
        col("Categories"),
        col("Rating").cast("Double").alias("Rating"),
        col("Reviews").cast("Long").alias("Reviews"),
        when(col("Size").endsWith("M"), regexp_replace(col("Size"), "M", "").cast("Double"))
          .when(col("Size").endsWith("k"), format_number(regexp_replace(col("Size"), "k", "").cast("Double") / 9, 3))
          .otherwise(col("Size").cast("Double")).alias("Size"),
        col("Installs"),
        col("Type"),
        format_number(regexp_replace(col("Price"), "[$]", "").cast("Double").multiply(0.9),2).alias("Price"),
        col("Content Rating").alias("Content_Rating"),
        split(col("Genres"), ";").cast("array<string>").alias("Genres"),
        to_date(col("Last Updated"), "MMM dd, yyyy").alias("Last_Updated"),
        col("Current Ver").alias("Current_Version"),
        col("Android Ver").alias("Minimum_Android_Version")
      )

    df_3

  }
}

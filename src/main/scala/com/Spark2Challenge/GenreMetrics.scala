package com.Spark2Challenge

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object GenreMetrics {
  def run(df_3: DataFrame): DataFrame = {
    // Group by Genres and calculate metrics
    val df_4 = df_3
      .groupBy("Genres")
      .agg(
        countDistinct("App").alias("Count"),
        format_number(avg("Rating"),2).alias("Average Rating"),
        format_number(avg("Average_Sentiment_Polarity"),2).alias("Average_Sentiment_Polarity")
      )

    // Save the final DataFrame as a Parquet file with gzip compression
    df_4
      .write
      .mode("overwrite")
      .option("compression", "gzip")
      .parquet("src/main/resources/googleplaystore_metrics")

    // Return Dataframe
    df_4
  }
}

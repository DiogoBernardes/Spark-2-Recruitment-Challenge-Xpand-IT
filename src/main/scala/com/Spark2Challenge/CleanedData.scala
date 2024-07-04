package com.Spark2Challenge

import org.apache.spark.sql.{DataFrame, SaveMode}

object CleanedData {
  def run(df_1: DataFrame, df_2: DataFrame): DataFrame = {
    // Join dataframes using 'App' column as joining key
    val df_3 = df_2.join(df_1, Seq("App"), "left")

    // Save the final DataFrame as a Parquet file with gzip compression
    df_3
      .write
      .mode(SaveMode.Overwrite)
      .option("header", "true")
      .option("compression", "gzip")
      .parquet("src/main/resources/googleplaystore_cleaned")

    df_3
  }
}

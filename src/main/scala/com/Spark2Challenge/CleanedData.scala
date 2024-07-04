package com.Spark2Challenge

import org.apache.spark.sql.{DataFrame, SaveMode}

object CleanedData {
  def run(df_1: DataFrame, df_3: DataFrame): DataFrame = {

        // Join dataframes using 'App' column as joining key
        val cleanedDataDF = df_3.join(df_1, Seq("App"), "left")

        // Save the final DataFrame as a Parquet file with gzip compression
        cleanedDataDF
          .write
          .mode(SaveMode.Overwrite)
          .option("header", "true")
          .option("compression", "gzip")
          .parquet("src/main/resources/googleplaystore_cleaned")

        cleanedDataDF
      }
}

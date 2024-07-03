package com.Spark2Challenge

import com.Spark2Challenge.Utils.ReadCSV
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object AverageSentimentPolarity {
  def run(sparkSession: SparkSession): DataFrame = {
    // Import implicits so we can use DataFrame functions
    import sparkSession.implicits._

      // Read the CSV file
      val userReviewsDataFrame = ReadCSV.readCSV(sparkSession, "src/main/resources/googleplaystore_user_reviews.csv")

      // Filter out rows with null or non-numeric Sentiment_Polarity
      val filteredDF = userReviewsDataFrame
        .filter($"Sentiment_Polarity".isNotNull && $"Sentiment_Polarity".rlike("""^\d+(\.\d+)?$"""))

      // Calculate average sentiment polarity by App
      val df_1 = filteredDF
        .groupBy("App")
        .agg(avg($"Sentiment_Polarity").alias("Average_Sentiment_Polarity"))
        .withColumn("Average_Sentiment_Polarity", format_number($"Average_Sentiment_Polarity",3).cast("Double"))

    // Result DataFrame
      df_1

  }
}

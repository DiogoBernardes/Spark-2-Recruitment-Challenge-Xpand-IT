package com.Spark2Challenge

import com.Spark2Challenge.Utils.SparkSessionProvider
import org.apache.spark.sql.DataFrame

object App {
  def main(args: Array[String]): Unit = {

    // Create the Spark Session
    val sparkSession = SparkSessionProvider.getSession

    // Run all the challenges
    val df_1 = AverageSentimentPolarity.run(sparkSession)
    TopRatedApps.run(sparkSession)
    val df_3 = DataProcessing.run(sparkSession)
    CleanedData.run(df_1,df_3)

    sparkSession.stop()

  }
}

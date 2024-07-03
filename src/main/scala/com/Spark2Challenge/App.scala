package com.Spark2Challenge

import com.Spark2Challenge.Utils.SparkSessionProvider

object App {
  def main(args: Array[String]): Unit = {

    // Create the Spark Session
    val sparkSession = SparkSessionProvider.getSession

    // Run all the challenges
    AverageSentimentPolarity.run(sparkSession).show()

    sparkSession.stop()

  }
}

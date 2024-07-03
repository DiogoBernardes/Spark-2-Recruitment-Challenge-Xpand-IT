# Official Image of JDK
FROM openjdk:17-slim

# Define the work directory inside the container
WORKDIR /app

# Install Scala
RUN apt-get update && \
    apt-get install -y scala

# Copy the JAR file to the container
COPY target/Spark2-Challenge-Xpand-IT-1.0-SNAPSHOT.jar /app

# Copy the necessary CSV files to the container
COPY src/main/resources/googleplaystore_user_reviews.csv /app/src/main/resources/
COPY src/main/resources/googleplaystore.csv /app/src/main/resources/

# Install Spark 3.4.3
RUN apt-get update && \
    apt-get install -y wget && \
    wget -qO spark.tgz https://downloads.apache.org/spark/spark-3.4.3/spark-3.4.3-bin-hadoop3.tgz && \
    tar -xvzf spark.tgz -C /opt && \
    mv /opt/spark-3.4.3-bin-hadoop3 /opt/spark && \
    rm spark.tgz && \
    rm -rf /var/lib/apt/lists/*

# Define the environment variables for Spark
ENV SPARK_HOME /opt/spark
ENV PATH $SPARK_HOME/bin:$PATH

# Start the Spark application using spark-submit
CMD ["spark-submit", "--class", "com.Spark2Challenge.App", "--master", "local[*]", "/app/Spark2-Challenge-Xpand-IT-1.0-SNAPSHOT.jar"]

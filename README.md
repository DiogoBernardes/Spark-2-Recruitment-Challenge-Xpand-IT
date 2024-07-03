# Spark 2 Recruitment Challenge

On that challenge, we pretend to develop a application in Spark that process 2 CSV's files. To develop this application we should do some basic operations on the given datasets and store the final results in a new archieve. The main objective of this challenge is to demonstrate the habilities in data manipulation using Spark, the project should be structured in maven for dependency management and with efficient and clean code.

## Datasets

The datasets that will be used are:

#### googleplaystore.csv
This dataset contains information about mobile app's registered in the Google Play Store.
* **App** - Application name
* **Category** - Category the app belongs to
* **Rating** - Overall user rating of the app (as when scraped)
* **Reviews** - Number of user reviews for the app (as when scraped)
* **Size** - Size of the app (as when scraped)
* **Installs** - Number of user downloads/installs for the app (as when scraped)
* **Type** - Paid or Free
* **Price** - Price of the app (as when scraped)
* **Content Rating** - Age group the app is targeted at - Children / Mature 21+ / Adult
* **Genres** - An app can belong to multiple genres (apart from its main category). For eg, a musical family game will belong to Music, Game, Family genres.
* **Last Updated** - Date when the app was last updated on Play Store (as when scraped)
* **Current Ver** - Current version of the app available on Play Store (as when scraped)
* **Android Ver** - Min required Android version (as when scraped)

#### googleplaystore_user_reviews.csv
This dataset contains information about the reviews of the users to the mobile apps in the Google Play Store.
* **App** - Name of app
* **Translated_Review** - User review (Preprocessed and translated to English)
* **Sentiment** - Positive/Negative/Neutral (Preprocessed)
* **Sentiment_Polarity** - Sentiment polarity score
* **Sentiment_Subjectivity** - Sentiment subjectivity score


## Exercises

* **Part 1:** Calculate the average sentiment polarity grouped by app name.
* **Part 2:** Filter and sort apps with a rating of 4.0 or higher and save the results in a CSV file.
* **Part 3:** Create a new DataFrame with data cleaned and formatted as specified.
* **Part 4:** Combine the results of Parts 1 and 3 into a final DataFrame and save as a Parquet file with gzip compression.
* **Part 5:** Calculate metrics by gender and save as a Parquet file with gzip compression.


## Running the Application

To run the application we need to:
1. Clone the repository:
```
git clone https://github.com/DiogoBernardes/Spark-2-Recruitment-Challenge-Xpand-IT.git
```
2. Build Docker Image:
```
docker build -t spark-app .
```

3. Execute Container:
```
docker run --name Spark2-Challenge-Xpand-IT -it spark-app
```

## Stack

![Spark](https://img.shields.io/badge/Apache_Spark-FFFFFF?style=for-the-badge&logo=apachespark&logoColor=#E35A16)
![Scala](https://img.shields.io/badge/scala-%23DC322F.svg?style=for-the-badge&logo=scala&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## Author

- [@DiogoBernardes](https://github.com/DiogoBernardes) 

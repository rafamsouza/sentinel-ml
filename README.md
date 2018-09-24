# sentinel-ml

To start Sentinel app, you must do as follows:

clone this repo.
go to app cloned dir
mvn clean install
java -jar target/sentinel-0.0.1-SNAPSHOT.jar

make a post with the body below:

path: /mutant

{
  "dna": ["YOUR DNA SEQUENCE", "YOUR DNA SEQUENCE", "YOUR DNA SEQUENCE", "YOUR DNA SEQUENCE", "YOUR DNA SEQUENCE", "YOUR DNA SEQUENCE"]
}

you can check the stats at /stats

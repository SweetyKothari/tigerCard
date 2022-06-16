# TigerCard

This project reads input from csv file in below mentioned format & calculate fare based on configuration mentioned in 
FareConfiguration Class

* **CSV format : .**
    * date,time,fromZone,toZone 
    * date format :yyyy-mm-dd ,time format: hh:mm
    * example 2022-06-01,10:30,1,2
  
Command-line Instructions
-------------------------

* **Prerequisites:**
    * Install the latest version of [Java](https://www.azul.com/downloads/?package=jdk) and [Maven](https://maven.apache.org/download.html).
    * You may need to set your `JAVA_HOME` & `MAVEN_HOME`.

```bash
cd tigercard
# Compile and run
mvn compile install
java -jar target/tigerCardApp.jar <fileName>  # Replace <fileName> with absolute path
```
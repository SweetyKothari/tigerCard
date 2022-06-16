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

* ** Rules : **

Rules
Time of travel
The fare varies based on the time of the trip. There are two types of fares based on time of
travel:
● Peak hours timings
○ Monday - Friday
■ 07:00 - 10:30, 17:00 - 20:00
○ Saturday - Sunday
■ 09:00 - 11:00, 18:00 - 22:00
● Off-peak hours timings
○ All hours except the above peak hours
The below table shows the fare that commuters will have to pay for a single journey from station
A to station B. There is no concept of a round-trip journey


Zones            | Peak hours| Off-peak hours
1 - 1            |30         | 25
1 - 2 or 2 - 1   |35         |30
2 - 2            |25         |20


Fare Capping
Fare capping works by rewarding commuters with free rides after they meet the fare equivalent
of a daily, weekly, or monthly pass. With fare capping, social equity is achieved by removing
upfront cost barriers associated with the recurrent passes. For example, a single ride costs 30
and the daily pass costs 90, the commuter earns a daily pass after the first 3 rides. For the rest
of the day, all rides will be free for the commuter. Due to fare capping, the commuter does not
have to invest 90 on the daily pass as they get the same features using a regular card.
The following capping categories are available:
● Daily
● Weekly
Capping Limits:

Zones         |  Daily Cap | Weekly Cap (Monday - Sunday)
1 - 1         |  100       | 500
1 - 2 or 2 - 1| 120        | 600
2 - 2         | 80          |400

The cap that is applicable for a day is based on the farthest journey in a day. For example, if a
few journeys are within zone 1 and a single journey is between zone 1 & 2, then the daily cap
applicable will be the one for zone 1 - 2. The first example later in the document illustrates the
same. The weekly cap uses the same logic as the daily cap when determining the zones
applicable for the week.
The weekly cap works in conjunction with the daily cap. So, when computing the weekly cap,
each day fare might still be capped to that day’s daily cap. The second example later in the
document illustrates the same
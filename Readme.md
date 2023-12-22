**Whay Debezium with Camel ?**

There has always been an issue when is comes to Data syncing between 2 applications , specially when a database is involved.
We see many accounts where two Applications share a database and an update done to a table has to be read by another application .

<img src="/images/img1.png" width="60%" height="60%" >

The usual old way was to keep on polling the database every nth minute to fetch the updated data. As the process is not real time there is always a delay in the sync time of the 2 applications .
However, we figured out a way to Capture Data change using Debezium which a more elegant solution to the problem .


 I already tried to demonstrate how we can setup a CDC with debezium and Kafka . In this example I have tried to do use Debezium without Kafka , I'll leverage Camel's Component for debezium & mysql

 <img src="/images/img2.png" width="60%" height="60%" >



The latest way i foung out to capture a change in Database is via Apache Camel . 
Camel includes individual components for each Debezium connector which allows for more flexibility to add more Debezium connectors support in Camel.


 <img src="/images/img3.png" width="60%" height="60%" >

Follow the guide below to setup a sample mysql DB  provide by **sakiladb**
 -- https://github.com/sakiladb/mysql


 1. Create a Camel Route and configure it to point to the Mysql Database .

https://github.com/Lord-of-the-Pods/debezium-camel/blob/bce07f617af6dcec0ac08eccb08f47ba82ede753/src/main/java/com/example/sbcamelkafkaproducer/MyDebeziumRoute.java#L10-L37


2. All you need to do now it to make a change in the database .

   <img src="/images/img4.png" width="60%" height="60%" >

4. You will notice the logs in the application will reflect the changes done the database .

   <img src="/images/img5.png" width="60%" height="60%" >

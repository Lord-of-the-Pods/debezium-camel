**Whay Debezium with Camel ?**

There has always been an issue when is comes to Data syncing between 2 applications , specially when a database is involved.
We see many accounts where two Applications share a database and an update done to a table has to be read by another application .

The usual old way was to keep on polling the database every nth minute to fetch the updated data. As the process is not real time there is always a delay in the sync time of the 2 applications .
However, we figured out a way to Capture Data change using Debezium which a more elegant solution to the problem .
We have already done a POC , I am attaching the same approaches with this email in the PDF file .


 I already tried to demonstrate how we can setup a CDC with debezium and Kafka . In this example I have tried to do use Debezium without Kafka , I'll leverage Camel's Component for debezium & mysql

 

Follow the guide below to setup a sample mysql DB  provide by **sakiladb**
 -- https://github.com/sakiladb/mysql


 1. Create a Camel Route

https://github.com/Lord-of-the-Pods/debezium-camel/blob/bce07f617af6dcec0ac08eccb08f47ba82ede753/src/main/java/com/example/sbcamelkafkaproducer/MyDebeziumRoute.java#L10-L37



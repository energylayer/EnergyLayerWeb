EnergyLayerWeb
==============

Web application for managing sensors data

How to run:

Clone project

>git clone https://github.com/energylayer/EnergyLayerWeb.git

Navigate to project folder

>cd EnergyLayerWeb/

Run jetty plugin via maven

>mvn jetty:run

Test it via browser

-post data from sensor:

>http://localhost:8080/webapp/data/post?deviceId=2&data=21

-get latest sensor data

>http://localhost:8080/webapp/data/get/2

-get last 10 aggregated posted data

>http://localhost:8080/webapp/data/aggregated/get/2?count=10

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

-open test page which emulates sending data from sensor, there you can specify sensor devide id:

>http://localhost:8080/webapp/test

-watch sent data from sensor via interactive chart, and specify device id if needed

>http://localhost:8080/webapp/

-get last 10 aggregated posted data for device id = 2

>http://localhost:8080/webapp/data/aggregated/get/2?count=10

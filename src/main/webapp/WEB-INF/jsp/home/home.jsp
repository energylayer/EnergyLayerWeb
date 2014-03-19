<!DOCTYPE html>
<%@ include file="../init.jsp" %>
<c:url value="/" var="home"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Energylayer</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <!--Energylayer css rules-->
    <link rel="stylesheet" href="css/main.css">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <!-- Latest compiled and minified JavaScript -->
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js" type="text/javascript"></script>

    <!--Flot.js from cdn-->
    <!--TBD-->
    <!--Flot.js from local system-->
    <script src="js/jquery.flot.js" type="text/javascript"></script>
    <!--Flot.js from server system-->
    <script src="/static/js/jquery.flot.min.js" type="text/javascript"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Energylayer</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Live Chart</a></li>
                <li><a href="#">Energy Map</a></li>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li class="active"><a href="/login">Log In</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <span>You are logged in as <sec:authentication property="principal.username"/></span>
                        <form action="/logout" method="POST">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="submit" value="logout">
                        </form>
                    </li>
                </sec:authorize>


            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!-- Jumbotron here-->
<div class="container">
    <div class="jumbotron">
        <h1>Live chart</h1>
        <p>This is a chart with the real-time data stream from the sensor(s)</p>
    </div>
    <div class="row">
        <div id="placeholder" style="height:400px;" class="col-md-11"></div>
        <div class="col-md-1">
            <h3>device id</h3>
            <input type="text" name="deviceId" value="1" size="3"/>
            <div id="SSE_result">result</div>
        </div>
    </div>
</div>
<script>
    //object passed to the plotting library
    $(function() {

////////////
//GLOBALS///
////////////

        var data = [],
                totalPoints = 1300;
        var sensorData = 100; // getting this from the server via SSE

/////////////
//FUNCTIONS//
/////////////

// Simulated data for chart drawing
        function getRandomData() {
            if (data.length > 0)
                data = data.slice(1);
            // Do a random walk
            while (data.length < totalPoints) {
                var prev = data.length > 0 ? data[data.length - 1] : 50,
                        y = prev + Math.random() * 10 - 5;

                if (y < 0) {
                    y = 0;
                } else if (y > 100) {
                    y = 100;
                }
                data.push(y);
            }
            // Zip the generated y values with the x values
            var res = [];
            for (var i = 0; i < data.length; ++i) {
                res.push([i, data[i]])
            }
            return res;
        }
// Real data for chart drawing		
        function getRealData() {
            if (data.length > 0)
                data = data.slice(1);
            // Do a random walk
            while (data.length < totalPoints) {
//				var prev = data.length > 0 ? data[data.length - 1] : 50,  //use later for smooth chart transition
//					y = prev + Math.random() * 10 - 5;
                y = sensorData;
                if (y < 0) {
                    y = 10;
                } else if (y > 100) {
                    y = 100;
                }
                data.push(y);
            }
            // Zip the generated y values with the x values
            var res = [];
            for (var i = 0; i < data.length; ++i) {
                res.push([i, data[i]])
            }
            return res;
        }

// Chart config
        var plot_conf = {
            series: {
                lines: {show: true, fill:0.15, lineWidth: 4},
//   points: { show: true, fill: false }
            }
        };

//Drawing the chart
        /*Draw chart with simulated data*/
//var plot = $.plot($("#placeholder"), [ {color:"green", data:getRandomData()} ], plot_conf);
        /*Draw chart with the real data*/
        var plot = $.plot($("#placeholder"), [ {color:"green", data:getRealData()} ], plot_conf);


        var updateInterval = 300;
        function update() {

//			plot.setData([ {color:"green", data:getRandomData()} ]);  //simulated
            plot.setData([ {color:"green", data:getRealData()} ]);  //real

            // Since the axes don't change, we don't need to call plot.setupGrid()

            plot.draw();
            setTimeout(update, updateInterval);
        }

        update();

        //SSE
        var source = new EventSource("http://162.243.226.226:8080/data/get/SSE/1/1");
        source.onmessage = function(event) {
            var sData;  //local variable to save data for server
            sData = event.data;
            document.getElementById("SSE_result").innerHTML = sData;
            sensorData = sData/100;
        };
    });
</script>

</body>
</html>

<!DOCTYPE html>
<%@ include file="../init.jsp" %>
<c:url value="/" var="home"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Energylayer</title>

    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>" type="text/css" />
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js" type="text/javascript"></script>
    <script src="http://malsup.github.com/jquery.form.js" type="text/javascript"></script>
    <script src="<c:url value="/static/js/jquery.flot.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/home/home.js"/>" type="text/javascript"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div id="contextPath" data-contextpath="<c:out value="${pageContext.servletContext.contextPath}"/>" class="none"></div>
<%@include file="../common/header.jspf" %>
<div class="container">
    <div class="jumbotron">
        <h1>Live chart</h1>
        <p>This is a chart with the real-time data stream from the sensor(s)</p>
        <h2>Solar panel power output: <span id="currentPowerHolder">0000</span> Watt.</h2>
        <div class="container">
            <ul class="nav navbar-nav">
                <li id="realTime" class="active"><a href="#">Real time</a></li>
                <li id="hourly"><a href="#">Hourly</a></li>
                <li id="daily"><a href="#">Daily</a></li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div id="chart-placeholder" style="height:400px;" class="col-md-11"></div>
        <div id="deviceContainer" class="col-md-1">
            <input type="hidden" name="sensorNumber" value="1" size="1"/>
            <button id="addDevice">Add device</button>
            <div id='devideDialog'>
                <form id="deviceForm">
                    <label for="deviceId">Device ID</label>
                    <input type="text" id="deviceId" name="deviceId"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%@ include file="../init.jsp" %>
<html>
<head>
    <title>Energy Layer</title>
    <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/static/js/jquery.flot.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/home/home.js"/>" type="text/javascript"></script>
</head>
<body>
<div id="contextPath" data-contextpath="<c:out value="${pageContext.servletContext.contextPath}"/>" class="none"></div>
<div class="body">
    <div class="header"><h3>HEAD</h3></div>
    <div class="content">
        <div class="wrapper">
            <div id="chart-holder" class="char-holder-block"></div>
            <h2>Retrieving data for device id =</h2>
            <input type="text" value="1"/>
        </div>
    </div>
    <div class="footer"><h3>FOOT</h3></div>
</div>
</body>
</html>
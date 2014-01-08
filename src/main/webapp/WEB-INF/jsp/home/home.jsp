<%@ include file="../init.jsp" %>
<c:url value="/" var="home"/>
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
    <%@include file="../common/header.jspf" %>
    <div class="content">
        <div class="adLine">
            <div><h2>Visualize your energy production</h2></div>
        </div>
        <div class="adVideo">
            <iframe width="560" height="315" src="//www.youtube.com/embed/tagg0ovjNtM" frameborder="0" allowfullscreen></iframe>
        </div>
        <div class="wrapper">
            <div id="chart-holder" class="char-holder-block"></div>
            <div class="chart-adjustments">
                <h2>Type your device id</h2>
                <input type="text" name="deviceId" value="1"/>
            </div>
        </div>
    </div>
    <%@include file="../common/footer.jspf" %>
</div>
</body>
</html>
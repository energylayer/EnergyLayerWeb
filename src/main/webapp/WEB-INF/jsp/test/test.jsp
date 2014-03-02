<%@ include file="../init.jsp" %>
<html>
<head>
    <title>Test Page</title>
    <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/static/js/test/test.js"/>" type="text/javascript"></script>
</head>
<body>
<div id="contextPath" data-contextpath="<c:out value="${pageContext.servletContext.contextPath}"/>" class="none"></div>
<h3>Sending random data to controller 1 time per second and with device id = </h3><input name="deviceId" type="text" value="1"/>
<h3>Deley time = </h3><input name="delay" type="text" value="1000"/>
<h3>Do ajax get = </h3><input name="doGet" type="checkbox" />
</body>
</html>
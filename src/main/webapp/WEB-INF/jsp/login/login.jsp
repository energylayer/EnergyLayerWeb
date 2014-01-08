<%@ include file="../init.jsp" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>" type="text/css"/>
</head>
<body>
<%@include file="../common/header.jspf" %>
<div class="login-body">
    <div class="login-main">
        <div class="login-form">
            <%@include file="loginForm.jspf" %>
        </div>

        <div class="register-link">
            <span>If you have no credentials<br/> you can register:</span>
            <%@include file="../login/registerForm.jspf" %>
        </div>
    </div>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>

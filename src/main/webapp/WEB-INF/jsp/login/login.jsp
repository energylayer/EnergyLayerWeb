<%@ include file="../init.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log In</title>

    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>" type="text/css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
<%--<%@include file="../common/footer.jspf" %>--%>
</body>
</html>

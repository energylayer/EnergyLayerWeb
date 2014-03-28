<%@ include file="../init.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register to Energylayer</title>

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
<c:url value="/login/create" var="createUrl"/>
<div class="login-body">
    <div class="login-main">
        <div class="login-form">
            <form:form action="${createUrl}" commandName="userQuery">
                <div>
                    <form:label path="email"><spring:message code='email'/></form:label>
                    <form:input path="email" maxlength="255"/>
                    <form:errors path="email" cssClass="error"/>
                </div>
                <div>
                    <form:label path="password"><spring:message code='password'/></form:label>
                    <form:password path="password" maxlength="255"/>
                    <form:errors path="password" cssClass="error"/>
                </div>
                <div>
                    <form:label path="firstName"><spring:message code='first.name'/></form:label>
                    <form:input path="firstName" maxlength="50"/>
                    <form:errors path="firstName" cssClass="error"/>
                </div>
                <div>
                    <form:label path="lastName"><spring:message code='last.name'/></form:label>
                    <form:input path="lastName" maxlength="50"/>
                    <form:errors path="lastName" cssClass="error"/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <form:button>Register</form:button>
            </form:form>
        </div>
    </div>
</div>
<%--<%@include file="../common/footer.jspf" %>--%>
</body>
</html>
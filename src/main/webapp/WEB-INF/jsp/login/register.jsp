<%@ include file="../init.jsp" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>" type="text/css"/>
</head>
<body>
<%@include file="../common/header.jspf" %>
<c:url value="/login/create" var="createUrl"/>
<div class="login-body">
    <div class="login-main">
        <div class="login-form">
            <form:form action="${createUrl}" commandName="userWeb">
                <div>
                    <form:label path="username"><spring:message code='username'/></form:label>
                    <form:input path="username"/>
                    <form:errors path="username" cssClass="error"/>
                </div>
                <div>
                    <form:label path="password"><spring:message code='password'/></form:label>
                    <form:input path="password"/>
                    <form:errors path="password" cssClass="error"/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <form:button>Register</form:button>
            </form:form>
        </div>
    </div>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>
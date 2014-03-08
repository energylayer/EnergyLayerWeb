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
<%@include file="../common/footer.jspf" %>
</body>
</html>
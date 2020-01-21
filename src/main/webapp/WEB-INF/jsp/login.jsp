<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
    ${msg}
    <form action="${pageContext.request.contextPath}/loginIn" method="post">
        用户名：<input type="text" name="userName"><br>
        密&nbsp;&nbsp;&nbsp;码:
        <input type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/2/2
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增yemian</title>
</head>
<body>
    <div>
        <form action="/task/user/addOne" method="post">
            用户名：<input type="text" name="userName" id="userName"/><br/>
            密  码：<input type="text" name="passWord" id="passWord"/><br/>
            <input type="submit" value="submit"/>
        </form>
    </div>
</body>
</html>

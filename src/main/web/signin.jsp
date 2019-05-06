<%--
  Created by IntelliJ IDEA.
  User: Adi
  Date: 01.05.2019
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<c:if test="${param.message == 'error'}">
    <h>Вы уже зарегистрированы. <br>
        Войдите под логином.
    </h>
</c:if>

<form action="signin" method="post">

    <td>Логин <input type="text" name="login"/></td>
    <br><br>
    <tr>
        <td>Пароль <input type="text" name="pass"/></td>
    </tr>
    <br><br>
    <input type="submit">
    </tr>
    <br><br>
</form>

<form action="edit.jsp" method="post">
    <input type="submit" value="Редактировать данные">
</form>

<form action="delete.jsp" method="post">
    <input type="submit" value="Удалить аккаунт">
</form>


</body>
</html>
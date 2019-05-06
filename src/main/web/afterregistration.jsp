<%--
  Created by IntelliJ IDEA.
  User: Adi
  Date: 27.04.2019
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>

<body>
<h4>Привет, <c:out value="${firstName}"/> <br>
    Ваш логин: <c:out value="${login}"/>  <br>
    Ваш пароль: <c:out value="${pass}"/>  <br>
</h4>

<br><br>
<form action="signin">
    <input type="submit" value="Войти">
</form>

<br><br>

<form action="edit.jsp" method="post">
    <input type="submit" value="Редактировать данные">
</form>

<form action="delete.jsp" method="get">
    <input type="submit" value="Удалить аккаунт">
</form>

</center>
</body>
</html>

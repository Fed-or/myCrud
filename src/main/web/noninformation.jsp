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
Не заполнены все поля. Пожалуйста, введите необходимую информацию.
<br><br>
<form action="registration.jsp">
    <input type="submit" value="Зарегистрироваться">
</form>

<c:if test="${param.message == 'isNotPresent'}">
    <p><h>"Такого пользователя в базе нет"</h></p>
</c:if>


</center>
</body>
</html>

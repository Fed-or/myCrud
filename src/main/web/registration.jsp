<%--
  Created by IntelliJ IDEA.
  User: Adi
  Date: 27.04.2019
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

<body>
<center>
    <h1><b>Зарегистрироваться:</b></h1>
    <br><br>

    <form action="registration" method="post">

        <td>Имя <input type="text" name="firstName"/></td>
        <br><br>
        <tr>
            <td>Фамилия <input type="text" name="lastName"/></td>
            <br><br>
        <tr>
            <td>Логин <input type="text" name="login"/></td>
            <br><br>
        <tr>
            <td>Пароль <input type="text" name="pass"/></td>
            <br><br>
        <tr>
            <td>Адресс <input type="text" name="address"/></td>
        </tr>
        <br><br>
        <tr>
            <td>Почта <input type="text" name="email"/></td>
        </tr>
        <br><br>
        <input type="checkbox" name="agree" value="yes"/> Согласен с политикой обработки данных </input>
        <br><br>
        <input type="submit">
    </form>
</center>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Project 1</title>
</head>
<body>
    <h1><%= "Log In!" %>
    </h1>
    <br/>
    <form action="login.html">
        <label for="Login">Login:</label><br>
        <input type="text" id="Login" name="Login"><br>
        <label for="Password">Password:</label><br>
        <input type="text" id="Password" name="Password"><br><br>
        <input type="submit" value="Sign in">
    </form>
    </body>
</html>
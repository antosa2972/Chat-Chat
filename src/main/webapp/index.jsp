<%--
  Created by IntelliJ IDEA.
  User: Panayiotis
  Date: 03/01/2020
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="/css/style.css"%>
    </style>
    <meta name="Description" content=""/>

    <title>Chat&Chat</title>
</head>
<body>
<div class="header-base">
</div>
<div class="header">
    <h1>Chat&Chat</h1>
    <!-- <div id="img1" class="header"><img src="" /></div> -->
    <form class="log-in-form" action="${pageContext.request.contextPath}/login" method="post">
        <input class="input-box" placeholder="Username" type="text" name="username" required/>
        <input class="input-box" placeholder="Password" type="password" name="password" required/>
        <input class="log-in-btn" type="submit" value="Log In">
    </form>
</div>
<div class="body">
    <div id="intro1" class="body">This app lets you chat with people from all<br>
        over the world.
    </div>
    <div id="intro2" class="body">Create an account</div>
    <!-- <div id="img2" class="body"><img src="" /></div> -->
    <form id="sign-up-form" class="body" action="${pageContext.request.contextPath}/register" method="post">
        <p>${register_message}</p>
        <input class="input-box" placeholder="First Name" type="text" name="firstName" required/>
        <input class="input-box" placeholder="Last Name" type="text" name="lastName" required/> <br>
        <input class="input-box" id="usename" placeholder="Username" type="text" name="username" required/><br>
        <input class="input-box" id="mailbox" placeholder="Email" type="email" name="email" required/><br>
        <input class="input-box" id="password" placeholder="Password" type="password" name="password" required/><br>
        <input class="input-box" type="date" name="birth-date" required/><br><br>
        <input class="log-in-btn" id="sign-up-btn" type="submit" value="Create an account"/>
        <br>
        <hr>
    </form>
</div>
</body>

</html>
<%@ page import="www.project.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="www.project.dao.UserDAO" %><%--
  Created by IntelliJ IDEA.
  User: gio
  Date: 08/01/2020
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Friends</title>
    <style>
        <%@include file="/css/addFriends.css"%>
    </style>

</head>

<body>
<div class="header">
    <h1>Add your friends</h1
</div>
<%
    UserDAO userDAO = new UserDAO();
    String usern = (String) session.getAttribute("username");
    List<User> userList = userDAO.getAllUsers(usern);
    for (User user : userList) {
        if (user.getUsername().equals(usern))
            continue;
        String temp = user.getFirstName() + " " + user.getLastName();
%>
<div class="body">
    <form action="${pageContext.request.contextPath}/addfriend" method="post">
        <h1><%=temp%>
        </h1>
        <input name="userid" type="hidden" value="<%=user.getId()%>">
        <button class="button2" type="submit">Add friend</button>
    </form>
    <%
        }
    String message =(String)session.getAttribute("no-friends");
    if(message==null||message.isEmpty()){
        message="";
    }
    %>
    <button class="button2"><a href="main.jsp">Go to messenger</a></button>
    <%=message%>
</div>

</body>
</html>

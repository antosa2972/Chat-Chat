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
    <link type="text/css" rel="stylesheet" href="./css/style.css" />
</head>

<body>
    <h1>Add your friends</h1>
    <%
        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.getAllUsers();
        for (User user : userList) {
            String temp = user.getFirstName() + " " + user.getLastName();
    %>
    <form action="${pageContext.request.contextPath}/addfriend" method="post">
        <li><%=temp%></li>
        <input name="userid" type="hidden" value="<%=user.getId()%>">
        <button type="submit">Add</button>
    </form>
    <%
        }
    %>

</body>
</html>

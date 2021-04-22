<%@ page import="www.project.dao.UserDAO" %>
<%@ page import="www.project.bean.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="www.project.dao.FriendshipDAO" %>
<%@ page import="www.project.bean.Friendship" %>
<%@ page import="java.util.List" %>
<%@ page import="www.project.dao.MessagesDAO" %>
<%@ page import="www.project.bean.Messages" %><%--
  Created by IntelliJ IDEA.
  User: gio
  Date: 06/01/2020
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="Description" content=""/>

    <style>
        <%@include file="/css/main.css"%>
    </style>

    <title>Chat&Chat</title>
</head>
<body>

<%
    FriendshipDAO friendshipDAO = new FriendshipDAO();
    UserDAO userDAO = new UserDAO();
    User user = userDAO.getUser((String) session.getAttribute("username"));
    String userFullName = user.getFirstName() + " " + user.getLastName();
    User friend = null;
    String friendPar = null;
    String friendName = null;
    String friendEmail = null;
    List<Friendship> list = null;
    User currentFriend = null;
    friendPar = (String) session.getAttribute("friend-uname");
    if (!friendPar.isEmpty()) {
        list = friendshipDAO.getFriendship(user.getId());
        friend = userDAO.getUser((String) session.getAttribute("friend-uname"));
        currentFriend = userDAO.getUser((String) session.getAttribute("friend-uname"));
        friendName = friend.getFirstName() + " " + friend.getLastName();
        friendEmail = friend.getEmail();
    } else {
        session.setAttribute("no-friends", "Add friends to continue!!!");
        response.sendRedirect("listUsers.jsp");
    }
%>

<div class="wrapper">
    <header>
        <div class="container">
            <div class="left"><h2>Chat&Chat</h2></div>

            <div class="middle">
                <h3><%=friendName%>
                </h3>
            </div>

            <div class="right">
                <div class="username">
                    <h3><%=userFullName%>
                    </h3>
                </div>
            </div>
        </div>
    </header>

    <main>
        <div class="col-left">
            <div class="col-content">
                <div class="messages" id="msg">
                    <%
                        String temp = "";
                        String friendUname = "";
                        if (list != null) {
                            for (Friendship friendship : list) {
                                friend = userDAO.getUser(friendship.getUser2());
                                temp = friend.getFirstName() + " " + friend.getLastName();
                                friendUname = friend.getUsername();
                    %>
                        <form action="${pageContext.request.contextPath}/fetch" method="post">
                            <input type="hidden" name="friend-uname" value="<%=friendUname%>">
                            <button class="friend-button" type="submit"><%=temp%></button>
                        </form>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="col-content">
                <section class="message">
                    <div class="grid-message" id="message">
                        <%
                            MessagesDAO messagesDAO = new MessagesDAO();
                            if (currentFriend != null) {
                                List<Messages> messagesList = messagesDAO.getAllMessages(user.getId(), currentFriend.getId());
                                for (Messages message : messagesList) {
                                    String newMessage = message.getMsg();
                                    if (message.getSender() == user.getId()) {
                        %>
                        <div class="col-message-sent">
                            <div class="message-sent">
                                <p><%=newMessage%>
                                </p>
                            </div>
                        </div>
                        <%
                        } else {
                        %>
                        <div class="col-message-received">
                            <div class="message-received">
                                <p><%=newMessage%>
                                </p>
                            </div>
                        </div>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                </section>
            </div>
            <div class="col-foot">

                <div class="compose">
                    <form action="${pageContext.request.contextPath}/sendmsg" method="post">
                        <input name="input-box" placeholder="Type a message">
                    </form>
                    <div class="compose-dock">
                    </div>

                </div>

            </div>
        </div>
        <div class="col-right">

            <div class="col-content">

                <div class="user-panel">

                    <h3><%=friendName%>
                    </h3>
                    <p><%=friendEmail%>
                    </p>
                    <form action="${pageContext.request.contextPath}/logout" method="post">
                        <button class="log-out" type="submit">Log Out</button>
                        <button class="add-friends"><a href="listUsers.jsp">Add Friends</a></button>
                    </form>
                </div>

            </div>

        </div>
    </main>
</div>
</body>
</html>

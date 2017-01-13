<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/24
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="message.MessageDataBean" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
    <title>查看信息</title>
</head>
<body>
<p align="center">所有信息</p>

<form action="AddMessageServlet" method="post">
<table align="center" border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>area</td>
        <td>message</td>
    </tr>
    <%
        int count = 0;
        Collection<MessageDataBean> m1 = (Collection<MessageDataBean>) request.getAttribute("message");
        Iterator<MessageDataBean> it = m1.iterator();
        while (it.hasNext())
        {
            MessageDataBean mg = (MessageDataBean) it.next();
    %>
    <tr>
        <td>
            <%=mg.getS1() %>
        </td>
        <td>
            <input type="text" value="<%=mg.getS2() %>"></>
        </td>
        <td>
            <input type="text" value="<%=mg.getS3() %>"></>
        </td>
        <td>
            <textarea><%=mg.getS4() %></textarea>
        </td>
    </tr>
<% count++; }  %>  <!-- 补全大括号  -->
</table>
    <p align="center"><input type="submit"></p>
<p align="center"><a href="index.html">信息管理</a></p>
    </form>
</body>
</html>
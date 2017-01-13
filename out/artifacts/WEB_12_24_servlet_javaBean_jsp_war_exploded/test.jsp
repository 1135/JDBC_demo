<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/9
  Time: 21:21
  To change this template use File | Settings | File Templates.




  demo：jsp代码查询mysql
--%>
<%@ page import="java.sql.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>WEB_execise</title>
</head>
<body>
<table border="1">
    <%
        String driverName = "com.mysql.jdbc.Driver";//不报错了！要把mysql-connector-java-5.1.35-bin.jar包 放在tomcat的lib目录 D:\apache-tomcat-7.0.72\lib

        String dbName = "test";//数据库名
        String userName = "root";
        String userPass = "";
        String tableName = "student";//表名

        String url = "jdbc:mysql://localhost:3306/" + dbName;//连接数据库的url

        //声明三类对象
        Connection con = null;
        Statement s;
        ResultSet rs;

        try {
            Class.forName(driverName).newInstance();
        } catch (Exception e) {
            System.err.println("ERROR!");
        }


        try {
            con = DriverManager.getConnection(url, userName, userPass);//连接数据库
        } catch (SQLException e) {
            System.out.println("Error getConnection！");
        }


        try {
            s = con.createStatement();//创建Statement类的对象
            String sql = "SELECT * FROM " + tableName;

            rs = s.executeQuery(sql);
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("area") + "</td>");
                out.println("<td>" + rs.getString("message") + "</td>");
                out.println("<tr>");
            }
            rs.close();
            s.close();
            con.close();
        } catch (Exception er) {
            System.err.println("Error executeQuery");
        }
        out.print("<h1>test测试</h1>");
    %>

</table>
</body>
</html>

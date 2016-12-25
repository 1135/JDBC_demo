package message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/*
要在web.xml中写
    <servlet>
        <servlet-name>AddMessageServlet</servlet-name>
        <servlet-class>AddMessageServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>AddMessageServlet</servlet-name>
        <url-pattern>/AddMessageServlet</url-pattern>
    </servlet-mapping>
*/
public class AddMessageServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    private Connection con;
    private static final long serialVersionUID = 1L;

    public AddMessageServlet() {
        String JDriver = "com.mysql.jdbc.Driver";//定义 驱动程序对象
        String userName = "root";//数据库用户名
        String userPass = "";//数据库密码
        String dbName = "test";//数据库名
        String dbPort = "3306";//数据库连接端口
        String conURL = "jdbc:mysql://localhost:" + dbPort + "/" + dbName+"?useUnicode=true&characterEncoding=utf-8";//注意编码为UTF-8

        try {
            Class.forName(JDriver).newInstance();//加载JDBC 驱动程序
            con = DriverManager.getConnection(conURL, userName, userPass);//得到连接

            //下面三个catch异常是IDEA生成的
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {//得到连接  那条语句可能导致sql异常
            e.printStackTrace();
        }

    }//AddMessageServlet 方法体结束


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //设置响应内容类型
        resp.setContentType("text/html;charset=utf-8");//servlet 一定要在response响应包里 指定字符集！

        //逻辑实现
        PrintWriter out = resp.getWriter();

        out.println("<h1>增加的数据</h1>");//测试输出

        String s1 = req.getParameter("id");
        byte[] b1=s1.getBytes("ISO-8859-1");
        s1 = new String(b1,"UTF-8");
        out.println(s1);

        //如果html是GBK写的，应该这样才能正确编码。  byte b2[] = req.getParameter("name").getBytes("ISO-8859-1");   if (b2[] == null) b2 = "";   String s2=new String(b2,"GBK");

        //本项目所有页面编码、数据库编码均为UTF-8。

        String s2 = req.getParameter("name");
        byte[] b2=s2.getBytes("ISO-8859-1");//http协议用的 ISO-8859-1    先把经过8859-1的字符串变成bytes
        s2 = new String(b2,"UTF-8");//把bytes 按照UTF-8恢复为字符串
        out.println(s2);

        String s3 = req.getParameter("area");
        byte[] b3=s3.getBytes("ISO-8859-1");
        s3 = new String(b3,"UTF-8");
        out.println(s3);

        String s4 = req.getParameter("message");
        byte[] b4=s4.getBytes("ISO-8859-1");
        s4 = new String(b4,"UTF-8");
        out.println(s4);


        try {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO student VALUES(?,?,?,?)");
            pstm.setString(1, s1);
            pstm.setString(2, s2);
            pstm.setString(3, s3);
            if (s4.length() == 0) pstm.setString(4, null);
            else pstm.setString(4, s4);
            //可以判断一下字符串s4是否是空字符串""  如果是的话 可以替换为null
// insert into student value(2,"wang","beijing",null);这样插入最后1个字段为null 而不是空字符串""

            pstm.execute();//执行sql语句

            pstm.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}

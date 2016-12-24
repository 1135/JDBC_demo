package message;

import com.sun.xml.internal.ws.encoding.xml.XMLMessage;
import message.MessageDataBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
/*
要在web.xml中写
    <servlet>
        <servlet-name>ViewMessageServlet</servlet-name>
        <servlet-class>message.ViewMessageServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewMessageServlet</servlet-name>
        <url-pattern>/ViewMessageServlet</url-pattern>
    </servlet-mapping>

/
 */
public class ViewMessageServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    private Connection con;
    private static final long serialVersionUID = 329109129L;

    public ViewMessageServlet() {
        String JDriver = "com.mysql.jdbc.Driver";//定义 驱动程序对象
        String userName = "root";//数据库用户名
        String userPass = "";//数据库密码
        String dbName = "test";//数据库名
        String dbPort = "3306";//数据库连接端口
        String conURL = "jdbc:mysql://localhost:" + dbPort + "/" + dbName;


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


    }//ViewMessageServlet构造方法 方法体结束


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<MessageDataBean> ret = new ArrayList<MessageDataBean>();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT count(*) FROM student");//语句不用加分号
            int Count = 0;
            if (rs.next()) {
                Count = rs.getInt(1);//参数是字段的索引值  字段在第几列就写几
                rs.close();
                if (Count > 0) {
                    rs = stm.executeQuery("SELECT * FROM student");
                    while (rs.next()) {
                        String s1 = rs.getString(1);
                        String s2 = rs.getString(2);
                        String s3 = rs.getString(3);
                        String s4 = rs.getString(4);
                        MessageDataBean m = new MessageDataBean();
                        m.setid(s1);
                        m.setname(s2);
                        m.setarea(s3);
                        m.setmessage(s4);
                        ret.add(m);

                    }
                    rs.close();
                    stm.close();
                }
                req.setAttribute("message",ret);

                RequestDispatcher reqD=req.getRequestDispatcher("viewMessage.jsp");//服务器端的 请求转发
                reqD.forward(req,resp);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher reqD=req.getRequestDispatcher("ViewMessageServlet");//服务器端的 请求转发
        reqD.forward(req,resp);

    }


}

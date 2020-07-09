package mew;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Delete_member {
    Connection ct;
    PreparedStatement ps;
    public Delete_member(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
            String userName="root";
            String userPwd="123456";
            ct = DriverManager.getConnection(url,userName,userPwd);
            ps = ct.prepareStatement("delete from shop.member where id=?");
            ps.setString(1,id);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e1) {
            System.out.println("ERROR:"+e1);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (ct != null) {
                    ct.close();
                }
            } catch (SQLException e2) {

            }
        }
    }

}
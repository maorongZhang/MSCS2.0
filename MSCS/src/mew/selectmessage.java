package mew;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;


final class selectmessage extends AbstractTableModel{
    Vector product ,jilu ;
    PreparedStatement ps=null;
    Connection ct=null;
    ResultSet rs=null;

    public selectmessage(){
        this.sql("select * from shop.product");
    }

    public
    selectmessage(String ss){
        this.sql(ss);
    }



    public void sql(String sql){

        product = new Vector();
        product.add("ID");
        product.add("name");
        product.add("number");
        product.add("discount");
        product.add("price");
        product.add("date");
        product.add("keep");

        jilu=new Vector();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
            String userName="root";
            String userPwd="123456";
            ct = DriverManager.getConnection(url,userName,userPwd);
            ps=ct.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector hang= new Vector();
                hang.add(rs.getInt("ID"));
                hang.add(rs.getString("name"));
                hang.add(rs.getInt("number"));
                hang.add(rs.getString("discount"));
                hang.add(rs.getString("price"));
                hang.add(rs.getString("date"));
                hang.add(rs.getString("keep"));
                JOptionPane.showMessageDialog(null, hang);
                jilu.add(hang);
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error "+e);
        }
        finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(ct!=null){
                    ct.close();
                }
            }catch(SQLException e){

            }
        }
    }


    @Override
    public int getRowCount() {//对行进行计数，横着的有几个，一旦数目多余存在的，就无法查出来
        return this.jilu.size();

    }

    @Override
    public int getColumnCount() {//对列进行计数，返回的值是什么就说明有几列,竖着的数目有几个
        return this.product.size();
        // return 1;
    }

    @Override
    public Object getValueAt(int hang, int lie) {//就是为了获得里面的值
        return ((Vector)this.jilu.get(hang)).get(lie);

    }

    @Override
    public String getColumnName(int e){//这条函数是为了得到最上面的一行的表头
        return (String)this.product.get(e);
        // return "h";
    }


}
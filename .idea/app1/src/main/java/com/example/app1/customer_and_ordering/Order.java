package com.example.app1.customer_and_ordering;

import com.example.app1.parts.Part;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int quantity;
    private String status;
    private String part;
    String username;
    static String url = "jdbc:postgresql://localhost:5432/testoop1";
    static String user = "postgres";
    static String password = "postgres";

    public Order(int id, int quantity, String status, String part) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
        this.part = part;
    }

    public Order(int id, int quantity, String status, String part, String username) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
        this.part = part;
        this.username = username;
    }

    public Order() {
    }

    public static List getOrders(String name, boolean priv){
        List<Order> list = new ArrayList<>();
        if(priv==false){
            String sql = "select u.username, o.order_id, p.name, o.quantity,o.status from \"user\" u " +
                    "join orders o on u.id =o.customer_id join part p on p.part_id =o.part_id \n" +
                    "where u.username = ?";

            try(Connection conn = DriverManager.getConnection(url,user,password);){
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,name);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    int id,quantity;
                    String part, status,customerName;
                    id = rs.getInt("order_id");
                    quantity = rs.getInt("quantity");
                    part = rs.getString("name");
                    status = rs.getString("status");
                    customerName = rs.getString("username");
                    list.add(new Order(id,quantity,status,part,customerName));
                }
                return list;
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String sql = "select u.username , o.order_id, p.name, o.quantity,o.status from \"user\" u " +
                    "join orders o on u.id =o.customer_id join part p on p.part_id =o.part_id \n";
            try(Connection conn = DriverManager.getConnection(url,user,password);){
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()) {
                    int id, quantity;
                    String part, status, customerName;
                    id = rs.getInt("order_id");
                    quantity = rs.getInt("quantity");
                    part = rs.getString("name");
                    status = rs.getString("status");
                    customerName = rs.getString("username");
                    list.add(new Order(id, quantity, status, part, customerName));
                }
                    return list;

            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int setDelivered(Order order){
        try(Connection conn = DriverManager.getConnection(url,user,password);){
            PreparedStatement stmt = conn.prepareStatement("Update orders set status = 'DELIVERED' where order_id = ?");
            stmt.setInt(1,order.getId());
            int rows = stmt.executeUpdate();
            return rows;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public static String newOrder(int userId, int partId, int quantity){
        if(partId==0)
            return null;
        try(Connection conn = DriverManager.getConnection(url,user,password);){
            String s = "Insert into orders(customer_id, part_id,quantity,status) values (?, ?, ?, 'NEW')";
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setInt(1,userId);
            stmt.setInt(2,partId);
            stmt.setInt(3,quantity);
            int r = stmt.executeUpdate();
            if(r!=0){
                return "Success";
            }
            else{
                return "Fail";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Order(int id, int quantity, String status) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPart() {
        return part;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}

package com.example.app1.customer_and_ordering;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String pass;
    private String email;
    static String url = "jdbc:postgresql://localhost:5432/testoop1";
    static String user = "postgres";
    static String password = "postgres";
    public static boolean logged=false;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public User(){
        this.id=-1;
        this.name="";
        this.pass="";
        this.email="";
    }

    public static String login(String name,String pass){
        String sql = "Select id,privilege FROM \"user\" WHERE username = ? and password = ?";
        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            stmt.setString(1,name);
            stmt.setString(2,pass);

            ResultSet rs = stmt.executeQuery();
            int id;
            if(rs.next()){
                id=rs.getInt("id");
                if(rs.getInt("privilege")==1){
                    return "a" + id;
                }
                else return "n" + id;
            }
            else return null;
        }
        catch(SQLException e ){
            e.printStackTrace();
        }
        return null;
    }

    public static int newUser(String name, String email, String pass){
        String sql = "Insert into \"user\" (username,email, password) values (?, ?, ?);";
        String sqltest = "Select username, email from \"user\" where username = ? or email = ?";

        try(Connection conn = DriverManager.getConnection(url,user,password);)
        {
            PreparedStatement test = conn.prepareStatement(sqltest);
            PreparedStatement stmt = conn.prepareStatement(sql);

            test.setString(1,name);
            test.setString(2,name);
            ResultSet rs = test.executeQuery();
            if(rs.next()){
                return -1;
            }
            stmt.setString(1,name);
            stmt.setString(2,email);
            stmt.setString(3,pass);

            int nr = stmt.executeUpdate();
            return nr;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public String setName(String name) {
        if (name == null || name.isBlank()) {
            return "Name cannot be empty.";
        }
        if (name.length() < 3) {
            return "Name must be at least 3 characters long.";
        }
        this.name = name;
        return null;
    }

    public String setPass(String pass) {
        if (pass == null || pass.isBlank()) {
            return "Password cannot be empty.";
        }
        if (pass.length() < 4) {
            return "Password must be at least 4 characters long.";
        }
        this.pass = pass;
        return null;
    }

    public String setEmail(String email) {
        if (email == null || email.isBlank()) {
            return "Email cannot be empty.";
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "Invalid email format.";
        }
        this.email = email;
        return null;
    }


}

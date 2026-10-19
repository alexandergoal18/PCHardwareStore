package com.example.app1.customer_and_ordering;
import java.sql.*;

public class SqlStuff {
    String url = "jdbc:postgresql://localhost:5432/testoop1";
    String user = "postgres";
    String password = "postgres";

    public SqlStuff() {
    }

    public void selectall(){


        String sql = "SELECT * FROM customers";

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + " - " + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertuser(String name){

        String sql = "INSERT INTO customers(name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    public void updateAdditionalInfo(int id) {
//        String sql = "UPDATE motherboard SET socket = ?, chipset = ?, form_factor = ?, ram_slots = ?, max_ram_gb = ?, has_wifi = ? WHERE part_id = ?";
//
//        try (Connection conn = DriverManager.getConnection(url, user, password);
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, this.socket);
//            stmt.setString(2, this.chipset);
//            stmt.setString(3, this.form);
//            stmt.setInt(4, this.slots);
//            stmt.setInt(5, this.max);
//            stmt.setBoolean(6, this.wifi);
//            stmt.setInt(7, id);
//
//            int rowsUpdated = stmt.executeUpdate(); // <-- use executeUpdate()
//
//            if (rowsUpdated > 0) {
//                System.out.println("Motherboard updated successfully.");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}

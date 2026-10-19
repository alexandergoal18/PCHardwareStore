package com.example.app1.parts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Part {
    private int id;
    private String name;
    private String brand;
    private double price;
    private int stock;
    private String category;
    static String url = "jdbc:postgresql://localhost:5432/testoop1";
    static String user = "postgres";
    static String password = "postgres";
    private static List<Part> savedParts = null;
    private static List<Part> allParts=null;

    public Part(int id, String name, String brand, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static void reloadParts() {
        savedParts = null;
    }

    public static List<Part> getAvailableParts() {
        if(savedParts!=null){
            return savedParts;
        }
        List<Part> parts = new ArrayList<>();

        String sql = "SELECT * FROM part where stock>0 order by part_id asc";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("part_id");
                int stock = rs.getInt("stock");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                switch (category) {
                    case "GPU":
                        GPU gpu = new GPU(id, name,brand,price,stock,category);
                        gpu.setAdditionalInfo(id);
                        parts.add(gpu);
                        break;

                    case "CPU":
                        CPU cpu = new CPU(id, name,brand,price,stock,category);
                        cpu.setAdditionalInfo(id);
                        parts.add(cpu);
                        break;

                    case "MOTHERBOARD":
                        Mobo mobo = new Mobo(id,name,brand,price,stock,category);
                        mobo.setAdditionalInfo(id);
                        parts.add(mobo);
                        break;

                    default:
                        // handle unknown or unsupported category
                        break;
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        savedParts=parts;
        return parts;
    }
    public static void reloadAllParts(){
        allParts=null;
    }
    public static boolean doesPartExistByName(String name){
        if(allParts==null){
            List<Part> parts = getAllParts();
            allParts=parts;
        }
        for(Part part: allParts){
            if(part.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public static List<Part> getAllParts() {
        if(allParts!=null){
            return allParts;
        }
        List<Part> parts = new ArrayList<>();

        String sql = "SELECT * FROM part order by part_id asc";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("part_id");
                int stock = rs.getInt("stock");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                switch (category) {
                    case "GPU":
                        GPU gpu = new GPU(id, name,brand,price,stock,category);
                        gpu.setAdditionalInfo(id);
                        parts.add(gpu);
                        break;

                    case "CPU":
                        CPU cpu = new CPU(id, name,brand,price,stock,category);
                        cpu.setAdditionalInfo(id);
                        parts.add(cpu);
                        break;

                    case "MOTHERBOARD":
                        Mobo mobo = new Mobo(id,name,brand,price,stock,category);
                        mobo.setAdditionalInfo(id);
                        parts.add(mobo);
                        break;

                    default:
                        // handle unknown or unsupported category
                        break;
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        allParts=parts;
        return parts;
    }

    public static Part findById(int id){
        for(Part part: savedParts){
            if(part.getId()==id){
                return part;
            }
        }
        return null;
    }

    public static int getPartIdByName(String name){
        for(Part part: savedParts){
            if(part.getName().equals(name)){
                return part.getId();
            }
        }
        return 0;
    }

    public static Part findByName(String name){
        for(Part part: savedParts){
            if(part.getName().equals(name))
                return part;
        }
        return null;
    }
    public static String buyPart(String name, int nr){
        Part part = Part.findByName(name);
        if(part==null){
            return "Inexistent part";
        }
        int stock = part.getStock();
        if(part.getStock()<nr){
            return "Not enough parts in stock";
        }
        part.setStock(stock-nr);
        Part.addExistingPartToStock(name,stock-nr);
        return "Purchase successful!";
    }

    public static void addExistingPartToStock(String name, int nr){
        String sql = "Update part set stock = ? where name = ?";
        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            stmt.setInt(1,nr);
            stmt.setString(2,name);

            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated>0){
                System.out.println("Update successful");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getName();   // or any field you want to show
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public String setId(int id) {
        if (id <= 0) {
            return "ID must be a positive integer.";
        }
        this.id = id;
        return null;
    }

    public String setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Name cannot be empty.";
        }
        this.name = name.trim();
        return null;
    }

    public String setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            return "Brand cannot be empty.";
        }
        this.brand = brand.trim();
        return null;
    }

    public String setPrice(double price) {
        if (price < 0) {
            return "Price cannot be negative.";
        }
        this.price = price;
        return null;
    }

    public String setStock(int stock) {
        if (stock < 0) {
            return "Stock cannot be negative.";
        }
        this.stock = stock;
        return null;
    }

    public String setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return "Category cannot be empty.";
        }
        this.category = category.trim();
        return null;
    }

    public Part() {
    }

    public static String addToStock(String name, int nr){
        String sql1="Select stock from part where name = ?";
        String sql2="Update part set stock = ? where name = ?";
        try(Connection conn = DriverManager.getConnection(url,user,password);){
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setString(1,name);
            ResultSet rs = stmt1.executeQuery();
            if(rs.next()){
                int stock = rs.getInt("stock");
                stock+=nr;
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1,stock);
                stmt2.setString(2,name);
                int rows=stmt2.executeUpdate();
                if(rows==1){
                    return "Successfully updated!";
                }
                else return "Error";
            }
            else return "Error";
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Part(String name, String brand, double price, int stock, String category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static String addNewPartToInventory(String name, String brand, double price, int stock, String category, String i1, String i2, String i3, String i4, String i5, String i6){
        Part newPart = null;
        switch(category){
            case "GPU":
                GPU gpu = new GPU(name,brand,price,stock,category);
                gpu.setVram(i1);
                gpu.setClock(i2);
                gpu.setPower(i3);
                gpu.setInterf(i4);
                newPart = gpu;
                break;
            case "CPU":
                CPU cpu = new CPU(name,brand,price,stock,category);
                cpu.setCores(i1);
                cpu.setThreads(i2);
                cpu.setBase(i3);
                cpu.setBoost(i4);
                cpu.setSocket(i5);
                cpu.setTdp(i6);
                newPart = cpu;
                break;
            case "Motherboard":
                Mobo mobo = new Mobo(name,brand,price,stock,category);
                mobo.setSocket(i1);
                mobo.setChipset(i2);
                mobo.setForm(i3);
                mobo.setSlots(i4);
                mobo.setMax(i5);
                mobo.setWifi(i6);
                newPart = mobo;
                break;
            default:
                break;
        }
        if(newPart!=null)
            newPart.describe();
        try(Connection conn = DriverManager.getConnection(url,user,password);){
            conn.setAutoCommit(false);
            String s1 = "INSERT INTO part (name, brand, price, stock, category) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p1 = conn.prepareStatement(s1,Statement.RETURN_GENERATED_KEYS);
            p1.setString(1,name);
            p1.setString(2,brand);
            p1.setDouble(3,price);
            p1.setInt(4,stock);
            p1.setString(5,category);
            int r = p1.executeUpdate();
            if(r==0){
                conn.rollback();
                return "Error inserting into main table";
            }
            ResultSet rs = p1.getGeneratedKeys();
            int id;
            if(rs.next()){
                id=rs.getInt(1);
            }
            else {
                conn.rollback();
                return "Error getting inserted id";
            }
            if(category.equals("GPU")){
                String s3 = "Insert into GPU(part_id, vram_gb, core_clock_mhz, power_watt, interface) values " +
                        "(?, ?, ?, ?, ?)";
                PreparedStatement p3 = conn.prepareStatement(s3);
                p3.setInt(1,id);
                p3.setInt(2,((GPU) newPart).getVram());
                p3.setInt(3,((GPU) newPart).getClock());
                p3.setInt(4,((GPU) newPart).getPower());
                p3.setString(5,((GPU) newPart).getInterf());
                r = p3.executeUpdate();
                if(r==0){
                    conn.rollback();
                    return "Error at inserting additional info";
                }
                conn.commit();
                return "Success";
            }
            if(category.equals("CPU")){
                String s3 = "Insert into CPU(part_id, cores, threads, base_ghz, boost_ghz, socket, tdp_watt) values " +
                        "(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p3 = conn.prepareStatement(s3);
                p3.setInt(1,id);
                p3.setInt(2,((CPU) newPart).getCores());
                p3.setInt(3,((CPU) newPart).getThreads());
                p3.setDouble(4,((CPU) newPart).getBase());
                p3.setDouble(5,((CPU) newPart).getBoost());
                p3.setString(6,((CPU) newPart).getSocket());
                p3.setInt(7,((CPU) newPart).getTdp());

                r = p3.executeUpdate();
                if(r==0){
                    conn.rollback();
                    return "Error at inserting additional info";
                }
                conn.commit();
                return "Success";
            }
            if(category.equals("Motherboard")){
                String s3 = "Insert into motherboard(part_id, socket, chipset, form_factor, ram_slots, max_ram_gb, has_wifi) values " +
                        "(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p3 = conn.prepareStatement(s3);
                p3.setInt(1,id);
                p3.setString(2,((Mobo) newPart).getSocket());
                p3.setString(3,((Mobo) newPart).getChipset());
                p3.setString(4,((Mobo) newPart).getForm());
                p3.setInt(5,((Mobo) newPart).getSlots());
                p3.setInt(6,((Mobo) newPart).getMax());
                p3.setBoolean(7,((Mobo) newPart).getWifi());

                r = p3.executeUpdate();
                if(r==0){
                    conn.rollback();
                    return "Error at inserting additional info";
                }
                conn.commit();
                return "Success";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void describe() {
        System.out.println("Name: " + name + "\n" +
                "Brand: " + brand + "\n" +
                "Price: " + price + "\n");
    }
}

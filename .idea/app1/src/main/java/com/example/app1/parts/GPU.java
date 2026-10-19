package com.example.app1.parts;

import com.example.app1.util.InputValidator;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.app1.util.InputValidator.*;

public class GPU extends Part{
    private int vram;
    private int clock;
    private int power;
    private String interf;
    private static List<GPU> savedGpus = null;

    public GPU(int id, String name, String brand, double price, int stock_qty, String category, int vram, int clock, int power, String interf) {
        super(id, name, brand, price, stock_qty, category);
        this.vram = vram;
        this.clock = clock;
        this.power = power;
        this.interf = interf;
    }

    public GPU(int id, String name, String brand, double price, int stock_qty, String category) {
        super(id, name, brand, price, stock_qty, category);
    }
    public GPU(String name, String brand, double price, int stock_qty, String category) {
        super(name, brand, price, stock_qty, category);
    }

    public void setAdditionalInfo(int id) {
        String sql = "SELECT * FROM GPU where part_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.vram = rs.getInt("vram_gb");
                this.clock = rs.getInt("core_clock_mhz");
                this.power = rs.getInt("power_watt");
                this.interf = rs.getString("interface");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }
    public static void reloadGPU(){
        savedGpus=null;
    }
    public static List getGPU(){
        if(savedGpus!=null){
            return savedGpus;
        }
        List <Part> parts = Part.getAvailableParts();
        List <GPU> gpu = new ArrayList<>();
        for(Part p: parts){
            if(p.getCategory().equals("GPU")){
                gpu.add((GPU) p);
            }
        }
        savedGpus=gpu;
        return gpu;
    }

    public void setVram(int vram) {
        this.vram = vram;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public void setPower(int power) {
        this.power = power;
    }


    public int getVram() {
        return vram;
    }

    public int getClock() {
        return clock;
    }

    public int getPower() {
        return power;
    }

    public String getInterf() {
        return interf;
    }

    public String setVram(String vram) {
        Integer parsed = tryParsePositiveInt(vram);
        if (parsed == null) return "VRAM must be a positive integer";
        this.vram = parsed;
        return null;
    }

    public String setClock(String clock) {
        Integer parsed = tryParsePositiveInt(clock);
        if (parsed == null) return "Clock speed must be a positive integer";
        this.clock = parsed;
        return null;
    }

    public String setPower(String power) {
        Integer parsed = tryParsePositiveInt(power);
        if (parsed == null) return "Power must be a positive integer";
        this.power = parsed;
        return null;
    }

    public String setInterf(String interf) {
        String parsed = tryParseNonEmptyString(interf);
        if (parsed == null) return "Interface cannot be empty";
        this.interf = parsed;
        return null;
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println(
                        "VRAM: " + vram + "\n" +
                        "Clock: " + clock + "\n" +
                        "Power: " + power + "\n" +
                        "Interface: " + interf);
    }


}

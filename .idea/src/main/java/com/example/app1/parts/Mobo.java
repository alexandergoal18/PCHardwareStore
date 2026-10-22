package com.example.app1.parts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.app1.util.InputValidator.*;

public class Mobo extends Part{
    private String socket;
    private String chipset;
    private String form;
    private int slots;
    private int max;
    private boolean wifi;
    private static List <Mobo> savedMobo = null;
    public Mobo(int id, String name, String brand, double price, int stock_qty, String category, String socket, String chipset, String form, int slots, int max, boolean wifi) {
        super(id, name, brand, price, stock_qty, category);
        this.socket = socket;
        this.chipset = chipset;
        this.form = form;
        this.slots = slots;
        this.max = max;
        this.wifi = wifi;
    }

    public Mobo(int id, String name, String brand, double price, int stock_qty, String category) {
        super(id, name, brand, price, stock_qty, category);
    }
    public Mobo(String name, String brand, double price, int stock_qty, String category) {
        super(name, brand, price, stock_qty, category);
    }

    public void setAdditionalInfo(int id) {
        String sql = "SELECT * FROM motherboard where part_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.socket = rs.getString("socket");
                this.chipset = rs.getString("chipset");
                this.form = rs.getString("form_factor");
                this.slots = rs.getInt("ram_slots");
                this.max = rs.getInt("max_ram_gb");
                this.wifi=rs.getBoolean("has_wifi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    public static void reloadMobo(){
        savedMobo=null;
    }
    public static List getMobo(){
        if(savedMobo!=null){
            return savedMobo;
        }
        List <Part> parts = Part.getAvailableParts();
        List <Mobo> mobo = new ArrayList<>();
        for(Part p: parts){
            if(p.getCategory().equals("MOTHERBOARD")){
                mobo.add((Mobo) p);
            }
        }
        savedMobo= mobo;
        return mobo;
    }

    public String getSocket() {
        return socket;
    }

    public String getChipset() {
        return chipset;
    }

    public String getForm() {
        return form;
    }

    public int getSlots() {
        return slots;
    }

    public int getMax() {
        return max;
    }

    public boolean getWifi() {
        return wifi;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String setSocket(String socket) {
        String parsed = tryParseNonEmptyString(socket);
        if (parsed == null) return "Socket cannot be empty";
        this.socket = parsed;
        return null;
    }

    public String setChipset(String chipset) {
        String parsed = tryParseNonEmptyString(chipset);
        if (parsed == null) return "Chipset cannot be empty";
        this.chipset = parsed;
        return null;
    }

    public String setForm(String form) {
        String parsed = tryParseNonEmptyString(form);
        if (parsed == null) return "Form factor cannot be empty";
        this.form = parsed;
        return null;
    }

    public String setSlots(String slots) {
        Integer parsed = tryParsePositiveInt(slots);
        if (parsed == null) return "RAM slots must be a positive integer";
        this.slots = parsed;
        return null;
    }

    public String setMax(String max) {
        Integer parsed = tryParsePositiveInt(max);
        if (parsed == null) return "Max memory must be a positive integer";
        this.max = parsed;
        return null;
    }

    public String setWifi(String wifi) {
        Boolean parsed = tryParseBoolean(wifi);
        if (parsed == null) return "WiFi must be yes/no or true/false";
        this.wifi = parsed;
        return null;
    }

    @Override
    public void describe() {
        System.out.println(
                        "Socket: " + socket + "\n" +
                        "Chipset: " + chipset + "\n" +
                        "Form: " + form + "\n" +
                        "Slots: " + slots + "\n" +
                        "Max RAM: " + max + "\n" +
                        "WiFi: " + wifi);
    }


}

package com.example.app1.parts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.app1.util.InputValidator.*;

public class CPU extends Part{
    private int cores;
    private int threads;
    private double base;
    private double boost;
    private String socket;
    private int tpd;
    private static List<CPU> savedCpus = null;

    public CPU(int id, String name, String brand, double price, int stock_qty, String category, int cores, int threads, double base, double boost, String socket, int tpd) {
        super(id, name, brand, price, stock_qty, category);
        this.cores = cores;
        this.threads = threads;
        this.base = base;
        this.boost = boost;
        this.socket = socket;
        this.tpd = tpd;
    }

    public CPU(int id, String name, String brand, double price, int stock_qty, String category) {
        super(id, name, brand, price, stock_qty, category);
    }
    public CPU( String name, String brand, double price, int stock_qty, String category) {
        super(name, brand, price, stock_qty, category);
    }

    public void setAdditionalInfo(int id) {
        String sql = "SELECT * FROM CPU where part_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.cores = rs.getInt("cores");
                this.threads = rs.getInt("threads");
                this.base = rs.getDouble("base_ghz");
                this.boost = rs.getDouble("boost_ghz");
                this.socket = rs.getString("socket");
                this.tpd = rs.getInt("tdp_watt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    public static void reloadCPU(){
        savedCpus=null;
    }
    public static List getCPU(){
        if(savedCpus!=null){
            return savedCpus;
        }
        List <Part> parts = Part.getAvailableParts();
        List <CPU> cpu = new ArrayList<>();
        for(Part p: parts){
            if(p.getCategory().equals("CPU")){
                cpu.add((CPU) p);
            }
        }
        savedCpus=cpu;
        return cpu;
    }

    public int getCores() {
        return cores;
    }

    public int getThreads() {
        return threads;
    }

    public double getBase() {
        return base;
    }

    public double getBoost() {
        return boost;
    }

    public String getSocket() {
        return socket;
    }

    public int getTdp() {
        return tpd;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setBoost(double boost) {
        this.boost = boost;
    }

    public void setTdp(int tpd) {
        this.tpd = tpd;
    }

    public String setCores(String cores) {
        Integer parsed = tryParsePositiveInt(cores);
        if (parsed == null) return "Cores must be a positive integer";
        this.cores = parsed;
        return null;
    }

    public String setThreads(String threads) {
        Integer parsed = tryParsePositiveInt(threads);
        if (parsed == null) return "Threads must be a positive integer";
        this.threads = parsed;
        return null;
    }

    public String setBase(String base) {
        Double parsed = tryParsePositiveDouble(base);
        if (parsed == null) return "Base clock must be a positive number";
        this.base = parsed;
        return null;
    }

    public String setBoost(String boost) {
        Double parsed = tryParsePositiveDouble(boost);
        if (parsed == null) return "Boost clock must be a positive number";
        this.boost = parsed;
        return null;
    }

    public String setSocket(String socket) {
        String parsed = tryParseNonEmptyString(socket);
        if (parsed == null) return "Socket cannot be empty";
        this.socket = parsed;
        return null;
    }

    public String setTdp(String tdp) {
        Integer parsed = tryParsePositiveInt(tdp);
        if (parsed == null) return "TDP must be a positive integer";
        this.tpd = parsed;
        return null;
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println(
                        "Cores: " + cores + "\n" +
                        "Threads: " + threads + "\n" +
                        "Base: " + base + "\n" +
                        "Boost: " + boost + "\n" +
                        "Socket: " + socket + "\n" +
                        "TDP: " + tpd);
    }


}

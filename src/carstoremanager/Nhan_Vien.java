/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carstoremanager;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Nhan_Vien implements Serializable{
    private String ma,hoten,email;
    private double luong;
    private int tuoi;

    public Nhan_Vien() {
    }
    public Nhan_Vien(String ma, String hoten, String email, double luong, int tuoi) {
        this.ma = ma;
        this.hoten = hoten;
        this.email = email;
        this.luong = luong;
        this.tuoi = tuoi;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
    
}

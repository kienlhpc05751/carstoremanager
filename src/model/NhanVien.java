/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class NhanVien {

    String maNV;
    String tenNV;
    int sdtNV;
    String diachiNV;
    String phai;
    String luong;
    String Date;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, int sdtNV, String diachiNV, String phai, String luong, String Date) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdtNV = sdtNV;
        this.diachiNV = diachiNV;
        this.phai = phai;
        this.luong = luong;
        this.Date = Date;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(int sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getDiachiNV() {
        return diachiNV;
    }

    public void setDiachiNV(String diachiNV) {
        this.diachiNV = diachiNV;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
    
    
    
}

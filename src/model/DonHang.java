/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class DonHang {
   private String maDH;
    private Date  ngayBan;
    private String maKHd;
    private String maXEd;
     private String maXNV;
    private Float donGia;
    private int soluong;
    private Float thanhTien;

    public DonHang() {
    }

    public DonHang(String maDH, Date ngayBan, String maKHd, String maXEd, String maXNV, Float donGia, int soluong, Float thanhTien) {
        this.maDH = maDH;
        this.ngayBan = ngayBan;
        this.maKHd = maKHd;
        this.maXEd = maXEd;
        this.maXNV = maXNV;
        this.donGia = donGia;
        this.soluong = soluong;
        this.thanhTien = thanhTien;
    }
   
    

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }
        
    public String getMaKHd() {
        return maKHd;
    }

    public void setMaKHd(String maKHd) {
        this.maKHd = maKHd;
    }

    public String getMaXEd() {
        return maXEd;
    }

    public void setMaXEd(String maXEd) {
        this.maXEd = maXEd;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaXNV() {
        return maXNV;
    }

    public void setMaXNV(String maXNV) {
        this.maXNV = maXNV;
    }
    
    
    
            
}

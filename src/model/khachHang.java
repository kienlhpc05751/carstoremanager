/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class khachHang {
    String  maKH ;
        String  tenKH ;

            String  phaiKH ;
    String  sdtKH ;
    String  diachiKH ;

    public khachHang() {
    }

    public khachHang(String maKH, String tenKH, String phaiKH, String sdtKH, String diachiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.phaiKH = phaiKH;
        this.sdtKH = sdtKH;
        this.diachiKH = diachiKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getPhaiKH() {
        return phaiKH;
    }

    public void setPhaiKH(String phaiKH) {
        this.phaiKH = phaiKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getDiachiKH() {
        return diachiKH;
    }

    public void setDiachiKH(String diachiKH) {
        this.diachiKH = diachiKH;
    }

    
            
            
    
}

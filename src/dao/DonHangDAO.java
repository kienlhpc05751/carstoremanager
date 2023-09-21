/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import model.DonHang;
import java.util.Locale;
//import model.Student;
//  package model có stutent

/**
 *
 * @author Asus
 */
public class DonHangDAO {

    //lay du lieu tu database
    public static List<DonHang> getAll(Connection con) {

        try {
            List<DonHang> donhang = new ArrayList<>();
            String sql = "select * from XE";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                DonHang dh = new DonHang();
                dh.setMaDH(rs.getString(1));
                dh.setNgayBan(rs.getDate(2));
                dh.setMaKHd(rs.getString(3));
                dh.setMaXEd(rs.getString(4));
                dh.setMaXNV(rs.getString(5));
                dh.setDonGia(rs.getFloat(6));
                dh.setSoluong(rs.getInt(7));
                dh.setThanhTien(rs.getFloat(8));
                donhang.add(dh);
            }
            return donhang;

        } catch (Exception e) {
        }
        return null;
    }

    //them du lieu student
    public static int insert(Connection con, DonHang donhang) {
        try {
            int rs = 0;
            String sql = "INSERT INTO XE VALUES(?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, donhang.getMaDH());
            statement.setDate(2, (Date) donhang.getNgayBan());
            statement.setString(3, donhang.getMaKHd());
            statement.setString(4, donhang.getMaXEd());
            statement.setString(5, donhang.getMaXNV());
            statement.setFloat(6, donhang.getDonGia());
            statement.setInt(7, donhang.getSoluong());
            statement.setFloat(8, donhang.getThanhTien());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {

        }
        return 0;
    }

    public static int update(Connection con, DonHang donHang) {
        try {
            int rs = 0;
            String sql = "UPDATE XE set TENXE =?, LOAIXE=?,NGAYNHAP=?,GIA=? WHERE MAXE=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDate(1, (Date) donHang.getNgayBan());
            statement.setString(2, donHang.getMaXEd());
            statement.setString(3, donHang.getMaXEd());
            statement.setString(4, donHang.getMaXNV());
            statement.setFloat(5, donHang.getDonGia());
            statement.setInt(6, donHang.getSoluong());
            statement.setFloat(7, donHang.getThanhTien());
            statement.setString(8, donHang.getMaKHd());

            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int delet(Connection con, DonHang donhang) {
        try {
            int rs = 0;
            String sql = "delete from XE WHERE MAXE=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, donhang.getMaDH());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {

        }
        return 0;
    }
    // lạm lại câu sql xôa

}

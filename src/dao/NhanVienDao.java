/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.CarStoreManagerA;
import model.NhanVien;

/**
 *
 * @author Asus
 */
//MANV VARCHAR(15) PRIMARY KEY NOT NULL,
//	TENNV NVARCHAR(15),
//	SDTNV INT ,
//	DIACHINV NVARCHAR(100),
//	PHAI NVARCHAR(3),
//	LUONG FLOAT ,
//	NGAYSINH DATE
public class NhanVienDao {

    public static List<NhanVien> getAllNV(Connection con) {

        try {
            List<NhanVien> nhanvien = new ArrayList<>();
            String sql = "select * from NHANVIEN";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("dao.NhanVienDao.getAllNV.0");

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setTenNV(rs.getString(2));
                nv.setSdtNV(rs.getInt(3));
                nv.setDiachiNV(rs.getString(4));
                nv.setPhai(rs.getString(5));
                nv.setLuong(rs.getString(6));
                nv.setDate(rs.getString(7));
                
                nhanvien.add(nv);
            }

            return nhanvien;

        } catch (Exception e) {
            System.out.println("dao.NhanVienDao.getAllNV() 1");
        }
        return null;
    }

    public static int insert(Connection con, NhanVien nv) {
        try {
            int rs = 0;
            String sql = "INSERT INTO NHANVIEN VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nv.getMaNV());
            statement.setString(2, nv.getTenNV());
            statement.setInt(3, nv.getSdtNV());
            statement.setString(4, nv.getDiachiNV());
            statement.setString(5, nv.getPhai());
            statement.setString(6, nv.getLuong());
            statement.setString(7, nv.getDate());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {

        }
        return 0;
    }
    public static int update(Connection con, NhanVien nv) {
        try {
            int rs = 0;
            String sql = " UPDATE NHANVIEN set  TENNV = ? ,  SDTNV =?, DIACHINV =?,PHAI = ?,LUONG = ? WHERE MANV = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nv.getTenNV());
            statement.setInt(2, nv.getSdtNV());
            statement.setString(3, nv.getDiachiNV());
            statement.setString(4, nv.getPhai());
            statement.setString(5, nv.getLuong());
            statement.setString(6, nv.getDate());
            statement.setString(7, nv.getMaNV());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
      public static int delet(Connection con, NhanVien nv) {
        try {
            int rs = 0;
            String sql = "delete from NHANVIEN WHERE MANV=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nv.getMaNV());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {

        }
        return 0;
    }
}

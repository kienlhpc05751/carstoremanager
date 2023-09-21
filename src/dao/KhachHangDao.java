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
import model.khachHang;

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
public class KhachHangDao {

    public static List<khachHang> getAllKH(Connection con) {

        try {
            List<khachHang> kh = new ArrayList<>();
            String sql = "select * from KHACHHANG";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("dao.KHACHHANG");

            while (rs.next()) {
                khachHang nv = new khachHang();
                nv.setMaKH(rs.getString(1));
                nv.setTenKH(rs.getString(2));
                nv.setSdtKH(rs.getString(4));
                nv.setDiachiKH(rs.getString(5));
                nv.setPhaiKH(rs.getString(3));
                kh.add(nv);
            }
            return kh;

        } catch (Exception e) {
            System.out.println("dao.kh 1");
        }
        return null;
    }

    public static int insert(Connection con, khachHang
            nv) {
        try {
            int rs = 0;
            String sql = "INSERT INTO KHACHHANG VALUES(?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nv.getMaKH());
            statement.setString(2, nv.getTenKH());
            statement.setString(4, nv.getSdtKH());
            statement.setString(5, nv.getDiachiKH());
            statement.setString(3, nv.getPhaiKH());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {
            System.out.println("dao.kh 2");

        }
        return 0;
    }

    public static int update(Connection con, khachHang nv) {
        try {
            int rs = 0;
            String sql = " UPDATE KHACHHANG set  TENKH = ? ,  PHAI= ?, SODT =?,DIACHI = ? WHERE MAKH = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nv.getTenKH());
            statement.setString(2, nv.getPhaiKH());
            statement.setString(3, nv.getSdtKH());
            statement.setString(4, nv.getDiachiKH());
            statement.setString(5, nv.getMaKH());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
             System.out.println("dao. kh 3");
        }
        return 0;
    }

    public static int delet(Connection con, khachHang nv) {
        try {
            int rs = 0;
            String sql = "delete from KHACHHANG WHERE MAKH=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nv.getMaKH());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {

        }
        return 0;
    }
}

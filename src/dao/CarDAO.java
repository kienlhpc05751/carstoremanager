/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import model.CarStoreManagerA;
import java.util.Locale;
//import model.Student;
//  package model có stutent

/**
 *
 * @author Asus
 */
public class CarDAO {

    //lay du lieu tu database
    public static List<CarStoreManagerA> getAll(Connection con) {
    
        try {
            List<CarStoreManagerA> carsDao = new ArrayList<>();
            String sql = "select * from XE";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                  CarStoreManagerA car = new CarStoreManagerA();
                car.setMaXe(rs.getString("MAXE"));
                car.setTenXe(rs.getString("TENXE"));
                car.setLoaiXe(rs.getString("LOAIXE"));
                car.setNgayNhapXe(rs.getString("NGAYNHAP"));
                 DecimalFormat df = new DecimalFormat("#.######");
                String giaxe = String.valueOf(df.format(rs.getDouble("GIA")));
                 car.setGia(giaxe);
//                car.setGia(rs.getString("GIA"));
//                String maxe = rs.getString(1);
//                String tenxe = rs.getString(2);
//                String loaixe = rs.getString(3);
//                String ngaynhap = rs.getString(4);
//                String giaxe = rs.getString(5);
//                CarStoreManagerA car = new CarStoreManagerA(maxe, tenxe, loaixe, ngaynhap, giaxe);
                carsDao.add(car);
            }
   
     
            return carsDao;

        } catch (Exception e) {
        }
        return null;
    }

    //them du lieu student
    public static int insert(Connection con, CarStoreManagerA cars) {
        try {
            int rs = 0;
            String sql = "INSERT INTO XE VALUES(?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, cars.getMaXe());
            statement.setString(2, cars.getTenXe());
            statement.setString(3, cars.getLoaiXe());
            statement.setString(4, cars.getNgayNhapXe());
//            statement.setInt(5, cars.getGioiTinh());
            statement.setString(5, cars.getGia());

            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {

        }
        return 0;
    }

    public static int update(Connection con, CarStoreManagerA cars) {
        try {
            int rs = 0;
            String sql = "UPDATE XE set TENXE =?, LOAIXE=?,NGAYNHAP=?,GIA=? WHERE MAXE=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, cars.getTenXe());
            statement.setString(2, cars.getLoaiXe());
            statement.setString(3, cars.getNgayNhapXe());
            statement.setString(4, cars.getGia());
            statement.setString(5, cars.getMaXe());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int delet(Connection con, CarStoreManagerA cars) {
        try {
            int rs = 0;
            String sql = "delete from XE WHERE MAXE=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, cars.getMaXe());
            rs = statement.executeUpdate();
            return rs;
        } catch (Exception ex) {

        }
        return 0;
    }

}

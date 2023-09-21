/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
//import model.Student;
//  package model có stutent
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author nttai
 */
public class StudentDAO {
    //lay du lieu tu database
//    public static List<Student> getAll(Connection con){
//        try{
//            List<Student> students = new ArrayList<>();
//            String sql = "select * from STUDENTS";
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//            while(rs.next()){
//                Student student = new Student();
//                student.setMaSV(rs.getString("masv"));
//                student.setHoTen(rs.getString("hoten"));
//                student.setEmail(rs.getString("email"));
//                student.setSoDT(rs.getString("sodt"));
//                student.setGioiTinh(rs.getInt("gioitinh"));
//                student.setDiaChi(rs.getString("diachi"));
//                students.add(student);
//            } 
//            return students;
//        }catch(Exception ex){
//            
//        }
//        return null;
//    }
//    //them du lieu student
//    public static int insert(Connection con, Student student){
//        try{
//            int rs = 0;
//            String sql = "INSERT INTO STUDENTS VALUES(?,?,?,?,?,?)";
//            PreparedStatement statement = con.prepareStatement(sql);
//            statement.setString(1, student.getMaSV());
//            statement.setString(2, student.getHoTen());
//            statement.setString(3, student.getEmail());
//            statement.setString(4, student.getSoDT());
//            statement.setInt(5, student.getGioiTinh());
//            statement.setString(6, student.getDiaChi());
//            
//            rs = statement.executeUpdate();
//            return rs;
//        }catch(Exception ex){
//            
//        }
//        return 0;
//    }
//    public static int update(Connection con, Student student){
//        try{
//            int rs = 0;
//            String sql = "UPDATE STUDENTS SET HOTEN=?, EMAIL =?, SODT=?,GIOITINH=?,DIACHI=? WHERE MASV=?";
//            PreparedStatement statement = con.prepareStatement(sql);
//            
//            statement.setString(1, student.getHoTen());
//            statement.setString(2, student.getEmail());
//            statement.setString(3, student.getSoDT());
//            statement.setInt(4, student.getGioiTinh());
//            statement.setString(5, student.getDiaChi());
//            statement.setString(6, student.getMaSV());
//            rs = statement.executeUpdate();
//            return rs;
//        }catch(Exception ex){
//            
//        }
//        return 0;
//    }
//    
//    public static int delet(Connection con, Student student){
//        try{
//            int rs = 0;
//            String sql = "delete from STUDENTS WHERE MASV=?";
//            PreparedStatement statement = con.prepareStatement(sql);
//            
//            
//            statement.setString(1, student.getMaSV());
//            rs = statement.executeUpdate();
//            return rs;
//        }catch(Exception ex){
//            
//        }
//        return 0;
//    }
}

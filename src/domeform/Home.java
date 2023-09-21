/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package domeform;

import model.CarStoreManagerA;

import dao.CarDAO;
import dao.*;
import java.sql.Connection;
import java.util.List;
import domeform.util.DatabaseHelper_1;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CarStoreManagerA;
import java.awt.Color;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import model.*;

/**
 *
 * @author Asus
 */
public class Home extends javax.swing.JFrame {

//    ArrayList<CarStoreManagerA> listcarsA = new ArrayList<>();
//    List<CarStoreManagerA> list = new ArrayList<>();
    int current = 0; //chỉ số truy cập
    int current1 = 0; //chỉ số truy cập
    int current2 = 0;

    NumberFormat nf = NumberFormat.getCurrencyInstance();
    DecimalFormat df = new DecimalFormat("#.######");
    int index;

    String id; // biến lưu mã để check trùng mã dùng lại
    String id1; // biến lưu mã để check trùng mã dùng lại

    // Định dạng phần ngàn
//        String formattedNumber = nf.format(number);
    // In kết quả
//        System.out.println(formattedNumber);
    List<CarStoreManagerA> cars = new ArrayList<>();
    List<NhanVien> listNVS = new ArrayList<>();
    List<khachHang> listKHS = new ArrayList<>();

    public Home() {
        initComponents();
        setTitle("QUẢN LÝ XE MÁY");
        setLocationRelativeTo(null);
        loaddate();
        loaddateNV();
        loaddateKH();

        DisplayKH(current2);
        DisplayNV(current1);
        Display(current);
//        RuingChu();
    }
//
//    public void SapXepTheogia() {
//        Collections.sort(cars, new Comparator<CarStoreManagerA>() {// dài đó là model1 .xe á
//            @Override
//            public int compare(CarStoreManagerA s1, CarStoreManagerA s2) {
//                Double a = Double.valueOf(s1.getGia());
//                Double b = Double.valueOf(s2.getGia());
//                
//
//                return Double.compare(a, b);
//            }
//          
//        });
//         System.out.println("thành công 22");
//        loaddate();
//
//    }
    public void RuingChu() {
        new Thread() {
            public void run() {
                String text = jblTenSR.getText() + (" ");
                while (true) {
                    text = text.substring(1, text.length()) + text.charAt(0);
                    try {
                        sleep(500);
                        jblTenSR.setText(text);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    boolean checkMaXe() {
        try {
            Connection con = DatabaseHelper_1.connectDb();
            Statement statement = con.createStatement();
            String sql = " select * from XE ";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String r = resultSet.getString("MAXE");
                if (r.equals(txtMaXe.getText())) {
                    System.out.println(" Trung mã MAXE rồi!");
                    return false;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    boolean checkMaKH() {
        try {
            Connection con = DatabaseHelper_1.connectDb();
            Statement statement = con.createStatement();
            String sql = " select * from KHACHHANG ";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String r = resultSet.getString("MAKH");
                if (r.equals(txtMaKH.getText())) {
                    System.out.println(" Trung mã MAKH rồi!");
                    return false;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    boolean checkMaNV() {
        try {
            Connection con = DatabaseHelper_1.connectDb();
            Statement statement = con.createStatement();
            String sql = " select * from NHANVIEN ";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String r = resultSet.getString("MANV");
                if (r.equals(txtMaXe.getText())) {
                    System.out.println(" Trung mã MANV rồi!");
                    return false;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // lấy dữ liệu từ đa ta
    public void loaddate() {
        Connection con = DatabaseHelper_1.connectDb();
        if (con != null) {
            cars = CarDAO.getAll(con);
            hienThi();
            if (!cars.isEmpty()) {
            } else {
                System.out.println("Danh sách đang trống");
            }
        } else {
            System.out.println("Kết nối thất bại");
        }
    }

    public void loaddateNV() {
        Connection con = DatabaseHelper_1.connectDb();
        if (con != null) {
            listNVS = NhanVienDao.getAllNV(con);
            hienThiNV();
            if (!listNVS.isEmpty()) {

            } else {
                System.out.println("danh sách rỗng 0");
            }

        } else {
            System.out.println("kết nối thất bại 0:");
        }

    }

    public void loaddateKH() {
        Connection con = DatabaseHelper_1.connectDb();
        if (con != null) {
            listKHS = KhachHangDao.getAllKH(con);
            hienThiKH();
            if (!listNVS.isEmpty()) {

            } else {
                System.out.println("danh sách rỗng 0");
            }

        } else {
            System.out.println("kết nối thất bại 0:");
        }

    }

    public void hienThiNV() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        for (NhanVien nv : listNVS) {
            Object[] row = {
                nv.getMaNV(), nv.getTenNV(), nv.getSdtNV(),
                nv.getDiachiNV(), nv.getPhai(), nv.getLuong(), nv.getDate()
            };
            model.addRow(row);
        }
    }

    public void hienThi() {
        DefaultTableModel model = (DefaultTableModel) tblBan.getModel();
        model.setRowCount(0);
        for (CarStoreManagerA car : cars) {
            Object[] row = {car.getMaXe(), car.getTenXe(),
                car.getLoaiXe(), car.getNgayNhapXe(), car.getGia()};
            model.addRow(row);
        }

    }

    public void hienThiKH() {
        DefaultTableModel model = (DefaultTableModel) tblKH.getModel();
        model.setRowCount(0);
        for (khachHang kh : listKHS) {
            Object[] row = {
                kh.getMaKH(), kh.getTenKH(), kh.getPhaiKH(),
                kh.getSdtKH(), kh.getDiachiKH()
            };
            model.addRow(row);
        }
    }

    public void DisplayNV(int i) {
        if (listNVS != null && i >= 0 && i < listNVS.size()) {
            NhanVien nv = listNVS.get(i);
            txtMaNV.setText(nv.getMaNV());
            txtHoTen.setText(nv.getTenNV());
            txtSDT.setText(String.valueOf(nv.getSdtNV()));
            txtdiachi.setText(nv.getDiachiNV());
            txtGia.setText(nv.getLuong());
            if (nv.getPhai().equals("Nam")) {
                jRadioButton1.setSelected(true);
            } else {
                jRadioButton2.setSelected(true);
            }
            txtLuong.setText(nv.getLuong());
            tblNhanVien.setRowSelectionInterval(i, i);
        } else {
            System.out.println("Không có đối tượng NhanVien tại vị trí " + i);
        }
    }

    // lấy cái int i = row
    public void Display(int i) {
        if (cars != null && i >= 0 && i < cars.size()) {
            CarStoreManagerA car = cars.get(i);
            txtMaXe.setText(car.getMaXe());
            txtTenXe.setText(car.getTenXe());
            txtLoaiXe.setText(car.getLoaiXe());
            txtNgayNhap.setText(car.getNgayNhapXe());
            txtGia.setText(car.getGia());
            tblBan.setRowSelectionInterval(i, i);
        } else {
            System.out.println("Không có đối tượng CarStoreManagerA tại vị trí " + i);
        }
    }

    public void DisplayKH(int i) {
        if (listKHS != null && i >= 0 && i < listKHS.size()) {
            khachHang car = listKHS.get(i);
            txtMaKH.setText(car.getMaKH());
            txtTenHK.setText(car.getTenKH());
            txtSĐTHK.setText(car.getSdtKH());
            if (car.getPhaiKH().equals("Nam")) {
                jRadioButton1.setSelected(true);
            } else {
                jRadioButton2.setSelected(true);
            }
            txtĐiaChiHK.setText(car.getDiachiKH());
            tblKH.setRowSelectionInterval(i, i);
        } else {
            System.out.println("Không có đối tượng khách hàng  tại vị trí " + i);
        }
    }

    public void deleteNV() {
        Connection con = DatabaseHelper_1.connectDb();
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMaNV.getText());
        int ketqua = NhanVienDao.delet(con, nv);
        if (ketqua == 0) {
            System.out.println(" Xóa không thành công");
        } else {
            loaddateNV();
            DisplayNV(current1);
            JOptionPane.showMessageDialog(this, " Xóa thành công");
        }
    }

    public void delete() {
        Connection con = DatabaseHelper_1.connectDb();
        CarStoreManagerA car = new CarStoreManagerA();
        car.setMaXe(txtMaXe.getText());
        int ketqua = CarDAO.delet(con, car);
        if (ketqua == 0) {
            System.out.println(" Xóa không thành công");
        } else {
            loaddate();
            Display(current);
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        }
    }

    public void deleteHK() {
        Connection con = DatabaseHelper_1.connectDb();
        khachHang kh = new khachHang();
        kh.setMaKH(txtMaKH.getText());
        int ketqua = KhachHangDao.delet(con, kh);
        if (ketqua == 0) {
            System.out.println(" Xóa không thành công");
        } else {
            loaddateKH();
            DisplayKH(current2);
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        }
    }

    public void top3() {
        try {
            Connection con = DatabaseHelper_1.connectDb();
            String sql = "SELECT TOP 3 * FROM XE ORDER BY GIA DESC;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String message = "Top 3 xe có giá cao nhất:\n\n";
            while (rs.next()) {
                String maXe = rs.getString("MAXE");
                String tenXe = rs.getString("TENXE");
                DecimalFormat df = new DecimalFormat("#.######");
                String giaXe = String.valueOf(df.format(rs.getDouble("GIA")));
//                double giaXe = df.format(rs.getDouble("GIA"));
                message += "Mã xe: " + maXe + "\n";
                message += "Tên xe: " + tenXe + "\n";
                message += "Giá xe: " + giaXe + " đ" + "\n\n";
            }
            JOptionPane.showMessageDialog(null, message);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void timKiemNV() {
        if (txtMaNV.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "trống mã!");
            return;
        } else {
            String ma = txtMaNV.getText();
            System.out.println("mã:" + ma);
            int size = listNVS.size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                if (listNVS.get(i).getMaNV().equalsIgnoreCase(ma)) {
                    JOptionPane.showMessageDialog(null, "Đã tìm thấy!!!");
                    DisplayNV(i);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Không tìm thấy!!!");
        }
    }

    public void timKiem() {
        if (jTextField1.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "trống mã!");
            return;
        } else {
            String ma = jTextField1.getText();
            System.out.println("mã:" + ma);
            int size = cars.size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                if (cars.get(i).getMaXe().equalsIgnoreCase(ma)) {
                    JOptionPane.showMessageDialog(null, "Đã tìm thấy!!!");
                    Display(i);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Không tìm thấy!!!");
        }
    }

    public void insertNV() {

        if (checkMaNV()) {

            System.out.println("Mã không trùng!");
            Connection con = DatabaseHelper_1.connectDb();
            NhanVien car = new NhanVien();
            car.setMaNV(txtMaNV.getText());
            car.setTenNV(txtHoTen.getText());
            car.setSdtNV(Integer.parseInt(txtSDT.getText()));
            car.setDiachiNV(txtdiachi.getText());
            car.setPhai(jRadioButton1.isSelected() ? "Nam" : "Nữ");
            car.setLuong(txtLuong.getText());
            car.setDate(txtNgayS.getText());
            String id1 = txtMaXe.getText(); // Đảm bảo rằng bạn đã khai báo id1
            int ketqua = NhanVienDao.insert(con, car);
            if (ketqua == 0) {
                System.out.println(" Thêm không thành công !");
            } else {
                loaddateNV();
                DisplayNV(current);
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "trùng mã: " + id);
        }

    }

    public void insertKH() {

        if (checkMaKH()) {
            System.out.println("mã không trùng!");
            Connection con = DatabaseHelper_1.connectDb();
            khachHang kh = new khachHang();
            kh.setMaKH(txtMaKH.getText());
            kh.setTenKH(txtTenHK.getText());
//            kh.setPhaiKH(rdoNam.isSelected() ? "Nam" : "Nữ");
            if (rdoNam.isSelected()) {
                kh.setPhaiKH("Nam");
            } else {
                kh.setPhaiKH("Nữ");
            }
            kh.setSdtKH(txtSĐTHK.getText());
            kh.setDiachiKH(txtĐiaChiHK.getText());
//            id = txtMaKH.getText();
            int ketqua = KhachHangDao.insert(con, kh);
            if (ketqua == 0) {
                System.out.println(" Thêm không thành công !");
            } else {
                loaddateKH();
                DisplayKH(current2);
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "trùng mã: ");
//            JOptionPane.showMessageDialog(this, "trùng mã: " + id);
            // phương án hai dialog yes no hỏi có muốn updata luôn không
        }
    }

    public void insert() {

        if (checkMaXe()) {
            System.out.println("mã không trùng!");
            Connection con = DatabaseHelper_1.connectDb();
            CarStoreManagerA car = new CarStoreManagerA();
            car.setMaXe(txtMaXe.getText());
            car.setTenXe(txtTenXe.getText());
            car.setLoaiXe(txtLoaiXe.getText());
            car.setNgayNhapXe(txtNgayNhap.getText());
            car.setGia(txtGia.getText());
            id = txtMaXe.getText();
            int ketqua = CarDAO.insert(con, car);
            if (ketqua == 0) {
                System.out.println(" insert không thành công !");
            } else {
                loaddate();
                Display(current);
                JOptionPane.showMessageDialog(this, "insert thành công!");

            }
        } else {
            JOptionPane.showMessageDialog(this, "trùng mã: " + id);
            // phương án hai dialog yes no hỏi có muốn updata luôn không
        }

    }

    public void updateNV() {
        Connection con = DatabaseHelper_1.connectDb();
        NhanVien car = new NhanVien();
        car.setTenNV(txtHoTen.getText());
        car.setSdtNV(Integer.parseInt(txtSDT.getText()));
        car.setDiachiNV(txtNgayNhap.getText());
        car.setPhai(txtGia.getText());
        car.setLuong(txtLuong.getText());
        car.setLuong(txtMaNV.getText());
        int rs = NhanVienDao.update(con, car);
        if (rs > 0) {
            JOptionPane.showMessageDialog(this, " Update thành công !");
            loaddateNV();
            DisplayNV(current);
        } else {
            JOptionPane.showMessageDialog(this, "insert thất bại");

        }
    }

    public void update() {
        Connection con = DatabaseHelper_1.connectDb();
        CarStoreManagerA car = new CarStoreManagerA();
        car.setTenXe(txtTenXe.getText());
        car.setLoaiXe(txtLoaiXe.getText());
        car.setNgayNhapXe(txtNgayNhap.getText());
        car.setGia(txtGia.getText());
        car.setMaXe(txtMaXe.getText());
        int rs = CarDAO.update(con, car);
        if (rs > 0) {
            JOptionPane.showMessageDialog(this, " Update thành công !");
            loaddate();
            Display(current);
        } else {
            JOptionPane.showMessageDialog(this, "insert thất bại");

        }
    }

    public void updateKH() {
        Connection con = DatabaseHelper_1.connectDb();
        khachHang kh = new khachHang();
        kh.setMaKH(txtMaKH.getText());
        kh.setTenKH(txtTenHK.getText());
        if (rdoNam.isSelected()) {
            kh.setPhaiKH("Nam");
        } else {
            kh.setPhaiKH("Nữ");
        }
        kh.setSdtKH(txtSDT.getText());
        kh.setDiachiKH(txtdiachi.getText());
        int rs = KhachHangDao.update(con, kh);
        if (rs > 0) {
            JOptionPane.showMessageDialog(this, " Update thành công !");
            loaddateKH();
            DisplayKH(current2);
        } else {
            JOptionPane.showMessageDialog(this, "insert thất bại");

        }
    }

    int width = 190, height = 590;

    public void openMenuBar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < width; i++) {
                    pnMenu.setSize(width, height);
                }
            }
        }).start();
    }

    public void closeMenuBar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = width; i > 0; i--) {
                    pnMenu.setSize(i, height);
                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel22 = new javax.swing.JLabel();
        pnMain = new javax.swing.JPanel();
        pnMenu = new javax.swing.JPanel();
        txtFormQLxe = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFormQLxe1 = new javax.swing.JLabel();
        txtFormQLxe2 = new javax.swing.JLabel();
        txtFormQLxe3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnParen = new javax.swing.JPanel();
        pnHome = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        pnEmployee = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtMaNV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        btnFist = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDetele = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        txtNgayS = new javax.swing.JTextField();
        pnQLXe = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jbnNew = new javax.swing.JButton();
        jbnSave = new javax.swing.JButton();
        jbnDelete = new javax.swing.JButton();
        jbnUpdate = new javax.swing.JButton();
        btnSX = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMaXe = new javax.swing.JTextField();
        txtTenXe = new javax.swing.JTextField();
        txtLoaiXe = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBan = new javax.swing.JTable();
        pnKhachhang = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTenHK = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtSĐTHK = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtĐiaChiHK = new javax.swing.JTextArea();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnNewKH = new javax.swing.JButton();
        btnSaveKH = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnTimKH = new javax.swing.JButton();
        btnUpdateKH = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        pnDonHang = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txtMaDh = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtNgban = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtMaXEd = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDH = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtMaKHd = new javax.swing.JTextField();
        txtMaNVd = new javax.swing.JTextField();
        txtĐơnG = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jblTenSR = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnMain.setPreferredSize(new java.awt.Dimension(110, 400));

        pnMenu.setBackground(new java.awt.Color(204, 204, 204));
        pnMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnMenu.setPreferredSize(new java.awt.Dimension(110, 395));

        txtFormQLxe.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        txtFormQLxe.setText("Quản lý xe");
        txtFormQLxe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFormQLxeMouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/domeform/circle-xmark.png"))); // NOI18N
        jLabel3.setText(" ");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        txtFormQLxe1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        txtFormQLxe1.setText("Quản lý khách hàng");
        txtFormQLxe1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFormQLxe1MouseClicked(evt);
            }
        });

        txtFormQLxe2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        txtFormQLxe2.setText("Quản lý nhân viên");
        txtFormQLxe2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFormQLxe2MouseClicked(evt);
            }
        });

        txtFormQLxe3.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        txtFormQLxe3.setText("Quản lý hoa đơn");
        txtFormQLxe3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFormQLxe3MouseClicked(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(153, 153, 153));
        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel20.setText(" EXIT");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFormQLxe, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFormQLxe2)
                    .addComponent(txtFormQLxe1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFormQLxe3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(59, 59, 59)
                .addComponent(txtFormQLxe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFormQLxe2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFormQLxe1)
                .addGap(18, 18, 18)
                .addComponent(txtFormQLxe3)
                .addGap(61, 61, 61)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(31, 31, 31))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/domeform/menu.png"))); // NOI18N
        jLabel4.setText(" ");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        pnParen.setLayout(new java.awt.CardLayout());

        pnHome.setBackground(java.awt.SystemColor.controlShadow);
        pnHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carstoremanager/winner-x-moi-9689-1598190940.png"))); // NOI18N

        javax.swing.GroupLayout pnHomeLayout = new javax.swing.GroupLayout(pnHome);
        pnHome.setLayout(pnHomeLayout);
        pnHomeLayout.setHorizontalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHomeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnHomeLayout.setVerticalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHomeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel21)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pnParen.add(pnHome, "card3");

        pnEmployee.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(102, 153, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Quản Lý Nhân Viên");

        jLabel2.setBackground(new java.awt.Color(251, 175, 175));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("Mã Nhân Viên ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 255));
        jLabel15.setText("Họ Và Tên ");

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 255));
        jLabel16.setText("Tuổi");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 255));
        jLabel17.setText("SDT");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 255));
        jLabel18.setText("Lương");

        tblNhanVien.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblNhanVien.setForeground(new java.awt.Color(0, 102, 255));
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

        btnFist.setBackground(new java.awt.Color(51, 102, 255));
        btnFist.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFist.setForeground(new java.awt.Color(255, 102, 51));
        btnFist.setText("|<");
        btnFist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFistActionPerformed(evt);
            }
        });

        btnPre.setBackground(new java.awt.Color(0, 102, 255));
        btnPre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPre.setForeground(new java.awt.Color(255, 102, 51));
        btnPre.setText("<<");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(0, 102, 255));
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 102, 51));
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(0, 102, 255));
        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 102, 51));
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel2.setForeground(new java.awt.Color(255, 102, 51));
        jPanel2.setLayout(null);

        btnNew.setBackground(new java.awt.Color(114, 151, 206));
        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 102, 51));
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel2.add(btnNew);
        btnNew.setBounds(20, 20, 85, 23);

        btnSave.setBackground(new java.awt.Color(128, 165, 220));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 102, 51));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave);
        btnSave.setBounds(20, 50, 85, 23);

        btnDetele.setBackground(new java.awt.Color(126, 174, 206));
        btnDetele.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDetele.setForeground(new java.awt.Color(255, 102, 51));
        btnDetele.setText("Detele");
        btnDetele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeteleActionPerformed(evt);
            }
        });
        jPanel2.add(btnDetele);
        btnDetele.setBounds(20, 80, 85, 23);

        btnFind.setBackground(new java.awt.Color(122, 172, 206));
        btnFind.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFind.setForeground(new java.awt.Color(255, 102, 51));
        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        jPanel2.add(btnFind);
        btnFind.setBounds(20, 110, 85, 23);

        btnOpen.setBackground(new java.awt.Color(129, 174, 204));
        btnOpen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOpen.setForeground(new java.awt.Color(255, 102, 51));
        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        jPanel2.add(btnOpen);
        btnOpen.setBounds(20, 140, 85, 23);

        btnExit.setBackground(new java.awt.Color(134, 175, 203));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 102, 51));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel2.add(btnExit);
        btnExit.setBounds(20, 170, 85, 30);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 153, 255));
        jLabel19.setText("Giới Tính");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton1.setText("Nam");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton2.setText("Nữ");

        txtNgayS.setText(" ");

        javax.swing.GroupLayout pnEmployeeLayout = new javax.swing.GroupLayout(pnEmployee);
        pnEmployee.setLayout(pnEmployeeLayout);
        pnEmployeeLayout.setHorizontalGroup(
            pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnEmployeeLayout.createSequentialGroup()
                .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                    .addGroup(pnEmployeeLayout.createSequentialGroup()
                        .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnEmployeeLayout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(btnFist, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnEmployeeLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(12, 12, 12)
                        .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnEmployeeLayout.createSequentialGroup()
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnEmployeeLayout.createSequentialGroup()
                                .addComponent(txtNgayS, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)))))
                .addContainerGap())
            .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnEmployeeLayout.createSequentialGroup()
                    .addGap(0, 77, Short.MAX_VALUE)
                    .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnEmployeeLayout.createSequentialGroup()
                            .addGap(160, 160, 160)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnEmployeeLayout.createSequentialGroup()
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnEmployeeLayout.createSequentialGroup()
                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnEmployeeLayout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                        .addComponent(txtdiachi, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                        .addComponent(txtSDT)
                                        .addComponent(txtMaNV)
                                        .addComponent(txtLuong))))))
                    .addGap(0, 243, Short.MAX_VALUE)))
        );
        pnEmployeeLayout.setVerticalGroup(
            pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnEmployeeLayout.createSequentialGroup()
                .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnEmployeeLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(pnEmployeeLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel17)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNgayS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFist)
                    .addComponent(btnPre)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
            .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnEmployeeLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(37, 37, 37)
                    .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnEmployeeLayout.createSequentialGroup()
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16)
                            .addGroup(pnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jRadioButton1)
                                .addComponent(jRadioButton2))))
                    .addGap(0, 305, Short.MAX_VALUE)))
        );

        pnParen.add(pnEmployee, "card4");

        pnQLXe.setBackground(new java.awt.Color(255, 255, 255));
        pnQLXe.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnQLXeComponentShown(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText(" Car Showroom Manager");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText(" Tìm kiếm");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText(" MaXe");

        jTextField1.setText(" ");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbnNew.setText(" New");
        jbnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNewActionPerformed(evt);
            }
        });

        jbnSave.setText(" Save");
        jbnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnSaveActionPerformed(evt);
            }
        });

        jbnDelete.setText(" Delete");
        jbnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnDeleteActionPerformed(evt);
            }
        });

        jbnUpdate.setText(" Update");
        jbnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnUpdateActionPerformed(evt);
            }
        });

        btnSX.setText(" sắp xếp");
        btnSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jbnDelete)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbnUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSX, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSX)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null));

        jLabel9.setText("Mã xe:");

        jLabel11.setText(" Tên xe:");

        jLabel12.setText("loại xe:");

        jLabel13.setText(" Ngày nhập:");

        jLabel14.setText("Giá :");

        txtMaXe.setText(" ");
        txtMaXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaXeActionPerformed(evt);
            }
        });

        txtTenXe.setText(" ");
        txtTenXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenXeActionPerformed(evt);
            }
        });

        txtLoaiXe.setText(" ");

        txtNgayNhap.setText(" ");
        txtNgayNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayNhapActionPerformed(evt);
            }
        });

        txtGia.setText(" ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaXe, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenXe, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(txtMaXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel11))
                    .addComponent(txtTenXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel12))
                    .addComponent(txtLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel13))
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel14))
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButton1.setText(" Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setLayout(null);

        jButton6.setText(" |<");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton6);
        jButton6.setBounds(10, 10, 46, 20);

        jButton7.setText(" <<");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton7);
        jButton7.setBounds(60, 10, 54, 20);

        jButton8.setText(" >>");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton8);
        jButton8.setBounds(150, 10, 60, 20);

        jButton9.setText(">|");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton9);
        jButton9.setBounds(220, 10, 56, 20);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(32, 32, 32))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText(" Top 3 theo giá");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        tblBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "mã xe", "tên xe", "loại xe", "ngày nhập", "giá"
            }
        ));
        tblBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBan);

        javax.swing.GroupLayout pnQLXeLayout = new javax.swing.GroupLayout(pnQLXe);
        pnQLXe.setLayout(pnQLXeLayout);
        pnQLXeLayout.setHorizontalGroup(
            pnQLXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQLXeLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnQLXeLayout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(pnQLXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnQLXeLayout.createSequentialGroup()
                        .addGroup(pnQLXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnQLXeLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207))))
        );
        pnQLXeLayout.setVerticalGroup(
            pnQLXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQLXeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        pnParen.add(pnQLXe, "card2");

        jLabel23.setText(" Mã KH:");

        txtMaKH.setText("jTextField1");
        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel24.setText(" Tên KH:");

        txtTenHK.setText("jTextField1");
        txtTenHK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHKActionPerformed(evt);
            }
        });

        jLabel25.setText("SĐT:");

        jLabel26.setText(" Địa chỉ:");

        txtSĐTHK.setText("jTextField1");
        txtSĐTHK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSĐTHKActionPerformed(evt);
            }
        });

        jLabel27.setText(" Phai:");

        txtĐiaChiHK.setColumns(20);
        txtĐiaChiHK.setRows(5);
        jScrollPane3.setViewportView(txtĐiaChiHK);

        buttonGroup2.add(rdoNam);
        rdoNam.setText(" Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoNu);
        rdoNu.setText(" Nữ");

        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKH);

        btnNewKH.setText(" New");

        btnSaveKH.setText(" Save");

        btnDelete.setText(" Delete");

        btnTimKH.setText(" Tìm");

        btnUpdateKH.setText(" Update");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdateKH)
                    .addComponent(btnDelete)
                    .addComponent(btnNewKH)
                    .addComponent(btnSaveKH)
                    .addComponent(btnTimKH))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnNewKH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveKH)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateKH)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKH)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel27))
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTenHK, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSĐTHK, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtTenHK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtSĐTHK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel28.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel28.setText(" KHÁCH HÀNG");

        javax.swing.GroupLayout pnKhachhangLayout = new javax.swing.GroupLayout(pnKhachhang);
        pnKhachhang.setLayout(pnKhachhangLayout);
        pnKhachhangLayout.setHorizontalGroup(
            pnKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachhangLayout.createSequentialGroup()
                .addGroup(pnKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhachhangLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnKhachhangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        pnKhachhangLayout.setVerticalGroup(
            pnKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pnParen.add(pnKhachhang, "card5");

        jLabel29.setText(" Mã ĐH :");

        txtMaDh.setText("jTextField1");
        txtMaDh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDhActionPerformed(evt);
            }
        });

        jLabel30.setText(" Ngày bán :");

        txtNgban.setText("jTextField1");
        txtNgban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgbanActionPerformed(evt);
            }
        });

        jLabel31.setText("Mã XE :");

        txtMaXEd.setText("jTextField1");
        txtMaXEd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaXEdActionPerformed(evt);
            }
        });

        jLabel32.setText(" Mã KH :");

        tblDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblDH);

        jLabel33.setText(" Mã NV :");

        jLabel34.setText("Đơn Giá :");

        jLabel35.setText("Số Lượng :");

        jLabel36.setText("Thành tiên :");

        txtMaKHd.setText("jTextField1");
        txtMaKHd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHdActionPerformed(evt);
            }
        });

        txtMaNVd.setText("jTextField1");
        txtMaNVd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVdActionPerformed(evt);
            }
        });

        txtĐơnG.setText("jTextField1");
        txtĐơnG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtĐơnGActionPerformed(evt);
            }
        });

        txtSoLuong.setText("jTextField1");
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtThanhTien.setText("jTextField1");
        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        jButton2.setText("jButton1");

        jButton3.setText("jButton1");

        jButton10.setText("jButton1");

        jButton4.setText("jButton1");

        jButton5.setText("jButton1");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addGap(20, 20, 20)
                                    .addComponent(txtMaDh, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel31)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNgban, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaXEd, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtMaKHd, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMaNVd, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtĐơnG, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtMaDh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtNgban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txtMaKHd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtMaXEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtMaNVd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(txtĐơnG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnDonHangLayout = new javax.swing.GroupLayout(pnDonHang);
        pnDonHang.setLayout(pnDonHangLayout);
        pnDonHangLayout.setHorizontalGroup(
            pnDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDonHangLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        pnDonHangLayout.setVerticalGroup(
            pnDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDonHangLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pnParen.add(pnDonHang, "card6");

        jblTenSR.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jblTenSR.setForeground(new java.awt.Color(0, 0, 255));
        jblTenSR.setText("Showroom Cars ");

        javax.swing.GroupLayout pnMainLayout = new javax.swing.GroupLayout(pnMain);
        pnMain.setLayout(pnMainLayout);
        pnMainLayout.setHorizontalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 473, Short.MAX_VALUE)
                .addComponent(jblTenSR)
                .addContainerGap())
            .addGroup(pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnMainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnParen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnMainLayout.setVerticalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jblTenSR)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMainLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnParen, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        openMenuBar();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        closeMenuBar();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jbnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbnNewActionPerformed

    private void txtMaXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaXeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaXeActionPerformed

    private void txtTenXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenXeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenXeActionPerformed

    private void txtNgayNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayNhapActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        index = 0;
        Display(index);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (index == 0) {
            return;
        }
        index--;
        Display(index);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if (index == cars.size() - 1) {
            return;
        }
        index++;
        Display(index);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtFormQLxeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFormQLxeMouseClicked
        // TODO add your handling code here:
// chuyển from đó á hiểu không 
        pnParen.removeAll();
        pnParen.add(pnQLXe);
        pnParen.repaint();
        pnParen.revalidate();
    }//GEN-LAST:event_txtFormQLxeMouseClicked

    private void txtFormQLxe1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFormQLxe1MouseClicked
        // TODO add your handling code here:
//        pnKhachhang.removeAll();
        pnParen.removeAll();
        pnParen.add(pnKhachhang);
        pnParen.repaint();
        pnParen.revalidate();

    }//GEN-LAST:event_txtFormQLxe1MouseClicked

    private void txtFormQLxe2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFormQLxe2MouseClicked
        // TODO add your handling code here:
        // chuyển trang
        pnParen.removeAll();
        pnParen.add(pnEmployee);
        pnParen.repaint();
        pnParen.revalidate();

    }//GEN-LAST:event_txtFormQLxe2MouseClicked

    private void txtFormQLxe3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFormQLxe3MouseClicked
        // TODO add your handling code here:
           pnParen.removeAll();
        pnParen.add(pnDonHang);
        pnParen.repaint();
        pnParen.revalidate();

    }//GEN-LAST:event_txtFormQLxe3MouseClicked
    //thành phần hiện khi mở 
    private void pnQLXeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnQLXeComponentShown
        // TODO add your handling code here:
        setLocationRelativeTo(null);
    }//GEN-LAST:event_pnQLXeComponentShown

    private void jbnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_jbnDeleteActionPerformed

    private void jbnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnSaveActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_jbnSaveActionPerformed

    private void jbnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jbnUpdateActionPerformed

    private void tblBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanMouseClicked
        // TODO add your handling code here:
        int row = tblBan.getSelectedRow();
        txtMaXe.setText(tblBan.getValueAt(row, 0).toString());
        txtTenXe.setText(tblBan.getValueAt(row, 1).toString());
        txtLoaiXe.setText(tblBan.getValueAt(row, 2).toString());
        txtNgayNhap.setText(tblBan.getValueAt(row, 3).toString());
        txtGia.setText(tblBan.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tblBanMouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int row1 = tblNhanVien.getSelectedRow();
        txtMaNV.setText(tblNhanVien.getValueAt(row1, 0).toString());
        txtHoTen.setText(tblNhanVien.getValueAt(row1, 1).toString());
        txtSDT.setText(tblNhanVien.getValueAt(row1, 2).toString());
        txtdiachi.setText(tblNhanVien.getValueAt(row1, 3).toString());
        String gioiTinh = tblNhanVien.getValueAt(row1, 4).toString();
        if (gioiTinh.equals("Nam")) {
            jRadioButton1.setSelected(true);
        } else {
            jRadioButton2.setSelected(true);
        }
        txtLuong.setText(tblNhanVien.getValueAt(row1, 5).toString());
        txtNgayNhap.setText(tblNhanVien.getValueAt(row1, 6).toString());

//        index = tblNhanVien.getSelectedRow();
//        fillNhanVienLenFrom(index);
//        lblBangGhi.setText(layThongTinBangGhi());
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnFistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFistActionPerformed
        // TODO add your handling code here:
//        fistNV();
        index = 0;
        DisplayNV(index);
    }//GEN-LAST:event_btnFistActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
//        preNV();
        if (index == 0) {
            return;
        }
        index--;
        DisplayNV(index);

    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
//        nextNV();
        if (index == listNVS.size() - 1) {
            return;
        }
        index++;
        DisplayNV(index);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
//        lastNV();
        index = listNVS.size() - 1;
        DisplayNV(index);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
//        clearFrom();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        insertNV();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeteleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeteleActionPerformed
        // TODO add your handling code here:
        deleteNV();
    }//GEN-LAST:event_btnDeteleActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
//        readFive();
        timKiemNV();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
//        savaFive();
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        top3();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        //        if (timTheoMa(txtMaNV.getText()) == null) {
        //            JOptionPane.showMessageDialog(this, "Khong Tim Thay Nhan Vien");
        //        } else {
        //            fillNhanVienLenFrom(timTheoMa(txtMaNV.getText()));
        //        }
        updateNV();
    }//GEN-LAST:event_btnFindActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        index = cars.size() - 1;
        Display(index);        // TODO add your handling code here:

    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtTenHKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHKActionPerformed

    private void txtSĐTHKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSĐTHKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSĐTHKActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void btnSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSXActionPerformed
        // TODO add your handling code here:
//        SapXepTheogia();
    }//GEN-LAST:event_btnSXActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        int row2 = tblKH.getSelectedRow();
        txtMaKH.setText(tblKH.getValueAt(row2, 0).toString());
        txtTenHK.setText(tblKH.getValueAt(row2, 1).toString());
        String gioiTinh = tblKH.getValueAt(row2, 2).toString();
        System.out.println(gioiTinh);
        if (gioiTinh.equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        txtSĐTHK.setText(tblNhanVien.getValueAt(row2, 3).toString());
        txtĐiaChiHK.setText(tblKH.getValueAt(row2, 3).toString());
    }//GEN-LAST:event_tblKHMouseClicked

    private void txtMaDhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDhActionPerformed

    private void txtNgbanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgbanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgbanActionPerformed

    private void txtMaXEdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaXEdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaXEdActionPerformed

    private void txtMaKHdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHdActionPerformed

    private void txtMaNVdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVdActionPerformed

    private void txtĐơnGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtĐơnGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtĐơnGActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetele;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFist;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNewKH;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSX;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveKH;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnUpdateKH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jblTenSR;
    private javax.swing.JButton jbnDelete;
    private javax.swing.JButton jbnNew;
    private javax.swing.JButton jbnSave;
    private javax.swing.JButton jbnUpdate;
    private javax.swing.JPanel pnDonHang;
    private javax.swing.JPanel pnEmployee;
    private javax.swing.JPanel pnHome;
    private javax.swing.JPanel pnKhachhang;
    private javax.swing.JPanel pnMain;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnParen;
    private javax.swing.JPanel pnQLXe;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblBan;
    private javax.swing.JTable tblDH;
    private javax.swing.JTable tblKH;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JLabel txtFormQLxe;
    private javax.swing.JLabel txtFormQLxe1;
    private javax.swing.JLabel txtFormQLxe2;
    private javax.swing.JLabel txtFormQLxe3;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLoaiXe;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaDh;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaKHd;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaNVd;
    private javax.swing.JTextField txtMaXEd;
    private javax.swing.JTextField txtMaXe;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtNgayS;
    private javax.swing.JTextField txtNgban;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSĐTHK;
    private javax.swing.JTextField txtTenHK;
    private javax.swing.JTextField txtTenXe;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextArea txtĐiaChiHK;
    private javax.swing.JTextField txtĐơnG;
    // End of variables declaration//GEN-END:variables
}

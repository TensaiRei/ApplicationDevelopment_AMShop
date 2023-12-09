package dao;

import static dao.DAO.connection;
import data.UtilityLocalDateTime;
import entity.DonDatHang;
import entity.KhachHang;
import entity.NhanVien;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_DonDatHang extends DAO {
    public static boolean createDonDatHang(DonDatHang donDatHang){
        int n = 0;
        try {
            String sql = ""
                    + "INSERT INTO DonDatHang "
                    + "VALUES ("
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?"
                    + ")";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, donDatHang.getMaDonDatHang());
            prs.setString(2, donDatHang.getNhanVien().getMaNhanVien());
            prs.setString(3, donDatHang.getKhachHang().getMaKhachHang());
            prs.setTimestamp(4, UtilityLocalDateTime.fromLocalDateTime(donDatHang.getThoiGianTao()));
            prs.setBoolean(5, donDatHang.isTrangThaiThanhToan());
                    
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static boolean updateDonDatHang(DonDatHang donDatHang){
        int n = 0;
        try {
            String sql = ""
                    + "UPDATE DonDatHang "
                    + "SET "
                    + "MaNhanVien = ?, "
                    + "MaKhachHang = ?, "
                    + "ThoiGianTao = ?, "
                    + "TrangThaiThanhToan = ? "
                    + "WHERE MaDonDatHang = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(5, donDatHang.getMaDonDatHang());
            prs.setString(1, donDatHang.getNhanVien().getMaNhanVien());
            prs.setString(2, donDatHang.getKhachHang().getMaKhachHang());
            prs.setTimestamp(3, UtilityLocalDateTime.fromLocalDateTime(donDatHang.getThoiGianTao()));
            prs.setBoolean(4, donDatHang.isTrangThaiThanhToan());
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static boolean deleteDonDatHangTheoMaDonDatHang(String maDonDatHang){
        int n = 0;
        try {
            
            String sql = ""
                    + "DELETE FROM DonDatHang "
                    + "WHERE MaDonDatHang = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, maDonDatHang);
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static ArrayList<DonDatHang> getAllDonDatHang(){
        ArrayList<DonDatHang> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DonDatHang";
            ResultSet rs = getResultSetFromStatement(sql);
            while(rs.next()){
                String maDonDatHang = rs.getString(1);
                String maNhanVien = rs.getString(2);
                String maKhachHang = rs.getString(3);
                LocalDateTime thoiGianTao = UtilityLocalDateTime.toLocalDateTime(rs.getTimestamp(4));
                boolean trangThaiThanhToan = rs.getBoolean(5);
                
                NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
                KhachHang khachHang = DAO_KhachHang.getKhachHangTheoMaKhachHang(maKhachHang);
                
                DonDatHang donDatHang = new DonDatHang(maDonDatHang, nhanVien, khachHang, thoiGianTao, trangThaiThanhToan);
                
                list.add(donDatHang);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public static DonDatHang getDonDatHangTheoMaDonDatHang(String maDonDatHang){
        try {
            String sql = ""
                    + "SELECT * "
                    + "FROM DonDatHang "
                    + "WHERE MaDonDatHang = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, maDonDatHang);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                String maNhanVien = rs.getString(2);
                String maKhachHang = rs.getString(3);
                LocalDateTime thoiGianTao = UtilityLocalDateTime.toLocalDateTime(rs.getTimestamp(4));
                boolean trangThaiThanhToan = rs.getBoolean(5);
                
                NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
                KhachHang khachHang = DAO_KhachHang.getKhachHangTheoMaKhachHang(maKhachHang);
                
                DonDatHang donDatHang = new DonDatHang(maDonDatHang, nhanVien, khachHang, thoiGianTao, trangThaiThanhToan);
                
                return donDatHang;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    public static String getMaDonDatHangCuoi(String prefix){
        String searchPrefix = prefix + "%";
        try {
            String sql = ""
                    + "SELECT * "
                    + "FROM DonDatHang "
                    + "WHERE MaDonDatHang LIKE ? "
                    + "ORDER BY MaDonDatHang DESC";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, searchPrefix);
            ResultSet rs = prs.executeQuery();
            if(rs.next()){
                String maDonDatHangCuoi = rs.getString(1);
                return maDonDatHangCuoi;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}

package dao;

import entity.KhachHang;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_KhachHang extends DAO {
    public static boolean createKhachHang(KhachHang khachHang){
        int n = 0;
        try {
            String sql = ""
                    + "INSERT INTO KhachHang "
                    + "VALUES ("
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?"
                    + ")";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, khachHang.getMaKhachHang());
            prs.setString(2, khachHang.getHoTen());
            prs.setString(3, khachHang.getSoDienThoai());
            prs.setString(4, khachHang.getDiaChi());
            prs.setString(5, khachHang.getNhomKhachHang());
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static boolean updateKhachHang(KhachHang khachHang){
        int n = 0;
        try {
            String sql = ""
                    + "UPDATE KhachHang "
                    + "SET "
                    + "HoTen = ?, "
                    + "SoDienThoai = ?, "
                    + "DiaChi = ?, "
                    + "NhomKhachHang = ? "
                    + "WHERE MaKhachHang = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(5, khachHang.getMaKhachHang());
            prs.setString(1, khachHang.getHoTen());
            prs.setString(2, khachHang.getSoDienThoai());
            prs.setString(3, khachHang.getDiaChi());
            prs.setString(4, khachHang.getNhomKhachHang());
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static ArrayList<KhachHang> getAllKhachHang(){
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KhachHang";
            ResultSet rs = getResultSetFromStatement(sql);
            while(rs.next()){
                String maKhachHang = rs.getString(1);
                String hoTen = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String diaChi = rs.getString(4);
                String nhomKhachHang = rs.getString(5);
                
                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, diaChi, nhomKhachHang);
                
                list.add(khachHang);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public static KhachHang getKhachHangTheoMaKhachHang(String maKhachHang){
        try {
            String sql = ""
                    + "SELECT * "
                    + "FROM KhachHang "
                    + "WHERE MaKhachHang = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, maKhachHang);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                String hoTen = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String diaChi = rs.getString(4);
                String nhomKhachHang = rs.getString(5);
                
                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, diaChi, nhomKhachHang);
                
                return khachHang;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    public static KhachHang getKhachHangTheoSoDienThoai(String soDienThoai){
        try {
            String sql = ""
                    + "SELECT * "
                    + "FROM KhachHang "
                    + "WHERE MaKhachHang = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, soDienThoai);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                String maKhachHang = rs.getString(1);
                String hoTen = rs.getString(2);
                String diaChi = rs.getString(4);
                String nhomKhachHang = rs.getString(5);
                
                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, diaChi, nhomKhachHang);
                
                return khachHang;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    public static String getMaKhachHangCuoi(String prefix){
        String searchPrefix = prefix + "%";
        try {
            String sql = ""
                    + "SELECT * "
                    + "FROM KhachHang "
                    + "WHERE MaKhachHang LIKE ? "
                    + "ORDER BY MaKhachHang DESC";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, searchPrefix);
            ResultSet rs = prs.executeQuery();
            if(rs.next()){
                String maKhachHang = rs.getString(1);
                return maKhachHang;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.An;

/**
 *
 * @author QuocAn
 */
import Helper.jdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Ban;
import model.KhuVuc;

public class KhuVucDAO_An {

    //code hướng mới
    //đọc 1 nhân viên từ bản ghi
    //load combob= ban new
    public Ban readFromBanResultSet(ResultSet rs) throws SQLException {
        Ban modeBan = new Ban();
        modeBan.setMaBan(rs.getInt(1));
        modeBan.setTenBan(rs.getString(2));
        modeBan.setMaKhuVuc(rs.getInt(3));

        return modeBan;
    }

    //láy list danh sách bàn
    public List<Ban> selectBan(String sql, Object... args) {
        List<Ban> ban = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ban.add(readFromBanResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();//đóng kết nối
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return ban;
    }

    //truy vấn bàn
    public List<Ban> selectListBan() {
        String sql = "select * from Ban\n"
                + "where trangThaiHD = 1";
        return selectBan(sql);
    }

    //thêm bàn
    public void insertBan(String tenBan, Integer maKhuVuc, Integer trangThaiHD) {
        String sql = "INSERT INTO Ban(Tenban,Makhuvuc,trangthaingoi,trangthaihd) VALUES (?,?,'0',?)";
        jdbcHelper.executeUpdate(sql, tenBan, maKhuVuc, trangThaiHD);
    }

    // xoá bàn
    public void deleteBan(String tenBan, Integer maKhuVuc, Integer trangThaiHD) {
        String sql = "update Ban\n"
                + "set trangThaiHD = ?\n"
                + "where TenBan = ? and MaKhuVuc = ? and trangThaiNgoi = 0";
        jdbcHelper.executeUpdate(sql, trangThaiHD, tenBan, maKhuVuc);
    }

    //load combo khu vực mới
    //đọc khu vực từ bản ghi
    public KhuVuc readComBoKhuVucFromResultSet(ResultSet rs) throws SQLException {
        KhuVuc mode = new KhuVuc();
        mode.setMaKhuVuc(rs.getInt(1));
        mode.setTenKhuVuc(rs.getString(2));
        mode.setTrangThai(rs.getBoolean(3));

        return mode;
    }

    //lấy list danh sách khu vực
    public List<KhuVuc> selectKhuVuc(String sql, Object... args) {
        List<KhuVuc> khuVuc = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    khuVuc.add(readComBoKhuVucFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return khuVuc;
    }

    //truy vấn danh sách khu vưc
    public List<KhuVuc> selectListKhuVuc() {
        String sql = "select * from KhuVuc\n"
                + "where TrangThaiHD = 1";
        return selectKhuVuc(sql);
    }

    //load combo khu vực
    public void loadDataToComBoKhuVuc(DefaultComboBoxModel cbx) {
        List<KhuVuc> khuVuc = selectListKhuVuc();
        cbx.removeAllElements();
        for (int i = 0; i < khuVuc.size(); i++) {
            KhuVuc kv = khuVuc.get(i);
            cbx.addElement(kv);
        }
    }

    //insert khu vực
    public void insertKV(String tenkhuvuc, Integer trangThaiHD) {
        String sql = "INSERT INTO KHUVUC (TenKHUVUC,TrangThaiHD) VALUES (?,?)";

        jdbcHelper.executeUpdate(sql, tenkhuvuc, trangThaiHD);
    }

    //delete khu vực
    public void delete(String tenkhuvuc, Integer trangThaiHD) {
        String sql = "update khuvuc\n"
                + "set trangThaiHD = ?\n"
                + "where TenKhuVuc = ?";
        jdbcHelper.executeUpdate(sql, trangThaiHD, tenkhuvuc);
    }

    public List<KhuVuc> check(int a,String b) {
        String sql = "select * from Ban\n"
                + "  where MaKhuVuc=? and TenBan=?";
        return selectKhuVuc(sql,a,b);
    }
    
    //tìm mã bàn
    public Ban getBan(String tenBan, Integer MakhuVuc) {
        String sql = "select * from ban\n"
                + "where TenBan = ? and MaKhuVuc = ?";
        List<Ban> ban = selectBan(sql, tenBan, MakhuVuc);
        return ban.size() > 0 ? ban.get(0) : null;
    }
     public Ban getBanDaxoa(String tenBan, Integer MakhuVuc) {
        String sql = "select * from ban\n"
                + "where TenBan = ? and MaKhuVuc = ? and trangthaihd='0'";
        List<Ban> ban = selectBan(sql, tenBan, MakhuVuc);
        return ban.size() > 0 ? ban.get(0) : null;
    }
    
    
    public void updateTrangThaiHD(String tenBan, Integer maKhuVuc, Integer trangThaiHD) {
        String sql = "update Ban\n"
                + "set trangThaiHD = ?\n"
                + "where TenBan = ? and MaKhuVuc = ?";
        jdbcHelper.executeUpdate(sql, trangThaiHD, tenBan, maKhuVuc);
    }
}

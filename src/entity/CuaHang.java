package entity;

public class CuaHang {
    private String maCuaHang;
    private String tenCuaHang;
    private String soDienThoai;
    private String diaChi;

    public String getMaCuaHang() {
        return maCuaHang;
    }

    public void setMaCuaHang(String maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public CuaHang() {
    }

    public CuaHang(String maCuaHang, String tenCuaHang, String soDienThoai, String diaChi) {
        this.maCuaHang = maCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "CuaHang{" + "maCuaHang=" + maCuaHang + ", tenCuaHang=" + tenCuaHang + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + '}';
    }
}
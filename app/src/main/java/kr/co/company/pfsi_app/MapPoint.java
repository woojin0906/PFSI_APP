package kr.co.company.pfsi_app;

public class MapPoint {

    private String name;
    private String phone;
    private String addr;
    private double latitude;
    private double longitude;

    public MapPoint() {
        super();
    }

    public MapPoint(String name, String phone, String addr, double latitude, double longitude) {
        this.name = name;
        this.phone = phone;
        this.addr = addr;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

package kr.co.company.pfsi_app;
// 장애지원정보 클래스 (2023-05-14 우진)
public class SupportInfoData {

    private String city;
    private String group;
    private String peopleState;
    private String peopleStateInfo;
    private String category;
    private String categoryInfo;
    private String programTitle;
    private String time;
    private String money;
    private String address;
    private String phone;
    private String programContent;
    private String latitude;
    private String longitude;

    public SupportInfoData(String city, String group, String peopleState, String peopleStateInfo, String category, String categoryInfo, String programTitle, String time, String money, String address, String phone, String programContent, String latitude, String longitude) {
        this.city = city;
        this.group = group;
        this.peopleState = peopleState;
        this.peopleStateInfo = peopleStateInfo;
        this.category = category;
        this.categoryInfo = categoryInfo;
        this.programTitle = programTitle;
        this.time = time;
        this.money = money;
        this.address = address;
        this.phone = phone;
        this.programContent = programContent;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPeopleState() {
        return peopleState;
    }

    public void setPeopleState(String peopleState) {
        this.peopleState = peopleState;
    }

    public String getPeopleStateInfo() {
        return peopleStateInfo;
    }

    public void setPeopleStateInfo(String peopleStateInfo) {
        this.peopleStateInfo = peopleStateInfo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProgramContent() {
        return programContent;
    }

    public void setProgramContent(String programContent) {
        this.programContent = programContent;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}

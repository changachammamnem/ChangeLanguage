package com.tuyetanh.model;
//làm thêm sản phẩm
public class Employee
{
    private int id;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private boolean saveinfor;
    //khi thiết kế cơ sở dữ liệu có thuộc tính nào thì thiết kế cho đủ;
    //đảm bao tính đóng gói, ở bên ngoài không được can thiệp,
    // muốn thay đổi dữ liệu thông qua getter (xem) và setter (chỉnh sửa)
    public Employee()
    {
    }
    // conjector không có đối số

    public Employee(int id, String name, String email, String phone, String username, String password, boolean saveinfor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.saveinfor = saveinfor;
    }

    //conjector có đầy đủ đối số
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSaveinfor() {
        return saveinfor;
    }

    public void setSaveinfor(boolean saveinfor) {
        this.saveinfor = saveinfor;
    }
    //getter setter (ctrl A)
}

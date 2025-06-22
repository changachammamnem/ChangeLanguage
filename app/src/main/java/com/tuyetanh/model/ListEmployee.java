package com.tuyetanh.model;

//gỉa lập dữ liệu bằng cách tạo danh sách thng tin cho file Employee.java
import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;


    public ListEmployee()
    {
        employees=new ArrayList<>();
    }
//new: câp phát ô nhớ// conjector: tạo thuộc tính cho đối tượng
    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees)
    {
        this.employees = employees;
    }

    public void gen_dataset() {
        Employee e1=new Employee();
        e1.setId(1);
        e1.setName("john");
        e1.setEmail("john.email.com");
        e1.setPhone("003738747");
        e1.setUsername("u");
        e1.setPassword("1");
        employees.add(e1);


        Employee e2=new Employee();
        e2.setId(2);
        e2.setName("alan");
        e2.setEmail("alan.email.com");
        e2.setPhone("035628637");
        e2.setUsername("user2");
        e2.setPassword("123344");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(3);
        e3.setName("walker");
        e3.setEmail("walker.email.com");
        e3.setPhone("03563784");
        e3.setUsername("user3");
        e3.setPassword("6437463");
        employees.add(e3);
    }
}

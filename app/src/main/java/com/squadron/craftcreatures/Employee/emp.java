package com.squadron.craftcreatures.Employee;

public class emp{
    private	int	id;
    private	String name;
    private	String phno;
    private	String jobtitle;
    private	String email;
    private	String salary;



    public emp(String name, String jobtitle, String salary ,String phno,String email) {
        this.name = name;
        this.phno = phno;
        this.jobtitle = jobtitle;
        this.salary = salary;
        this.email = email;
    }

    public emp(int id, String name, String jobtitle, String salary ,String phno,String email) {
        this.id = id;
        this.name = name;
        this.phno = phno;
        this.jobtitle = jobtitle;
        this.email = email;
        this.salary = salary;
    }

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


    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }


    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }


    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


package com.stewen.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userdev")
public class DevModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@javax.persistence.Column(name = "name")
    private String name;
    private int age;
    private Date birthday;
    private float salary;
    private String email;
    
    Locale loc = new Locale("pt","BR");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formatter.parse(birthday);
		
		this.birthday = dataFormatada;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		String numb = salary.replace(".", "");
		numb = numb.replace(",", "");
		float new_numb = Float.parseFloat(numb);
		this.salary = new_numb;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
	

    

}

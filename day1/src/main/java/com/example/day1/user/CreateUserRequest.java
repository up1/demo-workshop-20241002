package com.example.day1.user;

public class CreateUserRequest{
	private String fname;
	private String lname;
	private int age;

	public void setFname(String fname){
		this.fname = fname;
	}

	public String getFname(){
		return fname;
	}

	public void setLname(String lname){
		this.lname = lname;
	}

	public String getLname(){
		return lname;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}

	@Override
	public String toString() {
		return "CreateUserRequest{" +
				"fname='" + fname + '\'' +
				", lname='" + lname + '\'' +
				", age=" + age +
				'}';
	}
}

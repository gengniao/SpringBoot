package com.zhiyue.mybatis.pojo;

public class Student {
	
	
	private Long stuid;
	private String stuname;
	private String stusex;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Long stuid, String stuname, String stusex) {
		super();
		this.stuid = stuid;
		this.stuname = stuname;
		this.stusex = stusex;
	}
	public Long getStuid() {
		return stuid;
	}
	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getStusex() {
		return stusex;
	}
	public void setStusex(String stusex) {
		this.stusex = stusex;
	}
	@Override
	public String toString() {
		return "Student [stuid=" + stuid + ", stuname=" + stuname + ", stusex=" + stusex + "]";
	}
	
	
}

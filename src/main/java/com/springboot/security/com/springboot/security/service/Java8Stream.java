package com.springboot.security.com.springboot.security.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8Stream {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<Employee>() {
			{
				add(new Employee(100L, "Saurabh Gandhi", new BigDecimal(30000), "Singapore"));
				add(new Employee(200L, "Kamal Padhi", new BigDecimal(20000), "India"));
				add(new Employee(300L, "Amitava Mitra", new BigDecimal(10000), "India"));
				add(new Employee(300L, "Amil Paul", new BigDecimal(25000), "India"));
			}
		};
// write the code to find the total salary of all employees where country=India
		BigDecimal bigDecimal = new BigDecimal(0);
		List<BigDecimal> collect = employees.stream().filter(e -> "India".equals(e.getCountry()))
				.map(e -> e.getSalary()).collect(Collectors.toList());

		collect.forEach(b -> bigDecimal.add(b));
		System.out.println(bigDecimal);
	}

	public static class Employee {
		Long empId;
		String empName;
		BigDecimal salary;
		String country;

		public Employee(Long empId, String empName, BigDecimal salary, String country) {
			super();
			this.empId = empId;
			this.empName = empName;
			this.salary = salary;
			this.country = country;
		}

		public Long getEmpId() {
			return empId;
		}

		public void setEmpId(Long empId) {
			this.empId = empId;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public BigDecimal getSalary() {
			return salary;
		}

		public void setSalary(BigDecimal salary) {
			this.salary = salary;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
	}
}
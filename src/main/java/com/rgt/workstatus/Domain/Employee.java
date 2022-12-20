package com.rgt.workstatus.Domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private Integer empId;
	private String emailId;
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String mobilenumber;
	private String jobtitle;
	private Boolean acitve;

	private List<DepartmentDomain> departmentEntity;
	private List<Roles> roles;

}

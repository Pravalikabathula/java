package com.rgt.workstatus.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
	private Integer roleId;
	private String roleName;
	private String empById;
	private boolean status;

}

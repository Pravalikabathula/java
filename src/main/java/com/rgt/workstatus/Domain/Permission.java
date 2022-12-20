package com.rgt.workstatus.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

	private Integer permissionId;

	// @NotEmpty
	// @Size(min = 2, message = "title name should have at least 2 characters")
	private String title;

	// @NotEmpty
	// @Size(min = 2, message = "Type value should have at least 2 characters")
	private String type;

	private String description;
	private Boolean status = true;
	private String createdBy = "Ram Shukla";

}

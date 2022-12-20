package com.rgt.workstatus.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holidays {

	private Integer holidayId;
	private String holidayName;
	private Boolean status;

}

package com.rgt.workstatus.Entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "HOLIDAYS")
public class Holidays {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HOLIDAY_ID")
	private Integer holidayId;
	@Column(name = "HOLIDAY_NAME ")
	private String holidayName;
	@Column(name = "STATUS")
	private Boolean status;
	@Column(name = "HOLIDAY_DATE")
	private Date holidayDate;
	public Integer getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
	@Override
	public String toString() {
		return "Holidays [holidayId=" + holidayId + ", holidayName=" + holidayName + ", status=" + status
				+ ", holidayDate=" + holidayDate + ", getHolidayId()=" + getHolidayId() + ", getHolidayName()="
				+ getHolidayName() + ", getStatus()=" + getStatus() + ", getHolidayDate()=" + getHolidayDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
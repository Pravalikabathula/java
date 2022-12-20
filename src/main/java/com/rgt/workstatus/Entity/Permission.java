package com.rgt.workstatus.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name = "PERMISSION")
public class Permission  {

	//private static final long serialVersionUID = 1L;

	//protected final static String TABLE_NAME = "PERMISSION";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERMISSION_ID", unique = true, nullable = false)
	private Integer permissionId;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "STATUS")
	private Boolean status;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE ", insertable = false)
	private Date upadatedDate;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpadatedDate() {
		return upadatedDate;
	}

	public void setUpadatedDate(Date upadatedDate) {
		this.upadatedDate = upadatedDate;
	}

	public Permission(Integer permissionId, String title, String type, Boolean status, String description,
			Date createdDate, Date upadatedDate) {
		super();
		this.permissionId = permissionId;
		this.title = title;
		this.type = type;
		this.status = status;
		this.description = description;
		this.createdDate = createdDate;
		this.upadatedDate = upadatedDate;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
}

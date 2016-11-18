package com.shr.model;

import com.constant.DocumentsType;
import com.constant.GenderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.utils.RegexUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @description Guser 
 * @author <a href="mailto:ou8zz@sina.com">OLE</a>
 * @date 2016/05/29
 * @version 1.0
 */
public class Guser implements Serializable {
	private static final long serialVersionUID = -9137179245056475448L;
	
	private Integer id;
	private String jobNo;
	private String ename;
	private String pwd;
	private String cname;
	private String email;
	private GenderType gender;  			//性别
	private DocumentsType documents; 		//证件类型
	private String idcard; 					//身份证
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date birthday; 					// e.g. 出生年月1987-08-23
	private String phone; 					//固话
	private String telExt; 					//分机号
	private Boolean gactive;
	private Boolean locked;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date entryDate;  				//入职日期
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date exitDate;  				//离职日期
	private String cdepartment;
	private String department;
	private Integer dptId;
	private Integer[] dptIds;
	private String cposition;
	private String position;
	private Integer pstId;
	private String crole;
	private String role;
	private Integer roleId;

    // login setRememberMe default false
    private Boolean rememberMe = false;

	public Guser() {
	}
	
	public Guser(Integer id) {
		this.id = id;
	}
	
	public Guser(String ename) {
		this.ename = ename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelExt() {
		return telExt;
	}

	public void setTelExt(String telExt) {
		this.telExt = telExt;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public String getCdepartment() {
		return cdepartment;
	}

	public void setCdepartment(String cdepartment) {
		this.cdepartment = cdepartment;
	}
	
	public Integer getDptId() {
		return dptId;
	}

	public void setDptId(Integer dptId) {
		this.dptId = dptId;
	}

	public Integer getPstId() {
		return pstId;
	}

	public void setPstId(Integer pstId) {
		this.pstId = pstId;
	}

	public String getCposition() {
		return cposition;
	}

	public void setCposition(String cposition) {
		this.cposition = cposition;
	}

	public GenderType getGender() {
		return gender;
	}
	
	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public DocumentsType getDocuments() {
		return documents;
	}
	
	public String getDocumentsDes() {
		return RegexUtil.notEmpty(documents) ? documents.getDes():"";
	}

	public void setDocuments(DocumentsType documents) {
		this.documents = documents;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getLocked() {
		return locked;
	}
	
	public String getCrole() {
		return crole;
	}

	public void setCrole(String crole) {
		this.crole = crole;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Boolean getGactive() {
		return gactive;
	}

	public void setGactive(Boolean gactive) {
		this.gactive = gactive;
	}

	public Integer[] getDptIds() {
		return dptIds;
	}

	public void setDptIds(Integer[] dptIds) {
		this.dptIds = dptIds;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((cdepartment == null) ? 0 : cdepartment.hashCode());
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((cposition == null) ? 0 : cposition.hashCode());
		result = prime * result + ((crole == null) ? 0 : crole.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((documents == null) ? 0 : documents.hashCode());
		result = prime * result + ((dptId == null) ? 0 : dptId.hashCode());
		result = prime * result + Arrays.hashCode(dptIds);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
		result = prime * result + ((exitDate == null) ? 0 : exitDate.hashCode());
		result = prime * result + ((gactive == null) ? 0 : gactive.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idcard == null) ? 0 : idcard.hashCode());
		result = prime * result + ((jobNo == null) ? 0 : jobNo.hashCode());
		result = prime * result + ((locked == null) ? 0 : locked.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((pstId == null) ? 0 : pstId.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((telExt == null) ? 0 : telExt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guser other = (Guser) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (cdepartment == null) {
			if (other.cdepartment != null)
				return false;
		} else if (!cdepartment.equals(other.cdepartment))
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (cposition == null) {
			if (other.cposition != null)
				return false;
		} else if (!cposition.equals(other.cposition))
			return false;
		if (crole == null) {
			if (other.crole != null)
				return false;
		} else if (!crole.equals(other.crole))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (documents != other.documents)
			return false;
		if (dptId == null) {
			if (other.dptId != null)
				return false;
		} else if (!dptId.equals(other.dptId))
			return false;
		if (!Arrays.equals(dptIds, other.dptIds))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (ename == null) {
			if (other.ename != null)
				return false;
		} else if (!ename.equals(other.ename))
			return false;
		if (entryDate == null) {
			if (other.entryDate != null)
				return false;
		} else if (!entryDate.equals(other.entryDate))
			return false;
		if (exitDate == null) {
			if (other.exitDate != null)
				return false;
		} else if (!exitDate.equals(other.exitDate))
			return false;
		if (gactive == null) {
			if (other.gactive != null)
				return false;
		} else if (!gactive.equals(other.gactive))
			return false;
		if (gender != other.gender)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idcard == null) {
			if (other.idcard != null)
				return false;
		} else if (!idcard.equals(other.idcard))
			return false;
		if (jobNo == null) {
			if (other.jobNo != null)
				return false;
		} else if (!jobNo.equals(other.jobNo))
			return false;
		if (locked == null) {
			if (other.locked != null)
				return false;
		} else if (!locked.equals(other.locked))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (pstId == null) {
			if (other.pstId != null)
				return false;
		} else if (!pstId.equals(other.pstId))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (telExt == null) {
			if (other.telExt != null)
				return false;
		} else if (!telExt.equals(other.telExt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(this.id);
		sb.append(",").append(this.jobNo);
		sb.append(",").append(this.ename);
		sb.append(",").append(this.cname);
		sb.append(",").append(this.pwd);
		sb.append(",").append(this.email);
		sb.append("}");
		return sb.toString();
	}
}
package com.shr.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.constant.GroupType;

/**
 * @description Ugroup 
 * @author <a href="mailto:ou8zz@sina.com">OLE</a>
 * @date 2016/05/29
 * @version 1.0
 */
public class Ugroup implements Serializable {
	private static final long serialVersionUID = -8804809309718930582L;

	private Integer id;
	private String ename;
	private String cname;
	private GroupType gtype;
	
	
	
	// VO
	private Integer[] zids;
	private List<String> znames;
	
	public Ugroup() {}
	
	public Ugroup(Integer id) {
		this.id = id;
	}
	
	public Ugroup(String ename) {
		this.ename = ename;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public GroupType getGtype() {
		return gtype;
	}

	public void setGtype(GroupType gtype) {
		this.gtype = gtype;
	}


	public Integer[] getZids() {
		return zids;
	}

	public void setZids(Integer[] zids) {
		this.zids = zids;
	}

	public List<String> getZnames() {
		return znames;
	}

	public void setZnames(List<String> znames) {
		this.znames = znames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		result = prime * result + ((gtype == null) ? 0 : gtype.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(zids);
		result = prime * result + ((znames == null) ? 0 : znames.hashCode());
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
		Ugroup other = (Ugroup) obj;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (ename == null) {
			if (other.ename != null)
				return false;
		} else if (!ename.equals(other.ename))
			return false;
		if (gtype != other.gtype)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(zids, other.zids))
			return false;
		if (znames == null) {
			if (other.znames != null)
				return false;
		} else if (!znames.equals(other.znames))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ugroup [id=").append(id).append(", ename=").append(ename).append(", cname=").append(cname)
				.append(", gtype=").append(gtype).append(", zids=").append(Arrays.toString(zids)).append("]");
		return builder.toString();
	}

	
}

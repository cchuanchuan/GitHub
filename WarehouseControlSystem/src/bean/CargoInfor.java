package bean;

import java.io.Serializable;

public class CargoInfor implements Serializable{
	//±ê×¼POJOÀà
	private String cargoid;
	private String name;
	private String categroy;
	private String custid;
	private String storetime;
	public String getCargoid() {
		return cargoid;
	}
	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategroy() {
		return categroy;
	}
	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getStoretime() {
		return storetime;
	}
	public void setStoretime(String storetime) {
		this.storetime = storetime;
	}
	@Override
	public String toString() {
		return "CargoInfor [cargoid=" + cargoid + ", name=" + name + ", categroy=" + categroy + ", custid=" + custid
				+ ", storetime=" + storetime + "]";
	}
	public CargoInfor(String cargoid, String name, String categroy, String custid, String storetime) {
		super();
		this.cargoid = cargoid;
		this.name = name;
		this.categroy = categroy;
		this.custid = custid;
		this.storetime = storetime;
	}
	
	
	

}

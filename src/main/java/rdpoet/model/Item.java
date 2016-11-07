package rdpoet.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Model object representing an Item.
 * 
 * @author russell
 *
 */
public class Item implements Serializable {
	
	private static final long serialVersionUID = 7303670719781105747L;

	private Long id;
	private Long userId;
	private String desc;
	private String detail;
	private Date target;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getTarget() {
		return target;
	}
	public void setTarget(Date target) {
		this.target = target;
	}
	
	
}

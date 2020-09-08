package com.revature.models;

import java.sql.Timestamp;

public class ReimbursementDTO {
	
	public int reimb_id;
	public double reimb_amount;
	public String reimb_submitted;
	public String reimb_resolved;
	public String reimb_description;
	public String reimb_author;
	public String reimb_resolver;
	public String reimb_status_id;
	public String reimb_type_id;
	

	@Override
	public String toString() {
		return "ReimbursementDTO [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", reimb_description=" + reimb_description
				+ ", reimb_author=" + reimb_author + ", reimb_resolver=" + reimb_resolver + ", reimb_status_id="
				+ reimb_status_id + ", reimb_type_id=" + reimb_type_id + "]";
	}
	
	
	
	
	
	
	

}

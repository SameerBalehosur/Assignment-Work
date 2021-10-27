package com.te.gmailapplication.dto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_id;
	private String user_name;
	private String password;
	private String email;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="account")
	private List<Inbox> listofMails;

}

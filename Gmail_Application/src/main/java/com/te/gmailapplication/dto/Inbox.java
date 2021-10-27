package com.te.gmailapplication.dto;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Data
@Entity
public class Inbox implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int messege_id;
	private String messege;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private Account account;

}

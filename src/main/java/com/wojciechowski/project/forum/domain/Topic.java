package com.wojciechowski.project.forum.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude= {"section","answer","user"})
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Section section;
	
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private Set<Answer> answer;
	
	private String title;
	
	@Lob
	private String content;
	
	@CreationTimestamp
	private Date createTime;
	
	@UpdateTimestamp
	private Date updateTime;
	
	private Long views;
	
	private boolean closed;
	

	
	
}

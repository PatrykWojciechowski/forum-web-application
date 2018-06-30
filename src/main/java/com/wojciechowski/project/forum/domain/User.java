package com.wojciechowski.project.forum.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude= {"roles"})
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull(message="Username is required.")
	@Length(min=5, max =30, message="Your unsername should be between 5 and 30 characters.")
    private String username;
    @NotNull(message="Password is required.")
	@Length(min=5, max=80, message="Your password should be between 5 and 30 characters.")
    private String password;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}

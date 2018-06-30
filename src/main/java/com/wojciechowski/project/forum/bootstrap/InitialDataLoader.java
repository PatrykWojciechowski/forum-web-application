package com.wojciechowski.project.forum.bootstrap;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wojciechowski.project.forum.domain.Role;
import com.wojciechowski.project.forum.domain.User;
import com.wojciechowski.project.forum.repositories.RoleRepository;
import com.wojciechowski.project.forum.repositories.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener <ContextRefreshedEvent> {

	boolean alreadySetup = false;
	 
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
	
    @Autowired
	public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (alreadySetup)
            return;
		
		createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
        
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role userRole = roleRepository.findByName("ROLE_USER");
        User testUser = userRepository.findByUsername("admin");
        
        if (testUser == null) {
        testUser = new User();
        testUser.setUsername("admin");
        testUser.setPassword(passwordEncoder.encode("admin"));
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        roles.add(userRole);
        testUser.setRoles(roles);
        
        userRepository.save(testUser);
        }
        
        alreadySetup = true;
	}

	@Transactional
    private Role createRoleIfNotFound(String name) {
  
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
        return role;
    }
	
}

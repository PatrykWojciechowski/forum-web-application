package com.wojciechowski.project.forum.bootstrap;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wojciechowski.project.forum.domain.Answer;
import com.wojciechowski.project.forum.domain.Role;
import com.wojciechowski.project.forum.domain.Section;
import com.wojciechowski.project.forum.domain.Topic;
import com.wojciechowski.project.forum.domain.User;
import com.wojciechowski.project.forum.repositories.AnswerRepository;
import com.wojciechowski.project.forum.repositories.RoleRepository;
import com.wojciechowski.project.forum.repositories.SectionRepository;
import com.wojciechowski.project.forum.repositories.TopicRepository;
import com.wojciechowski.project.forum.repositories.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener <ContextRefreshedEvent> {

	boolean alreadySetup = false;
	 
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private SectionRepository sectionRepository;
    private TopicRepository topicRepository;
    private AnswerRepository answerRepository;
	
    @Autowired
	public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, SectionRepository sectionRepository, TopicRepository topicRepository,
			AnswerRepository answerRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.sectionRepository = sectionRepository;
		this.topicRepository = topicRepository;
		this.answerRepository = answerRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (alreadySetup)
            return;
		
		// Create users and roles
		
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
        
        User patrykUser = userRepository.findByUsername("Patryk");
        
        if (patrykUser == null) {
        	
        patrykUser = new User();
        patrykUser.setUsername("Patryk");
        patrykUser.setPassword(passwordEncoder.encode("patryk"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        patrykUser.setRoles(roles);
        
        userRepository.save(patrykUser);
        
        }
        
        User janekUser = userRepository.findByUsername("Janek23");
        
        if (janekUser == null) {
        	
        janekUser = new User();
        janekUser.setUsername("Janek23");
        janekUser.setPassword(passwordEncoder.encode("janek23"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        janekUser.setRoles(roles);
        
        userRepository.save(janekUser);
        
        }
        
        // Create Sections
        
        Section javaSection = new Section();
        javaSection.setName("Java");
        javaSection.setDescription("Java, Spring, Hibernate discussions");

        sectionRepository.save(javaSection);
        
        Section phpSection = new Section();
        phpSection.setName("PHP");
        phpSection.setDescription("PHP discussions");
        
        sectionRepository.save(phpSection);
        
        Section cSection = new Section();
        cSection.setName("C/C++");
        cSection.setDescription("C and C++ discussions");
        
        sectionRepository.save(cSection);
        
        Section pythonSection = new Section();
        pythonSection.setName("Python");
        pythonSection.setDescription("Python and Django discussions");
        
        sectionRepository.save(pythonSection);
        
        // Create topics
        
        Topic javaTopic = new Topic();
        
        javaTopic.setTitle("What exactly is Spring Framework for?");
        javaTopic.setContent("I hear a lot about Spring, people are saying all over the web"
        		+ " that Spring is a good framework for web development."
        		+ "What exactly is Spring Framework for? How can I use it for my"
        		+ " Web-Java application development? any examples?");
        javaTopic.setUser(patrykUser);
        javaTopic.setSection(javaSection);
        javaTopic.setClosed(false);
        javaTopic.setViews(0L);
        
        topicRepository.save(javaTopic);
        
        Topic javaTopic2 = new Topic();
        
        javaTopic2.setTitle("What in the world are Spring beans?");
        javaTopic2.setContent("I am yet to find a high-level definition of Spring beans that I can understand."
        		+ "I see them referenced often in Grails documentation and books, but I think that understanding"
        		+ " what they are would be beneficial. So what are Spring beans? How can they be used? Do they "
        		+ "have something to do with Dependency Injection?");
        javaTopic2.setUser(patrykUser);
        javaTopic2.setSection(javaSection);
        javaTopic2.setClosed(false);
        javaTopic2.setViews(0L);
        
        topicRepository.save(javaTopic2);
        
        Topic javaTopic3= new Topic();
        
        javaTopic3.setTitle("Spring MVC - How to get all request params in a map in Spring controller?");
        javaTopic3.setContent("How can I achieve this with Spring MVC?");
        javaTopic3.setUser(janekUser);
        javaTopic3.setSection(javaSection);
        javaTopic3.setClosed(false);
        javaTopic3.setViews(0L);
        
        topicRepository.save(javaTopic3);
        
        Topic javaTopic4 = new Topic();
        
        javaTopic4.setTitle("How can I enable spring security authentication?");
        javaTopic4.setContent("I'm using spring security 5 with spring boot 2.0.0\r\n"
        		+ "I want to make user authentication with permitAll but it does not work. I want to access \"localhost:9090/hello\" but always redirected to \"localhost:9090/login\".\r\n" 
        		+ "How can I make user authentication with spring security??");
        javaTopic4.setUser(janekUser);
        javaTopic4.setSection(javaSection);
        javaTopic4.setClosed(false);
        javaTopic4.setViews(0L);
        
        topicRepository.save(javaTopic4);
        
        // Create answers
        
        Answer answerJava = new Answer();
        answerJava.setTopic(javaTopic);
        answerJava.setContent("Old days, spring was a dependency injection frame work only like"
        		+ " (Guice, PicoContainer,...), but now a days it is a total solutions for "
        		+ "building you Enterprise Application.");
        answerJava.setUser(testUser);
        
        answerRepository.save(answerJava);
        
        Answer answerJavaTwo = new Answer();
        answerJavaTwo.setTopic(javaTopic);
        answerJavaTwo.setContent("Basically Spring is a framework for dependency-injection"
        		+ " which is a pattern that allows to build very decoupled systems.");
        answerJavaTwo.setUser(janekUser);
        
        answerRepository.save(answerJavaTwo);
        
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

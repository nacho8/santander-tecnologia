package santander.tecnologia.challenge.startUp;


import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

import santander.tecnologia.challenge.domain.MeetUp;
import santander.tecnologia.challenge.domain.MeetUpUsers;
import santander.tecnologia.challenge.domain.User;
import santander.tecnologia.challenge.persistence.MeetUpRepository;
import santander.tecnologia.challenge.persistence.MeetUpUsersRepository;
import santander.tecnologia.challenge.persistence.UserRepository;
import santander.tecnologia.challenge.service.user.UserService;

@Component
@ComponentScan(basePackages = { "org.baeldung.security" })
@EnableWebSecurity
public class UserStartUp {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MeetUpRepository meetUpRepository;
	
	@Autowired
	private MeetUpUsersRepository meetUpUsersRepository;
	
	@Autowired
	private UserService userService;
	
	private static final int INITIAL_DELAY =4000;
	
	
	
	@Scheduled(initialDelay = INITIAL_DELAY,fixedRate = 600000000)
	public void registerNewUser()  {
	    User user = new User("Leandro","Salomon","lsalomon");
	    user.setPassword(userService.encode("lsalomon1234"));
	    userRepository.save(user);
	    
	    User user1 = new User("Ignacio","Varela","ivarela");
	    user1.setPassword(userService.encode("ivarela1234"));
	    userRepository.save(user1);
	    
	    User user2 = new User("Pablo","Fernandez","pfernandez");
	    user2.setPassword(userService.encode("pfernandez1234"));
	    userRepository.save(user2);
	    
	    User user3 = new User("Santander","Tecnologia","adminSantander");
	    user3.setPassword(userService.encode("santander1234"));
	    userRepository.save(user3);
	    
	    User user4 = new User("Pedro","Lorenzo","plorenzo");
	    user4.setPassword(userService.encode("plorenzo1234"));
	    userRepository.save(user4);
	    
	    MeetUp meetUp = new MeetUp(LocalDate.of(2019, Month.FEBRUARY, 26),"AV CASEROS 2138");
	    meetUpRepository.save(meetUp);
	    MeetUp meetUp1 = new MeetUp(LocalDate.of(2019, Month.FEBRUARY, 27),"AV SAN JUAN 2138");
	    meetUpRepository.save(meetUp1);
	    MeetUp meetUp2 = new MeetUp(LocalDate.of(2019, Month.FEBRUARY, 25),"AV INDEPENDENCIA 2138");
	    meetUpRepository.save(meetUp2);
	    
	    MeetUpUsers meetUpUsers = new MeetUpUsers(meetUp,user,new Date(),false);
	    meetUpUsersRepository.save(meetUpUsers);
	    MeetUpUsers meetUpUsers1 = new MeetUpUsers(meetUp,user1,new Date(),false); 
	    meetUpUsersRepository.save(meetUpUsers1);
	    MeetUpUsers meetUpUsers2 = new MeetUpUsers(meetUp,user2,new Date(),false); 
	    meetUpUsersRepository.save(meetUpUsers2);
	    MeetUpUsers meetUpUsers3 = new MeetUpUsers(meetUp,user3,new Date(),false); 
	    meetUpUsersRepository.save(meetUpUsers3);
	    MeetUpUsers meetUpUsers4 = new MeetUpUsers(meetUp,user4,new Date(),false); 
	    meetUpUsersRepository.save(meetUpUsers4);
	    MeetUpUsers meetUpUsers5 = new MeetUpUsers(meetUp1,user1,new Date(),false);
	    meetUpUsersRepository.save(meetUpUsers5);
	    MeetUpUsers meetUpUsers6 = new MeetUpUsers(meetUp1,user3,new Date(),false); 
	    meetUpUsersRepository.save(meetUpUsers6);
	    MeetUpUsers meetUpUsers7 = new MeetUpUsers(meetUp2,user1,new Date(),false); 
	    meetUpUsersRepository.save(meetUpUsers7);
	    MeetUpUsers meetUpUsers8 = new MeetUpUsers(meetUp2,user4,new Date(),false); 
	    meetUpUsersRepository.save(meetUpUsers8);
	    MeetUpUsers meetUpUsers9 = new MeetUpUsers(meetUp2,user3,new Date(),false); 
	    meetUpUsersRepository.save(meetUpUsers9);
	    
	}

}

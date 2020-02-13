package santander.tecnologia.challenge.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Service;

import santander.tecnologia.challenge.domain.User;
import santander.tecnologia.challenge.exception.UserException;
import santander.tecnologia.challenge.persistence.UserRepository;

@Service
@ComponentScan(basePackages = { "org.baeldung.security" })
@EnableWebSecurity
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private String salt = null;
	
	
	public User getUser(String username, String password) throws Exception {
		
		User user = userRepository.findByUserName(username);
		if(user == null) {
			throw new UserException("El usuario no existe. Usuario recibido: " + username );
		}
		
		String passwordEncoded = encode(password);
		if(!user.getPassword().equals(passwordEncoded)) {
			throw new UserException("La password es incorrecta. Contraseña recibida " + password + " Contraseña codificada " + passwordEncoded);
		}
		return user;
	}
	
	
	public String encode(CharSequence rawPassword) {
		if(salt == null) {
			salt =  BCrypt.gensalt(BCryptVersion.$2A.getVersion(), 4);
		}
		return BCrypt.hashpw(rawPassword.toString(), salt);
	}
	
	 

}

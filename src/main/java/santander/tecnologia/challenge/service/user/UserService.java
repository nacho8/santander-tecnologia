package santander.tecnologia.challenge.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import santander.tecnologia.challenge.domain.User;
import santander.tecnologia.challenge.exception.UserException;
import santander.tecnologia.challenge.persistence.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private final static String  salt = "$2a$04$IwmFubEGW8Nw.majttflsO";
	
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
		return BCrypt.hashpw(rawPassword.toString(), salt);
	}
	

}

package santander.tecnologia.challenge.security.ws;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import santander.tecnologia.challenge.exception.UserException;
import santander.tecnologia.challenge.security.jwt.AccountCredentials;
import santander.tecnologia.challenge.service.user.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private static final String ERROR_MESSAGE_INTERNAL = "Error interno";
	
	private static final String ERROR_MESSAGE_NOT_EXITST_USER_OR_PASSWORD_INVALID = "El usuario o la contraseña son incorrectos";
	
	@Autowired
	private UserService userService;
	
	@PostMapping("logIn")
	@ApiOperation(value = "Metodo encargado de devolver el clima para una meetUp")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")})
	public ResponseEntity<AccountCredentials> login(@RequestBody AccountCredentials credentials) {
		AccountCredentials accountCredentials = new AccountCredentials();
		try {
			userService.getUser(credentials.getUsername(), credentials.getPassword());
			String token = getJWTToken(credentials.getUsername());
			accountCredentials.setUsername(credentials.getUsername());
			accountCredentials.setToken(token);		
			return new ResponseEntity<>(accountCredentials,HttpStatus.OK);
		}catch(UserException e) {
			LOGGER.error(ERROR_MESSAGE_NOT_EXITST_USER_OR_PASSWORD_INVALID, e);
			accountCredentials.setUsername(ERROR_MESSAGE_NOT_EXITST_USER_OR_PASSWORD_INVALID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accountCredentials);
		}catch(Exception e) {
			LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
			accountCredentials.setUsername(ERROR_MESSAGE_NOT_EXITST_USER_OR_PASSWORD_INVALID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accountCredentials);
		}
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities;
		
		if(username.contains("admin")) {
			grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		}else {
			 grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_USER");
		}
		
		
		String token = Jwts
				.builder()
				.setId("santanderTecnologiaJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}



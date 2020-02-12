package santander.tecnologia.challenge.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import santander.tecnologia.challenge.cache.TemperatureCache;
import santander.tecnologia.challenge.cache.TemperatureParametryCache;
import santander.tecnologia.challenge.domain.MeetUp;
import santander.tecnologia.challenge.domain.MeetUpUsers;
import santander.tecnologia.challenge.domain.TemperatureParametry;
import santander.tecnologia.challenge.domain.User;
import santander.tecnologia.challenge.exception.MeetUpException;
import santander.tecnologia.challenge.persistence.MeetUpRepository;
import santander.tecnologia.challenge.persistence.MeetUpUsersRepository;
import santander.tecnologia.challenge.persistence.UserRepository;
import santander.tecnologia.challenge.ws.response.MeetUpAddUserResponse;
import santander.tecnologia.challenge.ws.response.MeetUpConfirmAssistenceResponse;
import santander.tecnologia.challenge.ws.response.MeetUpCreateResponse;
import santander.tecnologia.challenge.ws.response.MeetUpObtainAmountBeerResponse;
import santander.tecnologia.challenge.ws.response.MeetUpObtainTemperatureResponse;
import santander.tecnologia.challenge.ws.response.Response;
import santander.tecnologia.challenge.ws.weather.response.WeatherDay;
import santander.tecnologia.challenge.ws.weather.response.WeatherResponse;

@Service
public class MeetUpService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MeetUpService.class);
	
	@Autowired
	private MeetUpRepository meetUpRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MeetUpUsersRepository meetUpUsersRepository;
	
	@Autowired
	private TemperatureParametryCache temperatureParametryCache;
	
	@Autowired
	private TemperatureCache temperatureCache;
	
	public MeetUpObtainAmountBeerResponse obtaintAmountBeer(Long meetUpId) throws Exception {
		MeetUpObtainAmountBeerResponse meetUpObtainAmountBeerResponse = new MeetUpObtainAmountBeerResponse();
		
		if(!meetUpRepository.findById(meetUpId).isPresent()) {
			throw new MeetUpException("No existe la meetUp");
		}
		
		MeetUp meetUp = meetUpRepository.findById(meetUpId).get();
		 if(meetUp == null) {
			 meetUpObtainAmountBeerResponse.setStatus("50001");
			 meetUpObtainAmountBeerResponse.setAmountBeer(0);
			 return meetUpObtainAmountBeerResponse;
		 }
		
		Double temperature = getTemperatureOfMeetUp(meetUp);
		if(temperature == null) {
			meetUpObtainAmountBeerResponse.setStatus("No se encontro la temperatura");
			return meetUpObtainAmountBeerResponse;
		}
		int value = calculateBeerByPerson(temperatureParametryCache.getParametry(),temperature, meetUp.getMeetUpUser().size());
		meetUpObtainAmountBeerResponse.setAmountBeer(value);
		meetUpObtainAmountBeerResponse.setStatus("200");
		return meetUpObtainAmountBeerResponse;
	}

	public int calculateBeerByPerson(List<TemperatureParametry> temperatureParametryList, Double temperature, int numberOfGuests) throws Exception {
		 Double beer = 0.0;
		 for(TemperatureParametry temperatureParametry : temperatureParametryList) {
			 if(temperatureParametry.getMaximumTemperature() != null) {
				 if(temperatureParametry.getMinimunTemperature() == null && temperatureParametry.getMaximumTemperature().compareTo(temperature) == 1) {
					 System.out.println("Compare " + 1);
					 beer = temperatureParametry.getAmountBeerByPerson();
					 break;
				 }
			 }
			 if(temperatureParametry.getMinimunTemperature() != null) {
				 if(temperatureParametry.getMaximumTemperature() == null && temperatureParametry.getMinimunTemperature().compareTo(temperature) == -1) {
					 System.out.println("Compare " + -1);	
					 beer = temperatureParametry.getAmountBeerByPerson();
					 break;
				 }
			 }
			 if(temperatureParametry.getMinimunTemperature() != null && temperatureParametry.getMaximumTemperature() != null) {
				 if(temperatureParametry.getMinimunTemperature().compareTo(temperature) >= 0 && temperatureParametry.getMaximumTemperature().compareTo(temperature) <= 0) {
					 System.out.println("Compare " + 0);	
					 beer = temperatureParametry.getAmountBeerByPerson();
					 break;
				 }
			 }
		 }
		 
		Double value = beer * numberOfGuests;
		return obtainBeerCrates(value);
	}
	
	//Uso Math.ceil() para redondear hacia arriba.
	private int obtainBeerCrates(Double value) {
		return (int) Math.ceil(value / 6);
	}
	
	private Double getTemperatureOfMeetUp(MeetUp meetUp) throws Exception {
		WeatherResponse weatherResponse = temperatureCache.getTemperature();
		Double temp = null;
		for(WeatherDay weatherDay: weatherResponse.getList()) {
			if(meetUp.getMeetUpDate().compareTo(weatherDay.getDate()) == 0) {
				temp = (double) weatherDay.getTemp().getMax();
			}
		}
		return temp;
	}
	
	public MeetUpObtainTemperatureResponse getTemperature(Long meetUpId) throws Exception {
		MeetUpObtainTemperatureResponse meetUpObtainTemperatureResponse = new MeetUpObtainTemperatureResponse();
		if(!meetUpRepository.findById(meetUpId).isPresent()) {
			throw new MeetUpException("No existe la meetUp");
		}
		
		MeetUp meetUp = meetUpRepository.findById(meetUpId).get();
		meetUpObtainTemperatureResponse.setTemperature(getTemperatureOfMeetUp(meetUp));
		return meetUpObtainTemperatureResponse;
	}
	
	public MeetUpAddUserResponse addUser(Long meetUpId, Long userId) throws Exception {
		MeetUpAddUserResponse meetUpAddUserResponse = new MeetUpAddUserResponse();
		
		if(!meetUpRepository.findById(meetUpId).isPresent()) {
			throw new MeetUpException("No existe la meetUp");
		}
		MeetUp meetUp = meetUpRepository.findById(meetUpId).get();
		if(!userRepository.findById(userId).isPresent()) {
			throw new MeetUpException("No existe el usuario");
		}
		 User user = userRepository.findById(userId).get();
		
		
		addUserToMeetUp(meetUpAddUserResponse, meetUp, user);
		return meetUpAddUserResponse;
		
	}
	
	public MeetUpCreateResponse createMeetUp(Long userId, String direction, LocalDate dateMeetUp)  throws Exception {
		MeetUpCreateResponse meetUpCreateResponse = new MeetUpCreateResponse();
		 
		if(!userRepository.findById(userId).isPresent()) {
			throw new MeetUpException("No existe el usuario");
		}
		 User user = userRepository.findById(userId).get();
		
		 MeetUp meetUp = new MeetUp();
		 meetUp.setDirection(direction);
		 meetUp.setMeetUpDate(dateMeetUp);
		 meetUpRepository.save(meetUp);
		 
		addUserToMeetUp(meetUpCreateResponse,meetUp,user);
		return meetUpCreateResponse;
	}
	
	public MeetUpConfirmAssistenceResponse confirmAssistence(Long userId, Long meetUpId) throws Exception {
		MeetUpConfirmAssistenceResponse meetUpConfirmAssistenceResponse = new MeetUpConfirmAssistenceResponse();
		
		if(!userRepository.findById(userId).isPresent()) {
			throw new MeetUpException("No existe el usuario");
		}
		 User user = userRepository.findById(userId).get();
		
		 for(MeetUpUsers meetUpUser:user.getMeetUpUser()) {
			 if(meetUpUser.getId().equals(meetUpId)) {
				 if(!meetUpUser.getUserMeetUpDate().before(new Date())) {
					 meetUpConfirmAssistenceResponse.setStatus("5002");
				 }else {
					 meetUpUser.setUserAttented(true);
					 meetUpUsersRepository.save(meetUpUser); 
				 }
			 }
		 }
		return meetUpConfirmAssistenceResponse;
	}

	private void addUserToMeetUp(Response meetUpResponse, MeetUp meetUp, User user) {
		MeetUpUsers meetUpUsers = new MeetUpUsers();
		meetUpUsers.setMeetUp(meetUp);
		meetUpUsers.setUser(user);
		meetUpUsers.setUserMeetUpDate(new Date());
		meetUpUsersRepository.save(meetUpUsers);
	}
}

package santander.tecnologia.challenge.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import santander.tecnologia.challenge.exception.MeetUpException;
import santander.tecnologia.challenge.service.MeetUpService;
import santander.tecnologia.challenge.ws.request.MeetUpCreateRequest;
import santander.tecnologia.challenge.ws.response.MeetUpAddUserResponse;
import santander.tecnologia.challenge.ws.response.MeetUpConfirmAssistenceResponse;
import santander.tecnologia.challenge.ws.response.MeetUpCreateResponse;
import santander.tecnologia.challenge.ws.response.MeetUpObtainAmountBeerResponse;
import santander.tecnologia.challenge.ws.response.MeetUpObtainTemperatureResponse;

@RestController
@RequestMapping("santander/meetUp")
public class MeetUpController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MeetUpController.class);
	
	private static final String ERROR_MESSAGE_INTERNAL = "Error interno";
	
	private static final String ERROR_MESSAGE_MEET_UP_OR_USER_NOT_EXIST = "No existe la meetUp/usuario";
	
	
	@Autowired
	private MeetUpService meetUpService;
	
	@GetMapping("amountBeer/{meetUpId}")
	@ApiOperation(value = "Metodo encargado de devolver la cantidad de cervezas para una meetUp")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")})
	public ResponseEntity<?> amountBeer(@PathVariable("meetUpId") long meetUpId) {
		try {
			MeetUpObtainAmountBeerResponse meetUpObtainAmountBeerResponse =meetUpService.obtaintAmountBeer(meetUpId);
			return new ResponseEntity<>(meetUpObtainAmountBeerResponse,HttpStatus.OK);
		}catch(MeetUpException e){
			LOGGER.error(ERROR_MESSAGE_MEET_UP_OR_USER_NOT_EXIST, e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch(Exception e) {
			LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

		}
	}
	
	@GetMapping("obtainWeather/{meetUpId}")
	@ApiOperation(value = "Metodo encargado de devolver el clima para una meetUp")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")})
	public ResponseEntity<?> obtainWeather(@PathVariable("meetUpId") Long meetUpId) {
		try {
			MeetUpObtainTemperatureResponse meetUpObtainTemperatureResponse = meetUpService.getTemperature(meetUpId);
			return new ResponseEntity<>(meetUpObtainTemperatureResponse,HttpStatus.OK);
		}catch(MeetUpException e){
			LOGGER.error(ERROR_MESSAGE_MEET_UP_OR_USER_NOT_EXIST, e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch(Exception e) {
			LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

		}
	}
	
	@PutMapping("addUser/{meetUpId}/user/{userId}")
	@ApiOperation(value = "Metodo encargado de agregar un usuario a la meetUp")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")})
	public ResponseEntity<?> addUser(@PathVariable("meetUpId") Long meetUpId,@PathVariable("userId") Long userId) {
		try {
			MeetUpAddUserResponse meetUpAddUserResponse = meetUpService.addUser(meetUpId,userId);
			return new ResponseEntity<>(meetUpAddUserResponse,HttpStatus.OK);
		}catch(MeetUpException e){
			LOGGER.error(ERROR_MESSAGE_MEET_UP_OR_USER_NOT_EXIST, e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch(Exception e) {
			LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

		}
	}
	
	
	@PutMapping("createMeetUp")
	@ApiOperation(value = "Metodo encargado de crear la meetUp")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")})
	public ResponseEntity<?> createMeetUp(@RequestBody MeetUpCreateRequest meetUpCreateRequest) {
		try {
			MeetUpCreateResponse meetUpCreateResponse = meetUpService.createMeetUp(meetUpCreateRequest.getUserId(),meetUpCreateRequest.getDirection(), meetUpCreateRequest.getDateMeetUp());
			return new ResponseEntity<>(meetUpCreateResponse,HttpStatus.OK);
		}catch(MeetUpException e){
			LOGGER.error(ERROR_MESSAGE_MEET_UP_OR_USER_NOT_EXIST, e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch(Exception e) {
			LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

		}
	}
	
	@PostMapping("confirmAssistence/user/{userId}/meetUp/{meetUpId}")
	@ApiOperation(value = "Metodo encargado de confirmar la asistencia")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")})
	public ResponseEntity<?> confirmAssistence(@PathVariable("userId") Long userId,@PathVariable("meetUpId") Long meetUpId) {
		try {
			MeetUpConfirmAssistenceResponse meetUpConfirmAssistenceResponse = meetUpService.confirmAssistence(userId,meetUpId);
			return new ResponseEntity<>(meetUpConfirmAssistenceResponse,HttpStatus.OK);
		}catch(MeetUpException e){
			LOGGER.error(ERROR_MESSAGE_MEET_UP_OR_USER_NOT_EXIST, e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch(Exception e) {
			LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

		}
	}
	
	
	

}

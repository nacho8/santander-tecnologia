package santander.tecnologia.challenge.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import santander.tecnologia.challenge.ws.weather.response.WeatherResponse;

@Service
public class WeatherService {
	
	
	private static final String WEATHER_URL = "https://community-open-weather-map.p.rapidapi.com/forecast/daily?q=argentina&ar&units=metric&cnt=16";
	
	private static final String HOST = "community-open-weather-map.p.rapidapi.com";

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

	private static final String KEY = "092c123636msh32777ac17332e7dp10f955jsn6a262fd6540b";
	
	
	protected static final HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-rapidapi-host", HOST);
		headers.set("x-rapidapi-key", KEY);
		return headers;
	}
	
	 public WeatherResponse getWeather() {
			LOGGER.info("Realizo pedido para obtener el tiempo. " + KEY);
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> request = new HttpEntity<>("", getHeaders());
			ResponseEntity<WeatherResponse> result = restTemplate.exchange(URI.create(WEATHER_URL),HttpMethod.GET, request, WeatherResponse.class);
			LOGGER.info("Finalizo pedido para obtener el tiempo.");
			return result.getBody();
		
	}

}

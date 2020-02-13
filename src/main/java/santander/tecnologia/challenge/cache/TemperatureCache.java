package santander.tecnologia.challenge.cache;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import santander.tecnologia.challenge.service.weather.WeatherService;
import santander.tecnologia.challenge.ws.weather.response.WeatherResponse;

@Transactional
@Component
public class TemperatureCache {
	
	@Autowired
	private WeatherService weatherService;
	
	@Cacheable("temperatureDay")
	public WeatherResponse getTemperature() throws Exception {
		return weatherService.getWeather();
		
	}
	
	@CachePut(value = "temperatureDay")
	public WeatherResponse refresh() throws Exception {
		return getTemperature();
	}
}

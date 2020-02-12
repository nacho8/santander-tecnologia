package santander.tecnologia.challenge.ws.weather.response;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class WeatherDay {
	
	private static final int SECONDS_TO_MILIS = 1000;
	
	private long dt;
	private long sunrise;
	private long sunset;
	private WeatherDayTemperature temp;
	
	public long getDt() {
		return dt;
	}
	public void setDt(long dt) {
		this.dt = dt;
	}
	public long getSunrise() {
		return sunrise;
	}
	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}
	public long getSunset() {
		return sunset;
	}
	public void setSunset(long sunset) {
		this.sunset = sunset;
	}
	public WeatherDayTemperature getTemp() {
		return temp;
	}
	public void setTemp(WeatherDayTemperature temp) {
		this.temp = temp;
	}
	
	
	public LocalDate getDate() {
		return Instant.ofEpochMilli(dt * SECONDS_TO_MILIS).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	

}

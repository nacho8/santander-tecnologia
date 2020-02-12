package santander.tecnologia.challenge.service;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import santander.tecnologia.challenge.domain.TemperatureParametry;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories("santander.tecnologia.challenge.persistence")
public class MeetUpServiceTest {

	@TestConfiguration
	static class MeetUpServiceTestContextConfiguration {

		@Bean
		public MeetUpService meetUpService() {
			return new MeetUpService();
		}

	}
	
	private List<TemperatureParametry> temperatureParametryList = new ArrayList<>();
	 
    @Before
    public void init() {
    	TemperatureParametry temperature = new TemperatureParametry();
    	temperature.setMaximumTemperature(null);
    	temperature.setMinimunTemperature(24.0);
    	temperature.setAmountBeerByPerson(2.0);
    	
    	TemperatureParametry temperature1 = new TemperatureParametry();
    	temperature1.setMaximumTemperature(20.0);
    	temperature1.setMinimunTemperature(null);
    	temperature1.setAmountBeerByPerson(0.75);
    	
    	TemperatureParametry temperature2 = new TemperatureParametry();
    	temperature2.setMaximumTemperature(20.0);
    	temperature2.setMinimunTemperature(24.0);
    	temperature2.setAmountBeerByPerson(1.0);
    	
    	temperatureParametryList.add(temperature);
    	temperatureParametryList.add(temperature1);
    	temperatureParametryList.add(temperature2);
    	
    }
 

	@Autowired
	private MeetUpService meetUpService;

	@Test
	public void obtaintAmountBeerTest() throws Exception {
		int value = meetUpService.calculateBeerByPerson(temperatureParametryList,23.0,9);
		int value1 = meetUpService.calculateBeerByPerson(temperatureParametryList,23.0,40);
		int value2 = meetUpService.calculateBeerByPerson(temperatureParametryList,3.0,60);
		int value3 = meetUpService.calculateBeerByPerson(temperatureParametryList,29.0,62);
		int value4 = meetUpService.calculateBeerByPerson(temperatureParametryList,24.1,60);
		int value5 = meetUpService.calculateBeerByPerson(temperatureParametryList,20.0,61);
		assertEquals(2,value);
		assertEquals(7,value1);
		assertEquals(8,value2);
		assertEquals(21,value3);
		assertEquals(20,value4);
		assertEquals(11,value5);

	}
}

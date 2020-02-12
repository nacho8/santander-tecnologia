package santander.tecnologia.challenge.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TemperatureParametry {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private Double minimumTemperature;
	
	private Double maximumTemperature;
	
	private Double amountBeerByPerson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMinimunTemperature() {
		return minimumTemperature;
	}

	public void setMinimunTemperature(Double minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
	}

	public Double getMaximumTemperature() {
		return maximumTemperature;
	}

	public void setMaximumTemperature(Double maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}

	public Double getAmountBeerByPerson() {
		return amountBeerByPerson;
	}

	public void setAmountBeerByPerson(Double amountBeerByPerson) {
		this.amountBeerByPerson = amountBeerByPerson;
	}
	
	

}

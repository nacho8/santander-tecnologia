package santander.tecnologia.challenge.cache;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import santander.tecnologia.challenge.domain.TemperatureParametry;

@Transactional
@Component
public class TemperatureParametryCache {
	
	@PersistenceContext
	private EntityManager em;
	
	@Cacheable("valuesMap")
	public List<TemperatureParametry> getParametry() {
		return em.createQuery("from TemperatureParametry", TemperatureParametry.class).getResultList();
	}
	
	@CachePut(value = "valuesMap")
	public List<TemperatureParametry> refresh() {
		return getParametry();
	}

}

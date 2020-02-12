package santander.tecnologia.challenge.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import santander.tecnologia.challenge.cache.TemperatureCache;

@Component
public class TemperatureJob {
	
	@Autowired
	private TemperatureCache cache;
	
	private static final int FIXED_RATE = 5 * 60 * 1000;
	private static final int INITIAL_DELAY = 6000;

	@Scheduled(initialDelay = INITIAL_DELAY, fixedRate = FIXED_RATE)
	protected void refreshcache() throws Exception {
		System.out.println("ARRANCO CACHE ");
		cache.getTemperature();
	}

}

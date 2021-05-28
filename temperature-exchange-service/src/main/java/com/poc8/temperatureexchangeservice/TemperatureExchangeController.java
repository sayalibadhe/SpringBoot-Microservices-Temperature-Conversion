package com.poc8.temperatureexchangeservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TemperatureExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private TemperatureExchangeRepository repository;
	@GetMapping("/temperature-exchange/from/{from}/to/{to}")
	public TemperatureExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to) {
		TemperatureExchange temperatureExchange= repository.findByFromAndTo(from, to);
		if(temperatureExchange==null) {
			throw new RuntimeException("Uanble to find data for" +from+ "to" +to);
		}
		
		String port=environment.getProperty("local.server.port");
		temperatureExchange.setEnvironment(port);
		return temperatureExchange;
		
	}
	

}

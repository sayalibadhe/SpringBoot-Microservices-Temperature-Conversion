package com.poc.temperatureconversionservice;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TemperatureConversionController {

	@Autowired
	private TemperatureExchangeProxy temperatureExchangeProxy;

	@Autowired
	private Environment environment;
	
	@GetMapping("/temperature-conversion/from/{from}/to/{to}/value/{value}")
	public TemperatureConversion caluclateTemperatureConversionWithoutFeign(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal value) {

		BigDecimal c1 = new BigDecimal("1.8");
		BigDecimal c2 = new BigDecimal("32");
		TemperatureConversion temperatureConversion = temperatureExchangeProxy.retrieveExchangeValue(from, to);

		if (from.equalsIgnoreCase("C")) {
			String port=environment.getProperty("local.server.port");
			return new TemperatureConversion(temperatureConversion.getId(), from, to, value,
					temperatureConversion.getConversionMultiple(), value.multiply(c1).add(c2),
					port);
		} else {
			String port=environment.getProperty("local.server.port");
			BigDecimal f1 = new BigDecimal("0.5555");
			BigDecimal f2 = new BigDecimal("32");
			return new TemperatureConversion(temperatureConversion.getId(), from, to, value,
					temperatureConversion.getConversionMultiple(), value.subtract(f2).multiply(f1),
					port);
		}

	}

	@GetMapping("/temperature-conversion-feign/from/{from}/to/{to}/value/{value}")
	public TemperatureConversion caluclateTemperatureConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal value) {

		String port=environment.getProperty("local.server.port");
		BigDecimal c1 = new BigDecimal("1.8");
		BigDecimal c2 = new BigDecimal("32");
		TemperatureConversion temperatureConversion = temperatureExchangeProxy.retrieveExchangeValue(from, to);

		if (from.equalsIgnoreCase("C")) {
			return new TemperatureConversion(temperatureConversion.getId(), from, to, value,
					temperatureConversion.getConversionMultiple(), value.multiply(c1).add(c2),
					port);
		} else {
			BigDecimal f1 = new BigDecimal("0.5555");
			BigDecimal f2 = new BigDecimal("32");
			return new TemperatureConversion(temperatureConversion.getId(), from, to, value,
					temperatureConversion.getConversionMultiple(), value.subtract(f2).multiply(f1),
					port);
		}

	}

}

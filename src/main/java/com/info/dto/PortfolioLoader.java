package com.info.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;



/**
 * @author vijay
 * 
 * A Mock class which is responsible to get the data from properties file which acts as a meta data for applications 
 *
 */
@Service
@PropertySource(value = "classpath:portfolio_list.properties")
public class PortfolioLoader {

	@Value("${bonds}")
	public String[] bonds;

	@Value("${largeCap}")
	public String[] largeCap;

	@Value("${midCap}")
	public String[] midCap;

	@Value("${foreign}")
	public String[] foreign;

	@Value("${smallCap}")
	public String[] smallCap;

}

package com.learnmicroservices.accounts.dto;

import java.util.Map;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import lombok.Getter;
import lombok.Setter;

@RefreshScope  //enables refresh actuator - to get latest properties info
//it pulls data from repo
@ConfigurationProperties(prefix = "accounts") // - add configuration-processor dependency to fetch details from properties file
@Setter
@Getter
public class AccountContactInfo {

	private String message;
	private Map<String,String> contactDetails;
	private List<String> onCallSupport;
	
	
}

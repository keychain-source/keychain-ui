package com.ericsson.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class KeychainProperties {
	
	@Value("${keychain.ws.url}")
	private String keychainWsUrl;

	public String getKeychainWsUrl() {
		return keychainWsUrl;
	}

	public void setKeychainWsUrl(String keychainWsUrl) {
		this.keychainWsUrl = keychainWsUrl;
	}
	
}

package com.esure.api.openapiexample.configuration;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ToString
@Slf4j
public class ApplicationProperties {
    @Value("${spring.application.name:not set}")
    private String applicationName;

    @Value("${application.description:not set}")
    private String applicationDescription;

    @Value("${application.version:not set}")
    private String applicationVersion;

    @Value("${application.license:not set}")
    private String applicationLicense;

    @Value("${application.licenseUrl:not set}")
    private String applicationLicenseUrl;

    @Value("${application.termsOfServiceUrl:not set}")
    private String applicationTermsOfServiceUrl;

    @Value("${application.createdBy:not set}")
    private String applicationCreatedBy;

    @Value("${application.seeMoreAtApi:not set}")
    private String applicationSeeMoreAtApi;

    @Value("${application.contactTheDeveloper:not set}")
    private String applicationContactTheDeveloper;
    
    @PostConstruct
    private void logValues() {
        log.debug("application properties {}", this.toString());
    }

}

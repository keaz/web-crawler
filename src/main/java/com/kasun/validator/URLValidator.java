package com.kasun.validator;

import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Log4j2
public class URLValidator implements ConstraintValidator<URLConstraint, List<String>> {

    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    @Override
    public boolean isValid(List<String> urls, ConstraintValidatorContext context) {
        
        for (var stringUrl : urls) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                log.info("Invalid URL {}", stringUrl);
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid url " + stringUrl).addConstraintViolation();
                return false;
            }
            final var protocol = url.getProtocol();
            log.debug("Protocol of the url {} is {}", url, protocol);
            if (!protocol.equalsIgnoreCase(HTTP) && !protocol.equalsIgnoreCase(HTTPS)) {
                log.info("Invalid protocol {} in the url {}", protocol, stringUrl);
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid protocol " + protocol + " in the " + stringUrl).addConstraintViolation();
                return false;
            }
        }

        return true;
    }

}

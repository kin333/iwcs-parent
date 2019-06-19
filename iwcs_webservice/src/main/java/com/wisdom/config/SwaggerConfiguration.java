package com.wisdom.config;

import com.wisdom.base.context.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Springfox Swagger configuration.
 * <p>
 * Warning! When having a lot of REST endpoints, Springfox can become a performance issue. In that
 * case, you can use a specific Spring profile for this class, so that only front-end developers
 * have access to the Swagger view.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    /**
     * Swagger Springfox configuration.
     *
     * @param properties of the application
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxDocket(ApplicationProperties properties) {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
                properties.getSwagger().getContactName(),
                properties.getSwagger().getContactUrl(),
                properties.getSwagger().getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
                properties.getSwagger().getTitle(),
                properties.getSwagger().getDescription(),
                properties.getSwagger().getVersion(),
                properties.getSwagger().getTermsOfServiceUrl(),
                contact,
                properties.getSwagger().getLicense(),
                properties.getSwagger().getLicenseUrl());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .directModelSubstitute(Byte.class, Integer.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }

    private List<? extends SecurityScheme> securitySchemes() {
        List<SecurityScheme> authorizationTypes = Arrays.asList(new ApiKey("Authorization", "JWT", "header"));
        return authorizationTypes;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = Arrays.asList(SecurityContext.builder().forPaths(PathSelectors.any()).securityReferences(securityReferences()).build());
        return securityContexts;
    }

    private List<SecurityReference> securityReferences() {
        List<SecurityReference> securityReferences = Arrays.asList(SecurityReference.builder().reference("Authorization").scopes(new AuthorizationScope[0]).build());
        return securityReferences;
    }

}

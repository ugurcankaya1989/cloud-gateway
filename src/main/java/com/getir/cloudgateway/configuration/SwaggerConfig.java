package com.getir.cloudgateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Primary
@Configuration
public class SwaggerConfig implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;

    public SwaggerConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        routeLocator.getRoutes().subscribe(route -> {
            System.out.println(route.getId());
            String name = route.getId();
            if (name.contains("swagger")) {
                String appName = name.split("-")[0];
                resources.add(swaggerResource(appName, "/" + appName.toLowerCase() + "/v3/api-docs", "1.0"));
            }
        });

        return resources;
    }

    private SwaggerResource swaggerResource(final String name, final String location,
                                            final String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}

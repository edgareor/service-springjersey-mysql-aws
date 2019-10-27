package cl.tesoreria.arquitectura.test;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cl.tesoreria.arquitectura.test.service.ServicioRest;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Configuration 
@ApplicationPath("/servlet-jersey/v1")				// A este nivel de ruta ya podemos generar el WADL con /application.wadl
public class JerseyConfiguration extends ResourceConfig {

    @Autowired
    public JerseyConfiguration() {
        register(ServicioRest.class);
        configureSwagger();
    }

    public void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        
        BeanConfig config = new BeanConfig();
        config.setConfigId("spring-jersey-swagger-example");
        config.setTitle("Spring, Jersey, and Swagger Example");
        config.setVersion("1.0.0");
        config.setSchemes(new String[] { "http", "https" });
        config.setContact("EdgarAdmin");
        config.setHost("http://localhost:8184");
        config.setBasePath("/servlet-jersey/v1");
        config.setResourcePackage("cl.tesoreria.arquitectura.test.service");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}

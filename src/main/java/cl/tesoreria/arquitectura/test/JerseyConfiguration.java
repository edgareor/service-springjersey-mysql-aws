package cl.tesoreria.arquitectura.test;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import cl.tesoreria.arquitectura.test.service.ServicioRest;

@Configuration 
@ApplicationPath("/servlet-jersey")				// A este nivel de ruta ya podemos generar el WADL con /application.wadl
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(ServicioRest.class); 
    }
}

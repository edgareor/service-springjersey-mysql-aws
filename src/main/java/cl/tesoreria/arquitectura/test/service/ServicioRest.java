package cl.tesoreria.arquitectura.test.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import cl.tesoreria.arquitectura.test.entity.Persona;
import cl.tesoreria.arquitectura.test.jpa.InterfacePersonasDAO;

@Controller
@Transactional
@Path("/service-name")
public class ServicioRest {
	
	@Autowired
	InterfacePersonasDAO dao;
	
	@GET
	@Path("/timenow")
	@Produces({"application/json"})
	public Map<String, Object> getStatus() {
		
		Date fecha = new Date ();
		Locale currentLocale = new Locale("EN");
		DateFormat formato = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, currentLocale);
		String output = formato.format(fecha);
		
		Map<String, Object> collection = new HashMap<String, Object>();
		collection.put("GetStatus", output);

         	return collection;
	}
	
	@GET
	@Path("/extraer-all")
	@Produces({"application/json"})
	public List<Persona> extraerAll() {

         	List<Persona> out = dao.findAll();
         	return out;
	}
	
	@POST
	@Path("/personas")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response insertar(Persona per) {
		
		dao.save(per);
		
		Map<String, String> collection = new HashMap<String, String>();
		collection.put("Data", "Objeto insertado correctamente");

		return Response.status(200).entity(collection).build();
	}
	
	@PUT
	@Path("/personas")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response modificar(Persona per) {  // NOTA: No se puede modificar el ID de la persona, el resto de atributos si.
		
		Map<String, String> collection = new HashMap<String, String>();

		if(per.getIdpersonas()==0){
			collection.put("Data", "Debe ingresar un ID a modificar");
			return Response.status(500).entity(collection).build();
		} else {
			dao.save(per);
			collection.put("Data", "Entidad modificada correctamente");
			return Response.status(200).entity(collection).build();
		}		
	}
	
	@DELETE
	@Path("/personas")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response eliminar(Persona per) {
		
		dao.deleteById(per.getIdpersonas());
		
		Map<String, String> collection = new HashMap<String, String>();
		collection.put("Data", "Objeto eliminado correctamente");

		return Response.status(202).entity(collection).build();
	}

}

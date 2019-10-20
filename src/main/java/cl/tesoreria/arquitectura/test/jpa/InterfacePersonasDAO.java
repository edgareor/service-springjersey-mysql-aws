package cl.tesoreria.arquitectura.test.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.tesoreria.arquitectura.test.entity.Persona;

public interface InterfacePersonasDAO extends JpaRepository<Persona, Integer> {

}

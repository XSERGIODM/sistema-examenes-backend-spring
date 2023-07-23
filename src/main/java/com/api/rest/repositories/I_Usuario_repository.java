package com.api.rest.repositories;

import com.api.rest.models.C_Usuario_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_Usuario_repository extends JpaRepository<C_Usuario_Model, Long> {

    C_Usuario_Model findByNombreUsuarioOrCorreo(String nombreUsuario, String correo);

    boolean existsByNombreUsuarioOrCorreo(String nombreUsuario, String correo);

}

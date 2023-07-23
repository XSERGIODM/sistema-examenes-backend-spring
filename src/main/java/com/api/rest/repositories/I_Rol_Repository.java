package com.api.rest.repositories;

import com.api.rest.models.C_Rol_Model;
import com.api.rest.utils.E_Rol_Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_Rol_Repository extends JpaRepository<C_Rol_Model,Long> {
    boolean existsByNombreRol(E_Rol_Util nombreRol);

}

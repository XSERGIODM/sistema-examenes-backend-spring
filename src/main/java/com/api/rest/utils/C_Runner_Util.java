package com.api.rest.utils;

import com.api.rest.models.C_Rol_Model;
import com.api.rest.repositories.I_Rol_Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class C_Runner_Util implements CommandLineRunner {


    private final I_Rol_Repository rolRepository;

    @Override
    public void run(String... args) {
        llenarRoles();
    }

    private void llenarRoles(){
        for (int i = 0; i < E_Rol_Util.getNames().size(); i++) {
            if (!rolRepository.existsByNombreRol(E_Rol_Util.valueOf(E_Rol_Util.getNames().get(i)))) {
                C_Rol_Model rol = C_Rol_Model.builder()
                        .nombreRol(E_Rol_Util.valueOf(E_Rol_Util.getNames().get(i)))
                        .build();
                rolRepository.save(rol);
                log.info("Rol: " + E_Rol_Util.getNames().get(i) + " cargado en la base de datos");
            }
            log.info("Rol: " + E_Rol_Util.getNames().get(i) + " ya esta cargado en la base de datos");
        }
        log.info("Roles cargados en la base de datos con éxito");
    }
}

package com.api.rest.utils;

import com.api.rest.dto.C_Usuario_Dto;
import com.api.rest.models.C_Rol_Model;
import com.api.rest.models.C_Usuario_Model;
import com.api.rest.repositories.I_Rol_Repository;
import com.api.rest.repositories.I_Usuario_repository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class C_Runner_Util implements CommandLineRunner {


    I_Rol_Repository rolRepository;
    I_Usuario_repository usuarioRepository;

    @Override
    public void run(String... args) {
        llenarRoles();
    }

    private void llenarRoles() {
        for (E_Rol_Util rol : E_Rol_Util.values()) {
            if (!rolRepository.existsByNombreRol(E_Rol_Util.valueOf(rol.name()))) {
                C_Rol_Model rolModel = C_Rol_Model.builder()
                        .nombreRol(E_Rol_Util.valueOf(rol.name()))
                        .build();
                rolRepository.save(rolModel);
                log.info("Rol: " + rol.name() + " cargado en la base de datos");
            } else {
                log.info("Rol: " + rol.name() + " ya existe en la base de datos");
            }
        }
        log.info("Roles cargados con éxito en la base de datos");
    }

    public void llenarUsuarios() {

        C_Usuario_Dto usuarioDto = C_Usuario_Dto.builder()
                .nombreUsuario("admin")
                .contrasenia("admin*123A")
                .correo("admin@admin.com")
                .nombres("admin")
                .apellidos("admin")
                .build();

        if (usuarioRepository.existsByNombreUsuarioOrCorreo(usuarioDto.getNombreUsuario(), usuarioDto.getCorreo())) {
            C_Usuario_Model usuarioModel = C_Usuario_Model.builder()
                    .nombreUsuario(usuarioDto.getNombreUsuario())
                    .contrasenia(usuarioDto.getContrasenia())
                    .correo(usuarioDto.getCorreo())
                    .nombres(usuarioDto.getNombres())
                    .apellidos(usuarioDto.getApellidos())
                    .build();

            usuarioRepository.save(usuarioModel);

            C_Rol_Model rolModel = rolRepository.findByNombreRol(E_Rol_Util.INVITADO);
            Set<C_Rol_Model> roles = new HashSet<>();
            roles.add(rolModel);

            usuarioModel.setRoles(roles);
            usuarioRepository.save(usuarioModel);

            log.info("Usuario: " + usuarioDto.getNombreUsuario() + " cargado en la base de datos");
        } else {
            log.info("Usuario: " + usuarioDto.getNombreUsuario() + " ya existe en la base de datos");
        }

    }

}

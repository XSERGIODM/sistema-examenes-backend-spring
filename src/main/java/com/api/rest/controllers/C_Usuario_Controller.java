package com.api.rest.controllers;

import com.api.rest.dto.C_Usuario_Dto;
import com.api.rest.models.C_Rol_Model;
import com.api.rest.models.C_Usuario_Model;
import com.api.rest.services.I_Rol_Service;
import com.api.rest.services.I_Usuario_Service;
import com.api.rest.utils.E_Rol_Util;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/usuario")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class C_Usuario_Controller {

    I_Usuario_Service usuarioService;
    I_Rol_Service rolService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        if (usuarioService.obtenerUsuarios().isEmpty()) {
            log.error("No hay usuarios");
            return new ResponseEntity<>("No hay usuarios", HttpStatus.BAD_REQUEST);
        } else {
            log.info("Listar usuarios");
            return ResponseEntity.ok(usuarioService.obtenerUsuarios());
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable(value = "id") Long id) {
        if (id != null) {
            if (usuarioService.obtenerUsuarioIde(id) != null) {
                log.info("Listar usuario por id: " + id);
                return ResponseEntity.ok(usuarioService.obtenerUsuarioIde(id));
            } else {
                log.error("El usuario con " + id + " no existe");
                return new ResponseEntity<>("El usuario con " + id + " no existe", HttpStatus.BAD_REQUEST);
            }
        } else {
            log.error("El id no puede ser nulo");
            return new ResponseEntity<>("El id no puede ser nulo", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar/buscar/{usuarioCorreo}")
    public ResponseEntity<?> listarPorCorreo(@PathVariable(value = "usuarioCorreo") String usuarioCorreo) {
        C_Usuario_Model usuarioModel = usuarioService.obtenerUsuarioNombreUsuarioCorreo(usuarioCorreo);
        if (usuarioModel != null) {
            log.info("Listar usuario por nombre de usuario o correo: " + usuarioCorreo);
            return ResponseEntity.ok(usuarioModel);
        } else {
            log.error("El usuario o correo no existe: " + usuarioCorreo);
            return new ResponseEntity<>("El usuario o correo no existe: " + usuarioCorreo, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody C_Usuario_Dto usuarioDto) {
        if (usuarioDto != null) {
            if (!usuarioService.existeUsuarioCorreo(usuarioDto.getNombreUsuario(), usuarioDto.getCorreo())) {
                Set<C_Rol_Model> roles = new HashSet<>();
                roles.add(rolService.buscarRolNombre(E_Rol_Util.INVITADO));
                C_Usuario_Model usuarioModel = mappearUsuarioDtoAUsuarioModel(usuarioDto);
                usuarioModel.setRoles(roles);
                log.info("Crear usuario: " + usuarioModel);
                return new ResponseEntity<>(usuarioService.crearUsuario(usuarioModel), HttpStatus.CREATED);
            } else {
                log.error("El usuario o correo ya existe: " + usuarioDto.getNombreUsuario() + " " + usuarioDto.getCorreo());
                return new ResponseEntity<>("El usuario o correo ya existe: " + usuarioDto.getNombreUsuario() + " " + usuarioDto.getCorreo(), HttpStatus.BAD_REQUEST);
            }
        } else {
            log.error("No se puede crear el usuario porque esta nulo");
            return new ResponseEntity<>("No se puede crear el usuario porque esta nulo", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody C_Usuario_Dto usuarioDto) {
        if (usuarioDto != null) {
            C_Usuario_Model usuarioModel = usuarioService.obtenerUsuarioIde(usuarioDto.getIde());
            if (usuarioModel != null) {

                if (!usuarioDto.getNombreUsuario().equals(usuarioModel.getNombreUsuario())) {
                    if (!usuarioService.existeUsuarioCorreo(usuarioDto.getNombreUsuario(), usuarioDto.getNombreUsuario())) {
                        usuarioModel.setNombreUsuario(usuarioDto.getNombreUsuario());
                    } else {
                        log.error("El usuario ya existe: " + usuarioDto.getNombreUsuario());
                        return new ResponseEntity<>("El usuario ya existe: " + usuarioDto.getNombreUsuario(), HttpStatus.BAD_REQUEST);
                    }
                }

                if (!usuarioDto.getCorreo().equals(usuarioModel.getCorreo())) {
                    if (!usuarioService.existeUsuarioCorreo(usuarioDto.getCorreo(), usuarioDto.getCorreo())) {
                        usuarioModel.setCorreo(usuarioDto.getCorreo());
                    } else {
                        log.error("El correo ya existe: " + usuarioDto.getCorreo());
                        return new ResponseEntity<>("El correo ya existe: " + usuarioDto.getCorreo(), HttpStatus.BAD_REQUEST);
                    }
                }

                usuarioModel.setNombres(usuarioDto.getNombres());
                usuarioModel.setApellidos(usuarioDto.getApellidos());
                usuarioModel.setContrasenia(usuarioDto.getContrasenia());
                usuarioModel.setRoles(usuarioDto.getRoles());

                log.info("Actualizar usuario: " + usuarioModel);
                return new ResponseEntity<>(usuarioService.actualizarUsuario(usuarioModel), HttpStatus.OK);
            } else {
                log.error("El usuario con " + usuarioDto.getIde() + " no existe");
                return new ResponseEntity<>("El usuario con " + usuarioDto.getIde() + " no existe", HttpStatus.BAD_REQUEST);
            }
        } else {
            log.error("No se puede actualizar el usuario porque esta nulo");
            return new ResponseEntity<>("No se puede actualizar el usuario porque esta nulo", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id) {
        if (id != null) {
            C_Usuario_Model usuarioModel = usuarioService.obtenerUsuarioIde(id);
            if (usuarioModel != null) {
                usuarioService.eliminarUsuario(id);
                log.info("Eliminar usuario con id: " + usuarioModel);
                return new ResponseEntity<>("Usuario eliminado: " + usuarioModel, HttpStatus.OK);
            } else {
                log.error("El usuario con " + id + " no existe");
                return new ResponseEntity<>("El usuario con " + id + " no existe", HttpStatus.BAD_REQUEST);
            }
        } else {
            log.error("El id no puede ser nulo");
            return new ResponseEntity<>("El id no puede ser nulo", HttpStatus.BAD_REQUEST);
        }
    }

    private C_Usuario_Model mappearUsuarioDtoAUsuarioModel(C_Usuario_Dto usuarioDto) {
        return C_Usuario_Model.builder()
                .ide(usuarioDto.getIde())
                .nombreUsuario(usuarioDto.getNombreUsuario())
                .correo(usuarioDto.getCorreo())
                .contrasenia(usuarioDto.getContrasenia())
                .nombres(usuarioDto.getNombres())
                .apellidos(usuarioDto.getApellidos())
                .roles(usuarioDto.getRoles())
                .build();
    }
}


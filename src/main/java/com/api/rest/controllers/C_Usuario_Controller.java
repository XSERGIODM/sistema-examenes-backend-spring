package com.api.rest.controllers;

import com.api.rest.services.I_Usuario_Service;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class C_Usuario_Controller {

    I_Usuario_Service usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioIde(id));
    }

    @GetMapping("/listar/buscar/{usuarioCorreo}")
    public ResponseEntity<?> listarPorCorreo(@PathVariable(value = "usuarioCorreo") String usuarioCorreo) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioNombreUsuarioCorreo(usuarioCorreo));
    }
}

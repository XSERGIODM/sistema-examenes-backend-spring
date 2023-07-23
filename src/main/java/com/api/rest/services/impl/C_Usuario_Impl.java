package com.api.rest.services.impl;

import com.api.rest.dto.C_Usuario_Dto;
import com.api.rest.models.C_Usuario_Model;
import com.api.rest.repositories.I_Usuario_repository;
import com.api.rest.services.I_Usuario_Service;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class C_Usuario_Impl implements I_Usuario_Service {

    I_Usuario_repository usuarioRepository;

    @Override
    public List<C_Usuario_Model> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public C_Usuario_Model obtenerUsuarioIde(Long ide) {
        return usuarioRepository.findById(ide).orElse(null);
    }

    @Override
    public C_Usuario_Model obtenerUsuarioNombreUsuarioCorreo(String nombreUsuarioCorreo) {
        return usuarioRepository.findByNombreUsuarioOrCorreo(nombreUsuarioCorreo, nombreUsuarioCorreo);
    }
}

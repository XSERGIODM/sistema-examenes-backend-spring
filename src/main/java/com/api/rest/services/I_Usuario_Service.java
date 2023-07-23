package com.api.rest.services;

import com.api.rest.models.C_Usuario_Model;

import java.util.List;

public interface I_Usuario_Service {
    List<C_Usuario_Model> obtenerUsuarios();

    C_Usuario_Model obtenerUsuarioIde(Long ide);

    C_Usuario_Model obtenerUsuarioNombreUsuarioCorreo(String nombreUsuarioCorreo);
}

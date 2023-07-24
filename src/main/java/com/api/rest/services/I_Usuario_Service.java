package com.api.rest.services;

import com.api.rest.models.C_Usuario_Model;

import java.util.List;

public interface I_Usuario_Service {
    List<C_Usuario_Model> obtenerUsuarios();

    C_Usuario_Model obtenerUsuarioIde(Long ide);

    C_Usuario_Model obtenerUsuarioNombreUsuarioCorreo(String usuarioCorreo);

    C_Usuario_Model crearUsuario(C_Usuario_Model usuarioDto);

    C_Usuario_Model actualizarUsuario(C_Usuario_Model usuarioModel);

    void eliminarUsuario(Long ide);

    boolean existeUsuarioCorreo(String usuario, String correo);
}

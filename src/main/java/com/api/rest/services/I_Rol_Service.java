package com.api.rest.services;

import com.api.rest.models.C_Rol_Model;
import com.api.rest.utils.E_Rol_Util;

public interface I_Rol_Service {
    C_Rol_Model buscarRolNombre(E_Rol_Util nombre);
}

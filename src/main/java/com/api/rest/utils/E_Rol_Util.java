package com.api.rest.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum E_Rol_Util {
    ADMINISTRADOR,
    USUARIO,
    INVITADO,
    ANONIMO,
    SUPER_ADMINISTRADOR;


    public static List<String> getNames(){
        return Arrays.stream(E_Rol_Util.values()).map(Enum::name).collect(Collectors.toList());
    }
}


package com.api.rest.services.impl;

import com.api.rest.repositories.I_Rol_Repository;
import com.api.rest.services.I_Rol_Service;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class C_Rol_Impl implements I_Rol_Service {
    I_Rol_Repository rolRepository;
}

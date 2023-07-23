package com.api.rest.models;

import com.api.rest.utils.E_Rol_Util;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity(name = "roles")
public class C_Rol_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ide;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, name = "nombre_rol")
    E_Rol_Util nombreRol;
}

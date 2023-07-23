package com.api.rest.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@Entity(name = "usuarios_roles")
public class C_Usuario_Rol_Model {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long ide;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY, cascade = CascadeType.PERSIST)
    C_Usuario_Model usuario;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY, cascade = CascadeType.PERSIST)
    C_Rol_Model rol;
}

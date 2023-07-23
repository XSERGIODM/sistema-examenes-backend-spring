package com.api.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity(name = "usuarios")
public class C_Usuario_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ide;

    @NotBlank(message = "El nombre no puede ser vació o nulo")
    @Size(min = 2, max = 40, message = "El nombre debe tener entre 2 y 40 caracteres")
    String nombres;

    @NotBlank(message = "El apellido no puede ser vació o nulo")
    @Size(min = 2, max = 40, message = "El apellido debe tener entre 2 y 40 caracteres")
    String apellidos;

    @Column(unique = true, name = "nombre_usuario")
    @NotBlank(message = "El nombre de usuario no puede ser vació o nulo")
    @Size(min = 2, max = 40)
    String nombreUsuario;

    @NotBlank(message = "La contraseña no puede ser vació o nulo")
    @Size(min = 8, message = "La contraseña no puede ser menor que 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "La contraseña debe contener al menos una letra minúscula, una letra mayúscula, un número y un carácter especial. Además, debe tener una longitud mínima de 8 caracteres.")
    String contrasenia;

    @Column(unique = true)
    @NotBlank(message = "El correo no puede ser vació o nulo")
    @Email
    String correo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "usuario")
    @NotEmpty(message = "El usuario debe tener al menos un rol")
    @JsonIgnore
    Set<C_Usuario_Rol_Model> roles;
}

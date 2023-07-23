package com.api.rest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = C_Rol_Model.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    Set<C_Rol_Model> roles = new HashSet<>();
}

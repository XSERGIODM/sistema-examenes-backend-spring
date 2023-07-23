package com.api.rest.dto;

import com.api.rest.models.C_Rol_Model;
import com.api.rest.models.C_Usuario_Model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true)
public class C_Usuario_Dto {

    @NotBlank(message = "El nombre no puede ser vació o nulo")
    @Size(min = 2, max = 40, message = "El nombre debe tener entre 2 y 40 caracteres")
    String nombres;

    @NotBlank(message = "El apellido no puede ser vació o nulo")
    @Size(min = 2, max = 40, message = "El apellido debe tener entre 2 y 40 caracteres")
    String apellidos;

    @NotBlank(message = "El nombre de usuario no puede ser vació o nulo")
    @Size(min = 2, max = 40)
    String nombreUsuario;

    @NotBlank(message = "La contraseña no puede ser vació o nulo")
    @Size(min = 8, message = "La contraseña no puede ser menor que 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "La contraseña debe contener al menos una letra minúscula, una letra mayúscula, un número y un carácter especial. Además, debe tener una longitud mínima de 8 caracteres.")
    String contrasenia;

    @NotBlank(message = "El correo no puede ser vació o nulo")
    @Email
    String correo;


}

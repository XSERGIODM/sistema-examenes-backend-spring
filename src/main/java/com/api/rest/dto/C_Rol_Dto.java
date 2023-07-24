package com.api.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
@ToString
public class C_Rol_Dto {

    Long ide;

    @NotBlank(message = "El nombre del rol no puede ser vació o nulo")
    String nombreRol;
}

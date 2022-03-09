package ru.gretchen.mimimimetr.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "UserCreate", description = "Create user")
public class UserCreateDto {

    @Schema(description = "User login",
            required = true)
    private String login;
}

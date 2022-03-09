package ru.gretchen.mimimimetr.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Cat", description = "Cat")
public class CatDto {
    @Schema(description = "Cat id",
            required = true,
            pattern = "*.",
            maxLength = 36,
            minLength = 36)
    UUID id;

    @Schema(description = "Cat name",
            required = true)
    String name;

    @Schema(description = "Cat photo",
            required = true)
    byte[] photo;
}

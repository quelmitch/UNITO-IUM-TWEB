package org.unito.postgreserver.utils;

import jakarta.validation.constraints.Min;
import lombok.*;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class GenericFilterDTO {
    @Min(0)
    private Integer offset = 0;
    @Min(1)
    private Integer limit = 10;

    private Type responseType = Type.FULL;

    public enum Type {
        BASIC,
        FULL
    }
}

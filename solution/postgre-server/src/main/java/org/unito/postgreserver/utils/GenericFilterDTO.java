package org.unito.postgreserver.utils;

import jakarta.validation.constraints.Min;
import lombok.*;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class GenericFilterDTO {
    @Min(0)
    private Integer page = 0;
    @Min(1)
    private Integer limit = 20;

    private Type responseType = Type.FULL;

    private Sort sort = Sort.ASC;

    public enum Type {
        BASIC,
        FULL
    }

    public enum Sort {
        ASC,
        DESC
    }
}

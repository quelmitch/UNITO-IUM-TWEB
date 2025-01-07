package org.unito.postgreserver.oscar.dto;

import lombok.*;
import org.unito.postgreserver.oscar.model.OscarCategory;

import java.util.List;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class OscarFilterDTO {
    OscarCategory category;
}

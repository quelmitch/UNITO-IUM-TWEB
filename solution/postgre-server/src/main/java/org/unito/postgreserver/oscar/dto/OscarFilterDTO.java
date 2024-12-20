package org.unito.postgreserver.oscar.dto;

import lombok.*;
import java.util.List;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class OscarFilterDTO {
    private Integer yearLT;
    private Integer yearGT;
    private Boolean winner;
    private List<String> categories; // TODO change to OscarCategory in case
    private String movie;
    private String person;
}

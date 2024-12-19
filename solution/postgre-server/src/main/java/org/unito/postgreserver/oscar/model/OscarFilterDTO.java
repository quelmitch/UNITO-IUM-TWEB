package org.unito.postgreserver.oscar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class OscarFilterDTO {
    private Integer yearLT;
    private Integer yearGT;
    private Boolean winner;
    private List<String> categories; // TODO change to OscarCategory in case
}

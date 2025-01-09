package org.unito.postgreserver.oscar.dto;

import lombok.*;
import org.unito.postgreserver.oscar.model.OscarType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CeremonyDTO implements OscarType{
    private Integer numberCeremony;
    private Integer yearCeremony;
}

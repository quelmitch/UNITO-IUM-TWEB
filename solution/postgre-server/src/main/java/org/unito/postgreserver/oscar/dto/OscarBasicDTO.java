package org.unito.postgreserver.oscar.dto;

import lombok.*;
import org.unito.postgreserver.oscar.model.Oscar;
import org.unito.postgreserver.oscar.model.OscarType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class OscarBasicDTO implements OscarType {
    private Integer numberCeremony;
    private Integer yearCeremony;
    private Integer yearMovie;
    private String category;
    private String nomineeName;
    private String nomineeMovie;
    private Boolean isWinner;

    public static OscarBasicDTO toDTO(Oscar oscar) {
        OscarBasicDTO dto = new OscarBasicDTO();
        dto.setNumberCeremony(oscar.getNumberCeremony());
        dto.setYearCeremony(oscar.getYearCeremony());
        dto.setYearMovie(oscar.getYearMovie());
        dto.setCategory(oscar.getCategory());
        dto.setNomineeName(oscar.getNomineeName());
        dto.setNomineeMovie(oscar.getNomineeMovie());
        dto.setIsWinner(oscar.getIsWinner());
        return dto;
    }
}

package org.unito.postgreserver.oscar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class OscarDTO {
    private OscarCeremony oscarCeremony;
    private List<nominationDTO> nominations;

    @AllArgsConstructor @NoArgsConstructor @Getter @Setter
    public static class nominationDTO {
        private Long id;
        private String category;
        private String movie;
        private String person;
        private boolean winner;
    }
}

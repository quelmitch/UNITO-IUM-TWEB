package org.unito.postgreserver.release.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ReleaseFilterDTO {
    private String sortBy = "id";

    private List<String> country;

    private List<String> distributionFormat;

    private List<String> rating;
}

package org.unito.postgreserver.release.dto;

import lombok.*;
import org.unito.postgreserver.release.model.Release;
import org.unito.postgreserver.release.model.ReleaseType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ReleaseBasicDTO implements ReleaseType {
    private String country;
    private String date;
    private String distributionFormat;
    private String rating;

    public static ReleaseBasicDTO toDTO(Release release) {
        ReleaseBasicDTO dto = new ReleaseBasicDTO();
        dto.setCountry(release.getCountry());
        dto.setDate(release.getDate());
        dto.setDistributionFormat(release.getDistributionFormat());
        dto.setRating(release.getRating());
        return dto;
    }
}

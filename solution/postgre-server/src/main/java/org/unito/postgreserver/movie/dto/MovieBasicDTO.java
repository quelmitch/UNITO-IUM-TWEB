package org.unito.postgreserver.movie.dto;

import lombok.*;
import org.unito.postgreserver.movie.model.Movie;
import org.unito.postgreserver.movie.model.MovieType;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class MovieBasicDTO implements MovieType {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String tagline;
    private String description;
    private Integer durationInMinutes;
    private Double rating;
    private String posterLink;

    public static MovieBasicDTO toDTO(Movie movie){
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setTagline(movie.getTagline());
        dto.setDescription(movie.getDescription());
        dto.setDurationInMinutes(movie.getDurationInMinutes());
        dto.setRating(movie.getRating());
        dto.setPosterLink(movie.getPosterLink());
        return dto;
    }
}

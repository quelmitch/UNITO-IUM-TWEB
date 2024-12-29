package org.unito.postgreserver.movie.dto;

import lombok.Data;
import org.unito.postgreserver.movie.model.Movie;

@Data
public class MovieDto {
    private String title;
    private Integer releaseYear;
    private String tagline;
    private String description;
    private Integer durationInMinutes;
    private Double rating;
    private String posterLink;

    public static MovieDto toDTO(Movie movie){
        MovieDto dto = new MovieDto();
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

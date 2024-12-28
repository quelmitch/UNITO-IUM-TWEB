package org.unito.postgreserver.movie.dto;

import lombok.Data;
import org.unito.postgreserver.movie.model.Movie;

@Data
public class MovieDto {
    private String title;

    public static MovieDto toDTO(Movie movie){
        MovieDto dto = new MovieDto();
        dto.setTitle(movie.getTitle());
        return dto;
    }
}

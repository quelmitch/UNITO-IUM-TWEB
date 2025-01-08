package org.unito.postgreserver.movie.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.movie.model.Movie;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
@Tag(name = "Movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(
        summary = "Fetch Movie by id",
        description =
            "Fetches a Movie from the database with the given id." +
            "<br>This endpoint returns only an item and do not supports pagination."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = Map.class,
                    description = "A movie object",
                    example = """
                    {
                        "id": 1000001,
                        "title": "Barbie",
                        "releaseYear": 2023,
                        "tagline": "She's everything. He's just Ken.",
                        "description": "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
                        "runtime": 114,
                        "rating": 3.86,
                        "posterLink": "https://a.ltrbxd.com/resized/film-poster/2/7/7/0/6/4/277064-barbie-0-230-0-345-crop.jpg?v=1b83dc7a71"
                    }
                """
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                {
                  "timestamp": "2025-01-08T18:27:44.980+00:00",
                  "status": 500,
                  "error": "Internal Server Error",
                  "path": "/movie/{id}"
                }
                """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                {
                  "timestamp": "2025-01-08T18:27:44.980+00:00",
                  "status": 500,
                  "error": "Not Found",
                  "path": "/movie/{id}"
                }
                """
                )
            )
        )
    })
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }


    // TODO should remove and merge with filter route????
    @GetMapping("/title")
    public List<Movie> getMovieByTitle(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilters,
            @ParameterObject @RequestParam String title
    ) {
        return movieService.getMovieByTitle(genericFilters, title);
    }

    @Operation(
        summary = "Fetch Movies by filter",
        description =
            "Fetches a list of Movies from the database based on the provided filter criteria." +
            "<br>If no filter parameters are provided, all Movies are returned." +
            "<br>This endpoint supports pagination through GenericFilterDTO."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = Map.class,
                    description = "A paginated list of movies objects",
                    example = """
                    {
                        "limit": 20,
                        "totalPages": 47080,
                        "page": 0,
                        "content": [
                            {
                                "id": 1000001,
                                "title": "Barbie",
                                "releaseYear": 2023,
                                "tagline": "She's everything. He's just Ken.",
                                "description": "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
                                "runtime": 114,
                                "rating": 3.86,
                                "posterLink": "https://a.ltrbxd.com/resized/film-poster/2/7/7/0/6/4/277064-barbie-0-230-0-345-crop.jpg?v=1b83dc7a71"
                            }
                        ]
                    }
                """
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                {
                  "timestamp": "2025-01-08T18:27:44.980+00:00",
                  "status": 500,
                  "error": "Internal Server Error",
                  "path": "/movie/filter"
                }
                """
                )
            )
        )
    })
    @GetMapping("/filter")
    public Map<String, Object> getMovies(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilters,
            @ParameterObject @ModelAttribute MovieFilterDTO movieFilters
    ) {
        return movieService.getMovieWithFilter(genericFilters, movieFilters);
    }
}

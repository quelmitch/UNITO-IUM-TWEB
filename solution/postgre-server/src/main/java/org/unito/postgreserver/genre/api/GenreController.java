package org.unito.postgreserver.genre.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
@Tag(name = "Genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(final GenreService genreService) { this.genreService = genreService; }

    @Operation(
            summary = "Get all genres",
            description = "Fetches all genres stored in the database. This endpoint does not support pagination."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class,
                                    description = "A list of genres",
                                    example = "[\"Action\", \"Adventure\", \"Animation\"]"
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                        {
                          "timestamp": "2025-01-08T18:27:44.980+00:00",
                          "status": 500,
                          "error": "Internal Server Error",
                          "path": "/genre"
                        }
                        """
                            )
                    )
            )
    })
    @GetMapping("")
    public List<String> getAllGenres() {
        return genreService.getAllGenres();
    }
}

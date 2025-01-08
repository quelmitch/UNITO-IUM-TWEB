package org.unito.postgreserver.theme.api;

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
@RequestMapping("/theme")
@Tag(name = "Themes")
public class ThemeController {
    private final ThemeService themeService;

    @Autowired
    public ThemeController(final ThemeService themeService) { this.themeService = themeService; }

    @Operation(
            summary = "Get all themes",
            description = "Fetches all themes stored in the database. This endpoint does not support pagination."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = List.class,
                    description = "A list of theme names",
                    example = "[\"Epic heroes\", \"Heists and thrilling action\", \"Spooky, scary comedy\"]"
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
                          "path": "/theme"
                        }
                        """
                )
            )
        )
    })
    @GetMapping("")
    public List<String> getAllThemes() throws Exception {
        return themeService.getAllThemes();
    }
}

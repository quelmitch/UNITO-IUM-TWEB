package org.unito.postgreserver.language.api;

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
import org.unito.postgreserver.language.model.Language;
import org.unito.postgreserver.theme.model.Theme;

import java.util.List;

@RestController
@RequestMapping("/language")
@Tag(name = "Languages")
public class LanguageController {
    private final LanguageService languageService;

    @Autowired
    public LanguageController(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @Operation(
        summary = "Get all languages",
        description =
            "Fetches all languages stored in the database." +
            "<br>This endpoint does not support pagination."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = List.class,
                    description = "A list of languages",
                    example = "[\"Italian\", \"Japanese\", \"Korean\"]"
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
                            "path": "/language"
                        }
                        """
                )
            )
        )
    })
    @GetMapping("")
    public List<String> getAllLanguages() {
        return languageService.getAllLanguages();
    }
}

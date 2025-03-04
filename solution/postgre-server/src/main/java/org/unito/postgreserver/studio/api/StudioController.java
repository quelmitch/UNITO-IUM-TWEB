package org.unito.postgreserver.studio.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unito.postgreserver.studio.dto.StudioFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studio")
@Tag(name = "Studios")
public class StudioController {
    private final StudioService studioService;

    @Autowired
    public StudioController(final StudioService studioService) {
        this.studioService = studioService;
    }

    @Operation(
        summary = "Fetch studios by filters",
        description =
            "Fetches a list of studios from the database based on the provided filter criteria." +
                "<br>If no filter parameters are provided, all studios are returned." +
                "<br>This endpoint support pagination through GenericFilterDTO."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = Map.class,
                    description = "A paginated list of studios object",
                    example = """
                        {
                            "limit": 10,
                            "totalPages": 33954,
                            "page": 0,
                            "content": [
                                {
                                    "studio": "LuckyChap Entertainment",
                                    "movieId": 1000001
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
                            "path": "/studio/filter"
                        }
                        """
                )
            )
        )
    })
    @GetMapping("/filter")
    public Map<String, Object> getStudioByFilter(
        @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
        @ParameterObject @ModelAttribute StudioFilterDTO studioFilter
    ) {
        return studioService.getStudioByFilter(genericFilter, studioFilter);
    }

    @Operation(
        summary = "Get all studios",
        description =
            "Fetches all studios stored in the database. " +
                "<br>This endpoint support pagination through GenericFilterDTO."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class,
                    description = "A list of studio names",
                    example = """
                        {
                            "limit": 20,
                            "totalPages": 66639,
                            "page": 0,
                            "content": [
                                "'A' Production Committee",
                                "'Kala ni Chuchi Productions",
                                "'S Wonderful Pictures"
                            ]
                        }
                        """
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
                            "path": "/studio"
                        }
                        """
                )
            )
        )
    })
    @GetMapping("")
    public Map<String, Object> getAllStudios(
        @ParameterObject @ModelAttribute GenericFilterDTO genericFilter
    ) {
        return studioService.getAllStudios(genericFilter);
    }
}

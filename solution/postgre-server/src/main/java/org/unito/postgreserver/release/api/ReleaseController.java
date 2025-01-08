package org.unito.postgreserver.release.api;

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
import org.unito.postgreserver.release.dto.ReleaseFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/release")
@Tag(name = "Releases")
public class ReleaseController {
    private final ReleaseService releaseService;

    @Autowired
    public ReleaseController(final ReleaseService releaseService) { this.releaseService = releaseService; }


    @Operation(
        summary = "Fetch releases by filter",
        description =
            "Fetches a list of releases from the database based on the provided filter criteria." +
            "<br>If no filter parameters are provided, all releases are returned." +
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
                    description = "A paginated list of release objects",
                    example = """
                    {
                        "limit": 20,
                        "totalPages": 1500,
                        "page": 0,
                        "content": [
                            {
                                "movieId": 1000001,
                                "country": "Andorra",
                                "date": "2023-07-21",
                                "distributionFormat": "Theatrical",
                                "rating": null
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
                  "path": "/release/filter"
                }
                """
                )
            )
        )
    })
    @GetMapping("/filter")
    public Map<String, Object> getReleases(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @ModelAttribute ReleaseFilterDTO releaseFilter) {
        return releaseService.getReleaseByFilters(genericFilter, releaseFilter);
    }
}

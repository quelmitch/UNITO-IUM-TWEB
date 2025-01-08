package org.unito.postgreserver.oscar.api;

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
import org.unito.postgreserver.oscar.dto.OscarFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oscar")
@Tag(name = "Oscars")
class OscarController {

    private final OscarService oscarService;

    @Autowired
    public OscarController(OscarService oscarService) {
        this.oscarService = oscarService;
    }

    @Operation(
            summary = "Fetch all Oscar Categories",
            description =
                    "Fetches all the different categories stored in the Oscar table. This endpoint does not support pagination."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = Map.class,
                                    description = "A list of oscar category names",
                                    example = """
                    [
                        "ACTOR",
                        "ACTRESS",
                        "ART DIRECTOR"
                    ]
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
                  "path": "/oscar/categories"
                }
                """
                            )
                    )
            )
    })
    @GetMapping("/categories")
    public List<String> getAllOscarsCategories() {
        return oscarService.getAllOscarsCategories();
    }

    @Operation(
        summary = "Fetch Oscars by filter",
        description =
            "Fetches a list of Oscars from the database based on the provided filter criteria." +
            "<br>If no filter parameters are provided, all Oscars are returned." +
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
                    description = "A paginated list of oscar objects",
                    example = """
                    {
                        "limit": 20,
                        "totalPages": 545,
                        "page": 0,
                        "content": [
                            {
                                "numberCeremony": 1,
                                "yearCeremony": 1928,
                                "yearMovie": 1927,
                                "category": "ACTOR",
                                "nomineeName": "Richard Barthelmess",
                                "nomineeMovie": "The Noose",
                                "isWinner": false
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
                  "path": "/oscar/filter"
                }
                """
                )
            )
        )
    })
    @GetMapping("/filter")
    public Map<String, Object> getOscars(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @ModelAttribute OscarFilterDTO oscarFilter) {
        return oscarService.getOscarByFilters(genericFilter, oscarFilter);
    }
}

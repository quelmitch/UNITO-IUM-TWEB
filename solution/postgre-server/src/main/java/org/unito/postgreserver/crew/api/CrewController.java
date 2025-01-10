package org.unito.postgreserver.crew.api;

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
import org.unito.postgreserver.crew.dto.CrewFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/crew")
@Tag(name = "Crew")
public class CrewController {
    private final CrewService crewService;

    @Autowired
    public CrewController(final CrewService crewService) { this.crewService = crewService; }

    @Operation(
        summary = "Fetch Crew Members by filters",
        description =
            "Fetches a list of Crew members from the database based on the provided filter criteria." +
                "<br>If no filter parameters are provided, all crew members are returned." +
                "<br>The name field supports searching using fuzzy matching" +
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
                    description = "A paginated list of crew objects",
                    example = """
                        {
                            "limit": 10,
                            "totalPages": 235945,
                            "page": 0,
                            "content": [
                                {
                                    "name": "Christopher",
                                    "role": "Director",
                                    "movieId": 1000007
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
                            "path": "/crew/filter"
                        }
                        """
                )
            )
        )
    })
    @GetMapping("/filter")
    public Map<String, Object> getCrewMembers(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @ModelAttribute CrewFilterDTO crewFilter
    ) {
        return crewService.getCrewMembersByFilter(genericFilter, crewFilter);
    }
}

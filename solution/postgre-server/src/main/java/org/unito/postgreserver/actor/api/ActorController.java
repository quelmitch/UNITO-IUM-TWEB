package org.unito.postgreserver.actor.api;

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
import org.unito.postgreserver.actor.dto.ActorFilterDTO;
import org.unito.postgreserver.actor.model.Actor;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/actor")
@Tag(name = "Actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(final ActorService actorService) {
        this.actorService = actorService;
    }

    @Operation(
        summary = "Fetch Actors by filters",
        description =
            "Fetches a list of Actors from the database based on the provided filter criteria." +
                "<br>If no filter parameters are provided, all actors are returned." +
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
                    description = "A paginated list of actor objects",
                    example = """
                        {
                            "limit": 20,
                            "totalPages": 289875,
                            "page": 0,
                            "content": [
                                {
                                  "name": "Margot Robbie",
                                  "role": "Barbie",
                                  "movieId": 1000001
                                },
                                {
                                  "name": "Ryan Gosling",
                                  "role": "Ken",
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
                            "path": "/actor/filter"
                        }
                        """
                )
            )
        )
    })
    @GetMapping("/filter")
    public Map<String, Object> getActors(
        @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
        @ParameterObject @ModelAttribute ActorFilterDTO actorFilter
    ) {
        return actorService.getActorByFilter(genericFilter, actorFilter);
    }
}

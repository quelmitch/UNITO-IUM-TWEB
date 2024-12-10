package org.unito.postgreserver.oscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.movie.MovieService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oscar")
public class OscarController {
    // TODO: HTTP routes go here

    private final OscarService oscarService;

    @Autowired
    public OscarController(OscarService oscarService) {
        this.oscarService = oscarService;
    }

    @GetMapping("/ceremonies")
    public ResponseEntity<List<OscarCeremony>> getAllCeremonies() {
        return ResponseEntity.ok(oscarService.getAllCeremonies());
    }

    // Endpoint per OscarNomination
    @GetMapping("/nominations/winners-by-ceremony")
    public ResponseEntity<Map<Integer, List<OscarNomination>>> getWinnersGroupedByCeremony() {
        return ResponseEntity.ok(oscarService.getWinnersGroupedByCeremony());
    }

    @GetMapping("/nominations/ceremony/{number}")
    public ResponseEntity<List<OscarNomination>> getNominationsByCeremony(@PathVariable int number) {
        return ResponseEntity.ok(oscarService.getNominationsByCeremony(number));
    }

    @GetMapping("/nominations/movie")
    public ResponseEntity<List<OscarNomination>> getNominationsByMovie(@RequestParam String title) {
        return ResponseEntity.ok(oscarService.getNominationsByMovie(title));
    }

    @GetMapping("/nominations/person")
    public ResponseEntity<List<OscarNomination>> getNominationsByPerson(@RequestParam String name) {
        return ResponseEntity.ok(oscarService.getNominationsByPerson(name));
    }

    @GetMapping("/nominations/category/winners")
    public ResponseEntity<List<OscarNomination>> getWinnersByCategory(@RequestParam String category) {
        return ResponseEntity.ok(oscarService.getWinnersByCategory(category));
    }

    @GetMapping("/nominations/category")
    public ResponseEntity<List<OscarNomination>> getNominationsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(oscarService.getNominationsByCategory(category));
    }

}

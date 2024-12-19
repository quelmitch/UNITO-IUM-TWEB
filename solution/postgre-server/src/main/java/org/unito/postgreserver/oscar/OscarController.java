package org.unito.postgreserver.oscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.oscar.model.OscarCeremony;
import org.unito.postgreserver.oscar.model.OscarDTO;
import org.unito.postgreserver.oscar.model.OscarFilterDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oscar")
class OscarController {

    private final OscarService oscarService;

    @Autowired
    public OscarController(OscarService oscarService) {
        this.oscarService = oscarService;
    }

    @GetMapping("/filter")
    public List<OscarDTO> getCeremoniesWithFilter(@ModelAttribute OscarFilterDTO filter) {
        return oscarService.getCeremoniesWithFilter(filter);
    }

    @GetMapping("/movies")
    public List<OscarCeremony> getCeremoniesByMovie(@RequestParam String movie) {
        return oscarService.getCeremoniesByMovie(movie);
    }

    @GetMapping("/person")
    public List<OscarCeremony> getCeremoniesByPerson(@RequestParam String person) {
        return oscarService.getCeremoniesByPerson(person);
    }
}

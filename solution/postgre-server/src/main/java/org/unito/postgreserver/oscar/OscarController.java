package org.unito.postgreserver.oscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.oscar.model.OscarCeremony;

import java.util.List;

@RestController
@RequestMapping("/oscar")
class OscarController {
    @Autowired
    private OscarService oscarService;

    @GetMapping("/ceremonies")
    public List<OscarCeremony> getCeremoniesByYear(@RequestParam int year) {
        return oscarService.getCeremoniesWithNominationsByYear(year);
    }

    @GetMapping("/movies")
    public List<OscarCeremony> getCeremoniesByMovie(@RequestParam String movie) {
        return oscarService.getCeremoniesByMovie(movie);
    }

    @GetMapping("/person")
    public List<OscarCeremony> getCeremoniesByPerson(@RequestParam String person) {
        return oscarService.getCeremoniesByPerson(person);
    }

    @GetMapping("/winners")
    public List<OscarCeremony> getWinningCeremonies(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer number) {
        return oscarService.getWinningCeremonies(year, number);
    }

    @GetMapping("/winners/category")
    public List<OscarCeremony> getWinnersByCategory(@RequestParam String category) {
        return oscarService.getWinnersByCategory(category);
    }

    @GetMapping("/category")
    public List<OscarCeremony> getCeremoniesByCategory(@RequestParam String category) {
        return oscarService.getCeremoniesByCategory(category);
    }
}

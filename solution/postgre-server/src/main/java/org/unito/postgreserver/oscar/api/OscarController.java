package org.unito.postgreserver.oscar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.oscar.dto.OscarFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

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
    public Map<String, Object> getOscars(
            @ModelAttribute GenericFilterDTO genericFilter,
            @ModelAttribute OscarFilterDTO oscarFilter) {
        return oscarService.getOscarByFilters(genericFilter, oscarFilter);
    }
}

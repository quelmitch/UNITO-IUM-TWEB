package org.unito.postgreserver.oscar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.oscar.dto.OscarFilterDTO;
import org.unito.postgreserver.oscar.model.Oscar;

import java.util.List;

@RestController
@RequestMapping("/oscar")
class OscarController {

    private final OscarService oscarService;

    @Autowired
    public OscarController(OscarService oscarService) {
        this.oscarService = oscarService;
    }

    @GetMapping("/filter")
    public List<Oscar> getCeremoniesWithFilter(@ModelAttribute OscarFilterDTO filter) {
        return oscarService.getCeremoniesWithFilter(filter);
    }
}

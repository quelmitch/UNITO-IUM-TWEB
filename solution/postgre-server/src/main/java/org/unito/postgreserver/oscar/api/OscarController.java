package org.unito.postgreserver.oscar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.oscar.model.OscarCeremony;
import org.unito.postgreserver.oscar.dto.OscarDTO;
import org.unito.postgreserver.oscar.dto.OscarFilterDTO;

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
    public List<OscarDTO> getCeremoniesWithFilter(@ModelAttribute OscarFilterDTO filter) {
        return oscarService.getCeremoniesWithFilter(filter);
    }
}

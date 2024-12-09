package org.unito.postgreserver.oscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OscarService {
    private OscarRepository oscarRepository;

    @Autowired
    public OscarService(OscarRepository oscarRepository) {
        this.oscarRepository = oscarRepository;
    }
}

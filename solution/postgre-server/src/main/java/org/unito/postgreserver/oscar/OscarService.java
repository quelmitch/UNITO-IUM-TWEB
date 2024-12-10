package org.unito.postgreserver.oscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OscarService {
    private OscarRepository oscarRepository;

    @Autowired
    public OscarService(OscarRepository oscarRepository) {
        this.oscarRepository = oscarRepository;
    }

    public List<OscarCeremony> getAllCeremonies() {
        return null; //oscarRepository.findAll();
    }

    public Map<Integer, List<OscarNomination>> getWinnersGroupedByCeremony() {
        List<OscarNomination> winners = oscarRepository.findByWinnerTrue();
        return winners.stream().collect(Collectors.groupingBy(nomination -> nomination.getCeremony().getNumber()));
    }

    public List<OscarNomination> getNominationsByCeremony(int ceremonyNumber) {
        return oscarRepository.findByCeremony_Number(ceremonyNumber);
    }

    public List<OscarNomination> getNominationsByMovie(String movieTitle) {
        return oscarRepository.findByMovie(movieTitle);
    }

    public List<OscarNomination> getNominationsByPerson(String personName) {
        return oscarRepository.findByPerson(personName);
    }

    public List<OscarNomination> getWinnersByCategory(String category) {
        return oscarRepository.findByCategoryAndWinnerTrue(category);
    }

    public List<OscarNomination> getNominationsByCategory(String category) {
        return oscarRepository.findByCategory(category);
    }
}

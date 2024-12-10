package org.unito.postgreserver.oscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class OscarService {
    @Autowired
    private OscarRepository oscarRepository;

    public List<OscarCeremony> getCeremoniesWithNominationsByYear(int year) {
        return oscarRepository.findCeremoniesWithNominationsByYear(year);
    }

    public List<OscarCeremony> getCeremoniesByMovie(String movie) {
        return oscarRepository.findCeremoniesByMovie(movie);
    }

    public List<OscarCeremony> getCeremoniesByPerson(String person) {
        return oscarRepository.findCeremoniesByPerson(person);
    }

    public List<OscarCeremony> getWinningCeremonies(Integer year, Integer number) {
        return oscarRepository.findWinningCeremonies(year, number);
    }

    public List<OscarCeremony> getWinnersByCategory(String category) {
        return oscarRepository.findWinnersByCategory(category);
    }

    public List<OscarCeremony> getCeremoniesByCategory(String category) {
        return oscarRepository.findCeremoniesByCategory(category);
    }
}


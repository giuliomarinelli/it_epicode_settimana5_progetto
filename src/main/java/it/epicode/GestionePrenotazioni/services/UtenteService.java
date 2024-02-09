package it.epicode.GestionePrenotazioni.services;

import it.epicode.GestionePrenotazioni.entities.Utente;
import it.epicode.GestionePrenotazioni.repositories.UtenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UtenteService {

    private static final Logger logger = LoggerFactory.getLogger(UtenteService.class);
    @Autowired
    private UtenteRepository utenteRp;

    public void save(Utente p) {
        try {
            utenteRp.save(p);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<Utente> findAll() {
        try {
            return utenteRp.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Utente findById(UUID id) {
        try {
            Optional<Utente> opt = utenteRp.findById(id);
            return opt.orElse(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Utente findById(String id) {
        return findById(UUID.fromString(id));
    }

    public void deleteById(UUID id) {
        try {
            utenteRp.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteById(String id) {
        deleteById(UUID.fromString(id));
    }

    // Sono metodi da utilizzare con cautela perché in caso di errore l'operazione non è atomica, ma possono semplificare
    // la stesura di codice successiva

    public void saveMany(Utente... u) { //array
        for (Utente ut :u) {
            save(ut);
        }
    }

    public List<Utente> findManyById(String... ids) {
        List<Utente> lu = new ArrayList<>();
        for (String id: ids) {
            lu.add(findById(id));
        }
        return lu;
    }

    public void deleteManyById(String... ids) {
        for (String id: ids) {
            deleteById(id);
        }
    }


}

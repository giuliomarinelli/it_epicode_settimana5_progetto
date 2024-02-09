package it.epicode.GestionePrenotazioni.services;


import it.epicode.GestionePrenotazioni.entities.Edificio;
import it.epicode.GestionePrenotazioni.repositories.EdificioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class EdificioService {

    private static final Logger logger = LoggerFactory.getLogger(EdificioService.class);
    @Autowired
    private EdificioRepository edificioRp;

    public void save(Edificio e) {
        try {
            edificioRp.save(e);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public List<Edificio> findAll() {
        try {
            return edificioRp.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Edificio findById(UUID id) {
        try {
            Optional<Edificio> opt = edificioRp.findById(id);
            return opt.orElse(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Edificio findById(String id) {
        return findById(UUID.fromString(id));
    }

    public void deleteById(UUID id) {
        try {
            edificioRp.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteById(String id) {
        deleteById(UUID.fromString(id));
    }

    // Sono metodi da utilizzare con cautela perché in caso di errore l'operazione non è atomica, ma possono semplificare
    // la stesura di codice successiva

    public void saveMany(Edificio... e) { //array
        for (Edificio ed :e) {
            save(ed);
        }
    }

    public List<Edificio> findManyById(String... ids) {
        List<Edificio> le = new ArrayList<>();
        for (String id: ids) {
            le.add(findById(id));
        }
        return le;
    }

    public void deleteManyById(String... ids) {
        for (String id: ids) {
            deleteById(id);
        }
    }


}

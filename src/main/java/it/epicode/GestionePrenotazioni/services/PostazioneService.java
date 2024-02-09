package it.epicode.GestionePrenotazioni.services;


import it.epicode.GestionePrenotazioni.entities.Postazione;
import it.epicode.GestionePrenotazioni.enums.TipoPostazione;
import it.epicode.GestionePrenotazioni.repositories.PostazioneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostazioneService {

    private static final Logger logger = LoggerFactory.getLogger(PostazioneService.class);
    @Autowired
    private PostazioneRepository postazioneRp;

    public List<Postazione> cercaPostazione(TipoPostazione tipo, String citta) {
        try {
            return postazioneRp.ricercaPostazioniPerTipoECitta(tipo, citta.toLowerCase());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public void save(Postazione p) {
        try {
            postazioneRp.save(p);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<Postazione> findAll() {
        try {
            return postazioneRp.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Postazione findById(UUID id) {
        try {
            Optional<Postazione> opt = postazioneRp.findById(id);
            return opt.orElse(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Postazione findById(String id) {
        return findById(UUID.fromString(id));
    }

    public void deleteById(UUID id) {
        try {
            postazioneRp.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteById(String id) {
        deleteById(UUID.fromString(id));
    }

    // Sono metodi da utilizzare con cautela perché in caso di errore l'operazione non è atomica, ma possono semplificare
    // la stesura di codice successiva

    public void saveMany(Postazione... p) { //array
        for (Postazione po : p) {
            save(po);
        }
    }

    public List<Postazione> findManyById(String... ids) {
        List<Postazione> le = new ArrayList<>();
        for (String id : ids) {
            le.add(findById(id));
        }
        return le;
    }

    public void deleteManyById(String... ids) {
        for (String id : ids) {
            deleteById(id);
        }
    }


}

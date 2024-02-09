package it.epicode.GestionePrenotazioni.services;

import it.epicode.GestionePrenotazioni.entities.Postazione;
import it.epicode.GestionePrenotazioni.entities.Prenotazione;
import it.epicode.GestionePrenotazioni.entities.Utente;
import it.epicode.GestionePrenotazioni.repositories.PrenotazioneRepository;
import it.epicode.GestionePrenotazioni.repositories.UtenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class PrenotazioneService {

    private static final Logger logger = LoggerFactory.getLogger(PrenotazioneService.class);
    @Autowired
    private PrenotazioneRepository prenotazioneRp;

    @Autowired
    private UtenteRepository utenteRp;

    public void effettuaPrenotazione(LocalDate data, Utente u, Postazione p) {

        if (LocalDate.now().isAfter(data)) {
            logger.error("Impossibile prenotare. Devi inserire una data che sia almeno quella di oggi");
            return;
        }

        if (!u.getPrenotazioni().isEmpty() && u.getPrenotazioni().stream()
                .map(Prenotazione::getDataPrenotazione).toList().contains(data)) {
            logger.error("L'utente ha già una prenotazione attiva per quella data");
            return;
        }

        if (prenotazioneRp.findByPostazione(p).stream().map(Prenotazione::getDataPrenotazione).toList().contains(data)) {
            logger.error("La postazione richiesta per questa data non è disponibile, impossibile prenotare");
            return;
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataPrenotazione(data);
        prenotazione.setPostazione(p);
        try {
            prenotazioneRp.save(prenotazione);
            u.getPrenotazioni().add(prenotazione);
            utenteRp.save(u);
        } catch (Exception e) {
            logger.error("Si è verificato un errore imporevisto, impossibile prenotare: " + e.getMessage());
        }
        logger.info("Prenotazione dell'utente " + u.getNomeCompleto() + " effettuata con successo per la data " + data + " per la postazione " +
                p.getDescrizione() + " nell'edificio " + p.getEdificio().getNome() + ", " + p.getEdificio().getIndirizzo());

    }

    public void annullaPrenotazione(LocalDate data, Utente utente) {/* Un utente se tutto funziona può avere una sola prenotazione
                                                                per data, pertanto, se la prenotazione non è associata ad un evento già
                                                                passato, viene effettuata una cancellazione "sicura" in modo da non
                                                                generare errori causati dalla violazione dell'integrità referenziale */
        if (LocalDate.now().isAfter(data)) {
            logger.error("La data della prenotazione è superata");
            return;
        }

        List<Prenotazione> pl = utente.getPrenotazioni().stream()
                .filter(p -> p.getDataPrenotazione().equals(data)).toList();

        if (pl.isEmpty()) {
            logger.error("Non ci sono prenotazioni per questa data");
            return;
        }
        if (pl.size() > 1) {
            logger.error("Si è verificato un errore grave e imprevisto, impossobile cancellare prenotazioni per questa data");
            return;
        }

        try {
            Prenotazione prenotazione = pl.getFirst();
            utente.getPrenotazioni().remove(prenotazione);
            utenteRp.save(utente);
            prenotazioneRp.delete(prenotazione);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        logger.info("La prenotazione è stata cancellata con successo");
    }


    public List<Prenotazione> findAll() {
        try {
            return prenotazioneRp.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Prenotazione findById(UUID id) {
        try {
            Optional<Prenotazione> opt = prenotazioneRp.findById(id);
            return opt.orElse(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Prenotazione findById(String id) {
        return findById(UUID.fromString(id));
    }



    public List<Prenotazione> findManyById(String... ids) {
        List<Prenotazione> le = new ArrayList<>();
        for (String id: ids) {
            le.add(findById(id));
        }
        return le;
    }






}

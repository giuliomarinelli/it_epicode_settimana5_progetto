package it.epicode.GestionePrenotazioni.repositories;

import it.epicode.GestionePrenotazioni.entities.Postazione;
import it.epicode.GestionePrenotazioni.entities.Prenotazione;
import it.epicode.GestionePrenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

    public List<Prenotazione> findByPostazione(Postazione postazione);
}

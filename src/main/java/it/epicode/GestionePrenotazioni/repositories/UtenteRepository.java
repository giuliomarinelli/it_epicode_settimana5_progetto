package it.epicode.GestionePrenotazioni.repositories;

import it.epicode.GestionePrenotazioni.entities.Prenotazione;
import it.epicode.GestionePrenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {

}

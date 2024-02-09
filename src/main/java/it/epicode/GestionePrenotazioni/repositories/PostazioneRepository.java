package it.epicode.GestionePrenotazioni.repositories;


import it.epicode.GestionePrenotazioni.entities.Postazione;
import it.epicode.GestionePrenotazioni.enums.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {
    @Query("SELECT p FROM Postazione p WHERE p.tipo = :tipo AND LOWER(p.edificio.citta) LIKE CONCAT('%', :citta, '%')")
    public List<Postazione> ricercaPostazioniPerTipoECitta(TipoPostazione tipo, String citta);
}

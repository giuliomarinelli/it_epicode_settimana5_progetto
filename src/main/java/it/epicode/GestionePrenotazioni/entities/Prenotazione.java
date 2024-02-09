package it.epicode.GestionePrenotazioni.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private LocalDate dataPrenotazione;

    @ManyToMany(mappedBy = "prenotazioni", fetch = FetchType.EAGER)
    private List<Utente> utente = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dataPrenotazione=" + dataPrenotazione +
                ", postazione=" + postazione +
                '}';
    }
}

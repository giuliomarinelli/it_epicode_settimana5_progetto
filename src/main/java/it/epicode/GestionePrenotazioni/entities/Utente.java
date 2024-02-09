package it.epicode.GestionePrenotazioni.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Table(name = "utenti")
public class Utente { // NON deve avere prenotazioni con la stessa data

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String username;

    private String nomeCompleto;

    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "prenotazioni_utenti",
            joinColumns = @JoinColumn(name = "utente_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "prenotazione_id", nullable = false)
    )
    private List<Prenotazione> prenotazioni = new ArrayList<>();

}

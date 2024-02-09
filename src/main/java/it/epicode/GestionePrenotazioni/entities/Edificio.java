package it.epicode.GestionePrenotazioni.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Entity
@Data
@Table(name = "edifici")
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String nome;

    private String indirizzo;

    private String citta;

    @OneToMany(mappedBy = "edificio", fetch = FetchType.EAGER)
    private List<Postazione> postazioni;

    @Override
    public String toString() {
        return "Edificio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}

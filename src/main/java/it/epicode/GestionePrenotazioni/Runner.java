package it.epicode.GestionePrenotazioni;

import it.epicode.GestionePrenotazioni.entities.Edificio;
import it.epicode.GestionePrenotazioni.entities.Postazione;
import it.epicode.GestionePrenotazioni.entities.Utente;
import it.epicode.GestionePrenotazioni.enums.TipoPostazione;
import it.epicode.GestionePrenotazioni.services.EdificioService;
import it.epicode.GestionePrenotazioni.services.PostazioneService;
import it.epicode.GestionePrenotazioni.services.PrenotazioneService;
import it.epicode.GestionePrenotazioni.services.UtenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Runner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);
    @Autowired
    private EdificioService edificioSvc;

    @Autowired
    private PostazioneService postazioneSvc;

    @Autowired
    private PrenotazioneService prenotazioneSvc;

    @Autowired
    private UtenteService utenteSvc;

    @Override
    public void run(String... args) throws Exception {

//        Edificio h3 = new Edificio();
//        h3.setNome("H3");
//        h3.setIndirizzo("Salita Monte Valerio 3");
//        h3.setCitta("Trieste");
//
//        Edificio c11 = new Edificio();
//        c11.setNome("C11");
//        c11.setIndirizzo("Salita Monte Valerio 3");
//        c11.setCitta("Trieste");
//
//        Edificio p = new Edificio();
//        p.setNome("Palazzo Montecitorio");
//        p.setIndirizzo("Piazza Monte Citorio");
//        p.setCitta("Roma");
//
//        Edificio f = new Edificio();
//        f.setNome("Farmacia Comelli");
//        f.setIndirizzo("Via Venudo 3");
//        f.setCitta("San Michele aL tagliamento VE");
//
//        edificioSvc.saveMany(c11, h3, p, f);
//
//        Postazione p1 = new Postazione();
//        p1.setDescrizione("Laboratorio chimico");
//        p1.setTipo(TipoPostazione.OPENSPACE);
//        p1.setNumeroMassimoOccupanti(5);
//        p1.setEdificio(edificioSvc.findById("5611ca48-4368-4f09-8a69-1dbf1adad23b"));
//
//        Postazione p2 = new Postazione();
//        p2.setDescrizione("Aula conferenze");
//        p2.setTipo(TipoPostazione.SALA_RIUNIONI);
//        p2.setNumeroMassimoOccupanti(100);
//        p2.setEdificio(edificioSvc.findById("5611ca48-4368-4f09-8a69-1dbf1adad23b"));
//        postazioneSvc.save(p2);
//
//        Postazione p3 = new Postazione();
//        p3.setNumeroMassimoOccupanti(6);
//        p3.setDescrizione("Ufficio Titolare");
//        p3.setTipo(TipoPostazione.PRIVATO);
//        p3.setEdificio(edificioSvc.findById("87b594b3-096f-402b-a5fa-ac3a5636ea2f"));
//
//        Postazione p4 = new Postazione();
//        p4.setDescrizione("Commissione parlamentare");
//        p4.setTipo(TipoPostazione.PRIVATO);
//        p4.setNumeroMassimoOccupanti(40);
//        p4.setEdificio(edificioSvc.findById("1538ee30-77cb-4c99-b6d9-edf95a859e4f"));

//        Postazione p5 = new Postazione();
//        p5.setDescrizione("Laboratorio fisico");
//        p5.setTipo(TipoPostazione.OPENSPACE);
//        p5.setNumeroMassimoOccupanti(30);
//        p5.setEdificio(edificioSvc.findById("5611ca48-4368-4f09-8a69-1dbf1adad23b"));
//
//
//        postazioneSvc.saveMany(p1, p2, p3, p4, p5);
//
//        prenotazioneSvc.effettuaPrenotazione(LocalDate.now(), utenteSvc.findById("7f1356b9-0606-4a7c-a19b-4d060a2e72f2"),
//                postazioneSvc.findById("92e30003-76f1-49cc-b862-1a0220728821"));
// Faccio domanda di prenotazione in data diversa per una stessa postazione
//        prenotazioneSvc.effettuaPrenotazione(LocalDate.now().plusDays(1), utenteSvc.findById("7f1356b9-0606-4a7c-a19b-4d060a2e72f2"),
//                postazioneSvc.findById("92e30003-76f1-49cc-b862-1a0220728821"));
// Faccio domanda di prenotazione con lo stesso utente nella stessa data per una postazione diversa
//        prenotazioneSvc.effettuaPrenotazione(LocalDate.now(), utenteSvc.findById("7f1356b9-0606-4a7c-a19b-4d060a2e72f2"),
//                postazioneSvc.findById("f8c8047c-791b-40ce-9f9e-e08d3bbfafb5"));
        // Faccio domanda di prenotazione con un utente diverso ma per la stessa postyazione in una stessa data
//        prenotazioneSvc.effettuaPrenotazione(LocalDate.now(), utenteSvc.findById("44ea58dd-defd-4d65-8108-62308eba618b"),
//                postazioneSvc.findById("f8c8047c-791b-40ce-9f9e-e08d3bbfafb5"));
//        prenotazioneSvc.effettuaPrenotazione(LocalDate.now(), utenteSvc.findById("295a2df1-547c-410b-9696-1a1592239e30"),
//                postazioneSvc.findById("f8c8047c-791b-40ce-9f9e-e08d3bbfafb5"));
        // stesso utente e postazione, ma il giorno dopo
//        prenotazioneSvc.effettuaPrenotazione(LocalDate.now().plusDays(1), utenteSvc.findById("295a2df1-547c-410b-9696-1a1592239e30"),
//                postazioneSvc.findById("f8c8047c-791b-40ce-9f9e-e08d3bbfafb5"));
        postazioneSvc.cercaPostazione(TipoPostazione.OPENSPACE, "trieste").forEach(p -> logger.info(p.toString()));
//        prenotazioneSvc.annullaPrenotazione(LocalDate.parse("2024-02-10"), utenteSvc.findById("295a2df1-547c-410b-9696-1a1592239e30"));

        prenotazioneSvc.effettuaPrenotazione(LocalDate.now().plusDays(11), utenteSvc.findById("295a2df1-547c-410b-9696-1a1592239e30"),
               postazioneSvc.findById("86602b52-6549-4c21-b3db-1d1c12a34e42"));

    }
}

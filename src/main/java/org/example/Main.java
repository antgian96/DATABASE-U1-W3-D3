package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // Creazione dell'EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventoPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Iniziare la transazione
            transaction.begin();

            // Creazione della Location
            Location location = new Location();
            location.setNome("Palazzo dei Congressi");
            location.setCitta("Roma");
            em.persist(location);  // Salvare la location nel database

            // Creazione dell'Evento
            Evento evento = new Evento();
            evento.setNome("Conferenza sulla tecnologia");
            evento.setData(new Date()); // Impostare la data attuale
            evento.setLocation(location); // Associare la location
            em.persist(evento);  // Salvare l'evento nel database

            // Creazione della Persona
            PERSONA persona = new PERSONA();
            persona.setNome("Mario");
            persona.setCognome("Rossi");
            persona.setEmail("mario.rossi@example.com");
            persona.setDataDiNascita(new Date(90, 5, 10)); // Data di nascita (sintassi deprecata)
            persona.setSesso(Sesso.M);
            em.persist(persona);  // Salvare la persona nel database

            // Creazione della Partecipazione
            Partecipazione partecipazione = new Partecipazione();
            partecipazione.setPersona(persona);  // Associare la persona
            partecipazione.setEvento(evento);    // Associare l'evento
            partecipazione.setStato(Partecipazione.Stato.CONFERMATA);  // Stato confermato
            em.persist(partecipazione);  // Salvare la partecipazione nel database

            // Commit della transazione
            transaction.commit();

            System.out.println("Dati salvati con successo!");

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Rollback in caso di errore
            }
            e.printStackTrace();
        } finally {
            // Chiusura dell'EntityManager
            em.close();
            emf.close();
        }
    }
}

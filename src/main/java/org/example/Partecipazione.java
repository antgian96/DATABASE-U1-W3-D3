package org.example;

import javax.persistence.*;

@Entity
public class Partecipazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PERSONA persona;

    @ManyToOne
    private Evento evento;

    @Enumerated(EnumType.STRING)
    private Stato stato;

    public enum Stato {
        CONFERMATA, DA_CONFERMARE
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PERSONA getPersona() {
        return persona;
    }

    public void setPersona(PERSONA persona) {
        this.persona = persona;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}

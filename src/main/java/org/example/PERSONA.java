package org.example;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// Classe di entità Persona
@Entity
public class PERSONA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dataDiNascita;

    @Enumerated(EnumType.STRING)
    private Sesso sesso;

    @OneToMany(mappedBy = "persona")
    private List<Partecipazione> partecipazioni;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    public List<Partecipazione> getPartecipazioni() {
        return partecipazioni;
    }

    public void setPartecipazioni(List<Partecipazione> partecipazioni) {
        this.partecipazioni = partecipazioni;
    }
}

// Enum Sesso
enum Sesso {
    M, F
}

// Classe DAO per la gestione delle operazioni su Persona
class PersonaDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PersonaDAO() {
        emf = Persistence.createEntityManagerFactory("eventoPU");
        em = emf.createEntityManager();
    }

    // Metodo per salvare una Persona nel database
    public void savePersona(PERSONA persona) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(persona);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;  // Propagare l'eccezione
        }
    }

    // Metodo per trovare una Persona per ID
    public PERSONA findPersona(Long id) {
        return em.find(PERSONA.class, id);
    }

    // Metodo per chiudere l'EntityManager
    public void close() {
        em.close();
        emf.close();
    }
}


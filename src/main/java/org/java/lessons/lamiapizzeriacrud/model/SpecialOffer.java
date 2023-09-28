package org.java.lessons.lamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Offer")
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate finishDate;
    @NotBlank
    @Size(min = 2, message = "Inserisci un titolo di almeno 2 caratteri.")
    private String offerTitle;


    @OneToMany(mappedBy = "specialOffer")
    public List<Pizza> pizze;

    //constructor
    public SpecialOffer(int id, LocalDate startDate, LocalDate finishDate, String offerTitle, List<Pizza> pizze) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.offerTitle = offerTitle;
        this.pizze = pizze;
    }

    //constructor default
    public SpecialOffer() {
    }

    //getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public List<Pizza> getPizze() {
        return pizze;
    }

    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }

    public boolean isActive() {
        LocalDate currentDate = LocalDate.now();
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(finishDate);
    }
}

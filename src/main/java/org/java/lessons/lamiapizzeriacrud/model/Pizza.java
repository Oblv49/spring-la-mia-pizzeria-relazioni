package org.java.lessons.lamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pizze")
public class Pizza {
    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Inserisci un nome che sia minimo 5 caratteri.")
    @Size(min = 5, max = 100)
    @Column(name = "nome", length = 100)
    private String name;
    @NotBlank
    @Size(min = 10, max = 150, message = "Inserisci un nome che sia minimo 5 caratteri.")
    @Column(length = 150)
    private String description;
    @NotNull
    @DecimalMin(value = "1.00", message = "Il prezzo deve essere maggiore di 1")
    @DecimalMax(value = "999.99", message = "Il prezzo non può superare 999.99")
    @Digits(integer = 3, fraction = 2, message = "Il prezzo deve avere al massimo due decimali")
    @Column(name = "price", precision = 5, scale = 2)
    private BigDecimal price;
    @NotBlank
    private String image;


    //Default Constructor
    public Pizza() {
    }

    //Constructor
    public Pizza(int id, String name, String description, BigDecimal price, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}

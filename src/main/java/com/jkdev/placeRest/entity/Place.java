package com.jkdev.placeRest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "places")
public class Place {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "localisation")
    private String localisation;
    @Column(name = "openingHours")
    private String openingHours;
    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "place", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Opinion> opinionList;

    public Place(Integer id, String name, String localisation, String openingHours, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.localisation = localisation;
        this.openingHours = openingHours;
        this.phone = phoneNumber;
    }

    public void addOpinion(Opinion opinion) {
        if (opinionList == null) {
            opinionList = new ArrayList<>();
        }
        opinion.setPlace(this);
        opinionList.add(opinion);
    }

    public void removeOpinion(Opinion opinion) {
        opinionList.remove(opinion);
    }

}


package com.jkdev.placeRest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
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
    private List<Opinion> opinionList;

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


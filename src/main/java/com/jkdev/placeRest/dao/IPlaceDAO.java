package com.jkdev.placeRest.dao;

import com.jkdev.placeRest.entity.Opinion;
import com.jkdev.placeRest.entity.Place;

import java.util.List;

public interface IPlaceDAO {

    List<Place> getPlaces();

    Place getPlace(int theId);

    void deletePlace(int theId);

    void savePlace(Place place);

    void updatePlace(Place place);

    void addOpinionByPlaceId(int id, Opinion opinion);

    List<Opinion> getOpinions(int placeId);

    void removeOpinionByPlaceId(int placeId, int opinionId);

    void updateOpinionByPlaceId(int placeId, int opinionId, Opinion opinion);

    Place getBestRatedPlaces();
}

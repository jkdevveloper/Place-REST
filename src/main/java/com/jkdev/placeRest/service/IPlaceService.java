package com.jkdev.placeRest.service;

import com.jkdev.placeRest.entity.Opinion;
import com.jkdev.placeRest.entity.Place;

import java.util.List;

public interface IPlaceService {
    List<Place> getPlaces();

    void savePlace(Place place);

    Place getPlace(int id);

    void deletePlace(int id);

    void updatePlace(Place place);

    void addOpinionToPlace(int id, Opinion opinion);

    void removeOpinionByPlaceId(int id, int opinionId);

    List<Opinion> getOpinionsByPlaceId(int id);

    void updateOpinionByPlaceId(int placeId, int opinionId, Opinion opinion);
}
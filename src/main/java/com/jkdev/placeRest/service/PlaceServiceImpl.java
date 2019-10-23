package com.jkdev.placeRest.service;

import com.jkdev.placeRest.entity.Opinion;
import com.jkdev.placeRest.entity.Place;
import com.jkdev.placeRest.dao.IPlaceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements IPlaceService {

    private final IPlaceDAO placeDAO;

    public PlaceServiceImpl(@Autowired IPlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    @Override
    public List<Place> getPlaces() {
        return placeDAO.getPlaces();
    }

    @Override
    public void savePlace(Place place) {
        placeDAO.savePlace(place);
    }

    @Override
    public Place getPlace(int id) {
        return placeDAO.getPlace(id);
    }

    @Override
    public void deletePlace(int id) {
        placeDAO.deletePlace(id);
    }

    @Override
    public void updatePlace(Place place) {
        placeDAO.updatePlace(place);
    }

    @Override
    public void addOpinionToPlace(int id, Opinion opinion) {
        placeDAO.addOpinionByPlaceId(id, opinion);
    }

    @Override
    public void removeOpinionByPlaceId(int id, int opinionId) {
        placeDAO.removeOpinionByPlaceId(id, opinionId);
    }

    @Override
    public List<Opinion> getOpinionsByPlaceId(int id) {
        return placeDAO.getOpinions(id);
    }

    @Override
    public void updateOpinionByPlaceId(int placeId, int opinionId, Opinion opinion) {
        placeDAO.updateOpinionByPlaceId(placeId, opinionId, opinion);
    }
}

package com.jkdev.placeRest.controller;

import com.jkdev.placeRest.entity.Opinion;
import com.jkdev.placeRest.entity.Place;
import com.jkdev.placeRest.exception.OpinionNotFoundException;
import com.jkdev.placeRest.exception.PlaceAlreadyAddedException;
import com.jkdev.placeRest.exception.PlaceNotFoundException;
import com.jkdev.placeRest.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController("/api")
public class PlaceController {

    private final IPlaceService placeService;

    public PlaceController(@Autowired IPlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/places")
    public List<Place> getPlaces() {
        if(placeService.getPlaces() == null){
            throw new PlaceNotFoundException("No places found");
        }
        return placeService.getPlaces();

    }

    @GetMapping("/places/{placeId}")
    public Place getPlace(@PathVariable int placeId) {

        Place place = placeService.getPlace(placeId);

        if (place == null) {
            throw new PlaceNotFoundException("Place id not found - " + placeId);
        }

        return place;
    }

    @GetMapping("places/{placeId}/opinions")
    public List<Opinion> getOpinionsByPlaceId(@PathVariable int placeId) {
        if (placeService.getPlace(placeId) == null) {
            throw new PlaceNotFoundException("Place id not found - " + placeId);
        } else
            return placeService.getOpinionsByPlaceId(placeId);
    }

    @GetMapping("places/{placeId}/opinions/{opinionId}")
    public Opinion getOpinionById(@PathVariable int placeId, @PathVariable int opinionId) {
        Place place = placeService.getPlace(placeId);

        if (place == null) {
            throw new PlaceNotFoundException("Place id not found - " + placeId);
        } else
            for (Opinion tempOpinion : placeService.getOpinionsByPlaceId(placeId)) {
                if (tempOpinion.getId() == opinionId) {
                    return tempOpinion;
                }
            }
        throw new OpinionNotFoundException("Opinion with id " + opinionId + " not found!");
    }

    @PostMapping("places/{placeId}/opinions")
    public Opinion addOpinion(@RequestBody Opinion opinion, @PathVariable int placeId) {
        if (placeService.getPlace(placeId) == null) {
            throw new PlaceNotFoundException("Place id not found - " + placeId);
        } else
            placeService.addOpinionToPlace(placeId, opinion);

        return opinion;
    }

    @PostMapping("/places")
    public Place addPlace(@RequestBody Place place) {
        for (Place servicePlace : placeService.getPlaces()) {
            if (place.getName().equals(servicePlace.getName())) {
                throw new PlaceAlreadyAddedException("Place is already in service!");
            }
        }
        place.setId(0);

        placeService.savePlace(place);

        return place;
    }


    @PutMapping("/places")
    public Place updatePlace(@RequestBody Place place) {
        if(placeService.getPlace(place.getId()) == null){
            throw new PlaceNotFoundException("Place not found");
        }
        placeService.updatePlace(place);

        return place;
    }

    @PutMapping("places/{placeId}/opinions/{opinionId}")
    public String updateOpinion(@RequestBody Opinion opinion, @PathVariable int placeId, @PathVariable int opinionId) {
        Place place = placeService.getPlace(placeId);

        if (place == null) {
            throw new PlaceNotFoundException("Place id not found - " + placeId);
        } else
            for (Opinion tempOpinion : placeService.getOpinionsByPlaceId(placeId)) {
                if (tempOpinion.getId() == opinionId) {
                    placeService.updateOpinionByPlaceId(placeId, opinionId, opinion);
                    return "Opinion with id: " + opinionId + " updated successfully";
                }
            }

        throw new OpinionNotFoundException("Opinion with id " + opinionId + " not found!");
    }

    @DeleteMapping("places/{placeId}/opinions/{opinionId}")
    public String removeOpinion(@PathVariable int placeId, @PathVariable int opinionId) {
        Place place = placeService.getPlace(placeId);

        if (place == null) {
            throw new PlaceNotFoundException("Place id not found - " + placeId);
        } else
            for (Opinion tempOpinion : placeService.getOpinionsByPlaceId(placeId)) {
                if (tempOpinion.getId() == opinionId) {
                    placeService.removeOpinionByPlaceId(placeId, opinionId);
                    return "Opinion with id: " + opinionId + " deleted successfully";
                }
            }

        throw new OpinionNotFoundException("Opinion with id " + opinionId + " not found!");
    }

    @DeleteMapping("/places/{placeId}")
    public String deletePlace(@PathVariable int placeId) {
        if(placeService.getPlace(placeId) == null){
            throw new PlaceNotFoundException("Place not found");
        }
        placeService.deletePlace(placeId);

        return "Deleted place with id - " + placeId;
    }
}

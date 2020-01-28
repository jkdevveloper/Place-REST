package com.jkdev.placeRest;

import com.google.gson.Gson;
import com.jkdev.placeRest.entity.Opinion;
import com.jkdev.placeRest.entity.Place;
import com.jkdev.placeRest.service.IPlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlaceRestEndpointsTests {

    @MockBean
    private IPlaceService placeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddPlace(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");

        Gson gson = new Gson();
        try {
            mockMvc.perform(post("/places").content(gson.toJson(p)).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddPlaceFail(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        given(placeService.getPlaces()).willReturn(Collections.singletonList(p));
        Gson gson = new Gson();
        try {
        mockMvc.perform(post("/places").content(gson.toJson(p)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddOpinion(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        Opinion o = new Opinion(p, 1, "TestComment", 3.5);
        given(placeService.getPlace(1)).willReturn(p);
        Gson gson = new Gson();

        try {
            mockMvc.perform(post("/places/1/opinions").content(gson.toJson(o)).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddOpinionFail(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        given(placeService.getPlace(1)).willReturn(null);
        Opinion o = new Opinion(p, 1, "TestComment", 3.5);
        Gson gson = new Gson();
        try {
            mockMvc.perform(post("/places/1/opinions").content(gson.toJson(o)).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError())
                    .andReturn();;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAllPlaces(){
        //given
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        p.setOpinionList(new ArrayList<>());
        given(placeService.getPlaces()).willReturn(Collections.singletonList(p));

        try {
            mockMvc.perform(get("/places")).
                     andExpect(status().isOk())
                     .andExpect(content().json("[{\n" +
                             "\"id\": 1,\n" +
                             "\"name\": \"TestName\",\n" +
                             "\"localisation\": \"TestLocalisation\",\n" +
                             "\"openingHours\": \"10-18\",\n" +
                             "\"phone\": \"555666777\",\n" +
                             "\"opinionList\": []\n" +
                             "}]"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFindAllPlacesFail(){
        //given
        given(placeService.getPlaces()).willReturn(null);

        try {
            mockMvc.perform(get("/places")).
                    andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFindPlaceById(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        p.setOpinionList(new ArrayList<>());

        given(placeService.getPlace(1)).willReturn(p);

        try {
            mockMvc.perform(get("/places/1")).
                    andExpect(status().isOk())
                    .andExpect(content().json("{\n" +
                            "\"id\": 1,\n" +
                            "\"name\": \"TestName\",\n" +
                            "\"localisation\": \"TestLocalisation\",\n" +
                            "\"openingHours\": \"10-18\",\n" +
                            "\"phone\": \"555666777\",\n" +
                            "\"opinionList\": []\n" +
                            "}"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFindPlaceByIdFail(){
        given(placeService.getPlace(1)).willReturn(null);

        try {
            mockMvc.perform(get("/places/1")).
                    andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetOpinionListByPlaceId(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        p.addOpinion(new Opinion(p, 1, "TestComment", 3.5));
        given(placeService.getPlace(1)).willReturn(p);
        given(placeService.getOpinionsByPlaceId(1)).willReturn(Collections.singletonList(new Opinion(p, 1, "TestComment", 3.5)));

        try {
            mockMvc.perform(get("/places/1/opinions")).
                    andExpect(status().isOk())
                    .andExpect(content().json("[{\n" +
                            "\"id\": 1,\n" +
                            "\"comment\": \"TestComment\",\n" +
                            "\"rate\": 3.5\n" +
                            "}]"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetOpinionListByPlaceIdFail(){
        given(placeService.getPlace(1)).willReturn(null);

        try {
            mockMvc.perform(get("/places/1/opinions")).
                    andExpect(status().is4xxClientError());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOpinionById(){

        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        p.addOpinion(new Opinion(p, 1, "TestComment", 3.5));
        given(placeService.getPlace(1)).willReturn(p);
        given(placeService.getOpinionsByPlaceId(1)).willReturn(Collections.singletonList(new Opinion(p, 1, "TestComment", 3.5)));

        try {
            mockMvc.perform(get("/places/1/opinions/1")).
                    andExpect(status().isOk())
                    .andExpect(content().json("{\n" +
                            "\"id\": 1,\n" +
                            "\"comment\": \"TestComment\",\n" +
                            "\"rate\": 3.5\n" +
                            "}"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetOpinionByIdFail(){
        given(placeService.getPlace(1)).willReturn(null);

        try {
            mockMvc.perform(get("/places/1/opinions/1")).
                    andExpect(status().is4xxClientError());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeletePlace(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        given(placeService.getPlace(1)).willReturn(p);

        try {
            mockMvc.perform(delete("/places/1")).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeletePlaceFail(){
        given(placeService.getPlace(1)).willReturn(null);

        try {
            mockMvc.perform(delete("/places/1")).
                    andExpect(status().is4xxClientError());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteOpinion(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        p.addOpinion(new Opinion(p, 1, "TestComment", 3.5));
        given(placeService.getPlace(1)).willReturn(p);
        given(placeService.getOpinionsByPlaceId(1)).willReturn(Collections.singletonList(new Opinion(p, 1, "TestComment", 3.5)));

        try {
            mockMvc.perform(delete("/places/1/opinions/1")).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteOpinionFail_NoPlaceFound(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        p.addOpinion(new Opinion(p, 1, "TestComment", 3.5));
        given(placeService.getPlace(1)).willReturn(null);
        given(placeService.getOpinionsByPlaceId(1)).willReturn(Collections.singletonList(new Opinion(p, 1, "TestComment", 3.5)));

        try {
            mockMvc.perform(delete("/places/1/opinions/1")).
                    andExpect(status().is4xxClientError());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteOpinionFail_NoOpinionFound(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        p.addOpinion(new Opinion(p, 1, "TestComment", 3.5));
        given(placeService.getPlace(1)).willReturn(p);
        given(placeService.getOpinionsByPlaceId(1)).willReturn(Collections.singletonList(new Opinion(p, 7, "TestComment", 3.5)));

        try {
            mockMvc.perform(delete("/places/1/opinions/1")).
                    andExpect(status().is4xxClientError());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateOpinion(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        Opinion o = new Opinion(p, 1, "TestComment", 3.5);
        given(placeService.getPlace(1)).willReturn(p);
        given(placeService.getOpinionsByPlaceId(1)).willReturn(Collections.singletonList(new Opinion(p, 1, "TestComment", 3.5)));
        Gson gson = new Gson();

        try {
            mockMvc.perform(put("/places/1/opinions/1").content(gson.toJson(o)).contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateOpinionFail(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        Opinion o = new Opinion(p, 1, "TestComment", 3.5);
        given(placeService.getPlace(1)).willReturn(p);
        given(placeService.getOpinionsByPlaceId(1)).willReturn(Collections.singletonList(new Opinion(p, 1, "TestComment", 3.5)));
        Gson gson = new Gson();

        try {
            mockMvc.perform(put("/places/1/opinions/1").content(gson.toJson(o)).contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdatePlace(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        given(placeService.getPlace(1)).willReturn(p);
        Gson gson = new Gson();

        try {
            mockMvc.perform(put("/places").content(gson.toJson(p)).contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUpdatePlaceFail(){
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        given(placeService.getPlace(1)).willReturn(null);
        Gson gson = new Gson();

        try {
            mockMvc.perform(put("/places").content(gson.toJson(p)).contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().is4xxClientError());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

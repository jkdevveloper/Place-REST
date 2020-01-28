package com.jkdev.placeRest;

import com.jkdev.placeRest.controller.RestController;
import com.jkdev.placeRest.entity.Opinion;
import com.jkdev.placeRest.entity.Place;
import com.jkdev.placeRest.service.IPlaceService;
import com.jkdev.placeRest.service.PlaceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void testFindAllPlaces(){
        //given
        Place p = new Place(1, "TestName", "TestLocalisation", "10-18", "555666777");
        p.setOpinionList(new ArrayList<>());
        given(placeService.getPlaces()).willReturn(Collections.singletonList(p));

        try {
            mockMvc.perform(get("/places/")).
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
    public void testDeletePlace(){

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


}

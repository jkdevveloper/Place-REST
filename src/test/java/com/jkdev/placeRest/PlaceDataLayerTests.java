package com.jkdev.placeRest;

import com.jkdev.placeRest.dao.PlaceDAOImpl;
import com.jkdev.placeRest.entity.Place;
import com.jkdev.placeRest.service.PlaceServiceImpl;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PlaceDataLayerTests {


	@Mock
	private PlaceDAOImpl placeDAO;

	@InjectMocks
	private PlaceServiceImpl placeService;

	@Test
	public void testGetByIdSuccess(){
		//given
		Place p = new Place(1, "McDonalds", "Kraków", "6-22", "555666777");
		when(placeDAO.getPlace(any(Integer.class))).thenReturn(p);
		//when
		Place place	= placeService.getPlace(1);
		//then
		verify(placeDAO, times(1)).getPlace(1);
		verifyNoMoreInteractions(placeDAO);
		assertEquals(p, place);
	}

	@Test
	public void testGetByIdFail(){
		//given
		when(placeDAO.getPlace(1)).thenReturn(null);
		//when
		Place place = placeService.getPlace(1);
		//then
		verify(placeDAO, times(1)).getPlace(1);
		verifyNoMoreInteractions(placeDAO);
		assertNull(place);
	}

	@Test
	public void testGetAllPlaces(){
		//given
		when(placeDAO.getPlaces()).thenReturn(Arrays.asList(new Place(1, "McD", "Kraków", "8-18", "535999555"),
				new Place(4, "Burger King", "Kraków", "8-18", "593128065")));

		//when
		List<Place> placeList = placeService.getPlaces();

		//then
		verify(placeDAO, times(1)).getPlaces();
		verifyNoMoreInteractions(placeDAO);
		assertEquals(2, placeList.size());
		assertNotNull(placeList);
	}

	@Test
	public void testSavePlace(){
		//given
		doAnswer((Answer<Void>) invocationOnMock -> {
			Object[] args = invocationOnMock.getArguments();
			if(args != null && args.length > 0 && args[0] != null){
				Place place = (Place)args[0];
				assertEquals("Burger King", place.getName());
				assertEquals("Kraków", place.getLocalisation());
				assertEquals("8-18", place.getOpeningHours());
				assertEquals("593128065", place.getPhone());
			}
			return null;
		}).when(placeDAO).savePlace(any(Place.class));

		Place p = new Place(4, "Burger King", "Kraków", "8-18", "593128065");
		//when
		placeService.savePlace(p);
		//then
		verify(placeDAO, times(1)).savePlace(p);
		verifyNoMoreInteractions(placeDAO);
	}

	@Test
	public void testUpdatePlace(){
		//given
		doAnswer((Answer<Void>) invocation -> {
			Object[] arguments = invocation.getArguments();
			if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {

				Place place = (Place) arguments[0];
				assertEquals("Burger King", place.getName());
				assertEquals("Kraków", place.getLocalisation());
				assertEquals("8-18", place.getOpeningHours());
				assertEquals("593128065", place.getPhone());
			}
			return null;
		}).when(placeDAO).updatePlace(any(Place.class));
		Place p = new Place(4, "Burger King", "Kraków", "8-18", "593128065");
		//when
		placeService.updatePlace(p);
		//then
		verify(placeDAO, times(1)).updatePlace(p);
		verifyNoMoreInteractions(placeDAO);

	}

	@Test
	public void testDeletePlace(){
		//given
		doAnswer((Answer<Void>) invocation -> {
			Object[] arguments = invocation.getArguments();
			if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {

				Integer placeId = (Integer) arguments[0];

				assertEquals(1, placeId.intValue());
			}
			return null;
		}).when(placeDAO).deletePlace(any(Integer.class));
		//when
		placeService.deletePlace(1);
		//then
		verify(placeDAO, times(1)).deletePlace(1);
		verifyNoMoreInteractions(placeDAO);

	}

}

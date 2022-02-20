package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MovieController service;
	
	@InjectMocks
	private MovieController controller;

	@Test
	void testGetAllMovies() throws Exception {

		// set up the uri for the request
		String uri = STARTING_URI + "/movie";

		// the data that will be returned when we call the getAllMovies()
		// method from service
		List<Movie> allMovies = Arrays.asList(new Movie(1L, "Comedy Film", "Some funny film", "Comedy", "2019"),
				new Movie(1L, "Horror Film", "Some scary film", "Horror", "2009"));

		// when service.getAllMovies() is called within the controller
		// instead of actually running the method, just make sure it returns the
		// allMovies list
		when(controller.getMovies()).thenReturn(allMovies);

		mvc.perform(get(uri)) // perform the get request
				// .andDo( print() ) // print the request sent and response given
				.andExpect(status().isOk()) // expect status code to be 200
				.andExpect(jsonPath("$.length()").value(allMovies.size())) // test how many movie objects were returned
																			// (expected 2)
				.andExpect(jsonPath("$[0].id").value(allMovies.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(allMovies.get(0).getName()))
				.andExpect(jsonPath("$[0].genre").value(allMovies.get(0).getGenre()))
				.andExpect(jsonPath("$[1].id").value(allMovies.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(allMovies.get(1).getName()))
				.andExpect(jsonPath("$[1].genre").value(allMovies.get(1).getGenre()));

		// verify -> check how many interactions with code there are
		verify(service, times(1)).getMovies(); // getAllMovies() called once from service
		verifyNoMoreInteractions(service); // after checking above, check service is no longer being used
	}

	// format an object of any type to a json string
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

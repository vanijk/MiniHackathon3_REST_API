package com.glab.minihackathon3_rest_api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glab.minihackathon3_rest_api.model.Movie;
import com.glab.minihackathon3_rest_api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;
    @GetMapping("/getMovie")
    public ModelAndView getMovieDetails(String title, ModelMap map) {
        movieService.addMovie(title);
        Movie movie = movieService.getMovieDetails(title);
        map.addAttribute(movie);
        return new ModelAndView("result", map);
    }
    @GetMapping("/getMovie/{title}")
    public void insert(@PathVariable("title") String title)
    {

        try {
            String url = "http://www.omdbapi.com/?apikey=b79fdda2&t=" + title;
            RestTemplate rt = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Movie movie = mapper.readValue(rt.getForObject(url, String.class), Movie.class);
            System.out.println(movie.toString());

        }catch(Exception e){

        }
        // Print and display name and age
        System.out.println(title);

    }

   @GetMapping(value ="/movie")
    public String getMovieDetails() {
        try{
       String url = "http://www.omdbapi.com/?apikey=b79fdda2&t=barbie&y=2023";
       // String url ="http://www.omdbapi.com/?apikey=b79fdda2";
        RestTemplate rt = new RestTemplate();
      //  ResponseEntity<Object[]> responseEntity =rt.getForEntity(url, Object[].class);
       //     Object[] objects = responseEntity.getBody();
       ObjectMapper mapper = new ObjectMapper();
       mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Movie movie = mapper.readValue(rt.getForObject(url,String.class),Movie.class);
      // mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      // Object movies = mapper.readValue(url,Object.class);
        System.out.println(movie.toString());
        //Object[] movies = rt.getForObject(url,Object[].class);
       //System.out.println(rt.getForObject(url,Object[].class));
        }catch (Exception e){

        }

        return "success";
    }
}

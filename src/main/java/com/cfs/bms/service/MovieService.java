package com.cfs.bms.service;

import com.cfs.bms.dto.MovieDto;
import com.cfs.bms.exception.ResourceNotFoundException;
import com.cfs.bms.model.Movie;
import com.cfs.bms.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie =mapToEntity(movieDto);
        Movie saveMovie =movieRepository.save(movie);
        return mapToDto(saveMovie);
    }
    public MovieDto getMovieById(Long id)
    {
        Movie movie = movieRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Movie Not Found with id : " +id ));
        return mapToDto(movie);

    }

    public List<MovieDto> getAllMovies()
    {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByLanguage(String language)
    {
        List<Movie> movies = movieRepository.findByLanguage(language);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByGenre(String genre)
    {
        List<Movie> movies=movieRepository.findByGenre(genre);
        return movies.stream()
                .map(this::mapToDto)
        .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByTitle(String title)
    {
        List<Movie> movies=movieRepository.findByTitleContaining(title);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long id,MovieDto movieDto)
    {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie Not Found with id : " +id));
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setGenre(movieDto.getGenre());
        movie.setLanguage(movieDto.getLanguage());
        movie.setDurationMin(movieDto.getDurationMin()); //CP
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());

       Movie updatedMovie = movieRepository.save(movie);
       return mapToDto(updatedMovie);
    }
    public void deleteMovie(Long id)
    {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie Not Found with id : "+id));
        movieRepository.delete(movie);
    }

    private MovieDto mapToDto(Movie movie)
    {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setLanguage(movie.getLanguage());
        movieDto.setGenre(movie.getGenre());
        movieDto.setDurationMin(movie.getDurationMin());
        movieDto.setReleaseDate(movie.getReleaseDate());
        movieDto.setPosterUrl(movie.getPosterUrl());
        return  movieDto;


    }

    public Movie mapToEntity(MovieDto movieDto)
    {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setGenre(movieDto.getGenre());
        movie.setLanguage(movieDto.getLanguage());
        movie.setDurationMin(movieDto.getDurationMin()); //CP
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());
        return movie;
    }

}
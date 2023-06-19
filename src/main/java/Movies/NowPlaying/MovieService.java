package Movies.NowPlaying;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

//    @Autowired
//   private MovieData movieData;
    @Autowired
    final private MovieRepository repository;
    @Autowired
    final private FavoriteRepository favoriteRepository;
    public MovieService(MovieRepository movieRepository, FavoriteRepository favoriteRepository) {
        this.repository = movieRepository;
        this.favoriteRepository = favoriteRepository;
    }

    private  String apiUrl = "https://api.themoviedb.org/3/movie/now_playing";
    private  String apiUrl1 = "https://api.themoviedb.org/3/search/movie";    //doubt
    private final String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMmE3NjU1OGEzYWM2YWJiMDAyY2Q1YWEyOTEyMGJiMiIsInN1YiI6IjY0NzIzNWFmOTQwOGVjMDEzZTgwNGU5NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rBt1rjWyLlTB3KVt8FIY4-KwbfwPkY6BUuLXZhW3OOk";


    public void allNowPlaying() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        headers.set("Content-type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = response.getBody();
        MovieDto movieDto = objectMapper.readValue(responseBody, MovieDto.class);
        System.out.println(movieDto.getResults().get(1).getTitle());
        ArrayList<Results> resultList= movieDto.getResults();
        ArrayList<MovieData> movieDataArrayList = new ArrayList<>();
        if(!resultList.isEmpty()){
            for(int i=0 ; i<resultList.size();i++){
                MovieData movieData = new MovieData();
                movieData.setMovie_lang(resultList.get(i).getMovie_lang());
                movieData.setMovie_id(resultList.get(i).getId());
                movieData.setTitle(resultList.get(i).getTitle());
                movieData.setPopularity(resultList.get(i).getPopularity());
                movieData.setViewers(resultList.get(i).getVote_count());
                movieData.setDate_of_release(resultList.get(i).getDate_of_release());
                movieDataArrayList.add(movieData);
            }
        }
        repository.saveAll(movieDataArrayList);
        System.out.println("Saved successfully in db");
    }

    public FavoriteMovie saveFavorite(FavoriteMovie favoriteMovie){
        return(favoriteRepository.save(favoriteMovie));
    }

    public List<String> allFavorites() {
        List<FavoriteMovie> all = favoriteRepository.findAll();
        List<String> movieData = new ArrayList<>();
        for(FavoriteMovie favoriteMovie : all){
            int mediaId = favoriteMovie.getMedia_id();
            String byMovieId = repository.findByMovie_id(mediaId);
            movieData.add(byMovieId);
        }
        return movieData;
    }

    public List<MovieData> allMoviesByName(String Name) throws JsonProcessingException {
        List<MovieData> movieData = new ArrayList<>();
        movieData = repository.findByTitle(Name);
//        System.out.println(" --------->  " + movieData.size());
        if(movieData.isEmpty()){
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            headers.set("Accept", "application/json");
            headers.set("Content-type", "application/json");
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            String apiUrl2 = apiUrl1 + "?query=" +Name;
            ResponseEntity<String> response = restTemplate.exchange(apiUrl2, HttpMethod.GET, entity, String.class);
//            System.out.println("response : - "+response.getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.getBody();
            MovieDto movieDto = objectMapper.readValue(responseBody, MovieDto.class);
            ArrayList<Results> resultList= movieDto.getResults();
            ArrayList<MovieData> movieDataArrayList = new ArrayList<>();
            if(!resultList.isEmpty()){
                for(int i=0 ; i<resultList.size();i++){
                    MovieData movieData1 = new MovieData();
                    movieData1.setMovie_lang(resultList.get(i).getMovie_lang());
                    movieData1.setMovie_id(resultList.get(i).getId());
                    movieData1.setTitle(resultList.get(i).getTitle());
                    movieData1.setPopularity(resultList.get(i).getPopularity());
                    movieData1.setViewers(resultList.get(i).getVote_count());
                    movieData1.setDate_of_release(resultList.get(i).getDate_of_release());
                    movieDataArrayList.add(movieData1);
                }
            }
            repository.saveAll(movieDataArrayList);
//            System.out.println("size of array :- "+movieDataArrayList.size());
            movieData = repository.findByTitle(Name);
            System.out.println("size of array2323 :- "+movieData.size());

        }
        return movieData ;
    }
}

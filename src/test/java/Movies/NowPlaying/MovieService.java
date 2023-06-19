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

@Service
public class MovieService {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    private final String apiUrl = "https://api.themoviedb.org/3/movie/now_playing";
    private final String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMmE3NjU1OGEzYWM2YWJiMDAyY2Q1YWEyOTEyMGJiMiIsInN1YiI6IjY0NzIzNWFmOTQwOGVjMDEzZTgwNGU5NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rBt1rjWyLlTB3KVt8FIY4-KwbfwPkY6BUuLXZhW3OOk";


    public void AllNowPlaying() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        headers.set("Content-type", "application/json");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();



        // Access the response body
        String responseBody = response.getBody();
        MovieDto movieDto = objectMapper.readValue(responseBody, MovieDto.class);
//        assertEquals((94),movieEntity.getTotal_pages());
        System.out.println(movieDto.getTotal_pages());
        System.out.println(responseBody);

    }

}

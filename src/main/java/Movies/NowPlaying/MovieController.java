package Movies.NowPlaying;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/NowPlaying")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public void findAllNowPlaying() throws JsonProcessingException {
        movieService.allNowPlaying();
    }


    @PostMapping("/AddFavorite")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteMovie favoriteMovie){
//        return new ResponseEntity<>("hi ",HttpStatus.CREATED);
        return new ResponseEntity<FavoriteMovie>(movieService.saveFavorite(favoriteMovie), HttpStatus.CREATED) ;
    }

    @GetMapping("/ShowFavorite")
    public ResponseEntity<?> showAllFavorite(){
        List<String> movieData = movieService.allFavorites();
        return new ResponseEntity<>(movieData,HttpStatus.OK);
    }

    @GetMapping("/MovieByName/{Name}")
    public ResponseEntity<?> showMovieByName(@PathVariable(value = "Name") String Name ) throws JsonProcessingException {
        List<MovieData> movieData = movieService.allMoviesByName(Name);
        return new ResponseEntity<>(movieData,HttpStatus.OK);
    }
}

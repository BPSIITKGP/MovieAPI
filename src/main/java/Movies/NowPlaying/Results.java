package Movies.NowPlaying;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Results{
    public boolean adult;
    public String backdrop_path;
    public int id;
    public String title;
    @JsonProperty(value = "original_language")
    public String movie_lang;
    public String original_title;
    public String overview;
    public String poster_path;
    public String media_type;
    public ArrayList<Integer> genre_ids;
    public float popularity;
    @JsonProperty(value = "release_date")
    public String date_of_release;
    public boolean video;
    public float vote_average;
    public int vote_count;
}

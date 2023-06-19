package Movies.NowPlaying;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Data
@Entity
@Table(name = "movie_table")
public class MovieData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @Column(name = "movie_id")
    public int movie_id;
    @Column(name = "title")
    public String title;
    @Column(name = "movie_lang")
    public String movie_lang;
    @Column(name = "popularity")
    public double popularity;
    @Column(name = "date_of_release")
    public String date_of_release;
    @Column(name = "viewers")
    public int viewers;
}

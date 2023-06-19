package Movies.NowPlaying;
import lombok.Data;
import java.util.ArrayList;

@Data
public class MovieDto {
    public Dates dates;
    public int page;
    public ArrayList<Results> results;
    public int total_pages;
    public int total_results;
}





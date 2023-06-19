package Movies.NowPlaying;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "favorite_movie")
public class FavoriteMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @Column(name = "media_id")
    public int media_id;
    @Column(name = "favorite")
    public boolean favorite;
}

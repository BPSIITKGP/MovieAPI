package Movies.NowPlaying;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteMovie,Long> {


}

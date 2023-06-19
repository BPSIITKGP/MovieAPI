package Movies.NowPlaying;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//@Repository
public interface MovieRepository extends JpaRepository<MovieData, Long> {
    @Query(value = "select title from movie_table where movie_id = :media_id",nativeQuery = true)
    String findByMovie_id(@Param(value = "media_id") int media_id);

   // @Query(value = "select * from movie_table where title =:Name",nativeQuery = true)
    //List<MovieData> findByTitle(@Param("Name") String Name);
   List<MovieData> findByTitle(String Name);
}

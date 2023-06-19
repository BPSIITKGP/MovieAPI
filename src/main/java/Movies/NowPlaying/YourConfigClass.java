package Movies.NowPlaying;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
@ComponentScan({"Movies.NowPlaying"})
public class YourConfigClass {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}


package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.moviesapi.MovieInfo;
import org.superbiz.moviefun.moviesapi.MovieFixtures;
import org.superbiz.moviefun.moviesapi.MoviesClient;


import java.util.Map;

@Controller
public class HomeController {

    private final MoviesClient moviesClient;
    private final MovieFixtures movieFixtures;

    public HomeController(MoviesClient moviesClient,MovieFixtures movieFixtures) {
        this.moviesClient = moviesClient;
        this.movieFixtures = movieFixtures;
    }

/*
    public org.superbiz.moviefun.HomeController(MoviesClient moviesClient, AlbumsBean albumsBean, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.moviesClient = moviesClient;
        this.albumsBean = albumsBean;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }
*/

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movie : movieFixtures.load()) {
            moviesClient.addMovie(movie);
        }

/*
        for (Album album : albumFixtures.load()) {
            albumsBean.addAlbum(album);
        }
*/

        model.put("movies", moviesClient.getMovies());
/*
        model.put("albums", albumsBean.getAlbums());
*/

        return "setup";
    }
}

package tr.ege.edu.microservices.gr5.audiostream.collection.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Album;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Artist;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Song;
import tr.ege.edu.microservices.gr5.audiostream.collection.repository.AlbumRepository;
import tr.ege.edu.microservices.gr5.audiostream.collection.repository.ArtistRepository;
import tr.ege.edu.microservices.gr5.audiostream.collection.repository.SongRepository;
import tr.ege.edu.microservices.gr5.audiostream.collection.type.Genre;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class InitialDataConfig {
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    @PostConstruct
    public void createInitialData() {
        if (artistRepository.count() > 0) {
            return;
        }

        createBeethovenSongs();
    }

    private void createBeethovenSongs() {
        var artist = new Artist();
        artist.setName("Ludwig van Beethoven");
        artist = artistRepository.save(artist);

        // Album "Classical Hits"
        var album = new Album();
        album.setArtist(artist);
        album.setName("Classical Hits");
        album.setGenre(Genre.CLASSICAL);
        album.setYear(2021);
        album = albumRepository.save(album);

        var song = new Song();
        song.setAlbum(album);
        song.setName("Speaking Unto Nations");
        song.setDuration(302f);
        songRepository.save(song);

        song = new Song();
        song.setAlbum(album);
        song.setName("6 Lander for 2 Violins And Bass, WoO: 15 No. 1 in D Major");
        song.setDuration(54f);
        songRepository.save(song);

        song = new Song();
        song.setAlbum(album);
        song.setName("12 German Dances, WoO 8: 8. German Dance in A Major");
        song.setDuration(84f);
        songRepository.save(song);

        song = new Song();
        song.setAlbum(album);
        song.setName("Path 19 (yet frailest)");
        song.setDuration(470f);
        songRepository.save(song);

        song = new Song();
        song.setAlbum(album);
        song.setName("5 Pieces for Musical Clock, WoO 33: 3. Allegro in G Major");
        song.setDuration(129f);
        songRepository.save(song);

        // Album "XII, The Hits of Beethoven"
        album = new Album();
        album.setArtist(artist);
        album.setName("XII, The Hits of Beethoven");
        album.setYear(2021);
        album.setGenre(Genre.CLASSICAL);
        album = albumRepository.save(album);

        song = new Song();
        song.setAlbum(album);
        song.setName("Symphony No. 5 in C Minor, Op. 67 \"Fate\": I. Allegro con brio");
        song.setDuration(448f);
        songRepository.save(song);

        song = new Song();
        song.setAlbum(album);
        song.setName("Piano Sonata No. 8 in C Minor, Op. 13 \"Pathetique\": II. Adagio cantabile");
        song.setDuration(297f);
        songRepository.save(song);

        song = new Song();
        song.setAlbum(album);
        song.setName("Romance No. 2 in F Major for Violin and Orchestra, Op. 50");
        song.setDuration(521f);
    }
}

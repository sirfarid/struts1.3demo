package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artistSeqGen")
    @SequenceGenerator(name = "artistSeqGen", sequenceName = "ARTIST_SEQ", allocationSize = 1)
    @Column(name = "artist_id")
    private int artistId;

    @Column(name = "artist_name")
    private String artistName;

    public Artist() {
    }

    public Artist(String artistName) {
        this.artistName = artistName;
    }

    public Artist(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}

package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
public class Albums {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String albumName;

  @Column(length = 64, nullable = false)
  private String author;

  @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Songs> songs = new ArrayList<>();

    public void setSongs(Songs song) {
      songs.add(song);
    }

  @Builder(toBuilder = true)
  public Albums(Integer id, String albumName, String author, List<Songs> songs) {
    this.id = id;
    this.albumName = albumName;
    this.author = author;
    this.songs = songs;
  }

  //    public List<Songs> addSongToList (Songs song){
  //        List<Songs> list = new ArrayList<>();
  //        list = this.songs;
  //        list.add(song);
  //      return list;
  //    }

  //  public List<Songs> addSongToList (String title, String author, Albums album){
  //     return songs.stream().map(songs1 ->
  // songs1.toBuilder().title(title).author(author).album(album).build()).collect(Collectors.toList());
  //  }
}

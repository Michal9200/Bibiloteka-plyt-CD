package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String title;

  @Column(length = 64, nullable = false)
  private String author;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Album album;

  @Builder(toBuilder = true)
  public Song(Integer id, String title, String author, Album album) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.album = album;
  }

  @Override
  public String toString() {
    return  id + ") " +  " title: " + title +  ", author: " + author +  ", album name: " + album;
  }
}

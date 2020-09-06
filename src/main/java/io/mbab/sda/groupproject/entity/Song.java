package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String title;

  @Column(length = 64, nullable = false)
  private String author;

  @ManyToOne
  private Album album;

  @Builder(toBuilder = true)
  public Song(Integer id, String title, String author, Album album) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.album = album;
  }
}

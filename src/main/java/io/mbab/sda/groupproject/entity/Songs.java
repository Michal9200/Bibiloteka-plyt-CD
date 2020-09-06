package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Songs {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer songId;

  @Column(length = 64, nullable = false)
  private String title;

  @Column(length = 64, nullable = false)
  private String author;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id")
  private Albums album;

  @Builder(toBuilder = true)
  public Songs(String title, String author, Albums album) {
    this.title = title;
    this.author = author;
    this.album = album;
  }
}

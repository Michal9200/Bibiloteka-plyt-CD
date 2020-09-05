package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Albums {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false)
    private String albumName;

    @Column(length = 64, nullable = false)
    private String author;

    @OneToMany(mappedBy = "Albums", cascade = CascadeType.ALL)
    private String song;


}

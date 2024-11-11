package com.example.orm;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(1000)")
    private String content;

}

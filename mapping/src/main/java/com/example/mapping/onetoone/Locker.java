package com.example.mapping.onetoone;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loc;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

}

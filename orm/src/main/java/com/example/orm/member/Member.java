package com.example.orm.member;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.sql.Timestamp;

@Entity // 테이블생성
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String username;

    //CreationTimestamp --> 처음 생성할때 값 들어감. UpdateTimestamp --> 변경할 때의 값 들어감
    @CreationTimestamp
    private Date regDate; // Date 타입 역시 CreationTimestamp를 통해 현재시간을 대입할 수 있음.

    @UpdateTimestamp // 수정된 시간을 Timestamp로 대입. 만일 변경사항이 없다면 CreationTimestamp와 동일하다.
    private Date updateDate;

    @CreationTimestamp
    private Timestamp regDate2;

    @UpdateTimestamp
    private Timestamp updateDate2;



}

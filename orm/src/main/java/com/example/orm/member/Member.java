package com.example.orm.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore // 비밀번호는
    private String password;
    private String username;

    //CreationTimestamp --> 처음 생성할때 값 들어감. UpdateTimestamp --> 변경할 때의 값 들어감
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date regDate; // Date 타입 역시 CreationTimestamp를 통해 현재시간을 대입할 수 있음.

    @UpdateTimestamp // 수정된 시간을 Timestamp로 대입. 만일 변경사항이 없다면 CreationTimestamp와 동일하다.
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Timestamp regDate2;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate2;



}

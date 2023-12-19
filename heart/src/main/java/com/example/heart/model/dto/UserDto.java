package com.example.heart.model.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "UserDto")
@Table(name = "user")
public class UserDto {
    @Id // 기본키: 유니크 
    private String name;
    private String pwd;
    @Column(unique = true)
    private String email;
    // 일반사용자 / 관리자를 구분용
    private String role; 
    // 로그인 유무
    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean isLogin; 

    private List<Long> resumeIds; //Entity를 사용하지 않고 resumeId들을 List 형태로 받음

}


// Validation 추후에 추가 !
package com.example.heart.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "user")
@Entity(name = "UserEntity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String userName;
    private String userPassword;
    private String role;
    // userEmail을 로그인 아이디로 사용하겠습니다!!
    private String userEmail;
    private Boolean isLogin; 


    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResumeEntity> resumes;

    // // getPassword() 메서드 추가
    // public String getPassword() {
    //     return userPassword;
    // }

    // // setPassword() 메서드 추가
    // public void setPassword(String userPasswordd) {
    //     this.userPassword = userPasswordd;
    // }
}

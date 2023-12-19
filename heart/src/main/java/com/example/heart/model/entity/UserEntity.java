package com.example.heart.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
//JPA Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;
    private String userPassword;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    //일대다 관계 명시. 한 유저가 여러개의 이력서를 가지고 있을 수 있음을 알려줌.
    //mappedBy : 매핑 기준
    //cascade : User엔티티의 변경사항이 연관된 Resume엔티티에도 적용됨. ex) 유저가 삭제되면 해당 유저에 매핑된 resume도 전부 삭제됨
    //orphanRemoval : ??

    private List<ResumeEntity> resumes; // 일대다라서 List형태로 받음. 
    private String role;
    private String userEmail;

    // getPassword() 메서드 추가
    public String getPassword() {
        return userPassword;
    }

    // setPassword() 메서드 추가
    public void setPassword(String password) {
        this.userPassword = password;
    }
}

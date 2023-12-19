package com.example.heart.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
@Table(name = "resume")
@Entity(name = "ResumeEntity")
//jpa entity /데이터베이스의 resume테이블과매핑//userEntity와 다대일관계
public class ResumeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    private String resumeFilename; // PDF 파일의 이름

    private Date uploadDate;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    // 엔티티가 저장되기 전에 실행되는 메서드
    @PrePersist

    protected void onCreate() {
        
        this.uploadDate = new Date(); // 현재 날짜와 시간으로 업로드 일자 설정
    }
}

package com.example.heart.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ResumeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resume_id;

    private String resume_filename; // PDF 파일의 이름

    private Date upload_date; 

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // 엔티티가 저장되기 전에 실행되는 메서드
    @PrePersist
    protected void onCreate() {
        this.upload_date = new Date(); // 현재 날짜와 시간으로 업로드 일자 설정
    }
}

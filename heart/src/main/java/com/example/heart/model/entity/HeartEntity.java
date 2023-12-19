package com.example.heart.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "heart")
@Entity(name = "HeartEntity")
public class HeartEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @ManyToOne
    @JoinColumn(name = "resume_id") 
    private ResumeEntity resumeEntity;
    //HeartEntity는 ResumeEntity랑 (N:1) 관계
    //JoinColumn 어노테이션-> 
    //(Foreign Key) "resume_id"와 매핑

    private int pageNumber;
    private double xCoordinate;
    private double yCoordinate;

    @Override
    public String toString() {
        return "HeartEntity{" +
                "heartId=" + heartId +
                ", pageNumber=" + pageNumber +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
}

}

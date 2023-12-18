package com.example.heart.model.dto;

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
public class HeartDto {
    
    private Long heartId;
    private Long resumeId; //heart가 위치한 resume의 id
    private int resumePageNumber;
    private double xCoordinate;
    private double yCoordinate;

    // Getter, Setter, Constructors 등...
}

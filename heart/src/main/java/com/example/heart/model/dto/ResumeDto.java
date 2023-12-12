package com.example.heart.model.dto;

import java.util.Date;
import java.util.List;

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
public class ResumeDto {
    
    private Long resumeId;
    private String resumeFilename;
    private Date uploadDate;
    private List<Long> userIds;
}

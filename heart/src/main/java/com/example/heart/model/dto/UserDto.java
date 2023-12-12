package com.example.heart.model.dto;

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
public class UserDto {
    private Long userId;
    private String userName;
    private List<Long> resumeIds; //Entity를 사용하지 않고 resumeId들을 List 형태로 받음
}

// Validation 추후에 추가 !
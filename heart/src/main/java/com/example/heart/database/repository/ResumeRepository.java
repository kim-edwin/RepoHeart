package com.example.heart.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.heart.model.entity.ResumeEntity;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
    // 이 인터페이스는 Spring Data JPA에서 제공하는 JpaRepository를 상속
    // JpaRepository는 기본적인 CRUD(Create, Read, Update, Delete) 작업을 수행하는 메서드제공
    //여기서는 ResumeEntity라는 엔티티 클래스를 관리하며, 해당 엔티티의 기본 키 타입이 Long임을 명시
    Optional<ResumeEntity> findById(Long resumeId);
    //있을수도있고 아닐수도있으니까 optional
    //findById -> resumeId를 기반해서,특정 ResumeEntity를 찾는 역할
    //resumeId와 일치하는 ResumeEntity가 존재하면 해당 엔티티를 감싼 Optional 객체를 반환
    //존재하지 않으면 빈 Optional을 반환



    // 아래 메서드는 파일 이름을 통한 검색을 위한 메서드입니다.
    // 'Containing'은 해당 키워드를 포함한 모든 항목을 찾습니다.
    List<ResumeEntity> findByResumeFilenameContaining(String keyword);
}

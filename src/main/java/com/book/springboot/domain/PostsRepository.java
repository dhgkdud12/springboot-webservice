package com.book.springboot.domain;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends org.springframework.data.jpa.repository.JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 됨
    List<Posts> findAllDesc();
}

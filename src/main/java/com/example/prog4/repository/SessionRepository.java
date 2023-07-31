package com.example.prog4.repository;

import com.example.prog4.repository.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    @Query(value = "select 1 from session a where a.session_id = :sessionId order by a.timeout desc", nativeQuery = true)
    Optional<Session> findOneBySessionId(@Param("sessionId") String sessionId);
}

package com.example.ProjectStageBackend.repository;

import com.example.ProjectStageBackend.model.MessageModel;
import com.example.ProjectStageBackend.resource.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    @Query("SELECT new com.example.ProjectStageBackend.resource.MessageDTO(m.sender, m.message)" +
            "FROM MessageModel m " +
            "WHERE m.account.id = :accountId " +
            "ORDER BY m.time ASC")
    List<MessageDTO> findAllByAccountIdOrderByTimeAsc(@Param("accountId") Long accountId);
}

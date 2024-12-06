package com.example.ProjectStageBackend.repository;

import com.example.ProjectStageBackend.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//interface for both mongoDB repositories
// for mongo MongoRepository<AccountModel, Long>
@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
    AccountModel findByUsername(String username);
}

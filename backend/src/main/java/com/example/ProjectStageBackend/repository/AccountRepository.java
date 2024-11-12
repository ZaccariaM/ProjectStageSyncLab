package com.example.ProjectStageBackend.repository;

import com.example.ProjectStageBackend.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

//interface for both mongoDB and postgreSQL repositories
public interface AccountRepository extends MongoRepository<AccountModel, String>, JpaRepository<AccountModel, String>{
}

package com.example.ProjectStageBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder                //lombok annotations
@Data                   //lombok annotations
@NoArgsConstructor      //lombok annotations
@AllArgsConstructor     //lombok annotations
@Entity                 //postgres annotation
@Table(name="account")  //postgres annotation
//@Document("Account")    //mongodb annotation
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
}

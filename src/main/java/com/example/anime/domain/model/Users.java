package com.example.anime.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID animeid;

    public String username;
    public String password;
    public String role;
    public boolean enabled;
}

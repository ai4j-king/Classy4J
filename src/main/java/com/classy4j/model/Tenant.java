package com.classy4j.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "encrypt_public_key")
    private String encryptPublicKey;

    @Column(nullable = false)
    private String plan;

    @Column(nullable = false)
    private String status;

    @Column(name = "custom_config")
    private String customConfig;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
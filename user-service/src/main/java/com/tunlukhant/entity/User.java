package com.tunlukhant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String surname;

    @Column(nullable = false)
    private String email;

    private String address;

    @Column(nullable = false)
    @Builder.Default
    private boolean alerting=true;

    @Column(nullable = false)
    @Builder.Default
    private double energyAlertingThreshold=0;

}

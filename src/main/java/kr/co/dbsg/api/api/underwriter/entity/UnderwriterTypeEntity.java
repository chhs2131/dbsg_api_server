package kr.co.dbsg.api.api.underwriter.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "underwriter_type")
@Getter
public class UnderwriterTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
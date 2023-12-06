package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sector_type")
public class SectorType {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;
}
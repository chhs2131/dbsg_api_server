package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import kr.co.dbsg.api.api.stock.domain.Sector;

@Entity
@Table(name = "sector_type")
public class SectorTypeEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    public Sector toSector() {
        return new Sector(
                id,
                name
        );
    }
}
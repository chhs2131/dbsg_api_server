package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import kr.co.dbsg.api.api.stock.domain.type.CorporationType;

@Entity
@Table(name = "company_type")
public class CompanyTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public CorporationType toCorporationType() {
        return CorporationType.valueOf(name);
    }
}

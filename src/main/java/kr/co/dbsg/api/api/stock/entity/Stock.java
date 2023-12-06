package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "market_code")
    private String marketCode;

    @Column(name = "dart_code", nullable = false)
    private String dartCode;

    @Column(name = "corp_name")
    private String corpName;

    @Column(name = "modify_date")
    private String modifyDate;
}
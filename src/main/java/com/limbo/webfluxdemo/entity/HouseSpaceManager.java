package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="house_space_manager",schema = "house",catalog = "house")
public class HouseSpaceManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "id")
    private String id;

    @Column(name = "block_id")
    private String blockId;

    @Column(name = "zone_id")
    private String unitId;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "block_name")
    private String blockName;

    @Column(name = "zone_name")
    private String unitName;
}

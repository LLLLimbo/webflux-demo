package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema="kpi_report",name = "dim_room",catalog = "kpi_report")
public class DimRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "house_space_id")
    private String houseSpaceId;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "zone_id")
    private String zoneId;

    @Column(name = "manager_type")
    private String managerType;

    @Column(name = "zone_path")
    private String zonePath;

    @Column(name = "count_number")
    private Integer countNumber;

    @Column(name = "zone_manager_id")
    private String zoneManagerId;

    @Column(name = "housekeeper_id")
    private String housekeeperId;
}

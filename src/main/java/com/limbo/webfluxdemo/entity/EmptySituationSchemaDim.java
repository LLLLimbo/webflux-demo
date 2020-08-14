package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "empty_situation_schema_dim", schema = "house", catalog = "house")
public class EmptySituationSchemaDim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "house_space_id")
    private String houseSpaceId;

    @Column(name = "house_space_manager_id")
    private String houseSpaceManagerId;

    @Column(name = "block_id")
    private String blockId;

    @Column(name = "unit_id")
    private String unitId;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "block_name")
    private String blockName;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "cur_status")
    private String curStatus;

    @Column(name = "house_create_time")
    private Date houseCreateTime;

    @Column(name = "count_number")
    private Integer countNumber;
}

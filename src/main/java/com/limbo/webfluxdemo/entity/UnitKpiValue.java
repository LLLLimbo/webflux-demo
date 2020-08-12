package com.limbo.webfluxdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Accessors(chain = true)
@Table(schema="kpi_report",name = "unit_kpi_value",catalog = "kpi_report")
public class UnitKpiValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "id")
    private String id;

    @Column(name = "cur_date")
    private Date curDate;

    @Column(name = "unit_id")
    private String unitId;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "house_type")
    private String houseType;

    @Column(name = "kpi_value")
    private Integer kpiValue;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "kpi_pid")
    private Long kpiPid;

    @Column(name = "kpi_name")
    private String kpiName;

    @Transient
    private String path;

}

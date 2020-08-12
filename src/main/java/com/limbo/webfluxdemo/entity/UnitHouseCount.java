package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="unit_house_count",schema = "heibao",catalog = "heibao")
public class UnitHouseCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name="unit_id")
    private String unitId;

    @Column(name="city_name")
    private String cityName;

    @Column(name="unit_name")
    private String unitName;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="year_month")
    private String yearMonth;

    @Column(name="house_count_number")
    private Integer houseCountNumber;

    @Column(name="rented_count_number")
    private Integer rentedCountNumber;

    @Column(name="unit_path")
    private String unitPath;

}

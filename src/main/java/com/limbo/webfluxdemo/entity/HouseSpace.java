package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="house_space",schema = "house",catalog = "house")
public class HouseSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "id")
    private String id;

    @Column(name = "house_space_manager_id")
    private String houseSpaceManagerId;

    @Column(name = "house_space_status")
    private String houseSpaceStatus;

    @Column(name = "create_time")
    private Date createTime;
}

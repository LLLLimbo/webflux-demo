package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="contract",schema = "contract",catalog = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "id")
    private String id;

    @Column(name = "house_space_id")
    private String houseSpaceId;

    @Column(name = "contract_status")
    private String contractStatus;


}

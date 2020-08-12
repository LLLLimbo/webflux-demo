package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="company",schema = "house",catalog = "house")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "id")
    private String id;

    @Column(name = "company_name")
    private String companyName;
}

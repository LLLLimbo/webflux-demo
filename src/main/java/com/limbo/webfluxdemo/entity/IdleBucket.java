package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="idle_bucket",schema = "house",catalog = "house")
public class IdleBucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "group_name")
    private String name;

    @Column(name = "group_type")
    private String group;

    @Column(name = "bucket1")
    private Integer bucket1;

    @Column(name = "bucket2")
    private Integer bucket2;

    @Column(name = "bucket3")
    private Integer bucket3;

}

package com.limbo.webfluxdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name="empty_situation_schema_fact",schema = "house",catalog = "house")
public class EmptySituationSchemaFact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "logged_year")
    private Integer loggedYear;

    @Column(name = "bitset")
    private String bitset;

    @Column(name = "dim_id")
    private Long dimId;
}

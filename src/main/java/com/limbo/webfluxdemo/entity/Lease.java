package com.limbo.webfluxdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="lease",schema = "rent",catalog = "rent")
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "id")
    private String id;

    @Column(name = "lease_status")
    private String leaseStatus;

    @Column(name = "contract_id")
    private String contractId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "actual_out_time")
    private Date actualOutTime;
}

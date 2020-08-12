package com.limbo.webfluxdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class LoadingRequestBody {

    private Integer kpiPid;

    private String kpiType;

    private String kpiName;

    private String unitId;

    private String path;

    private String userId;

    private Boolean isDerived;

    private Boolean isCombined;

    private Date curDate;
}

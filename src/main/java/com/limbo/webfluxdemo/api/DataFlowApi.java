package com.limbo.webfluxdemo.api;

import com.limbo.webfluxdemo.entity.LoadingRequestBody;
import com.limbo.webfluxdemo.entity.UnitKpiValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("SAYYOO-DATAFLOW")
public interface DataFlowApi {

    @PostMapping("/dataflow/gatheringOnly")
    List<UnitKpiValue> gatheringOnly(@RequestBody LoadingRequestBody body);

}

package com.limbo.webfluxdemo.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.limbo.webfluxdemo.api.DataFlowApi;
import com.limbo.webfluxdemo.entity.LoadingRequestBody;
import com.limbo.webfluxdemo.entity.UnitHouseCount;
import com.limbo.webfluxdemo.entity.UnitKpiValue;
import com.limbo.webfluxdemo.repository.DimRoomRepo;
import com.limbo.webfluxdemo.repository.UnitHouseCountRepo;
import com.limbo.webfluxdemo.repository.UnitIdRepo;
import com.limbo.webfluxdemo.repository.UnitKpiRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.*;

@Service
@Slf4j
public class CompensationService {

    private final UnitIdRepo unitIdRepo;
    private final UnitHouseCountRepo unitHouseCountRepo;
    private final DimRoomRepo dimRoomRepo;
    private final UnitKpiRepo unitKpiRepo;
    private final DataFlowApi dataFlowApi;

    public CompensationService(UnitIdRepo unitIdRepo, UnitHouseCountRepo unitHouseCountRepo, DimRoomRepo dimRoomRepo, UnitKpiRepo unitKpiRepo, DataFlowApi dataFlowApi) {
        this.unitIdRepo = unitIdRepo;
        this.unitHouseCountRepo = unitHouseCountRepo;
        this.dimRoomRepo = dimRoomRepo;
        this.unitKpiRepo = unitKpiRepo;
        this.dataFlowApi = dataFlowApi;
    }

    public String compensate() {
        List<UnitHouseCount> unitIds = unitIdRepo.queryUnitIds();
        Date start = DateUtil.parseDate("2019-01-01 00:00:00");
        LinkedList<UnitKpiValue> unitKpiValues = new LinkedList<>();
        Set<LoadingRequestBody> gatheringRequests = new HashSet<>();
        unitIds.forEach(s -> {
            List<UnitHouseCount> unitHouseCounts =
                    unitHouseCountRepo.queryAllByUnitIdAndStartDateGreaterThanEqual(s.getUnitId(), start);
            if (CollectionUtil.isNotEmpty(unitHouseCounts)) {
                unitHouseCounts.forEach(unitHouseCount -> {
                    String companyId = dimRoomRepo.findFirstByZoneId(unitHouseCount.getUnitId()).getCompanyId();
                    Date cursor = DateUtil.beginOfDay(unitHouseCount.getStartDate());
                    Date end = DateUtil.offsetMonth(unitHouseCount.getStartDate(), 1);
                    while (cursor.before(end)) {
                        UnitKpiValue unitKpiValue = new UnitKpiValue();
                        unitKpiValue.setCompanyId(companyId)
                                .setCreateTime(new Date())
                                .setModifyTime(new Date())
                                .setCurDate(cursor)
                                .setHouseType("HOUSE")
                                .setId(null)
                                .setKpiName("HouseCount__RENTED__RENTAL")
                                .setKpiPid(30L)
                                .setKpiValue(unitHouseCount.getRentedCountNumber())
                                .setUnitId(unitHouseCount.getUnitId());
                        unitKpiValues.add(unitKpiValue);
                        LoadingRequestBody loadingRequestBody = new LoadingRequestBody();
                        loadingRequestBody.setKpiPid(30)
                                .setKpiName("HouseCount__RENTED__RENTAL")
                                .setCurDate(cursor);
                        gatheringRequests.add(loadingRequestBody);
                        cursor = DateUtil.offsetDay(cursor, 1);
                    }
                });
            }
        });
        unitKpiRepo.saveAll(unitKpiValues);
        unitKpiValues.clear();
        gatheringRequests.forEach(dataFlowApi::gatheringOnly);
        //unitKpiRepo.saveAll(unitKpiValues);
        return "Success";
    }

}

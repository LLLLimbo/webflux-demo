package com.limbo.webfluxdemo.service;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.limbo.webfluxdemo.entity.*;
import com.limbo.webfluxdemo.repository.ContractRepo;
import com.limbo.webfluxdemo.repository.DimRepo;
import com.limbo.webfluxdemo.repository.FactRepo;
import com.limbo.webfluxdemo.repository.LeaseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class TransformService {

    private final ContractRepo contractRepo;
    private final LeaseRepo leaseRepo;
    private final DimRepo dimRepo;
    private final FactRepo factRepo;

    public TransformService(ContractRepo contractRepo, LeaseRepo leaseRepo, DimRepo dimRepo, FactRepo factRepo) {
        this.contractRepo = contractRepo;
        this.leaseRepo = leaseRepo;
        this.dimRepo = dimRepo;
        this.factRepo = factRepo;
    }

    public void trans() {
        List<EmptySituationSchemaDim> dimList = dimRepo.findAllByHouseCreateTimeAfter(DateUtil.parseDate("2016-01-01"));
        dimList.forEach(dim -> {
            List<Contract> contractSet = contractRepo.findAllByHouseSpaceIdAndContractStatusIsNot(dim.getHouseSpaceId(), "CANCEL");
            List<EmptySituationSchemaFact> persisted = new ArrayList<>();
            Set<String> contractIdSet = new HashSet<>();
            contractSet.forEach(c -> contractIdSet.add(c.getId()));
            List<Lease> leaseList = leaseRepo.findAllByContractIdIn(contractIdSet);
            HashMap<Integer, BitSet> hashMap = null;
            for (Lease lease : leaseList) {
                hashMap = generateBiSets(hashMap, dim.getHouseCreateTime(), lease.getStartTime(), lease.getActualOutTime());
            }
            if (hashMap != null) {
                hashMap.forEach((key, bitSet) -> persisted.add(new EmptySituationSchemaFact()
                        .setLoggedYear(key)
                        .setDimId(dim.getPid())
                        .setBitset(Arrays.toString(bitSet.toLongArray()))
                ));
            }
            factRepo.saveAll(persisted);
        });
    }

    private HashMap<Integer, BitSet> generateBiSets(HashMap<Integer, BitSet> hashMap, Date houseCreateTime, Date leaseStartTime, Date leaseOutTime) {
        if (leaseOutTime == null) {
            leaseOutTime = DateUtil.beginOfDay(new Date());
        }
        if (leaseStartTime.after(leaseOutTime)) {
            return new HashMap<>();
        }
        int createYear = DateUtil.year(houseCreateTime);
        int startYear = DateUtil.year(leaseStartTime);
        int endYear = DateUtil.year(leaseOutTime);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            while (createYear < 2021) {
                int daysOfThisYear = 365;
                if (DateUtil.isLeapYear(createYear)) {
                    daysOfThisYear = 366;
                }
                hashMap.put(createYear, new BitSet(daysOfThisYear));
                createYear++;
            }
        }
        if (startYear < endYear) {
            for (int fromYear = startYear; fromYear <= endYear; fromYear++) {
                int daysOfThisYear = 365;
                if (DateUtil.isLeapYear(fromYear)) {
                    daysOfThisYear = 366;
                }
                int finalFromYear = fromYear;
                int finalDaysOfThisYear = daysOfThisYear;
                Date finalLeaseOutTime = leaseOutTime;
                hashMap.computeIfPresent(fromYear, (key, bs) -> {
                    BitSet nbs = new BitSet(bs.size());
                    if (finalFromYear == startYear) {
                        int startTimeDayOfYear = DateUtil.dayOfYear(leaseStartTime);
                        nbs.set(startTimeDayOfYear - 1, finalDaysOfThisYear, true);
                        bs.or(nbs);
                    } else if (finalFromYear == endYear) {
                        nbs.set(0, DateUtil.dayOfYear(finalLeaseOutTime), true);
                        bs.or(nbs);
                    } else if (finalFromYear < endYear) {

                        nbs.set(0, finalDaysOfThisYear, true);
                        bs.or(nbs);
                    }
                    return bs;
                });
            }

        } else {
            Date finalLeaseOutTime1 = leaseOutTime;
            hashMap.computeIfPresent(startYear, (key, bs) -> {
                BitSet nbs = new BitSet(bs.size());
                nbs.set(DateUtil.dayOfYear(leaseStartTime) - 1, DateUtil.dayOfYear(finalLeaseOutTime1) - 1, true);
                bs.or(nbs);
                return bs;
            });
        }
        return hashMap;
    }

    public List<JSONObject> querySchema(QueryEntity queryEntity) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        EmptySituationSchemaDim dim = dimRepo.findByHouseSpaceId(queryEntity.getHouseId());
        EmptySituationSchemaFact fact = factRepo.findByDimIdAndLoggedYear(dim.getPid(), queryEntity.getYear());
        BitSet bitSet = reducing(fact.getBitset());
        if (bitSet == null) {
            return null;
        }
        Calendar calendar = CalendarUtil.calendar();
        calendar.set(queryEntity.getYear(), queryEntity.getMonth(), 0);
        calendar.setTimeZone(TimeZone.getDefault());
        Date date = calendar.getTime();
        Date beginOfMonth = DateUtil.beginOfMonth(date);
        for (int i = 0; i < bitSet.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set(DateUtil.formatDate(beginOfMonth), bitSet.get(i));
            jsonObjects.add(jsonObject);
            beginOfMonth = DateUtil.offsetDay(beginOfMonth, 1);
        }
        return jsonObjects;
    }

    private BitSet reducing(String bitset) {
        if (StrUtil.isEmpty(bitset)) {
            return null;
        }
        String[] array = bitset.replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",");
        long[] b = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            b[i] = Long.parseLong(array[i]);
        }
        return BitSet.valueOf(b);
    }
}
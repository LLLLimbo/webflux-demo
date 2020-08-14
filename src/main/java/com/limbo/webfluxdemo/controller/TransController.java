package com.limbo.webfluxdemo.controller;

import cn.hutool.json.JSONObject;
import com.limbo.webfluxdemo.entity.IdleBucket;
import com.limbo.webfluxdemo.entity.QueryEntity;
import com.limbo.webfluxdemo.service.TransformService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transform")
public class TransController {

    private final TransformService transformService;

    public TransController(TransformService transformService) {
        this.transformService = transformService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> start() {
        transformService.trans();
        return ResponseEntity.ok("Succeeded");
    }

    @PostMapping("/query")
    private ResponseEntity<List<JSONObject>> query(@RequestBody QueryEntity queryEntity){
        return ResponseEntity.ok(transformService.querySchema(queryEntity));
    }

    @PostMapping("/queryByIdleBucket/{groupBy}/{persist}")
    private ResponseEntity<List<IdleBucket>> queryByIdleBucket(@PathVariable("groupBy") String groupBy,@PathVariable("persist") Boolean persist){
       return ResponseEntity.ok(transformService.generateIdleBucket(groupBy,persist));
    }
}

package com.limbo.webfluxdemo.controller;

import com.limbo.webfluxdemo.api.DataFlowApi;
import com.limbo.webfluxdemo.service.CompensationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive")
public class ReactiveController {

    private final CompensationService compensationService;

    public ReactiveController(CompensationService compensationService) {
        this.compensationService = compensationService;
    }

/*    @PostMapping("/gather")
    public String gather(){
        List<LoadingRequestBody> gatheringRequests = compensationService.getList();
        gatheringRequests.forEach(dataFlowApi::gatheringOnly);
        return "finish";
    }*/

    @PostMapping("/compo")
    public Mono<String> compo(){
        return Mono.just(compensationService.compensate()).doOnError(throwable-> System.out.println("AAA"));
    }

}

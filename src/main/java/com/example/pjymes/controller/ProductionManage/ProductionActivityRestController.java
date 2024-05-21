package com.example.pjymes.controller.ProductionManage;

import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.service.productionManage.ProductionActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/productionActivity")
@Log4j2
@RequiredArgsConstructor
public class ProductionActivityRestController {

    private final ProductionActivityService productionActivityService;

    public static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @GetMapping
    public List<ProductionPlanDTO> getProductionActivity(@ModelAttribute SearchDTO searchDTO) {
        log.info("getProductionActivity...");
        return productionActivityService.list(searchDTO);
    }

    @PutMapping
    public ProductionPlanDTO putProductionActivity(@RequestBody ProductionPlanDTO productionPlanDTO) {
        log.info("putProductionActivity..." + productionPlanDTO);
        return productionActivityService.production(productionPlanDTO);
    }

    @GetMapping("/connect")
    public SseEmitter connect() {
        // 현재 클라이언트를 위한 SseEmitter 생성
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            // 연결!!
            sseEmitter.send(SseEmitter.event().name("connect").data("연결됨"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // user의 pk값을 key값으로 해서 SseEmitter를 저장
        ProductionActivityRestController.sseEmitters.put("admin", sseEmitter);

        sseEmitter.onCompletion(() -> ProductionActivityRestController.sseEmitters.remove("admin"));
        sseEmitter.onTimeout(() -> ProductionActivityRestController.sseEmitters.remove("admin"));
        sseEmitter.onError((e) -> ProductionActivityRestController.sseEmitters.remove("admin"));

        return sseEmitter;
    }

    @PostMapping(value = "/sendTest")
    public void sendJson(@RequestBody ProductionPlanDTO productionPlanDTO) {
        log.info("확인 : " + productionPlanDTO);
        ProductionPlanDTO modifyData = productionActivityService.production(productionPlanDTO);
        SseEmitter sseEmitter = ProductionActivityRestController.sseEmitters.get("admin");
        try {
            sseEmitter.send(SseEmitter.event().name("notification").data(modifyData));
        } catch (Exception e) {
            ProductionActivityRestController.sseEmitters.remove("admin");
        }
    }
}

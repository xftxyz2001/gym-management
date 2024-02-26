package com.xftxyz.gymadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xftxyz.gymadmin.domain.Consume;
import com.xftxyz.gymadmin.domain.Refund;
import com.xftxyz.gymadmin.service.ConsumeService;
import com.xftxyz.gymadmin.service.RefundService;
import com.xftxyz.gymadmin.vo.req.ListConsumeReq;
import com.xftxyz.gymadmin.vo.req.ListRefundReq;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/finance")
public class FinanceController {

    private final ConsumeService consumeService;
    private final RefundService refundService;

    // 新增消费记录
    @PostMapping("/consume")
    public Boolean saveConsume(@RequestBody @NotNull Consume consume) {
        return consumeService.saveConsume(consume);
    }

    // 删除消费记录
    @DeleteMapping("/consume/{id}")
    public Boolean removeConsume(@PathVariable("id") @NotNull Long id) {
        return consumeService.removeConsume(id);
    }

    // 批量删除消费记录
    @DeleteMapping("/consumes")
    public Boolean removeConsumes(@RequestBody @NotNull List<Long> idList) {
        return consumeService.removeConsumes(idList);
    }

    // 更新消费记录
    @PutMapping("/consume")
    public Boolean updateConsume(@RequestBody @NotNull Consume consume) {
        return consumeService.updateConsume(consume);
    }

    // 查询消费记录
    @GetMapping("/consume/{id}")
    public Consume getConsume(@PathVariable("id") @NotNull Long id) {
        return consumeService.getConsume(id);
    }

    // 条件查询消费记录
    @PostMapping("/consumes")
    public IPage<Consume> listConsumes(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                       @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                       @RequestBody ListConsumeReq listConsumeReq) {
        return consumeService.listConsumes(listConsumeReq, current, size);
    }


    // 新增退款记录
    @PostMapping("/refund")
    public Boolean saveRefund(@RequestBody @NotNull Refund refund) {
        return refundService.saveRefund(refund);
    }

    // 删除退款记录
    @DeleteMapping("/refund/{id}")
    public Boolean removeRefund(@PathVariable("id") @NotNull Long id) {
        return refundService.removeRefund(id);
    }

    // 批量删除退款记录
    @DeleteMapping("/refunds")
    public Boolean removeRefunds(@RequestBody @NotNull List<Long> idList) {
        return refundService.removeRefunds(idList);
    }

    // 更新退款记录
    @PutMapping("/refund")
    public Boolean updateRefund(@RequestBody @NotNull Refund refund) {
        return refundService.updateRefund(refund);
    }

    // 查询退款记录
    @GetMapping("/refund/{id}")
    public Refund getRefund(@PathVariable("id") @NotNull Long id) {
        return refundService.getRefund(id);
    }

    // 条件查询退款记录
    @PostMapping("/refunds")
    public IPage<Refund> listRefunds(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                     @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                     @RequestBody ListRefundReq listRefundReq) {
        return refundService.listRefunds(listRefundReq, current, size);
    }

}

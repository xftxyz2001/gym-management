package com.xftxyz.gymadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xftxyz.gymadmin.domain.Exchange;
import com.xftxyz.gymadmin.domain.Reward;
import com.xftxyz.gymadmin.service.ExchangeService;
import com.xftxyz.gymadmin.service.RewardService;
import com.xftxyz.gymadmin.vo.req.ExchangeReq;
import com.xftxyz.gymadmin.vo.req.ListExchangeReq;
import com.xftxyz.gymadmin.vo.req.ListRewardReq;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointsController {

    private final RewardService rewardService;
    private final ExchangeService exchangeService;

    // 新增奖励
    @PostMapping("/reward")
    public Boolean saveReward(@RequestBody @NotNull Reward reward) {
        return rewardService.saveReward(reward);
    }

    // 删除奖励
    @DeleteMapping("/reward/{id}")
    public Boolean removeReward(@PathVariable("id") @NotNull Long id) {
        return rewardService.removeReward(id);
    }

    // 批量删除奖励
    @DeleteMapping("/rewards")
    public Boolean removeRewards(@RequestBody @NotNull List<Long> idList) {
        return rewardService.removeRewards(idList);
    }

    // 更新奖励
    @PutMapping("/reward")
    public Boolean updateReward(@RequestBody @NotNull Reward reward) {
        return rewardService.updateReward(reward);
    }

    // 查询奖励
    @GetMapping("/reward/{id}")
    public Reward getReward(@PathVariable("id") @NotNull Long id) {
        return rewardService.getReward(id);
    }

    // 条件查询奖励
    @PostMapping("/rewards")
    public IPage<Reward> listRewards(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                     @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                     @RequestBody ListRewardReq listRewardReq) {
        return rewardService.listRewards(listRewardReq, current, size);
    }


    // 兑换奖励
    @PostMapping("/exchange")
    public Boolean exchangeReward(@RequestBody @NotNull ExchangeReq exchangeReq) {
        return exchangeService.exchangeReward(exchangeReq);
    }

    // 删除兑换记录
    @DeleteMapping("/exchange/{id}")
    public Boolean removeExchange(@PathVariable("id") @NotNull Long id) {
        return exchangeService.removeExchange(id);
    }

    // 批量删除兑换记录
    @DeleteMapping("/exchanges")
    public Boolean removeExchanges(@RequestBody @NotNull List<Long> idList) {
        return exchangeService.removeExchanges(idList);
    }

    // 查询兑换记录
    @GetMapping("/exchange/{id}")
    public Exchange getExchange(@PathVariable("id") @NotNull Long id) {
        return exchangeService.getExchange(id);
    }

    // 条件查询兑换记录
    @PostMapping("/exchanges")
    public IPage<Exchange> listExchanges(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                         @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                         @RequestBody ListExchangeReq listExchangeReq) {
        return exchangeService.listExchanges(listExchangeReq, current, size);
    }

}

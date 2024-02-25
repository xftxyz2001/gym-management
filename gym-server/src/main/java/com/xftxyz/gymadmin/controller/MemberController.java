package com.xftxyz.gymadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xftxyz.gymadmin.domain.Card;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.service.CardService;
import com.xftxyz.gymadmin.service.MemberService;
import com.xftxyz.gymadmin.vo.req.ListCardReq;
import com.xftxyz.gymadmin.vo.req.ListMemberReq;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final CardService cardService;

    // 新增会员
    @PostMapping("/member")
    public Boolean saveMember(@RequestBody @NotNull Member member) {
        return memberService.saveMember(member);
    }

    // 删除会员
    @DeleteMapping("/member/{id}")
    public Boolean removeMember(@PathVariable("id") @Min(1) Long id) {
        return memberService.removeMember(id);
    }

    // 批量删除会员
    @DeleteMapping("/members")
    public Boolean removeMembers(@RequestBody @NotEmpty List<Long> idList) {
        return memberService.removeMembers(idList);
    }

    // 更新会员
    @PutMapping("/member")
    public Boolean updateMember(@RequestBody @NotNull Member member) {
        return memberService.updateMember(member);
    }

    // 查询会员
    @GetMapping("/member/{id}")
    public Member getMember(@PathVariable("id") @Min(1) Long id) {
        return memberService.getMember(id);
    }

    // 条件查询会员
    @GetMapping("/members")
    public IPage<Member> listMembers(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                     @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                     @RequestBody ListMemberReq listMemberReq) {
        return memberService.listMembers(listMemberReq, current, size);
    }


    // 新增会员卡
    @PostMapping("/card")
    public Boolean saveCard(@RequestBody @NotNull Card card) {
        return cardService.saveCard(card);
    }

    // 删除会员卡
    @DeleteMapping("/card/{id}")
    public Boolean removeCard(@PathVariable("id") @Min(1) Long id) {
        return cardService.removeCard(id);
    }

    // 批量删除会员卡
    @DeleteMapping("/cards")
    public Boolean removeCards(@RequestBody @NotEmpty List<Long> idList) {
        return cardService.removeCards(idList);
    }

    // 更新会员卡
    @PutMapping("/card")
    public Boolean updateCard(@RequestBody @NotNull Card card) {
        return cardService.updateCard(card);
    }

    // 查询会员卡
    @GetMapping("/card/{id}")
    public Card getCard(@PathVariable("id") @Min(1) Long id) {
        return cardService.getCard(id);
    }

    // 条件查询会员卡
    @GetMapping("/cards")
    public IPage<Card> listCards(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                 @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                 @RequestBody ListCardReq listCardReq) {
        return cardService.listCards(listCardReq, current, size);
    }

}

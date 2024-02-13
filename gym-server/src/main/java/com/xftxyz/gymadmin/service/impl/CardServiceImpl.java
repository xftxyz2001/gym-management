package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Card;
import com.xftxyz.gymadmin.service.CardService;
import com.xftxyz.gymadmin.mapper.CardMapper;
import org.springframework.stereotype.Service;

/**
* @author 25810
* @description 针对表【card(会员卡信息)】的数据库操作Service实现
* @createDate 2024-02-14 07:08:06
*/
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card>
    implements CardService{

}





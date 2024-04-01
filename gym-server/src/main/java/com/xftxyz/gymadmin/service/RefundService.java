package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Refund;
import com.xftxyz.gymadmin.vo.req.ListRefundReq;
import com.xftxyz.gymadmin.vo.req.RefundReq;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【refund(退款记录)】的数据库操作Service
 * @createDate 2024-02-14 07:08:07
 */
public interface RefundService extends IService<Refund> {

    Boolean saveRefund(Refund refund);

    Boolean removeRefund(Long id);

    Boolean removeRefunds(List<Long> idList);

    Boolean updateRefund(Refund refund);

    Refund getRefund(Long id);

    IPage<Refund> listRefunds(ListRefundReq listRefundReq, Integer current, Integer size);

    Boolean refund(RefundReq refundReq);
}

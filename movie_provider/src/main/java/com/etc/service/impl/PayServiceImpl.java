package com.etc.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.alipay.api.AlipayApiException;
import com.etc.epay.Alipay;
import com.etc.epay.AlipayBean;
import com.etc.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }

}

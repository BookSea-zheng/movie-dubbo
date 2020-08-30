package com.etc.amovie.service.impl;


import com.alipay.api.AlipayApiException;


import com.etc.amovie.epay.Alipay;
import com.etc.amovie.epay.AlipayBean;
import com.etc.amovie.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }

}

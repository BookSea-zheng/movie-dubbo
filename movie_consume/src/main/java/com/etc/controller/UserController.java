package com.etc.controller;

import com.alipay.api.AlipayApiException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.etc.constant.Role;
import com.etc.entity.User;
import com.etc.epay.AlipayBean;
import com.etc.exception.MessageException;
import com.etc.service.PayService;
import com.etc.service.UserService;
import com.etc.utils.MD5Utils;
import com.etc.utils.Msg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author
 */
@Controller
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping(value = "/users")
    @ResponseBody
    public Msg getUsers() {
        List<User> users = userService.getUsers();
        return Msg.success().add("first", users);
    }

    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public Msg login(@RequestParam("email") String email,
                     @RequestParam("password") String password,
                     HttpSession session) throws MessageException {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new MessageException("该用户不存在！");
        }
        if (userService.login(email, password)) {
            session.setAttribute("login", user);
            return Msg.success().add("msg", "成功登录！");
        }
        throw new MessageException("密码错误！");
    }

    @RequestMapping(value = "/doLogout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/doRegister")
    @ResponseBody
    public Msg register(User user) throws MessageException {
        User user1 = userService.getUserByEmail(user.getEmail());
        if (user1 != null) {
            throw new MessageException("该用户已经被注册");
        }
        user.setRole(Role.USER);
        user.setSalt(MD5Utils.getSalt());
        user.setPassword(MD5Utils.md5(user.getPassword(), user.getSalt()));
        userService.saveUser(user);
        return Msg.success().add("mes", "注册成功！");
    }


    @RequestMapping(value = "/doUpdUser")
    @ResponseBody
    public Msg doUpdUser(@RequestParam("email") String email,
                         @RequestParam("phone") String phone,
                         @RequestParam("password") String password,
                         @RequestParam("nickname") String name)throws MessageException{
        User user=userService.getUserByEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setNickname(name);
        userService.updUser(user);
        return Msg.success().add("mes","修改成功");
    }


    @Reference
    private PayService payService;

    /**
     * 阿里支付
     * @param tradeNo
     * @param subject
     * @param amount
     * @param body
     * @return
     * @throws AlipayApiException
     */
    @PostMapping(value = "alipay")
    @ResponseBody
    public String alipay(String outTradeNo, String subject, String totalAmount, String body,String phone) throws AlipayApiException {
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        alipayBean.setPhone(phone);
        return payService.aliPay(alipayBean);
    }


    //阿里云发送短信
    @RequestMapping(value = "/core")
    public  String register(@RequestParam("phoneNumber") String phoneNumber) throws ClientException, ClientException, ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//替换成你的AK
        final String accessKeyId = "LTAI4GKZuqsLjeubuoJpeaej";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "8ool7xjD8ueO3sifuJpD3ulbRIy4zr";//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("Dubbo电影购票系统");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_198917705");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//参考：request.setTemplateParam("{\"变量1\":\"值1\",\"变量2\":\"值2\",\"变量3\":\"值3\"}")
        request.setTemplateParam("{\"code\":\"7878\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//请求成功
            System.out.println("短信发送成功！");
        }
        return "/register";
    }


}

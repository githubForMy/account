package com.boniu.account.server.controller;

import com.boniu.account.api.AccountApi;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AccountController
 * @Description 账户相关接口
 * @Author HanXin
 * @Date 2019-06-24
 */
@RestController
@RequestMapping("/account")
@Api(value = "com.boniu.account.api.AccountApi", description = "账户相关接口")
public class AccountController implements AccountApi {

}

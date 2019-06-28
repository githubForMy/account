package com.boniu.account.server.controller;

import com.boniu.base.cache.common.LocalCacheManage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by ZZF on 18/06/12.
 */

@RestController
@RequestMapping(value = "/cache/manage")
@Api(value = "本地缓存管理", description = "本地缓存管理")
public class CacheManageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "本地缓存查询", notes = "本地缓存查询")
    @RequestMapping(value = "/localCache", method = RequestMethod.GET)
    public Map<String, Object> queryLocalCache() {
        logger.info("#1[查询本地缓存列表]");
        return LocalCacheManage.getCaches();

    }

    @ApiOperation(value = "重置本地缓存", notes = "重置本地缓存")
    @RequestMapping(value = "/localCache/clear", method = RequestMethod.POST)
    public void clear() {
        logger.info("#1[刷新本地缓存列表] [开始]");
        LocalCacheManage.clear();
        logger.info("#1[刷新本地缓存列表] [结束]");
    }


}

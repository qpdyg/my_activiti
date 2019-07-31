package com.xiaolc.service.impl;

import com.xiaolc.mapper.ActIdUserMapper;
import com.xiaolc.model.ActIdUser;
import com.xiaolc.service.ActIdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lc
 * @Date: 2019/6/21 11:25
 */
@Service
public class ActIdUserServiceImpl implements ActIdUserService {

    @Autowired
    private ActIdUserMapper actIdUserMapper;

    @Override
    public ActIdUser save(ActIdUser actIdUser) {
        return actIdUserMapper.save(actIdUser);
    }
}

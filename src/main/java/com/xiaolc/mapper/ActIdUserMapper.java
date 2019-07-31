package com.xiaolc.mapper;

import com.xiaolc.model.ActIdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: lc
 * @Date: 2019/6/21 11:22
 */
public interface ActIdUserMapper extends JpaRepository<ActIdUser, String> {
    @Override
    <S extends ActIdUser> S save(S s);
}

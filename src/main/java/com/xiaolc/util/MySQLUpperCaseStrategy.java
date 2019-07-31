package com.xiaolc.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 *
 * 忽略表名大小写
 * @Author: lc
 * @Date: 2019/6/21 14:39
 */
public class MySQLUpperCaseStrategy extends PhysicalNamingStrategyStandardImpl {
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {

        String tableName = name.getText().toUpperCase();
        return name.toIdentifier(tableName);
    }
}

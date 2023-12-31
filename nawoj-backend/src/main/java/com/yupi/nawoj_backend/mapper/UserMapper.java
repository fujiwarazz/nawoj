package com.yupi.nawoj_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.nawoj_backend.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库操作
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Mapper

public interface UserMapper extends BaseMapper<User> {

}





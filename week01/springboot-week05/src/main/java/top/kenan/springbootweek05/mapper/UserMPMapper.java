package top.kenan.springbootweek05.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.kenan.springbootweek05.entity.User;

@Mapper
public interface UserMPMapper extends BaseMapper<User> {}



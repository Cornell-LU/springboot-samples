package top.kenan.springbootweek05.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.kenan.springbootweek05.entity.User;
import top.kenan.springbootweek05.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public int add(User user) {
        int result = userMapper.insert(user);
        user.setCreateTime(LocalDateTime.now());
        System.out.println(user);
        return result;
    }

    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> list() {
        return userMapper.selectList();
    }

    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public List<User> search(String username, Integer minAge) {
        return userMapper.selectByCondition(username, minAge);
    }


}
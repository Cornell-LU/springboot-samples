package top.kenan.springbootweek05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.kenan.springbootweek05.entity.User;
import top.kenan.springbootweek05.mapper.UserMapper;


@Service
@RequiredArgsConstructor
public class UserTxService {
    private final UserMapper userMapper;

    /**
     * 新增两个用户，添加工厂事务：方法内任意一步出错，全部回滚
     *
     * @param user1 用户1
     * @param user2 用户2
     */
    @Transactional
    public void addTwoUsers(User user1, User user2) {
        // 第一步：新增用户1
        userMapper.insert(user1);
        // 模拟异常：如果用户2的用户名为空，抛出运行时异常，触发事务回滚
        if (user2.getUsername() == null || user2.getUsername().isEmpty()) {
            throw new RuntimeException("用户2姓名不能为空，事务回滚");
        }
        // 第二步：新增用户2（若上面抛出异常，此步骤不会执行，用户1也会被回滚）
        userMapper.insert(user2);
    }
}

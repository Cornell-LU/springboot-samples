package top.kenan.zhihu.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.kenan.zhihu.api.entity.Special;

@SpringBootTest
@Slf4j
class SpecialServiceTest {
    @Resource
    private SpecialService specialService;

    @Test
    void selectByTitle() {
        Page<Special> specialPage = specialService.selectByTitle("", 1, 10);
        log.info("专题记录数量:{}", specialPage.getRecords().size());
    }
}
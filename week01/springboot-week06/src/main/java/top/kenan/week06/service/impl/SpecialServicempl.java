package top.kenan.week06.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.kenan.week06.entity.Special;
import top.kenan.week06.mapper.SpecialMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialServicempl extends ServiceImpl<SpecialMapper, Special> {

    /**
     * 分页查询（支持姓名模糊查询 + 分页）
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param name 姓名（可选）
     * @return 分页结果
     */
    public Page<Special> pageList(Integer pageNum, Integer pageSize, String name) {
        // 1. 构建分页对象
        Page<Special> page = Page.of(pageNum, pageSize);

        // 2. 构建查询条件（姓名模糊匹配）
        LambdaQueryWrapper<Special> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, Special::getTitle, name);

        // 3. 执行分页查询
        return this.page(page, wrapper);
    }

    public List<Special> search(String title, Integer viewCount) {
    }
}

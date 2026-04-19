package top.kenan.week06.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.kenan.week06.entity.Special;
import top.kenan.week06.service.impl.SpecialServicempl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/special")
public class SpecialController {

    @Autowired
    private SpecialServicempl specialServicempl;


    @GetMapping("/page")
    public Page<Special> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name
    ) {
        return specialServicempl.pageList(pageNum, pageSize, name);
    }
    @GetMapping("/search")
    public List<Special> search(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) Integer viewCount) {
        return specialServicempl.search(id, viewCount);
    }
}

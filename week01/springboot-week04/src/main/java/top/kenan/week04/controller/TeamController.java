package top.kenan.week04.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kenan.week04.common.Result;
import top.kenan.week04.entity.Team;
import top.kenan.week04.exception.BusinessException;

/**
 * @author kenan
 * @date 2026/3/28
 * @description TeamController
 */
@RestController
@RequestMapping("/api/team")
@Slf4j
public class TeamController {

    private static final Logger log = LoggerFactory.getLogger(TeamController.class);

    @PostMapping("/add")
    public Result<String> addTeam(@Validated @RequestBody Team team, HttpServletRequest request) {
        log.info("添加团队: {}", team);

        String token = request.getHeader("token");
        if (token == null || token.isBlank()) {
            throw new BusinessException(401, "请先登录");
        }
        if (!"admin".equals(token)) {
            throw new BusinessException(403, "没有权限");
        }

        return Result.success("添加成功");
    }
}
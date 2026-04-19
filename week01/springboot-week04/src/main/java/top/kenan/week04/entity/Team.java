package top.kenan.week04.entity;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

/**
 * 团队信息实体类
 * 包含属性校验与配置文件注入
 */
@Data
@Component
public class Team {

    // 负责人姓名
    @Value("${team.leader}")
    @NotBlank(message = "负责人姓名不能为空")
    @Length(min = 2, max = 10, message = "负责人姓名长度2-10位")
    private String leader;

    // 负责人年龄
    @Value("${team.age}")
    @Max(value = 60, message = "年龄不能大于60")
    @Min(value = 30, message = "年龄不能小于30")
    private Integer age;

    // 邮箱
    @Value("${team.email}")
    @Email(message = "邮箱格式不正确")
    private String email;

    // 手机号
    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号格式不正确")
    private String phone;


    // 创建时间
    @Value("${team.createTime}")
    @Past(message = "创建时间必须早于当前时间")
    private LocalDate createTime;
}

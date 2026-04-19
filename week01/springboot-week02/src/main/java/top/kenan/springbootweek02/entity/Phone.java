package top.kenan.springbootweek02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author kenan
 * @date 2026/3/12
 * @description Phone
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    private String band;
    private Double price;
    private String color;
}

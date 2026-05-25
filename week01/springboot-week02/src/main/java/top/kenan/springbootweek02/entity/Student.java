package top.kenan.springbootweek02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * @author kenan
 * @data 2026/3/12
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private long id;
    private String name;
    private String gender;
    private LocalDate birthday;
    private Phone phone;
}
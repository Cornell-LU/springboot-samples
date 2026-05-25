package top.kenan.samples.dto;

import lombok.Data;

@Data
public class WeatherResponse {
    private String code;
    private String updateTime;
    private Now now;
}

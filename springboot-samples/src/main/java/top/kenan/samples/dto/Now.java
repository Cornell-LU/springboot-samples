package top.kenan.samples.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Now {
    @JsonProperty("obsTime")
    private String obsTime;
    private String temp;
    @JsonProperty("feelsLike")
    private String feelsLike;
    private String text;
    @JsonProperty("windDir")
    private String windDir;
    @JsonProperty("humidity")
    private String humidity;
    @JsonProperty("precip")
    private String precip;
    @JsonProperty("pressure")
    private String pressure;
    @JsonProperty("vis")
    private String vis;
}

package org.java.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RequestEntity {
    @JsonProperty("addition")
    private Addition addition;

    @Builder
    @Getter
    public static class Addition {
        @JsonProperty("additional_info")
        private String additionalInfo;
        @JsonProperty("additional_number")
        private Integer additionalNumber;
    }

    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;
    @JsonProperty("title")
    private String title;
    @JsonProperty("verified")
    private Boolean verified;
}
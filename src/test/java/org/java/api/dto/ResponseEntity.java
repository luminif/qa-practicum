package org.java.api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ResponseEntity {
    private Integer id;
    private String title;
    private Boolean verified;
    private Addition addition;

    @Builder
    @Getter
    public static class Addition {
        private Integer id;
        @SerializedName("additional_info")
        private String additionalInfo;
        @SerializedName("additional_number")
        private Integer additionalNumber;
    }

    @SerializedName("important_numbers")
    private List<Integer> importantNumbers;
}

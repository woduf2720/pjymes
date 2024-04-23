package com.example.pjymes.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonCodeDTO {

    private String commonCodeId;
    private String majorCode;
    private String subCode;
    private String commonCodeName;
    private String remarks;
    private Boolean useStatus;
}

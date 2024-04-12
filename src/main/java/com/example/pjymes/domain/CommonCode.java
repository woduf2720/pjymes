package com.example.pjymes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonCode {

    @Id
    @Column(length = 4, nullable = false)
    private String commonCodeId;
    @Column(length = 2, nullable = false)
    private String majorCode;
    @Column(length = 2, nullable = false)
    private String subCode;
    @Column(length = 20, nullable = false)
    private String commonCodeName;

    //수정시 이용
    public void change(String commonCodeName) {
        this.commonCodeName = commonCodeName;
    }
}

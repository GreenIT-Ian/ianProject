package com.ian.ianproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class CommonResponse<T> {

    // 0000 : 이면 성공
    // 그 외 실패.
    private String code;
    private String message;

    private boolean result;

    /* 사용자에게 알림을 해야할 경우 true */
    private boolean alertFlag = false;
    private T       data;

    @Builder
    public CommonResponse(boolean result, String message, boolean alertFlag, T data) {
        this.result = result;
        this.message = StringUtils.hasText(message) ? message : "";
        this.alertFlag = alertFlag;
        this.data = data;

    }
}

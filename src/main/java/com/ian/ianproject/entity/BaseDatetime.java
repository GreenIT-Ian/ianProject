/*
 * Copyright (c) 2022, GreenIT B2C, Inc. All rights reserved.
 */

package com.ian.ianproject.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * <b>엔티티 공통 필드부 구현</b>
 * <p>
 * auditing 모델
 *
 * @see <a href=
 * "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#auditing.basics">JPA
 * AUDIT</a>
 */
@Getter
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BaseDatetime {

    /**
     * 생성한 사용자 : UserID
     */
    @CreatedBy
    @Setter
    @Column(name = "CREATED_BY", updatable = false, length = 36, columnDefinition = "VARCHAR(36) COMMENT '생성자'")
    protected String createdBy;

    /**
     * 생성 년월일시분초 : Asia/Seoul TimeZone LocalDateTime
     */
    @CreationTimestamp
//    @CreatedDate
    @Comment("생성 일시")
    @Column(name = "CREATED_AT", nullable = false, updatable = false, columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP")
    protected LocalDateTime createdAt;

    /**
     * 갱신한 사용자 : UserID
     */
    @LastModifiedBy
    @Column(name = "UPDATED_BY", length = 36, insertable = false, columnDefinition = "VARCHAR(36) COMMENT '수정자'")
    protected String updatedBy;

    /**
     * 갱신 년월일시분초
     */
    @UpdateTimestamp
//    @LastModifiedDate
    @Comment("수정 일시")
    @Column(name = "UPDATED_AT", nullable = true, insertable = false, columnDefinition = "DATETIME(6) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    protected LocalDateTime updatedAt;

}

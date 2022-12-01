package com.ian.ianproject.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "USER", schema = "B2C_USER")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseDatetimeExtended implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", columnDefinition = "VARCHAR(40) COMMENT '관리 ID'", updatable = false, nullable = false, unique = true, length = 40)
    private String id;

    @Column(name = "EMAIL", length = 100, unique = true)
    @Comment("회원 이메일")
    private String email;

    @Column(name = "NAME", length = 50)
    @Comment("회원 성명")
    private String name;

    @Column(name = "PASSWORD", length = 100)
    @Comment("비밀번호(암호화)")
    private String password;

    @Column(name = "STATUS_CODE", length = 10)
    @Comment("상태코드")
    private String statusCode;

    @Column(name = "PASSWORD_CHANGE_DATE")
    @Comment("비밀번호 변경일자")
    protected LocalDate passwordChangeDate;

    @Column(name = "LAST_LOGIN_DATE")
    @Comment("마지막 로그인일자")
    protected LocalDate lastLoginDate;

    @Column(name = "CELL_PHONE", length = 20)
    @Comment("휴대전화번호")
    private String cellPhone;

    public void updateCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
}
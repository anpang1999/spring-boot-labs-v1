package com.captainyun7.ch2codeyourself._05_validation;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
public class SignupRequest {
    @NotBlank (message  = "아이디는 필수입니다.")// 공백 불허용
    @Size(min = 5, max = 10, message = "아이디는 5~10자리여야 합니다.")
    private String username;

    // 특수문자 포함 + 대소문자 포함 + 숫자 포함
    @ValidPassword //  커스텀 어노테이션
    private String password;

    @NotBlank(message =  "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Min(value = 14, message= "만 14세 이상만 가입 가능합니다.")
    private Integer age;

    @AssertTrue (message="약관에 동의해야합니다.")// True 만
    private Boolean agreeTerms;
}

package com.example.ch2labs.labs05;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static java.lang.Long.sum;

@RestController
public class DigitSumController {

    @RequestMapping("/sum-digits")
    public ResponseEntity<String> sumDigits(@RequestParam String number){
        // number 검증
        if (number == null){
            return ResponseEntity.badRequest().body("number 파라미터는 필수입니다.");
        }

        int num =0; ;
        try {
            num = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            // 숫자가 아니다
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("정수만 입력 가능합니다.");
        }

        if (num<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("음수는 지원하지 않습니다. 양의 정수를 입력해주세요.");
        }

        // 각 자리수를 더한 값

        int sum =   Arrays.stream(number.split(""))
                        .mapToInt(Integer::parseInt).sum();


        return ResponseEntity.status(HttpStatus.OK)
                .body("각 자리수의 합은 " + sum + "입니다.");
    }
}

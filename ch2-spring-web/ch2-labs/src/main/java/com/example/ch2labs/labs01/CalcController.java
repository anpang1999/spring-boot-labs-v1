package com.example.ch2labs.labs01;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

    @GetMapping("/calc")
    public String calculate(
            @RequestParam int x,
            @RequestParam int y,
            @RequestParam String op) {

        int result;
        String operator;

        switch (op) {
            case "add":
                result = x + y;
                operator = "+";
                break;
            case "sub":
                result = x - y;
                operator = "-";
                break;
            case "mul":
                result = x * y;
                operator = "*";
                break;
            case "div":
                if (y == 0) {
                    return "오류: 0으로 나눌 수 없습니다.";
                }
                result = x / y;
                operator = "/";
                break;
            default:
                return "오류: 지원하지 않는 연산자입니다. (add, sub, mul, div 중 선택)";
        }

        return String.format("결과: %d %s %d = %d", x, operator, y, result);
    }
}

package com.example.ch2labs.labs04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
public class RpsController {

    private static final String[] choices = {"rock", "paper", "scissors"};
    private static final Map<String, String> winMap = Map.of(
            "rock", "scissors",
            "paper", "rock",
            "scissors", "paper"
    );

    @GetMapping("/rps")
    public Object playRps(@RequestParam String user) {
        String userChoice = user.toLowerCase();

        if (!winMap.containsKey(userChoice)) {
            return Map.of("error", "Invalid choice. Choose one of: rock, paper, scissors.");
        }

        String serverChoice = choices[new Random().nextInt(3)];
        String outcome;

        if (userChoice.equals(serverChoice)) {
            outcome = "Draw!";
        } else if (winMap.get(userChoice).equals(serverChoice)) {
            outcome = "You Win!";
        } else {
            outcome = "You Lose!";
        }

        return new Result(userChoice, serverChoice, outcome);
    }
}


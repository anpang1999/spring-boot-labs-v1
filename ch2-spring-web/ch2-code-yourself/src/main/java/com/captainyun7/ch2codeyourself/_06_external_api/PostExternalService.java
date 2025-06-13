package com.captainyun7.ch2codeyourself._06_external_api;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service // 빈 주입
public class PostExternalService {

    private final WebClient webClient;

    public PostExternalService(WebClient webClient) {
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }
    public Post[] getAllPosts() {
        Post[] posts = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(Post[].class)
                .block();

        return posts;
    }
}

package com.oleksiiai.oleksiiai.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/")
public class RootController {

    private final static String OPEN_AI_URL = "https://api.openai.com/v1/completions";

    @Autowired
    private ObjectMapper jsonMapper;

    private final HttpClient client = HttpClient.newHttpClient();
//    private OpenAiService service = new OpenAiService("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");
    private static final String OPEN_AI_TOKEN = "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";

    @GetMapping
    public String rootGetEndpoint() {
        return "HALLO FROM GET!";
    }

    @GetMapping("/getData")
    public String getData(@RequestParam String question) throws IOException, InterruptedException {

        var request = HttpRequest.newBuilder().uri(URI.create(OPEN_AI_URL))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + OPEN_AI_TOKEN)
                .POST(HttpRequest.BodyPublishers.ofString("requestBodyAsJson")).build();

        client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        return null;
    }

//    @GetMapping("/getData")
//    public List<CompletionChoice> getData(@RequestParam String question) {
//        CompletionRequest completionRequest = CompletionRequest.builder()
//                .prompt(question)
//                .model("ada")
//                .echo(true)
//                .build();
//        CompletionResult result = service.createCompletion(completionRequest);
//        List<CompletionChoice> choices = result.getChoices();
//
//        return choices;
//    }

    @PostMapping
    public String rootPostEndpoint() {
        return "HALLO FROM POST!";
    }
}


package com.Gymlog.Service;

import com.Gymlog.Controllers.Response.ExerciseResponse;
import com.Gymlog.Records.ExerciseApiWrapperResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExerciseApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExerciseApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExerciseResponse getExerciseById(String id) {
        String url = "https://www.exercisedb.dev/api/v1/exercises/" + id;
        ExerciseApiWrapperResponse response = restTemplate.getForObject(url, ExerciseApiWrapperResponse.class);

        System.out.println(id);

        System.out.println(response);

        if (response != null && response.success()) {
            System.out.println(response.data());
            return response.data();
        }
        throw new RuntimeException("Failed to fetch exercise");
    }

}

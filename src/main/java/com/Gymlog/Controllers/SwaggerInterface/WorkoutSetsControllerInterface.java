package com.Gymlog.Controllers.SwaggerInterface;


import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "Sets do Exercício de Treino", description = "Operações relacionadas aos sets do exercício de treino")
public interface WorkoutSetsControllerInterface {
    @GetMapping("/")
    public ResponseEntity<List<WorkoutSetsResponse>> getWorkoutSets();

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSetsResponse> getWorkoutSetsById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutSets(@PathVariable Long id);

    @GetMapping("/{id}/workoutExercises")
    public ResponseEntity<List<WorkoutSetsResponse>> getWorkoutSetsByWorkoutExercisesId(@PathVariable Long id);

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSetsResponse> updateWorkoutSets(@RequestBody WorkoutSetsRequest workoutSetsRequest, @PathVariable Long id);

    @PostMapping("/")
    public ResponseEntity<WorkoutSetsResponse> createWorkoutSets(@RequestBody WorkoutSetsRequest workoutSetsRequest, UriComponentsBuilder uriBuilder);




}

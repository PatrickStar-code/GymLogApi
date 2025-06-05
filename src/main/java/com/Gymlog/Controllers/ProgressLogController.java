package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.ProgressLogMapper;
import com.Gymlog.Controllers.Request.ProgressLogRequest;
import com.Gymlog.Controllers.Response.ProgressLogResponse;
import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Entity.ProgressLogEntity;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Service.ProgressLogService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/progresslog")
@RequiredArgsConstructor
public class ProgressLogController {

    private final ProgressLogService progressLogService;

    @GetMapping("/{id}")
    public ResponseEntity<ProgressLogResponse> getProgressLogById(@PathVariable Long id) {
        ProgressLogEntity progressLog = progressLogService.getProgressLogById(id);
        return ResponseEntity.ok(ProgressLogMapper.toProgressLogResponse(progressLog));
    }

    @PostMapping("/")
    public  ResponseEntity<ProgressLogResponse> createProgressLog(@RequestBody ProgressLogRequest progressLogRequest, @NotNull UriComponentsBuilder uriBuilder) {
        ProgressLogEntity response = progressLogService.createProgressLog(progressLogRequest);
        ProgressLogResponse progressLogResponse = ProgressLogMapper.toProgressLogResponse(response);
        return ResponseEntity.created(uriBuilder.path("/GymLog/progresslog/{id}").buildAndExpand(response.getId()).toUri()).body(progressLogResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgressLogResponse> updateProgressLog(@RequestBody ProgressLogRequest progressLogRequest , @PathVariable Long id) {
        Optional<ProgressLogEntity> response = progressLogService.updateProgressLog(progressLogRequest, id);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ProgressLogMapper.toProgressLogResponse(response.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressLog(@PathVariable Long id) {
        progressLogService.deleteProgressLog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<List<ProgressLogResponse>> getAllProgressLogByUser(@PathVariable Long id) {
         Optional<List<ProgressLogEntity>> progressLog = progressLogService.findByUser(id);
         if(progressLog.isEmpty()) return ResponseEntity.notFound().build();
         return ResponseEntity.ok(progressLog.get().stream().map(ProgressLogMapper::toProgressLogResponse).toList());
    }


}

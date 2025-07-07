package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.ProgressLogMapper;
import com.Gymlog.Controllers.Request.ProgressLogRequest;
import com.Gymlog.Controllers.Response.ProgressLogResponse;
import com.Gymlog.Entity.ProgressLogEntity;
import com.Gymlog.Service.ProgressLogService;
import com.Gymlog.SwaggerInterface.ProgressLogControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/progresslog")
@RequiredArgsConstructor
public class ProgressLogController implements ProgressLogControllerInterface {

    private final ProgressLogService progressLogService;



    @Override
    public ResponseEntity<ProgressLogResponse> getProgressLogById(Long id) {
        ProgressLogEntity progressLog = progressLogService.getProgressLogById(id);
        return ResponseEntity.ok(ProgressLogMapper.toProgressLogResponse(progressLog));
    }

    @Override
    public ResponseEntity<ProgressLogResponse> createProgressLog(ProgressLogRequest progressLogRequest, UriComponentsBuilder uriBuilder) {
        ProgressLogEntity response = progressLogService.createProgressLog(progressLogRequest);
        ProgressLogResponse progressLogResponse = ProgressLogMapper.toProgressLogResponse(response);
        return ResponseEntity.created(uriBuilder.path("/GymLog/progresslog/{id}").buildAndExpand(response.getId()).toUri()).body(progressLogResponse);
    }

    @Override
    public ResponseEntity<ProgressLogResponse> updateProgressLog(ProgressLogRequest progressLogRequest, Long id) {
        Optional<ProgressLogEntity> response = progressLogService.updateProgressLog(progressLogRequest, id);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ProgressLogMapper.toProgressLogResponse(response.get()));
    }

    @Override
    public ResponseEntity<Void> deleteProgressLog(Long id) {
        progressLogService.deleteProgressLog(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProgressLogResponse>> getAllProgressLogByUser(Long id) {
        Optional<List<ProgressLogEntity>> progressLog = progressLogService.findByUser(id);
        if(progressLog.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(progressLog.get().stream().map(ProgressLogMapper::toProgressLogResponse).toList());    }
}

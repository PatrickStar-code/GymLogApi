package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.ProgressLogMapper;
import com.Gymlog.Controllers.Request.ProgressLogRequest;
import com.Gymlog.Controllers.Response.ProgressLogResponse;
import com.Gymlog.Entity.ProgressLogEntity;
import com.Gymlog.Service.ProgressLogService;
import com.Gymlog.Controllers.SwaggerInterface.ProgressLogControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Page<ProgressLogResponse>> getAllProgressLogByUser(Long id, int page, int size) {
        List<ProgressLogEntity> progressLog = progressLogService.findByUser(id);
        if(progressLog.isEmpty()) return ResponseEntity.notFound().build();
        if(page >= 0 && size > 0) {
            Page<ProgressLogEntity> progressLogs = progressLogService.getAllProgressLogByUserPage(page, size, id);
            Page<ProgressLogResponse> progressLogResponseStream =  progressLogs.map(ProgressLogMapper::toProgressLogResponse);
            return ResponseEntity.ok().body(progressLogResponseStream);
        }
        List<ProgressLogEntity> progressLogs = progressLogService.findByUser(id);
        List<ProgressLogResponse> response = progressLogs.stream().map(ProgressLogMapper::toProgressLogResponse).toList();
        PageImpl<ProgressLogResponse> progressLogResponsePage = new PageImpl<>(response, PageRequest.of(0,progressLogs.size() > 0 ? progressLogs.size() : 1),progressLogs.size() > 0 ? progressLogs.size() : 1);
        return ResponseEntity.ok(progressLogResponsePage);
    }


}

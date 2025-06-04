package com.Gymlog.Service;

import com.Gymlog.Repository.ProgressLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressLogService {

    private final ProgressLogRepository repository;


}

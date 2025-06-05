package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.ProgressLogMapper;
import com.Gymlog.Controllers.Request.ProgressLogRequest;
import com.Gymlog.Entity.ProgressLogEntity;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Exceptions.NotFoundException;
import com.Gymlog.Repository.ProgressLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressLogService {

    private final ProgressLogRepository repository;
    private final UserService userService;


    public ProgressLogEntity getProgressLogById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("NOT_FOUND","ProgressLog nao encontrado!"));
    }

    @Transactional
    public ProgressLogEntity createProgressLog(ProgressLogRequest progressLogRequest) {
        UserEntity user = findUser(progressLogRequest.user());
        ProgressLogEntity progressLog = ProgressLogMapper.toProgressEntity(progressLogRequest, user);
        return repository.save(progressLog);
    }

    public Optional<ProgressLogEntity> updateProgressLog(ProgressLogRequest progressLogRequest, Long id) {
        Optional<ProgressLogEntity> progressLog = repository.findById(id);
        if (progressLog.isPresent()) {
            ProgressLogEntity progressLogEntity = progressLog.get();
            progressLogEntity.setDate(progressLogRequest.date());
            progressLogEntity.setWeight(progressLogRequest.weight());
            progressLogEntity.setBodyFat(progressLogRequest.bodyFat());
            progressLogEntity.setHip(progressLogRequest.hip());
            progressLogEntity.setChest(progressLogRequest.chest());
            progressLogEntity.setArmsLeft(progressLogRequest.armsLeft());
            progressLogEntity.setArmsRight(progressLogRequest.armsRight());
            progressLogEntity.setThighLeft(progressLogRequest.thighLeft());
            progressLogEntity.setThighRight(progressLogRequest.thighRight());
            progressLogEntity.setLeftCalf(progressLogRequest.leftCalf());
            progressLogEntity.setRightCalf(progressLogRequest.rightCalf());
            progressLogEntity.setWaist(progressLogRequest.waist());
            progressLogEntity.setComment(progressLogRequest.comment());
            progressLogEntity.setResponsible(progressLogRequest.responsible());
            return Optional.of(repository.save(progressLogEntity));
        } else {
            return Optional.empty();
     }
    }


    public Optional<Void> deleteProgressLog(Long id) {
        Optional<ProgressLogEntity> progressLog = repository.findById(id);
        if(progressLog.isEmpty()) throw new NotFoundException("NOT_FOUND", "ProgressLog nao encontrado!");
        repository.delete(progressLog.get());
        return Optional.empty();
    }

    public Optional<List<ProgressLogEntity>> findByUser(Long id) {
        UserEntity user = findUser(id);
        return repository.findByUser(user);
    }

    private UserEntity findUser(Long id) {
      Optional<UserEntity> user = userService.getUser(id);
      if(user.isEmpty()) throw new NotFoundException("NOT_FOUND", "Usuario nao encontrado!");
        return user.get();
    }
}

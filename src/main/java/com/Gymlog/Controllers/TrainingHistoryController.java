package com.Gymlog.Controllers;

import com.Gymlog.Service.TrainingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/Gymlog/trainingHistory")
@RequiredArgsConstructor
public class TrainingHistoryController {

 private  final TrainingHistoryService trainingHistoryService;



}

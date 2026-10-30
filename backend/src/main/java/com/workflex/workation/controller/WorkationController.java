package com.workflex.workation.controller;
import com.workflex.workation.model.Workation;
import com.workflex.workation.service.WorkationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/workflex/workation")
public class WorkationController {
 private final WorkationService service;
 public WorkationController(WorkationService service){this.service=service;}
 @GetMapping public List<Workation> list(){return service.findAll();}
}

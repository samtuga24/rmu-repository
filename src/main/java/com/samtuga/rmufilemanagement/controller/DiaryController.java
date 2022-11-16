package com.samtuga.rmufilemanagement.controller;

import com.samtuga.rmufilemanagement.entity.FileDiary;
import com.samtuga.rmufilemanagement.entity.IncomingCorrespondence;
import com.samtuga.rmufilemanagement.service.FileDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/")
public class DiaryController {
    @Autowired
    private FileDiaryService diaryService;

    @PostMapping("post-diary")
    public ResponseEntity<?>addDiary(@RequestBody FileDiary fileDiary){
        return ResponseEntity.ok().body(diaryService.addDiary(fileDiary));
    }

    @GetMapping("get-diary")
    public ResponseEntity<List<FileDiary>> viewDiary(){
        return ResponseEntity.ok().body(diaryService.viewDiary());
    }

    @GetMapping("reference/{reference}")
    public ResponseEntity<List<FileDiary>> viewReference(@PathVariable("reference") String reference){
        return ResponseEntity.ok().body(diaryService.viewByReference(reference));
    }

    @GetMapping("diary-count")
    public ResponseEntity<?> getCount(){
        return ResponseEntity.ok().body(diaryService.getCount());
    }

    //    findByYear
    @GetMapping("diary-find-year")
    public ResponseEntity<List<FileDiary>>getYear(){
        return ResponseEntity.ok().body(diaryService.getByYear());
    }

    @GetMapping("diary-count-year")
    public ResponseEntity<?>getYearCount(){
        return ResponseEntity.ok().body(diaryService.getRows());
    }
    //    findQuarterly
    @GetMapping("diary-first-quarter")
    public ResponseEntity<List<FileDiary>>firstQuarter(){
        return ResponseEntity.ok().body(diaryService.getFirstQuarter());
    }

    @GetMapping("diary-first-count")
    public ResponseEntity<?>firstCount(){
        return ResponseEntity.ok().body(diaryService.getFirstRow());
    }

    @GetMapping("diary-second-quarter")
    public ResponseEntity<List<FileDiary>>secondQuarter(){
        return ResponseEntity.ok().body(diaryService.getSecondQuarter());
    }

    @GetMapping("diary-second-count")
    public ResponseEntity<?>secondCount(){
        return ResponseEntity.ok().body(diaryService.getSecondRow());
    }

    @GetMapping("diary-third-quarter")
    public ResponseEntity<List<FileDiary>>thirdQuarter(){
        return ResponseEntity.ok().body(diaryService.getThirdQuarter());
    }

    @GetMapping("diary-third-count")
    public ResponseEntity<?>thirdCount(){
        return ResponseEntity.ok().body(diaryService.getThirdRow());
    }

    @GetMapping("diary-fourth-quarter")
    public ResponseEntity<List<FileDiary>>fourthQuarter(){
        return ResponseEntity.ok().body(diaryService.getFourthQuarter());
    }

    @GetMapping("diary-fourth-count")
    public ResponseEntity<?>fourthCount(){
        return ResponseEntity.ok().body(diaryService.getFourthRow());
    }

}

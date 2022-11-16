package com.samtuga.rmufilemanagement.controller;

import com.samtuga.rmufilemanagement.entity.FileTransitSheet;
import com.samtuga.rmufilemanagement.entity.OutgoingCorrespondence;
import com.samtuga.rmufilemanagement.service.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/")
public class TransitController {
    @Autowired
    private TransitService transitService;

    @PostMapping("add-transit")
    public ResponseEntity<?> addTransit(@RequestBody FileTransitSheet fileTransitSheet){
        return ResponseEntity.ok().body(transitService.addTransit(fileTransitSheet));
    }

    @PatchMapping("update-transit/{ref}")
    public ResponseEntity<?> updateTransit(@PathVariable("ref") String ref, @RequestBody String officer){
        return ResponseEntity.ok().body(transitService.updateReturn(ref,officer));
    }

    //    findByReference
    @GetMapping("transit-reference/{reference}")
    public ResponseEntity<List<FileTransitSheet>> viewReference(@PathVariable("reference") String reference){
        return ResponseEntity.ok().body(transitService.viewByReference(reference));
    }

    @GetMapping("count-transit")
    public ResponseEntity<?> getCount(){
        return ResponseEntity.ok().body(transitService.getCount());
    }

    //    findAll
    @GetMapping("view-transit")
    public ResponseEntity<List<FileTransitSheet>> viewAll(){
        return ResponseEntity.ok().body(transitService.viewAll());
    }
    //    findByYear
    @GetMapping("transit-year")
    public ResponseEntity<List<FileTransitSheet>>getYear(){
        return ResponseEntity.ok().body(transitService.getByYear());
    }

    @GetMapping("count-year-transit")
    public ResponseEntity<?>getYearCount(){
        return ResponseEntity.ok().body(transitService.getRows());
    }

//    findByQuarter

    @GetMapping("first-quarter-transit")
    public ResponseEntity<List<FileTransitSheet>>firstQuarter(){
        return ResponseEntity.ok().body(transitService.getFirstQuarter());
    }

    @GetMapping("first-count-transit")
    public ResponseEntity<?>firstCount(){
        return ResponseEntity.ok().body(transitService.getFirstRow());
    }

    @GetMapping("second-quarter-transit")
    public ResponseEntity<List<FileTransitSheet>>secondQuarter(){
        return ResponseEntity.ok().body(transitService.getSecondQuarter());
    }

    @GetMapping("second-count-transit")
    public ResponseEntity<?>secondCount(){
        return ResponseEntity.ok().body(transitService.getSecondRow());
    }

    @GetMapping("third-quarter-transit")
    public ResponseEntity<List<FileTransitSheet>>thirdQuarter(){
        return ResponseEntity.ok().body(transitService.getThirdQuarter());
    }

    @GetMapping("third-count-transit")
    public ResponseEntity<?>thirdCount(){
        return ResponseEntity.ok().body(transitService.getThirdRow());
    }

    @GetMapping("fourth-quarter-transit")
    public ResponseEntity<List<FileTransitSheet>>fourthQuarter(){
        return ResponseEntity.ok().body(transitService.getFourthQuarter());
    }

    @GetMapping("fourth-count-transit")
    public ResponseEntity<?>fourthCount(){
        return ResponseEntity.ok().body(transitService.getFourthRow());
    }

}

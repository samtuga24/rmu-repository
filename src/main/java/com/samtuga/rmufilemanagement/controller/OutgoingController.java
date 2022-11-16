package com.samtuga.rmufilemanagement.controller;

import com.samtuga.rmufilemanagement.entity.AppUser;
import com.samtuga.rmufilemanagement.entity.IncomingCorrespondence;
import com.samtuga.rmufilemanagement.entity.OutgoingCorrespondence;
import com.samtuga.rmufilemanagement.service.OutgoingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class OutgoingController {
    @Autowired
    private OutgoingService outgoingService;

    @PostMapping("add-outgoing")
    public ResponseEntity<?> addOutgoing(@RequestParam("date") @DateTimeFormat(pattern= "yyyy-MM-dd") LocalDate date,
                                         @RequestParam("reference") String reference, @RequestParam("subject") String subject,
                                         @RequestParam("mode") String mode, @RequestParam("address") String[] address, @RequestParam("user")AppUser user,
                                         @RequestParam("file") MultipartFile file) throws IOException{
        return outgoingService.addOutgoing(reference,date,subject,mode,user,address,file);
    }

    @PatchMapping("update-out/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("id") String id) throws IOException {
        return ResponseEntity.ok().body(outgoingService.updateIncoming(file,id));
    }

//    findByReference
    @GetMapping("out-reference/{reference}")
    public ResponseEntity<List<OutgoingCorrespondence>> viewReference(@PathVariable("reference") String reference){
        return ResponseEntity.ok().body(outgoingService.viewSub(reference));
    }

    @GetMapping("count-out")
    public ResponseEntity<?> getCount(){
        return ResponseEntity.ok().body(outgoingService.getCount());
    }

//    findAll
    @GetMapping("view-outgoing")
    public ResponseEntity<List<OutgoingCorrespondence>> viewAll(){
        return ResponseEntity.ok().body(outgoingService.viewAll());
    }
//    findByYear
    @GetMapping("out-year")
    public ResponseEntity<List<OutgoingCorrespondence>>getYear(){
        return ResponseEntity.ok().body(outgoingService.getByYear());
    }

    @GetMapping("count-year-out")
    public ResponseEntity<?>getYearCount(){
        return ResponseEntity.ok().body(outgoingService.getRows());
    }

//    findByQuarter

    @GetMapping("first-quarter-out")
    public ResponseEntity<List<OutgoingCorrespondence>>firstQuarter(){
        return ResponseEntity.ok().body(outgoingService.getFirstQuarter());
    }

    @GetMapping("first-count-out")
    public ResponseEntity<?>firstCount(){
        return ResponseEntity.ok().body(outgoingService.getFirstRow());
    }

    @GetMapping("second-quarter-out")
    public ResponseEntity<List<OutgoingCorrespondence>>secondQuarter(){
        return ResponseEntity.ok().body(outgoingService.getSecondQuarter());
    }

    @GetMapping("second-count-out")
    public ResponseEntity<?>secondCount(){
        return ResponseEntity.ok().body(outgoingService.getSecondRow());
    }

    @GetMapping("third-quarter-out")
    public ResponseEntity<List<OutgoingCorrespondence>>thirdQuarter(){
        return ResponseEntity.ok().body(outgoingService.getThirdQuarter());
    }

    @GetMapping("third-count-out")
    public ResponseEntity<?>thirdCount(){
        return ResponseEntity.ok().body(outgoingService.getThirdRow());
    }

    @GetMapping("fourth-quarter-out")
    public ResponseEntity<List<OutgoingCorrespondence>>fourthQuarter(){
        return ResponseEntity.ok().body(outgoingService.getFourthQuarter());
    }

    @GetMapping("fourth-count-out")
    public ResponseEntity<?>fourthCount(){
        return ResponseEntity.ok().body(outgoingService.getFourthRow());
    }
}

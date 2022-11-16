package com.samtuga.rmufilemanagement.controller;

import com.samtuga.rmufilemanagement.entity.AppUser;
import com.samtuga.rmufilemanagement.entity.IncomingCorrespondence;
import com.samtuga.rmufilemanagement.service.IncomingService;
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
public class IncomingController {
    @Autowired
    private IncomingService incomingService;
//
    @RequestMapping( value = "add-incoming", method=RequestMethod.POST)
    public ResponseEntity<?> addIncoming(@RequestParam("date") @DateTimeFormat(pattern= "yyyy-MM-dd")LocalDate date, @RequestParam("whom") String whom, @RequestParam("instRef") String instRef , @RequestParam("subject") String subject, @RequestParam("user")AppUser user, @RequestParam("file") MultipartFile file) throws IOException{
        return incomingService.addIncoming(date,whom,instRef,subject,user,file);
    }

//    @PatchMapping("update/{id}")
//    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("id") String id) throws IOException {
//        return ResponseEntity.ok().body(incomingService.updateIncoming(file,id));
//    }

    @RequestMapping(value="/update-minuted/{id}", method=RequestMethod.PATCH)
    public ResponseEntity<?> uploadMinutedImage(@PathVariable("id") String id, @RequestParam(name="reference",required=false) String reference[],  @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(incomingService.updateMinuted(id,file,reference));
    }


    @GetMapping("view-incoming")
    public ResponseEntity<List<IncomingCorrespondence>> viewAll(){
        return ResponseEntity.ok().body(incomingService.viewAll());
    }

    @RequestMapping(value = "view/{reference}", method = RequestMethod.GET)
    public ResponseEntity<List<IncomingCorrespondence>> viewReference(@PathVariable("reference") String reference){

        return ResponseEntity.ok().body(incomingService.viewSub(reference));
    }

    @GetMapping("count")
    public ResponseEntity<?> getCount(){
        return ResponseEntity.ok().body(incomingService.getCount());
    }

//    findByYear
    @GetMapping("find-year")
    public ResponseEntity<List<IncomingCorrespondence>>getYear(){
        return ResponseEntity.ok().body(incomingService.getByYear());
    }

    @GetMapping("count-year")
    public ResponseEntity<?>getYearCount(){
        return ResponseEntity.ok().body(incomingService.getRows());
    }
//    findQuarterly
    @GetMapping("first-quarter")
    public ResponseEntity<List<IncomingCorrespondence>>firstQuarter(){
        return ResponseEntity.ok().body(incomingService.getFirstQuarter());
    }

    @GetMapping("first-count")
    public ResponseEntity<?>firstCount(){
        return ResponseEntity.ok().body(incomingService.getFirstRow());
    }

    @GetMapping("second-quarter")
    public ResponseEntity<List<IncomingCorrespondence>>secondQuarter(){
        return ResponseEntity.ok().body(incomingService.getSecondQuarter());
    }

    @GetMapping("second-count")
    public ResponseEntity<?>secondCount(){
        return ResponseEntity.ok().body(incomingService.getSecondRow());
    }

    @GetMapping("third-quarter")
    public ResponseEntity<List<IncomingCorrespondence>>thirdQuarter(){
        return ResponseEntity.ok().body(incomingService.getThirdQuarter());
    }

    @GetMapping("third-count")
    public ResponseEntity<?>thirdCount(){
        return ResponseEntity.ok().body(incomingService.getThirdRow());
    }

    @GetMapping("fourth-quarter")
    public ResponseEntity<List<IncomingCorrespondence>>fourthQuarter(){
        return ResponseEntity.ok().body(incomingService.getFourthQuarter());
    }

    @GetMapping("fourth-count")
    public ResponseEntity<?>fourthCount(){
        return ResponseEntity.ok().body(incomingService.getFourthRow());
    }
}

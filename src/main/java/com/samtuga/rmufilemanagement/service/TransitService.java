package com.samtuga.rmufilemanagement.service;

import com.samtuga.rmufilemanagement.entity.AppUser;
import com.samtuga.rmufilemanagement.entity.FileDiary;
import com.samtuga.rmufilemanagement.entity.FileTransitSheet;
import com.samtuga.rmufilemanagement.entity.OutgoingCorrespondence;
import com.samtuga.rmufilemanagement.repository.DiaryRepository;
import com.samtuga.rmufilemanagement.repository.TransitRepository;
import com.samtuga.rmufilemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TransitService {
    @Autowired
    private TransitRepository transitRepository;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private UserRepository userRepository;
    private final String FILE_DOES_NOT_EXIST = "file with reference %s does not exist";

    public ResponseEntity<?>addTransit(FileTransitSheet fileTransitSheet){
        FileDiary fileDiary = diaryRepository.findByReference(fileTransitSheet.getReference()).orElse(null);
        if(fileDiary == null){
            return ResponseEntity.ok().body(String.format(FILE_DOES_NOT_EXIST,fileTransitSheet.getReference()));
        }



        AppUser appUser = userRepository.findByEmail(fileTransitSheet.getAppUser().getEmail()).orElse(null);
        if(appUser == null){
            ResponseEntity.ok().body("You need to log in to perform this Operation");
        } else{
            appUser.setEmail(appUser.getEmail());
            appUser.setFirstName(appUser.getFirstName());
            appUser.setPassword(appUser.getPassword());
        }
        String str = fileTransitSheet.getReference().replaceAll("\\/","");
        fileTransitSheet.setAppUser(appUser);
        fileTransitSheet.setFileDiary(fileDiary);
        fileTransitSheet.setDate(LocalDate.now());
        fileTransitSheet.setRef(str);
        fileTransitSheet.setFileTitle(fileDiary.getFileTitle());
        fileTransitSheet.setStatus("out");
        transitRepository.save(fileTransitSheet);
        return ResponseEntity.ok().body(String.format("File has been added to Transit Sheet successfully"));
    }

    public List<FileTransitSheet> viewAll(){
        return transitRepository.findAll();
    }

    public List<FileTransitSheet> viewByReference(String reference){
        return transitRepository.findByRefIgnoreCase(reference);
    }

    public long getCount (){
        return transitRepository.getCount();
    }

    public List<FileTransitSheet>getByYear(){
        return transitRepository.getOutgoingByYear(LocalDate.now().getYear());
    }

    public int getRows(){
        return transitRepository.getRows(LocalDate.now().getYear());
    }

    public List<FileTransitSheet>getFirstQuarter(){
        return transitRepository.getOutgoingByQuarter(LocalDate.now().getYear());
    }

    public int getFirstRow(){
        return transitRepository.getFirstQuarterCount(LocalDate.now().getYear());
    }

    public List<FileTransitSheet> getSecondQuarter(){
        return transitRepository.getSecondQuarter(LocalDate.now().getYear());
    }

    public int getSecondRow(){
        return transitRepository.getSecondQuarterCount(LocalDate.now().getYear());
    }

    public List<FileTransitSheet>getThirdQuarter(){
        return transitRepository.getThirdQuarter(LocalDate.now().getYear());
    }

    public int getThirdRow(){
        return transitRepository.getThirdQuarterCount(LocalDate.now().getYear());
    }

    public List<FileTransitSheet> getFourthQuarter(){
        return transitRepository.getFourthQuarter(LocalDate.now().getYear());
    }

    public int getFourthRow(){
        return transitRepository.getFourthQuarterCount(LocalDate.now().getYear());
    }

    public ResponseEntity<?>updateReturn(String ref, String returningOfficer){
        FileTransitSheet fileTransitSheet = transitRepository.getUpdate(ref).orElse(null);
        System.out.println(fileTransitSheet);
        if(fileTransitSheet == null){
            return ResponseEntity.ok().body("file does not exist on transit sheet");
        }
//        String str = fileTransitSheet.getReference().replaceAll("\\/","");
        fileTransitSheet.setAppUser(fileTransitSheet.getAppUser());
        fileTransitSheet.setTransitId(fileTransitSheet.getTransitId());
        fileTransitSheet.setDate(fileTransitSheet.getDate());
        fileTransitSheet.setFileDiary(fileTransitSheet.getFileDiary());
        fileTransitSheet.setStatus("in");
        fileTransitSheet.setReference(fileTransitSheet.getReference());
        fileTransitSheet.setRef(fileTransitSheet.getRef());
        fileTransitSheet.setSignature(fileTransitSheet.getSignature());
        fileTransitSheet.setRemarks(fileTransitSheet.getSentTo());
        fileTransitSheet.setReturningOfficer(returningOfficer);
        fileTransitSheet.setReturnedDate(LocalDate.now());
        transitRepository.save(fileTransitSheet);
        return ResponseEntity.ok().body("transit sheet has been updated successfully");

    }
}



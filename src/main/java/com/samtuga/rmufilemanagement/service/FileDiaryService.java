package com.samtuga.rmufilemanagement.service;

import com.samtuga.rmufilemanagement.entity.*;
import com.samtuga.rmufilemanagement.jwt.AuthEntryPointJwt;
import com.samtuga.rmufilemanagement.repository.DepartmentRepository;
import com.samtuga.rmufilemanagement.repository.DiaryRepository;
import com.samtuga.rmufilemanagement.repository.UnitRepository;
import com.samtuga.rmufilemanagement.repository.UserRepository;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FileDiaryService {
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    private final String FILE_EXIST = "The file with reference %s already exist";

    private static final Logger logger = LoggerFactory.getLogger(FileDiaryService.class);

    public ResponseEntity<?> addDiary(FileDiary fileDiary) {
        if (diaryRepository.findByReference(fileDiary.getReference()).isPresent()) {
            return ResponseEntity.ok().body(String.format(FILE_EXIST, fileDiary.getReference()));
        }


        Department dept = departmentRepository.findByName(fileDiary.getDepartment().getName()).orElse(null);
        if (dept == null) {
            dept = new Department();
        } else {
            logger.info("dept{}:", dept.getName());
//            dept.setId(dept.getId());
            fileDiary.setDepartment(dept);
        }

        Unit unit = unitRepository.findByName(fileDiary.getUnit().getName()).orElse(null);
        if (unit == null) {
            unit = new Unit();
        } else {
            logger.info("dept{}:", unit.getName());
//            dept.setId(dept.getId());
            fileDiary.setUnit(unit);

        }

        AppUser appUser = userRepository.findByEmail(fileDiary.getAppUser().getEmail()).orElse(null);
        if(appUser == null){
            ResponseEntity.ok().body("You need to log in to perform this Operation");
        } else{
            appUser.setEmail(appUser.getEmail());
            appUser.setFirstName(appUser.getFirstName());
            appUser.setPassword(appUser.getPassword());
        }
        String str = fileDiary.getReference().replaceAll("\\/","");
        fileDiary.setAppUser(appUser);
        fileDiary.setRef(str);
        fileDiary.setDateOpened(LocalDate.now());
        diaryRepository.save(fileDiary);
        return ResponseEntity.ok().body(String.format("File has been added to Diary successfully"));
    }

    public List<FileDiary> viewDiary() {
        return diaryRepository.findAll();
    }


    public List<FileDiary> viewByReference(String reference) {
        return diaryRepository.findByRefIgnoreCase(reference);
    }

    public long getCount() {
        return diaryRepository.getCount();
    }

    public List<FileDiary> getByYear() {
        return diaryRepository.getOutgoingByYear(LocalDate.now().getYear());
    }

    public int getRows() {
        return diaryRepository.getRows(LocalDate.now().getYear());
    }

    public List<FileDiary> getFirstQuarter() {
        return diaryRepository.getOutgoingByQuarter(LocalDate.now().getYear());
    }

    public int getFirstRow() {
        return diaryRepository.getFirstQuarterCount(LocalDate.now().getYear());
    }

    public List<FileDiary> getSecondQuarter() {
        return diaryRepository.getSecondQuarter(LocalDate.now().getYear());
    }

    public int getSecondRow() {
        return diaryRepository.getSecondQuarterCount(LocalDate.now().getYear());
    }

    public List<FileDiary> getThirdQuarter() {
        return diaryRepository.getThirdQuarter(LocalDate.now().getYear());
    }

    public int getThirdRow() {
        return diaryRepository.getThirdQuarterCount(LocalDate.now().getYear());
    }

    public List<FileDiary> getFourthQuarter() {
        return diaryRepository.getFourthQuarter(LocalDate.now().getYear());
    }

    public int getFourthRow() {
        return diaryRepository.getFourthQuarterCount(LocalDate.now().getYear());
    }

}


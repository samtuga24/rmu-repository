package com.samtuga.rmufilemanagement.service;
import com.samtuga.rmufilemanagement.entity.*;
import com.samtuga.rmufilemanagement.repository.DiaryRepository;
import com.samtuga.rmufilemanagement.repository.IncomingRepository;
import com.samtuga.rmufilemanagement.repository.StorageRepository;
import com.samtuga.rmufilemanagement.repository.UserRepository;
import com.samtuga.rmufilemanagement.util.DocumentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IncomingService {
    @Autowired
    private IncomingRepository incomingRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    private final String NO_DOCUMENT_FOUND = "File With Subject %s does not exist";
    private final String NO_DOCUMENT_FOUND_SUBJECT = "File With Subject %s already exist";
    private final String DOCUMENT_ALREADY_EXIST = "The file with reference %s already exist";
    private final String FILE_DOES_NOT_EXIST = "file has no reference in the file diary";

    Logger logger = LoggerFactory.getLogger(IncomingService.class);
    public ResponseEntity<?> addIncoming(LocalDate date, String fromWhom, String instRef, String subject, AppUser user,MultipartFile file) throws IOException{
        if (incomingRepository.findBySubjectIgnoreCase(subject).isPresent()){
            return ResponseEntity.ok().body(String.format(NO_DOCUMENT_FOUND_SUBJECT,subject));
        }

        if(storageRepository.findByName(file.getOriginalFilename()).isPresent()){
            return ResponseEntity.ok().body("Please Rename file");
        }

        Document document = Document.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .document(DocumentUtils.compressImage(file.getBytes())).build();


        AppUser appUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if(appUser == null){
            ResponseEntity.ok().body("You need to log in to perform this Operation");
        } else{
            appUser.setEmail(appUser.getEmail());
            appUser.setFirstName(appUser.getFirstName());
            appUser.setPassword(appUser.getPassword());
        }
        IncomingCorrespondence incoming = new IncomingCorrespondence();
        incoming.setDateOfLetter(date);
        incoming.setDocuments(document);
        incoming.setSubject(subject);
        incoming.setDateReceived(LocalDate.now());
        incoming.setAppUser(appUser);
        incoming.setFromWhomReceived(fromWhom);
        incoming.setInstitutionsReference(instRef);
        incomingRepository.save(incoming);
            return ResponseEntity.ok().body(String.format("Incoming file has been added successfully"));
    }

    public ResponseEntity<?>updateMinuted(String id, MultipartFile file,String reference[]) throws IOException{
        IncomingCorrespondence correspondence = incomingRepository.findBySubjectIgnoreCase(id).orElse(null);
        if(correspondence == null){
            return ResponseEntity.ok().body(String.format(NO_DOCUMENT_FOUND,id));
        }

        System.out.println("this is the length of the array" + reference.toString());


        Set<FileDiary> fileDiaries = new HashSet<>();


        MinutedDocument document = MinutedDocument.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .document(DocumentUtils.compressImage(file.getBytes())).build();

        if (document != null) {

            IncomingCorrespondence incoming = new IncomingCorrespondence();
            for(int i = 0; i<= reference.length-1; i++){
                FileDiary fileDiary =  diaryRepository.findByReference(reference[i]).orElse(null);
                if(fileDiary == null) {
                    return ResponseEntity.ok().body(String.format(FILE_DOES_NOT_EXIST));
                }
                    fileDiary.setReference(reference[i]);

                    fileDiaries.add(fileDiary);
                    incoming.setFileDiary(fileDiaries);
                    incoming.setFolioNumber(correspondence.getFolioNumber());
                    incoming.setMinutedDocument(document);


                    fileDiaries.stream().forEach(arr->{
                        incoming.setReference(arr.getReference());
                        String str = arr.getReference().replaceAll("\\/","");
                        incoming.setRef(str);

                    });

                    System.out.println("this is the incoming" + incoming);

//                    incoming.setDocuments(correspondence.getDocuments());
                    incoming.setDateReceived(correspondence.getDateReceived());
                    incoming.setDocuments(correspondence.getDocuments());
                    incoming.setDateOfLetter(correspondence.getDateOfLetter());
                    incoming.setAppUser(correspondence.getAppUser());
                    incoming.setFromWhomReceived(correspondence.getFromWhomReceived());
                    incoming.setInstitutionsReference(correspondence.getInstitutionsReference());
                    incoming.setSubject(correspondence.getSubject());

                    incomingRepository.save(incoming);




            }


            return ResponseEntity.ok().body(String.format("The file %s has been uploaded successfully",file.getOriginalFilename()));
        }
        return null;
    }

    public List<IncomingCorrespondence> viewAll(){
        return incomingRepository.findAll();
    }

    public ResponseEntity<?> viewByReference(String reference){
        return ResponseEntity.ok().body(incomingRepository.findBySubjectIgnoreCase(reference));
    }

    public List<IncomingCorrespondence>viewSub(String reference){
        String str = reference.replaceAll("\\/","");

        return incomingRepository.findIncomingByRefIgnoreCase(str);
    }

    public long getCount (){
        return incomingRepository.getCount();
    }

    public List<IncomingCorrespondence>getByYear(){
        return incomingRepository.getIncomingByYear(LocalDate.now().getYear());
    }

    public int getRows(){
        return incomingRepository.getRows(LocalDate.now().getYear());
    }

    public List<IncomingCorrespondence>getFirstQuarter(){
        return incomingRepository.getIncomingByQuarter(LocalDate.now().getYear());
    }

    public int getFirstRow(){
        return incomingRepository.getFirstQuarterCount(LocalDate.now().getYear());
    }

    public List<IncomingCorrespondence> getSecondQuarter(){
        return incomingRepository.getSecondQuarter(LocalDate.now().getYear());
    }

    public int getSecondRow(){
        return incomingRepository.getSecondQuarterCount(LocalDate.now().getYear());
    }

    public List<IncomingCorrespondence>getThirdQuarter(){
        return incomingRepository.getThirdQuarter(LocalDate.now().getYear());
    }

    public int getThirdRow(){
        return incomingRepository.getThirdQuarterCount(LocalDate.now().getYear());
    }

    public List<IncomingCorrespondence> getFourthQuarter(){
        return incomingRepository.getFourthQuarter(LocalDate.now().getYear());
    }

    public int getFourthRow(){
        return incomingRepository.getFourthQuarterCount(LocalDate.now().getYear());
    }
}

package com.samtuga.rmufilemanagement.service;

import com.samtuga.rmufilemanagement.entity.*;
import com.samtuga.rmufilemanagement.repository.*;
import com.samtuga.rmufilemanagement.util.DocumentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OutgoingService {
    @Autowired
    private OutgoingRepository outgoingRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private StorageRepository storageRepository;

    private final String NO_DOCUMENT_FOUND = "File With Reference Number %s does not exist";
    private final String FILE_ALREADY_EXIST = "file with reference %s already exist";
    private final String FILE_DOES_NOT_EXIST = "file has no reference in the file diary";
    Logger logger = LoggerFactory.getLogger(OutgoingService.class);
    public ResponseEntity<?> addOutgoing(String reference, LocalDate date,String subject, String mode,AppUser user, String address[], MultipartFile file) throws IOException {
        if (diaryRepository.findByReference(reference).isEmpty()) {
            return ResponseEntity.ok().body(String.format(FILE_DOES_NOT_EXIST, reference));
        }

        if(storageRepository.findByName(file.getOriginalFilename()).isPresent()){
            return ResponseEntity.ok().body("Please Rename file");
        }
        OutgoingCorrespondence outgoing = new OutgoingCorrespondence();
        Set<Address> addresses = new HashSet<>();

        Document document = Document.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .document(DocumentUtils.compressImage(file.getBytes())).build();

        if (document != null) {
            for (int i = 0; i <= address.length - 1; i++) {
                Address ad = addressRepository.findByAddressIgnoreCase(address[i]).orElse(null);
                if( ad == null){
                    ad = new Address();
                }
                    ad.setAddress(address[i]);
                    addresses.add(ad);
                    outgoing.setAddressee(addresses);



                AppUser appUser = userRepository.findByEmail(user.getEmail()).orElse(null);
                if (appUser == null) {
                    ResponseEntity.ok().body("You need to log in to perform this Operation");
                } else {
                    appUser.setEmail(appUser.getEmail());
                    appUser.setFirstName(appUser.getFirstName());
                    appUser.setPassword(appUser.getPassword());
                }
                String str = reference.replaceAll("\\/", "");
                outgoing.setDateDispatched(LocalDate.now());
                outgoing.setDateReceivedForDispatch(date);
                outgoing.setSubject(subject);
                outgoing.setModeOfDispatch(mode);
                outgoing.setAppUser(appUser);
                outgoing.setDocuments(document);
                outgoing.setRef(str);
                outgoing.setReference(reference);
                outgoing.setAddressee(addresses);
                outgoingRepository.save(outgoing);

            }
        }
        return ResponseEntity.ok().body(String.format("Outgoing file has been added successfully"));
    }

    public ResponseEntity<?>updateIncoming(MultipartFile file, String reference) throws IOException {
        OutgoingCorrespondence outgoingCorrespondence = outgoingRepository.findBySubjectIgnoreCase(reference).orElse(null);
        if(outgoingCorrespondence == null){
            return ResponseEntity.ok().body(String.format(NO_DOCUMENT_FOUND,reference));
        }
        Document document = Document.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .document(DocumentUtils.compressImage(file.getBytes())).build();
        if (document != null) {
//            return "file uploaded successfully : " + file.getOriginalFilename();
            OutgoingCorrespondence out = new OutgoingCorrespondence();
            out.setFolioNumber(outgoingCorrespondence.getFolioNumber());
            out.setDocuments(document);
            out.setAddressee(outgoingCorrespondence.getAddressee());
            out.setReference(outgoingCorrespondence.getReference());
            out.setDateReceivedForDispatch(outgoingCorrespondence.getDateReceivedForDispatch());
            out.setDateDispatched(outgoingCorrespondence.getDateDispatched());
            out.setAppUser(outgoingCorrespondence.getAppUser());
            out.setModeOfDispatch(outgoingCorrespondence.getModeOfDispatch());
            out.setSubject(outgoingCorrespondence.getSubject());

            out.setSub(outgoingCorrespondence.getSub());
            outgoingRepository.save(out);
            return ResponseEntity.ok().body(String.format("The file %s has been uploaded successfully",file.getOriginalFilename()));
        }
        return null;

    }


    public List<OutgoingCorrespondence> viewAll(){
        return outgoingRepository.findAll();
    }

    public  ResponseEntity<?> viewByReference(String reference){
        return ResponseEntity.ok().body(outgoingRepository.findBySubjectIgnoreCase(reference));
    }

    public List<OutgoingCorrespondence>viewSub(String sub){
        return outgoingRepository.findByRefIgnoreCase(sub);
    }

    public long getCount (){
        return outgoingRepository.getCount();
    }

    public List<OutgoingCorrespondence>getByYear(){
        return outgoingRepository.getOutgoingByYear(LocalDate.now().getYear());
    }

    public int getRows(){
        return outgoingRepository.getRows(LocalDate.now().getYear());
    }

    public List<OutgoingCorrespondence>getFirstQuarter(){
        return outgoingRepository.getOutgoingByQuarter(LocalDate.now().getYear());
    }

    public int getFirstRow(){
        return outgoingRepository.getFirstQuarterCount(LocalDate.now().getYear());
    }

    public List<OutgoingCorrespondence> getSecondQuarter(){
        return outgoingRepository.getSecondQuarter(LocalDate.now().getYear());
    }

    public int getSecondRow(){
        return outgoingRepository.getSecondQuarterCount(LocalDate.now().getYear());
    }

    public List<OutgoingCorrespondence>getThirdQuarter(){
        return outgoingRepository.getThirdQuarter(LocalDate.now().getYear());
    }

    public int getThirdRow(){
        return outgoingRepository.getThirdQuarterCount(LocalDate.now().getYear());
    }

    public List<OutgoingCorrespondence> getFourthQuarter(){
        return outgoingRepository.getFourthQuarter(LocalDate.now().getYear());
    }

    public int getFourthRow(){
        return outgoingRepository.getFourthQuarterCount(LocalDate.now().getYear());
    }
}

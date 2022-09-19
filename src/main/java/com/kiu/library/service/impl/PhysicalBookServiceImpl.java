package com.kiu.library.service.impl;

import com.kiu.library.entity.BookRequirementEntity;
import com.kiu.library.entity.PhysicalBookEntity;
import com.kiu.library.entity.StudentDetailEntity;
import com.kiu.library.model.physicalBook.PhysicalBookReqInfo;
import com.kiu.library.model.physicalBook.PhysicalDataBookReqInfo;
import com.kiu.library.model.physicalBook.StudentInfo;
import com.kiu.library.model.physicalBook.StudentInfoObj;
import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.repository.PhysicalBookRepository;
import com.kiu.library.repository.RequirementRepository;
import com.kiu.library.repository.StudentRepository;
import com.kiu.library.service.BookRequirementService;
import com.kiu.library.service.FileStorageService;
import com.kiu.library.service.PhysicalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PhysicalBookServiceImpl implements PhysicalBookService {

    @Autowired
    private PhysicalBookRepository resourceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public PhysicalBookReqInfo getAllInfo(int page, int limit, String sort) {

        Pageable pageable = PageRequest.of(page - 1, limit);

        List<PhysicalBookEntity> allInfo = resourceRepository.findByStatusOrderByIdDesc(1);

        Integer count = resourceRepository.countAllResource();

        PhysicalDataBookReqInfo resourceDataInfo = new PhysicalDataBookReqInfo();
        resourceDataInfo.setTotal(count);

        List<PhysicalBookInfo> listOfResources = new ArrayList<>();

        for (PhysicalBookEntity info : allInfo) {

            PhysicalBookInfo resourceInfo = new PhysicalBookInfo(info.getId(),
                    info.getStudentId(),info.getBookId(),info.getReturnDate(),
                    info.getStatus(), info.getFine(), info.getDateTime());

            listOfResources.add(resourceInfo);


        }
        resourceDataInfo.setItems(listOfResources);

        PhysicalBookReqInfo allResourceInfo = new PhysicalBookReqInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    @Transactional
    public StudentInfo getStudentInfo() {

        List<StudentDetailEntity> studentList = studentRepository.findAll();

        StudentInfo resourceDataInfo = new StudentInfo();
        resourceDataInfo.setCode(200);

        List<StudentInfoObj> allInfo = new ArrayList<>();

        for (StudentDetailEntity obj : studentList) {
            StudentInfoObj studentInfoObj1 = new StudentInfoObj();
            studentInfoObj1.setId(obj.getStudentId());
            studentInfoObj1.setName(obj.getEmail());
            allInfo.add(studentInfoObj1);
        }

        resourceDataInfo.setData(allInfo);

        return resourceDataInfo;
    }

    @Override
    @Transactional
    public StudentInfo getBookInfo() {

        StudentInfo resourceDataInfo = new StudentInfo();
        resourceDataInfo.setCode(200);

        List<StudentInfoObj> allInfo = new ArrayList<>();


        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "file1.txt"));
            String line = reader.readLine();
            while (line != null) {

                line = reader.readLine();

                if (line != null) {
                    String[] splitValue = line.split("\t");

                    StudentInfoObj studentInfoObj1 = new StudentInfoObj();
                    studentInfoObj1.setId(Integer.parseInt(splitValue[0]));
                    studentInfoObj1.setName(splitValue[1]);
                    allInfo.add(studentInfoObj1);
                }


            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




        resourceDataInfo.setData(allInfo);

        return resourceDataInfo;
    }

    @Override
    public SaveResourceResponse updatePhysicalBookStatus(int id, int type,  Integer user) {

        Optional<PhysicalBookEntity> allInfo = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        PhysicalBookEntity updateObj = allInfo.get();
        updateObj.setStatus(type);
//        updateObj.setLastUpdatedTime((double) timestamp.getTime());
//        updateObj.setReasonIfRejected(resource);

        List<PhysicalBookEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updateObj);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(200, "success");

    }

    @Override
    public SaveEResourceResponse createPhysicalBook(PhysicalBookInfo physicalBookInfo) {

        Pageable topOne = PageRequest.of(0, 1);
        List<PhysicalBookEntity> allInfo = resourceRepository.getLatestId(topOne);

        Integer id = 1;

        if (!allInfo.isEmpty())
            id = allInfo.get(0).getId();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int idNew = ++id;

        PhysicalBookEntity resourceEntity = new PhysicalBookEntity(idNew,
                physicalBookInfo.getStudentId(), physicalBookInfo.getBookId(),
                physicalBookInfo.getReturnDate(), 1, 0, dtf.format(now));

        List<PhysicalBookEntity> listOfResources = new ArrayList<>();
        listOfResources.add(resourceEntity);

        resourceRepository.saveAll(listOfResources);

        return new SaveEResourceResponse(20000, "success", idNew);

    }

}

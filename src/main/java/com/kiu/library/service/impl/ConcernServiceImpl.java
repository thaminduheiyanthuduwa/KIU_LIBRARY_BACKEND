package com.kiu.library.service.impl;

import com.kiu.library.entity.ConcernEntity;
import com.kiu.library.entity.DatabaseEntity;
import com.kiu.library.model.concernModel.AllConcernInfo;
import com.kiu.library.model.concernModel.ConcernDataInfo;
import com.kiu.library.model.concernModel.ConcernInfo;
import com.kiu.library.model.databaseModel.*;
import com.kiu.library.model.resouceModel.SaveResourceResponse;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.repository.ConcernRepository;
import com.kiu.library.repository.DatabaseRepository;
import com.kiu.library.service.ConcernService;
import com.kiu.library.service.EDatabaseService;
import com.kiu.library.service.FileStorageServiceForDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ConcernServiceImpl implements ConcernService {

    @Autowired
    private ConcernRepository concernRepository;

    @Autowired
    private FileStorageServiceForDatabase fileStorageService;

    @Override
    @Transactional
    public AllConcernInfo getAllInfo(int page, int limit, String sort) {

        Pageable pageable = PageRequest.of(page - 1, limit);

        List<ConcernEntity> allInfo = concernRepository.findByIsActiveOrderByIdDesc(1);

        Integer count = concernRepository.countAllResource();

        ConcernDataInfo resourceDataInfo = new ConcernDataInfo();
        resourceDataInfo.setTotal(count);

        List<ConcernInfo> listOfResources = new ArrayList<>();

        for (ConcernEntity info : allInfo) {

            ConcernInfo concernInfo = new ConcernInfo(info.getId(), info.getStudentId(),
                    info.getConcern(), info.getConcernId(), info.getStatus(), info.getFile(),
                    info.getHeadApproval(), info.getDate(), info.getIsActive(), info.getFinalDoc(), info.getIsPublic());

            listOfResources.add(concernInfo);


        }
        resourceDataInfo.setItems(listOfResources);

        AllConcernInfo allResourceInfo = new AllConcernInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    public AllConcernInfo getAllInfoById(int id) throws IOException {


        Optional<ConcernEntity> allInfoObj = concernRepository.findById(id);

        ConcernEntity info = allInfoObj.get();

        Integer count = concernRepository.countAllResource();

        ConcernDataInfo resourceDataInfo = new ConcernDataInfo();
        resourceDataInfo.setTotal(count);

        List<ConcernInfo> listOfResources = new ArrayList<>();

        ConcernInfo concernInfo = new ConcernInfo(info.getId(), info.getStudentId(),
                info.getConcern(), info.getConcernId(), info.getStatus(), info.getFile(),
                info.getHeadApproval(), info.getDate(), info.getIsActive(), info.getFinalDoc(), info.getIsPublic());

        listOfResources.add(concernInfo);

        resourceDataInfo.setItems(listOfResources);

        AllConcernInfo allResourceInfo = new AllConcernInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;

    }

    @Override
    public UploadFileResponse uploadFiles(int id, MultipartFile[] cover) {

        return uploadFile(id, Arrays.asList(cover).get(0));

    }

    private UploadFileResponse uploadFile(int id, MultipartFile cover) {

        String coverImage = fileStorageService.storeFile(id, cover);

        String coverFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/concern/")
                .path(coverImage)
                .toUriString();

        Optional<ConcernEntity> resource = concernRepository.findById(id);

        ConcernEntity updatedResource = resource.get();
        updatedResource.setFinalDoc(coverImage);

        List<ConcernEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        concernRepository.saveAll(listOfResources);

        return new UploadFileResponse(coverImage, coverFileDownloadUri,
                cover.getContentType(), cover.getSize());

    }

    @Override
    public SaveResourceResponse updateStatus(int id, String status, int user) {

        Optional<ConcernEntity> resource = concernRepository.findById(id);

        ConcernEntity updatedResource = resource.get();

        if (status.equals("1"))
            updatedResource.setStatus(1);
        else if (status.equals("2"))
            updatedResource.setStatus(2);
        else if (status.equals("3"))
            updatedResource.setStatus(3);
        else if (status.equals("4"))
            updatedResource.setStatus(4);
        else if (status.equals("5"))
            updatedResource.setStatus(5);
        else if (status.equalsIgnoreCase("public")) {
            updatedResource.setIsPublic(1);
        } else if (status.equalsIgnoreCase("private")) {
            updatedResource.setIsPublic(0);
        } else if (status.equalsIgnoreCase("active")) {
            updatedResource.setIsActive(0);
        } else if (status.equalsIgnoreCase("inactive")) {
            updatedResource.setIsActive(0);
        }

        List<ConcernEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        concernRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000, "success");
    }

}

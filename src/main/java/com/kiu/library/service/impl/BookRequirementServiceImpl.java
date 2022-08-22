package com.kiu.library.service.impl;

import com.kiu.library.entity.BookRequirementEntity;
import com.kiu.library.entity.ResourceEntity;
import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.repository.RequirementRepository;
import com.kiu.library.repository.ResourceRepository;
import com.kiu.library.service.BookRequirementService;
import com.kiu.library.service.EResourceService;
import com.kiu.library.service.FileStorageService;
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
public class BookRequirementServiceImpl implements BookRequirementService {

    @Autowired
    private RequirementRepository resourceRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    @Transactional
    public AllResourceBookReqInfo getAllInfo(int page, int limit, String sort) {

        Pageable pageable = PageRequest.of(page - 1, limit);

        List<BookRequirementEntity> allInfo = resourceRepository.findByIsActiveOrderByIdDesc(1, pageable);

        Integer count = resourceRepository.countAllResource();

        ResourceDataBookReqInfo resourceDataInfo = new ResourceDataBookReqInfo();
        resourceDataInfo.setTotal(count);

        List<BookResourceInfo> listOfResources = new ArrayList<>();

        for (BookRequirementEntity info : allInfo) {

            String[] test = {"a-platform"};

            BookResourceInfo resourceInfo = new BookResourceInfo(info.getId(), info.getTimestamp(),
                    info.getTitle(), info.getAuthor(), info.getDepartment(),
                    info.getResource(), info.getImportance(), info.getType(), info.getStatus(),
                    info.getDisplayTime(), info.isCommentDisabled(),
                    info.getPageViews(), test, info.getDocumentName(), info.getDescription()
                    , info.getDocumentImage(), info.getHeadApproval(), info.getFinalApproval(), info.getReasonIfRejected());

            listOfResources.add(resourceInfo);


        }
        resourceDataInfo.setItems(listOfResources);

        AllResourceBookReqInfo allResourceInfo = new AllResourceBookReqInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    public AllResourceInfo getInfoById(int id) throws IOException {

        Optional<BookRequirementEntity> allInfo = resourceRepository.findById(id);

        Integer count = resourceRepository.countAllResource();

        ResourceDataInfo resourceDataInfo = new ResourceDataInfo();
        resourceDataInfo.setTotal(count);

        List<ResourceInfo> listOfResources = new ArrayList<>();

        String[] test = {"a-platform"};

        BookRequirementEntity info = allInfo.get();

        ResourceInfo resourceInfo = new ResourceInfo(info.getId(), info.getTimestamp(),
                info.getTitle(), info.getAuthor(), info.getDepartment(),
                info.getResource(), info.getImportance(), info.getType(), info.getStatus(),
                info.getDisplayTime(), info.isCommentDisabled(),
                info.getPageViews(), test, info.getDocumentName(), info.getDescription()
                , info.getDocumentImage());

        listOfResources.add(resourceInfo);


        resourceDataInfo.setItems(listOfResources);

        AllResourceInfo allResourceInfo = new AllResourceInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    public SaveEResourceResponse saveInfo(SaveResourceRequest saveRequest) {

        Pageable topOne = PageRequest.of(0, 1);
        List<BookRequirementEntity> allInfo = resourceRepository.getLatestId(topOne);

        Integer id = 1;

        if (!allInfo.isEmpty())
            id = allInfo.get(0).getId();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int idNew = ++id;

        BookRequirementEntity resourceEntity = new BookRequirementEntity(idNew, (double) timestamp.getTime(),
                saveRequest.getTitle(),
                saveRequest.getAuthor(), saveRequest.getDepartment(), saveRequest.getResource(),
                0, saveRequest.getType(), "draft", dtf.format(now), false, 0,
                saveRequest.getAddedUser(), (double) timestamp.getTime(), saveRequest.getAddedUser(), 0, saveRequest.getDescription(), "", "", 0, 0, "");

        List<BookRequirementEntity> listOfResources = new ArrayList<>();
        listOfResources.add(resourceEntity);

        resourceRepository.saveAll(listOfResources);

        return new SaveEResourceResponse(20000, "success", idNew);

    }


    @Override
    public SaveResourceResponse deleteEresource(int id, int user) {

        Optional<BookRequirementEntity> resource = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        BookRequirementEntity updatedResource = resource.get();

        updatedResource.setIsActive(0);
        updatedResource.setLastUpdatedTime((double) timestamp.getTime());
        updatedResource.setLastUpdatedUser(user);

        List<BookRequirementEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000, "success");

    }

    @Override
    public SaveResourceResponse updateEresourceStatus(int id, String status, int user, String type) {
        Optional<BookRequirementEntity> resource = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        BookRequirementEntity updatedResource = resource.get();

        if (type.equals("published"))
            updatedResource.setStatus(status);
        else if (type.equals("draft"))
            updatedResource.setStatus(status);
        else if (type.equals("2"))
            updatedResource.setHeadApproval(1);
        else if (type.equals("3"))
            updatedResource.setHeadApproval(0);
        else if (type.equals("4"))
            updatedResource.setFinalApproval(1);
        else if (type.equals("5"))
            updatedResource.setHeadApproval(0);

        updatedResource.setLastUpdatedTime((double) timestamp.getTime());
        updatedResource.setLastUpdatedUser(user);

        List<BookRequirementEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000, "success");
    }

    @Override
    public SaveResourceResponse updateEresource(int id, String type, String resource, Integer user) {

        Optional<BookRequirementEntity> allInfo = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        BookRequirementEntity updateObj = allInfo.get();

        if (type == "1"){
//            updateObj.setLastUpdatedUser(user);
//            updateObj.setLastUpdatedTime((double) timestamp.getTime());
        }
        if (type == "2"){

            updateObj.setLastUpdatedUser(user);
        }

        updateObj.setLastUpdatedTime((double) timestamp.getTime());
        updateObj.setReasonIfRejected(resource);

        List<BookRequirementEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updateObj);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000, "success");

    }

    @Override
    public UploadFileResponse uploadFiles(int id, MultipartFile[] files, String type) {

        return uploadFile(id, Arrays.asList(files).get(0), type);
    }

    private UploadFileResponse uploadFile(int id, MultipartFile file, String type) {
        String fileName = fileStorageService.storeFile(id, file, type);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploadedFiles/")
                .path(fileName)
                .toUriString();

        Optional<BookRequirementEntity> resource = resourceRepository.findById(id);

        BookRequirementEntity updatedResource = resource.get();
        if (type.equals("1")) {
            updatedResource.setDocumentName(fileName);
            updatedResource.setIsActive(1);
        } else if (type.equals("2")) {
            updatedResource.setDocumentImage(fileName);
        }

        List<BookRequirementEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }


}

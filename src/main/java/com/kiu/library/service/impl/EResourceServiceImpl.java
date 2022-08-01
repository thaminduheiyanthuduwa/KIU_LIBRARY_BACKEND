package com.kiu.library.service.impl;

import com.kiu.library.entity.ResourceEntity;
import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.repository.ResourceRepository;
import com.kiu.library.service.EResourceService;
import com.kiu.library.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EResourceServiceImpl implements EResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    @Transactional
    public AllResourceInfo getAllInfo(int page, int limit, String sort) {

        Pageable pageable = PageRequest.of(page - 1, limit);

        List<ResourceEntity> allInfo = resourceRepository.findByIsActive(1, pageable);

        Integer count = resourceRepository.countAllResource();

        ResourceDataInfo resourceDataInfo = new ResourceDataInfo();
        resourceDataInfo.setTotal(count);

        List<ResourceInfo> listOfResources = new ArrayList<>();

        for (ResourceEntity info : allInfo) {

            String[] test = {"a-platform"};

            ResourceInfo resourceInfo = new ResourceInfo(info.getId(), info.getTimestamp(),
                    info.getTitle(), info.getAuthor(), info.getDepartment(),
                    info.getResource(), info.getImportance(), info.getType(), info.getStatus(),
                    info.getDisplayTime(), info.isCommentDisabled(),
                    info.getPageViews(), test, info.getDocumentName(), info.getDescription()
                    , info.getDocumentImage());

            listOfResources.add(resourceInfo);


        }
        resourceDataInfo.setItems(listOfResources);

        AllResourceInfo allResourceInfo = new AllResourceInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    public AllResourceInfo getInfoById(int id) throws IOException {

        Optional<ResourceEntity> allInfo = resourceRepository.findById(id);

        Integer count = resourceRepository.countAllResource();

        ResourceDataInfo resourceDataInfo = new ResourceDataInfo();
        resourceDataInfo.setTotal(count);

        List<ResourceInfo> listOfResources = new ArrayList<>();

        String[] test = {"a-platform"};

        ResourceEntity info = allInfo.get();

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
        List<ResourceEntity> allInfo = resourceRepository.getLatestId(topOne);

        Integer id = 1;

        if (!allInfo.isEmpty())
            id = allInfo.get(0).getId();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int idNew = ++id;

        ResourceEntity resourceEntity = new ResourceEntity(idNew, (double) timestamp.getTime(),
                saveRequest.getTitle(),
                saveRequest.getAuthor(), saveRequest.getDepartment(), saveRequest.getResource(),
                0, saveRequest.getType(), "draft", dtf.format(now), false, 0,
                saveRequest.getAddedUser(), (double) timestamp.getTime(), saveRequest.getAddedUser(), 0, saveRequest.getDescription(), "", "");

        List<ResourceEntity> listOfResources = new ArrayList<>();
        listOfResources.add(resourceEntity);

        resourceRepository.saveAll(listOfResources);

        return new SaveEResourceResponse(20000, "success", idNew);

    }


    @Override
    public SaveResourceResponse deleteEresource(int id, int user) {

        Optional<ResourceEntity> resource = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ResourceEntity updatedResource = resource.get();

        updatedResource.setIsActive(0);
        updatedResource.setLastUpdatedTime((double) timestamp.getTime());
        updatedResource.setLastUpdatedUser(user);

        List<ResourceEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000, "success");

    }

    @Override
    public SaveResourceResponse updateEresourceStatus(int id, String status, int user) {
        Optional<ResourceEntity> resource = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ResourceEntity updatedResource = resource.get();

        updatedResource.setStatus(status);
        updatedResource.setLastUpdatedTime((double) timestamp.getTime());
        updatedResource.setLastUpdatedUser(user);

        List<ResourceEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000, "success");
    }

    @Override
    public SaveResourceResponse updateEresource(int id, SaveResourceRequest saveRequest) {

        Optional<ResourceEntity> allInfo = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ResourceEntity updateObj = allInfo.get();
        updateObj.setAuthor(saveRequest.getAuthor());
        updateObj.setDepartment(saveRequest.getDepartment());
        updateObj.setTitle(saveRequest.getTitle());
        updateObj.setType(saveRequest.getType());
        updateObj.setResource(saveRequest.getResource());
        updateObj.setDescription(saveRequest.getDescription());
        updateObj.setLastUpdatedUser(saveRequest.getAddedUser());
        updateObj.setLastUpdatedTime((double) timestamp.getTime());

        List<ResourceEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updateObj);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000, "success");

    }

    @Override
    public UploadFileResponse uploadFiles(int id, MultipartFile[] files, MultipartFile[] cover) {

        return uploadFile(id, Arrays.asList(files).get(0), Arrays.asList(cover).get(0));
    }

    private UploadFileResponse uploadFile(int id, MultipartFile file, MultipartFile cover) {
        String fileName = fileStorageService.storeFile(id, file);
        String coverImage = fileStorageService.storeFile(id, cover);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploadedFiles/")
                .path(fileName)
                .toUriString();

        String coverFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploadedFiles/")
                .path(coverImage)
                .toUriString();

        Optional<ResourceEntity> resource = resourceRepository.findById(id);

        ResourceEntity updatedResource = resource.get();
        updatedResource.setDocumentName(fileName);
        updatedResource.setDocumentImage(coverImage);
        updatedResource.setIsActive(1);

        List<ResourceEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        new UploadFileResponse(coverImage, coverFileDownloadUri,
                cover.getContentType(), cover.getSize());

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }


}

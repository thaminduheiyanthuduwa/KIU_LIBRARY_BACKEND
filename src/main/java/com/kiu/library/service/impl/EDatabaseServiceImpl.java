package com.kiu.library.service.impl;

import com.kiu.library.entity.DatabaseEntity;
import com.kiu.library.entity.ResourceEntity;
import com.kiu.library.model.databaseModel.*;
import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.repository.DatabaseRepository;
import com.kiu.library.repository.ResourceRepository;
import com.kiu.library.service.EDatabaseService;
import com.kiu.library.service.FileStorageService;
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
public class EDatabaseServiceImpl implements EDatabaseService {

    @Autowired
    private DatabaseRepository resourceRepository;

    @Autowired
    private FileStorageServiceForDatabase fileStorageService;

    @Override
    @Transactional
    public AllDatabaseInfo getAllInfo(int page, int limit, String sort) {

        Pageable pageable = PageRequest.of(page-1, limit);

        List<DatabaseEntity> allInfo = resourceRepository.findByIsActive(1);

        Integer count = resourceRepository.countAllResource();

        DatabaseDataInfo resourceDataInfo = new DatabaseDataInfo();
        resourceDataInfo.setTotal(count);

        List<DatabaseInfo> listOfResources = new ArrayList<>();

        for (DatabaseEntity info : allInfo){

            DatabaseInfo resourceInfo = new DatabaseInfo(info.getId(),info.getTimestamp(),
                    info.getTitle(),info.getUrl(),info.getDescription(),info.getCoverPhoto(),
                    info.getAddedUser(),info.getLastUpdatedTime(),info.getLastUpdatedUser(),info.getDisplayTime(), info.getStatus(),
                    info.getIsActive());

            listOfResources.add(resourceInfo);


        }
        resourceDataInfo.setItems(listOfResources);

        AllDatabaseInfo allResourceInfo = new AllDatabaseInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    public AllDatabaseInfo getInfoById(int id) throws IOException {

        Optional<DatabaseEntity> allInfo = resourceRepository.findById(id);

        Integer count = resourceRepository.countAllResource();

        DatabaseDataInfo resourceDataInfo = new DatabaseDataInfo();
        resourceDataInfo.setTotal(count);

        List<DatabaseInfo> listOfResources = new ArrayList<>();


        DatabaseEntity info = allInfo.get();

        DatabaseInfo resourceInfo = new DatabaseInfo(info.getId(), info.getTimestamp(),
                info.getTitle(), info.getUrl(), info.getDescription(), info.getCoverPhoto(),
                info.getAddedUser(), info.getLastUpdatedTime(), info.getLastUpdatedUser(),
                info.getDisplayTime(), info.getStatus(),
                info.getIsActive());

        listOfResources.add(resourceInfo);


        resourceDataInfo.setItems(listOfResources);

        AllDatabaseInfo allResourceInfo = new AllDatabaseInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    public SaveEDatabaseResponse saveInfo(SaveDatabaseRequest saveRequest) {
        Pageable topOne = PageRequest.of(0, 1);
        List<DatabaseEntity> allInfo = resourceRepository.getLatestId(topOne);

        Integer id = 1;

        if (!allInfo.isEmpty())
            id = allInfo.get(0).getId();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int newId = ++id;

        DatabaseEntity resourceEntity = new DatabaseEntity(newId,(double)timestamp.getTime(),
                saveRequest.getTitle(),
                saveRequest.getDescription(),saveRequest.getUrl(),"",saveRequest.getAddedUser(),(double)timestamp.getTime(),
                1,dtf.format(now), "draft", 0);

        List<DatabaseEntity> listOfResources = new ArrayList<>();
        listOfResources.add(resourceEntity);

        resourceRepository.saveAll(listOfResources);

        return new SaveEDatabaseResponse(20000,"success", newId);

    }


    @Override
    public SaveResourceResponse deleteEresource(int id, int user) {

        Optional<DatabaseEntity> resource = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DatabaseEntity updatedResource = resource.get();

        updatedResource.setIsActive(0);
        updatedResource.setLastUpdatedTime((double)timestamp.getTime());
        updatedResource.setLastUpdatedUser(user);

        List<DatabaseEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000,"success");

    }

    @Override
    public SaveResourceResponse updateEresource(Integer id, UpdateDatabaseRequest saveRequest) {

        Optional<DatabaseEntity> allInfo = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DatabaseEntity resourceEntity = new DatabaseEntity(allInfo.get().getId(),
                allInfo.get().getTimestamp(),
                saveRequest.getTitle(),
                saveRequest.getDescription(),saveRequest.getUrl(),allInfo.get().getCoverPhoto(),1,(double)timestamp.getTime(),1,
                allInfo.get().getDisplayTime(), allInfo.get().getStatus(), 1);

        List<DatabaseEntity> listOfResources = new ArrayList<>();
        listOfResources.add(resourceEntity);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000,"success");

    }

    @Override
    public SaveResourceResponse updateEresourceStatus(int id, String status, int user) {

        Optional<DatabaseEntity> resource = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DatabaseEntity updatedResource = resource.get();

        updatedResource.setStatus(status);
        updatedResource.setLastUpdatedTime((double)timestamp.getTime());
        updatedResource.setLastUpdatedUser(user);

        List<DatabaseEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000,"success");
    }

    @Override
    public UploadFileResponse uploadFiles(int id, MultipartFile[] cover) {

        return uploadFile(id, Arrays.asList(cover).get(0));
    }


    private UploadFileResponse uploadFile(int id, MultipartFile cover) {

        String coverImage = fileStorageService.storeFile(id, cover);

        String coverFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/database/")
                .path(coverImage)
                .toUriString();

        Optional<DatabaseEntity> resource = resourceRepository.findById(id);

        DatabaseEntity updatedResource = resource.get();
        updatedResource.setCoverPhoto(coverImage);
        updatedResource.setIsActive(1);

        List<DatabaseEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        new UploadFileResponse(coverImage, coverFileDownloadUri,
                cover.getContentType(), cover.getSize());

        return new UploadFileResponse(coverImage, coverFileDownloadUri,
                cover.getContentType(), cover.getSize());

    }


}

package com.kiu.library.service.impl;

import com.kiu.library.entity.DatabaseEntity;
import com.kiu.library.entity.DocumentEntity;
import com.kiu.library.entity.ResourceEntity;
import com.kiu.library.model.databaseModel.*;
import com.kiu.library.model.libraryDocument.*;
import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.repository.DocumentRepository;
import com.kiu.library.repository.ResourceRepository;
import com.kiu.library.service.EResourceService;
import com.kiu.library.service.FileStorageService;
import com.kiu.library.service.FileStorageServiceForDocumnet;
import com.kiu.library.service.LibraryDocumentService;
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
public class EDocumentServiceImpl implements LibraryDocumentService {

    @Autowired
    private DocumentRepository resourceRepository;

    @Autowired
    private FileStorageServiceForDocumnet fileStorageService;

    @Override
    @Transactional
    public AllDocumentInfo getAllInfo(int page, int limit, String sort) {

        Pageable pageable = PageRequest.of(page-1, limit);

        List<DocumentEntity> allInfo = resourceRepository.findByIsActive(1);

        Integer count = resourceRepository.countAllResource();

        DocumentDataInfo resourceDataInfo = new DocumentDataInfo();
        resourceDataInfo.setTotal(count);

        List<DocumentInfo> listOfResources = new ArrayList<>();

        for (DocumentEntity info : allInfo){

            DocumentInfo resourceInfo = new DocumentInfo(info.getId(),info.getTimestamp(),
                    info.getTitle(),info.getDocument(),info.getDescription(),info.getCoverPhoto(),
                    info.getAddedUser(),info.getLastUpdatedTime(),info.getLastUpdatedUser(),info.getDisplayTime(), info.getStatus(),
                    info.getIsActive());

            listOfResources.add(resourceInfo);


        }
        resourceDataInfo.setItems(listOfResources);

        AllDocumentInfo allResourceInfo = new AllDocumentInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    public AllDocumentInfo getInfoById(int id) throws IOException {

        Optional<DocumentEntity> allInfo = resourceRepository.findById(id);

        Integer count = resourceRepository.countAllResource();

        DocumentDataInfo resourceDataInfo = new DocumentDataInfo();
        resourceDataInfo.setTotal(count);

        List<DocumentInfo> listOfResources = new ArrayList<>();


        DocumentEntity info = allInfo.get();

        DocumentInfo resourceInfo = new DocumentInfo(info.getId(), info.getTimestamp(),
                info.getTitle(), info.getDocument(), info.getDescription(), info.getCoverPhoto(),
                info.getAddedUser(), info.getLastUpdatedTime(), info.getLastUpdatedUser(),
                info.getDisplayTime(), info.getStatus(),
                info.getIsActive());

        listOfResources.add(resourceInfo);


        resourceDataInfo.setItems(listOfResources);

        AllDocumentInfo allResourceInfo = new AllDocumentInfo();
        allResourceInfo.setCode(20000);
        allResourceInfo.setData(resourceDataInfo);

        return allResourceInfo;
    }

    @Override
    public SaveEDocumentResponse saveInfo(SaveDocumentRequest saveRequest) {
        Pageable topOne = PageRequest.of(0, 1);
        List<DocumentEntity> allInfo = resourceRepository.getLatestId(topOne);

        Integer id = 1;

        if (!allInfo.isEmpty())
            id = allInfo.get(0).getId();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int newId = ++id;

        DocumentEntity resourceEntity = new DocumentEntity(newId,(double)timestamp.getTime(),
                saveRequest.getTitle(),
                saveRequest.getDescription(),saveRequest.getDocument(),"",saveRequest.getAddedUser(),(double)timestamp.getTime(),
                1,dtf.format(now), "draft", 0);

        List<DocumentEntity> listOfResources = new ArrayList<>();
        listOfResources.add(resourceEntity);

        resourceRepository.saveAll(listOfResources);

        return new SaveEDocumentResponse(20000,"success", newId);

    }


    @Override
    public SaveResourceResponse deleteEresource(int id, int user) {

        Optional<DocumentEntity> resource = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DocumentEntity updatedResource = resource.get();

        updatedResource.setIsActive(0);
        updatedResource.setLastUpdatedTime((double)timestamp.getTime());
        updatedResource.setLastUpdatedUser(user);

        List<DocumentEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000,"success");

    }

    @Override
    public SaveResourceResponse updateEresource(int id, UpdateDocumentRequest saveRequest) {

        Optional<DocumentEntity> allInfo = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DocumentEntity resourceEntity = new DocumentEntity(allInfo.get().getId(),
                allInfo.get().getTimestamp(),
                saveRequest.getTitle(),
                saveRequest.getDescription(),allInfo.get().getDocument(),allInfo.get().getCoverPhoto(),1,(double)timestamp.getTime(),1,
                allInfo.get().getDisplayTime(), allInfo.get().getStatus(), 1);

        List<DocumentEntity> listOfResources = new ArrayList<>();
        listOfResources.add(resourceEntity);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000,"success");

    }

    @Override
    public SaveResourceResponse updateEresourceStatus(int id, String status, int user) {

        Optional<DocumentEntity> resource = resourceRepository.findById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DocumentEntity updatedResource = resource.get();

        updatedResource.setStatus(status);
        updatedResource.setLastUpdatedTime((double)timestamp.getTime());
        updatedResource.setLastUpdatedUser(user);

        List<DocumentEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        return new SaveResourceResponse(20000,"success");
    }

    @Override
    public UploadFileResponse uploadFiles(int id, MultipartFile[] file, MultipartFile[] cover) {

        return uploadFile(id, Arrays.asList(file).get(0), Arrays.asList(cover).get(0));
    }


    private UploadFileResponse uploadFile(int id, MultipartFile file, MultipartFile cover) {

        String fileName = fileStorageService.storeFile(id, file);
        String coverImage = fileStorageService.storeFile(id, cover);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/documents/")
                .path(fileName)
                .toUriString();

        String coverFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/documents/")
                .path(coverImage)
                .toUriString();

        Optional<DocumentEntity> resource = resourceRepository.findById(id);

        DocumentEntity updatedResource = resource.get();
        updatedResource.setDocument(fileName);
        updatedResource.setCoverPhoto(coverImage);
        updatedResource.setIsActive(1);

        List<DocumentEntity> listOfResources = new ArrayList<>();
        listOfResources.add(updatedResource);

        resourceRepository.saveAll(listOfResources);

        new UploadFileResponse(coverImage, coverFileDownloadUri,
                cover.getContentType(), cover.getSize());

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());

    }


}

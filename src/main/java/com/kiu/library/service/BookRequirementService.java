package com.kiu.library.service;

import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface BookRequirementService {

    AllResourceBookReqInfo getAllInfo(int page, int limit, String sort) throws IOException;

    AllResourceInfo getInfoById(int id) throws IOException;

    SaveEResourceResponse saveInfo(SaveResourceRequest saveRequest) throws IOException;

    SaveResourceResponse deleteEresource(int id, int user);

    SaveResourceResponse updateEresourceStatus(int id, String status, int user, String type);

    SaveResourceResponse updateEresource(int id, String type, String resource, Integer user);

    UploadFileResponse uploadFiles(int id, MultipartFile[] file, String type);
}

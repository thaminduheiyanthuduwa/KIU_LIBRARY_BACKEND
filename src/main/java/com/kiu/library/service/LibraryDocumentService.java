package com.kiu.library.service;

import com.kiu.library.model.libraryDocument.AllDocumentInfo;
import com.kiu.library.model.libraryDocument.SaveDocumentRequest;
import com.kiu.library.model.libraryDocument.SaveEDocumentResponse;
import com.kiu.library.model.libraryDocument.UpdateDocumentRequest;
import com.kiu.library.model.resouceModel.AllResourceInfo;
import com.kiu.library.model.resouceModel.SaveEResourceResponse;
import com.kiu.library.model.resouceModel.SaveResourceRequest;
import com.kiu.library.model.resouceModel.SaveResourceResponse;
import com.kiu.library.payload.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface LibraryDocumentService {

    AllDocumentInfo getAllInfo(int page, int limit, String sort) throws IOException;

    AllDocumentInfo getInfoById(int id) throws IOException;

    SaveEDocumentResponse saveInfo(SaveDocumentRequest saveRequest) throws IOException;

    SaveResourceResponse deleteEresource(int id, int user);

    SaveResourceResponse updateEresourceStatus(int id, String status, int user);

    SaveResourceResponse updateEresource(int id, UpdateDocumentRequest saveRequest);

    UploadFileResponse uploadFiles(int id, MultipartFile[] file, MultipartFile[] cover);
}

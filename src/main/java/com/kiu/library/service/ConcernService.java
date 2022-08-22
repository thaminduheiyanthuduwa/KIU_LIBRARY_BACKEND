package com.kiu.library.service;

import com.kiu.library.model.concernModel.AllConcernInfo;
import com.kiu.library.model.databaseModel.AllDatabaseInfo;
import com.kiu.library.model.databaseModel.SaveDatabaseRequest;
import com.kiu.library.model.databaseModel.SaveEDatabaseResponse;
import com.kiu.library.model.databaseModel.UpdateDatabaseRequest;
import com.kiu.library.model.resouceModel.SaveResourceResponse;
import com.kiu.library.payload.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ConcernService {

    AllConcernInfo getAllInfo(int page, int limit, String sort) throws IOException;

    //    AllDatabaseInfo getInfoById(int id) throws IOException;
//
//    SaveEDatabaseResponse saveInfo(SaveDatabaseRequest saveRequest) throws IOException;
//
//    SaveResourceResponse deleteEresource(int id, int user);
//
//    SaveResourceResponse updateEresource(Integer id, UpdateDatabaseRequest saveRequest);
//
    SaveResourceResponse updateStatus(int id, String status, int user);

    //
    UploadFileResponse uploadFiles(int id, MultipartFile[] cover);
}

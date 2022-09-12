package com.kiu.library.service;

import com.kiu.library.model.physicalBook.PhysicalBookReqInfo;
import com.kiu.library.model.physicalBook.StudentInfo;
import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface PhysicalBookService {

    PhysicalBookReqInfo getAllInfo(int page, int limit, String sort) throws IOException;

    StudentInfo getStudentInfo();

    StudentInfo getBookInfo();

    SaveResourceResponse updatePhysicalBookStatus(int id, int type, Integer user);

    SaveEResourceResponse createPhysicalBook(PhysicalBookInfo physicalBookInfo);

}

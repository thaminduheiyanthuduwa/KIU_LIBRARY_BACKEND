package com.kiu.library.controller;


import com.kiu.library.model.physicalBook.PhysicalBookReqInfo;
import com.kiu.library.model.physicalBook.StudentInfo;
import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.service.BookRequirementService;
import com.kiu.library.service.PhysicalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/physical")
@CrossOrigin(origins = "*")
public class PhysicalBookController {

    @Autowired
    PhysicalBookService eResourceService;


    @RequestMapping(value = "/get-all-info", method = RequestMethod.GET, headers = "Accept=application/json")
    public PhysicalBookReqInfo getAllInfo(@RequestParam(value = "page") int page,
                                       @RequestParam(value = "limit") int limit,
                                       @RequestParam(value = "sort") String sort) throws IOException {

        return eResourceService.getAllInfo(page, limit, sort);

    }

    @RequestMapping(value = "/get-all-student-info", method = RequestMethod.GET, headers = "Accept=application/json")
    public StudentInfo getAllStudentInfo() throws IOException {

        return eResourceService.getStudentInfo();

    }

    @RequestMapping(value = "/get-all-book-info", method = RequestMethod.GET, headers = "Accept=application/json")
    public StudentInfo getAllBookInfo() throws IOException {

        return eResourceService.getBookInfo();

    }

    @RequestMapping(value = "/update-status", method = RequestMethod.PUT, headers = "Accept=application/json")
    public SaveResourceResponse updateStatus(@RequestParam(value = "data") int id,
                                                     @RequestParam(value = "status") int status,
                                                     @RequestParam(value = "updated_user") int user) {

        return eResourceService.updatePhysicalBookStatus(id, status, user);

    }

    @RequestMapping(value = "/create-physical-book", method = RequestMethod.POST, headers = "Accept=application/json")
    public SaveEResourceResponse updateStatus(@RequestBody PhysicalBookInfo saveRequest) {

        return eResourceService.createPhysicalBook(saveRequest);

    }

}

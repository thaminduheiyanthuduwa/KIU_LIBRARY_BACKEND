package com.kiu.library.controller;


import com.kiu.library.model.resouceModel.*;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.service.BookRequirementService;
import com.kiu.library.service.EResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "*")
public class BookReqController {

    @Autowired
    BookRequirementService eResourceService;

    @RequestMapping(value = "/get-all-info", method = RequestMethod.GET, headers = "Accept=application/json")
    public AllResourceBookReqInfo getAllResource(@RequestParam(value = "page") int page,
                                                      @RequestParam(value = "limit") int limit,
                                                      @RequestParam(value = "sort") String sort) throws IOException {

        return eResourceService.getAllInfo(page, limit, sort);

    }

    @RequestMapping(value = "/get-by-id", method = RequestMethod.GET, headers = "Accept=application/json")
    public AllResourceInfo getById(@RequestParam(value = "id") int id) throws IOException {

        return eResourceService.getInfoById(id);

    }

    @RequestMapping(value = "/save-eresource", method = RequestMethod.POST, headers = "Accept=application/json")
    public SaveEResourceResponse saveResource(@RequestBody SaveResourceRequest saveRequest) throws IOException {

        return eResourceService.saveInfo(saveRequest);

    }

    @RequestMapping(value = "/delete-eresource", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public SaveResourceResponse deleteResource(@RequestParam(value = "data") Integer id, @RequestParam(value = "updated_user") int user) throws IOException {

        return eResourceService.deleteEresource(id, user);

    }

    @RequestMapping(value = "/update-eresource", method = RequestMethod.POST, headers = "Accept=application/json")
    public SaveResourceResponse updateResource(@RequestParam(value = "new_id") int id,
                                               @RequestParam(value = "type") String type,
                                               @RequestParam(value = "resource") String resource,
                                               @RequestParam(value = "user") int user) {

        return eResourceService.updateEresource(id, type, resource, user);

    }

    @RequestMapping(value = "/update-eresource-status", method = RequestMethod.PUT, headers = "Accept=application/json")
    public SaveResourceResponse updateResourceStatus(@RequestParam(value = "data") int id,
                                                     @RequestParam(value = "status") String status,
                                                     @RequestParam(value = "updated_user") int user,
                                                     @RequestParam(value = "type") String type) {

        return eResourceService.updateEresourceStatus(id, status, user, type);

    }

    @PostMapping("/uploadMultipleFiles")
    public UploadFileResponse uploadBookDocument(@RequestParam("id") int id, @RequestParam("files") MultipartFile[] files, @RequestParam("type") String type) {

        return eResourceService.uploadFiles(id, files, type);
    }

    @PostMapping("/uploadFinalDocument")
    public UploadFileResponse uploadMultipleFiles(@RequestParam("id") int id, @RequestParam("files") MultipartFile[] files, @RequestParam("type") String type) {

        return eResourceService.uploadFiles(id, files, type);
    }

}

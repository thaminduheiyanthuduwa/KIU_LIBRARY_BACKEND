package com.kiu.library.controller;


import com.kiu.library.model.databaseModel.AllDatabaseInfo;
import com.kiu.library.model.databaseModel.SaveDatabaseRequest;
import com.kiu.library.model.databaseModel.SaveEDatabaseResponse;
import com.kiu.library.model.databaseModel.UpdateDatabaseRequest;
import com.kiu.library.model.resouceModel.AllResourceInfo;
import com.kiu.library.model.resouceModel.SaveResourceRequest;
import com.kiu.library.model.resouceModel.SaveResourceResponse;
import com.kiu.library.model.resouceModel.UpdateResourceRequest;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.service.EDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/database")
@CrossOrigin(origins = "*")
public class DatabaseController {

    @Autowired
    EDatabaseService eResourceService;

    @RequestMapping(value = "/get-all-info" ,method = RequestMethod.GET ,headers="Accept=application/json")
    public AllDatabaseInfo getAllResource(@RequestParam(value = "page") int page,
                                          @RequestParam(value = "limit") int limit,
                                          @RequestParam(value = "sort") String sort) throws IOException {

        return eResourceService.getAllInfo(page,limit,sort);

    }

    @RequestMapping(value = "/get-by-id", method = RequestMethod.GET, headers = "Accept=application/json")
    public AllDatabaseInfo getById(@RequestParam(value = "id") int id) throws IOException {

        return eResourceService.getInfoById(id);

    }

    @RequestMapping(value = "/save-eresource" ,method = RequestMethod.POST ,headers="Accept=application/json")
    public SaveEDatabaseResponse saveResource(@RequestBody SaveDatabaseRequest saveRequest) throws IOException {

        return eResourceService.saveInfo(saveRequest);

    }

    @RequestMapping(value = "/delete-eresource" ,method = RequestMethod.DELETE ,headers="Accept=application/json")
    public SaveResourceResponse deleteResource(@RequestParam(value = "data") Integer id,@RequestParam(value = "updated_user") int user) throws IOException {

        return eResourceService.deleteEresource(id,user);

    }

    @RequestMapping(value = "/update-eresource" ,method = RequestMethod.POST ,headers="Accept=application/json")
    public SaveResourceResponse updateResource(@RequestParam(value = "new_id") int id,@RequestBody UpdateDatabaseRequest updateResourceRequest) {

        return eResourceService.updateEresource(id,updateResourceRequest);

    }

    @RequestMapping(value = "/update-eresource-status", method = RequestMethod.PUT, headers = "Accept=application/json")
    public SaveResourceResponse updateResourceStatus(@RequestParam(value = "data") int id,
                                                     @RequestParam(value = "status") String status,
                                                     @RequestParam(value = "updated_user") int user) {

        return eResourceService.updateEresourceStatus(id, status, user);

    }

    @PostMapping("/uploadMultipleFiles")
    public UploadFileResponse uploadMultipleFiles(@RequestParam("id") int id, @RequestParam("cover") MultipartFile[] cover) {

        return eResourceService.uploadFiles(id, cover);
    }

}

package com.kiu.library.controller;


import com.kiu.library.model.concernModel.AllConcernInfo;
import com.kiu.library.model.resouceModel.SaveResourceResponse;
import com.kiu.library.payload.UploadFileResponse;
import com.kiu.library.service.FileStorageService;
import org.springframework.core.io.Resource;
import com.kiu.library.service.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/concern")
@CrossOrigin(origins = "*")
public class ConcernController {

    @Autowired
    ConcernService concernService;

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/get-all-info" ,method = RequestMethod.GET ,headers="Accept=application/json")
    public AllConcernInfo getAllResource(@RequestParam(value = "page") int page,
                                         @RequestParam(value = "limit") int limit,
                                         @RequestParam(value = "sort") String sort) throws IOException {

        return concernService.getAllInfo(page,limit,sort);

    }


    @RequestMapping(value = "/get-all-info-by-id" ,method = RequestMethod.GET ,headers="Accept=application/json")
    public AllConcernInfo getResourceById(@RequestParam(value = "id") int id) throws IOException {

        return concernService.getAllInfoById(id);

    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam(value = "data") String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName, "concern");

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {

        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @RequestMapping(value = "/update-status", method = RequestMethod.PUT, headers = "Accept=application/json")
    public SaveResourceResponse updateResourceStatus(@RequestParam(value = "data") int id,
                                                     @RequestParam(value = "status") String status,
                                                     @RequestParam(value = "updated_user") int user) {

        return concernService.updateStatus(id, status, user);

    }

    @PostMapping("/uploadMultipleFiles")
    public UploadFileResponse uploadMultipleFiles(@RequestParam("id") int id, @RequestParam("cover") MultipartFile[] cover) {

        return concernService.uploadFiles(id, cover);
    }

}

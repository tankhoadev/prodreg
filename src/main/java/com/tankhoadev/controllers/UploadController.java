package com.tankhoadev.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tankhoadev.util.FileUpload;
import com.tankhoadev.util.FileUploadResponse;

@RestController
@RequestMapping("/forms")
public class UploadController {
      
   @PostMapping("/upload")
   public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) 
         throws IOException {      
      String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
      long size = multipartFile.getSize();
      
      String filecode = FileUpload.saveFile(fileName, multipartFile);
      
      FileUploadResponse response = new FileUploadResponse();
      response.setFileName(fileName);
      response.setSize(size);
      response.setDownloadUri("/downloadFile/" + filecode);
      
      return new ResponseEntity<>(response, HttpStatus.OK);
   }
}

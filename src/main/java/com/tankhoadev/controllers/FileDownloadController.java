package com.tankhoadev.controllers;

import com.tankhoadev.util.FileDownload;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FileDownloadController 
{    
   @GetMapping("/download/{fileCode}")
   public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) 
   {
      FileDownload downloadUtil = new FileDownload();       
      Resource resource = null;
      
      try {
         resource = downloadUtil.getFileAsResource(fileCode);
      } 
      catch (IOException e) {
         return ResponseEntity.internalServerError().build();
      }
       
      if (resource == null) {
         return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
      }
       
      String contentType = "application/octet-stream";
      String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
       
      return ResponseEntity.ok()
         .contentType(MediaType.parseMediaType(contentType))
         .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
         .body(resource);       
   }
}

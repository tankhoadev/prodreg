package com.tankhoadev.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class FileDownload 
{   
   private Path foundFile;
     
   public Resource getFileAsResource(String fileCode) throws IOException {
      
      Path downloadPath = Path.of("src/main/resources/Files-Upload");
         
      Files.list(downloadPath).forEach(file -> {
         if (file.getFileName().toString().startsWith(fileCode)) {
            foundFile = file;
            return;
         }
      });

      if (foundFile != null) {
         return new UrlResource(foundFile.toUri());
      }
      
      return null;
   }
}

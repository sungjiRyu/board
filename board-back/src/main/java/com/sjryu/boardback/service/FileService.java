package com.sjryu.boardback.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    
    // 파일의 url을 반환
    String Upload(MultipartFile file);

    // url로 접근한 파일을 반환
    Resource getImage(String fileName);
}

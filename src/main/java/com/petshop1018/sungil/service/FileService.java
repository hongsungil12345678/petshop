package com.petshop1018.sungil.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
/**
 // 추후 고려 사항 (배포 시 클라우드 스토리지)
 // 외부에서 설정할 수 있도록 application.properties에 파일 업로드 경로를 지정해두고 가져오는 방식으로 개선
 // private static final String IMAGE_UPLOAD_DIR = "/var/www/uploads"; // 예시: 실제 운영 환경에 맞는 경로
 */
@Service
@RequiredArgsConstructor
public class FileService {
    private static final String IMAGE_UPLOAD_DIR = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
    
    // 파일 저장
    public String saveFile(MultipartFile file) throws IOException {

        // 파일 이름 중복 방지 위해 UUID 사용
        String originalFileName = file.getOriginalFilename();
        String savedFileName = UUID.randomUUID() + "_" + originalFileName;

        // 파일 저장 경로
        Path targetPath = Paths.get(IMAGE_UPLOAD_DIR, savedFileName);

        // 파일 저장
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        // 저장된 파일 이름 반환
        return savedFileName;
    }
    //  ** 추후 고려 사항 파일의 실제 URL 반환 (서버에서 접근할 수 있도록 설정)
    // 실제 URL 경로로 설정 (운영 서버의 도메인 포함)
    // return "https://yourdomain.com/files/" + savedFileName;  // 실제 도메인 경로로 변경
    public String getImageUrl(String savedFileName) {
        return "/files/" + savedFileName; // 실제 파일 경로에 맞게 수정
    }

    // 파일 삭제
    public void deleteFile(String savedFileName) throws IOException {
        // 파일 삭제 경로
        Path filePath = Paths.get(IMAGE_UPLOAD_DIR, savedFileName);

        // 파일 존재 여부 확인 후 삭제
        Files.deleteIfExists(filePath);
    }
}
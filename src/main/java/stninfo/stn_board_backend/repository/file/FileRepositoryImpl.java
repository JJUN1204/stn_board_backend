package stninfo.stn_board_backend.repository.file;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FileRepositoryImpl implements FileRepository {

    // 업로드할 때 저장되는 위치
    private static final String PATH = "C:/Users/user/Downloads/fileTest/";

    @Override
    public List<String> save(MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile file : files) {
            // 파일 이름 중복 없게 UUID 추가
            String fileName = String.format("%s=%s", UUID.randomUUID().toString(), file.getOriginalFilename());
            fileNames.add(fileName);
            file.transferTo(new File(PATH + fileName));
        }

        return fileNames;
    }

    @Override
    public byte[] getFileByFileName(String fileName) throws IOException {
        Path filePath = Paths.get(PATH + fileName);
        // 바이트 단위로 읽음
        return Files.readAllBytes(filePath);
    }

    @Override
    public boolean deleteFileByFileName(String fileName) throws IOException {
        Path filePath = Paths.get(PATH + fileName);
        return Files.deleteIfExists(filePath);
    }

    @Override
    public List<String> updateFiles(MultipartFile[] newFiles, String[] oldFileNames) throws IOException {
        // 기존 파일 삭제
        for (String oldFileName : oldFileNames) {
            deleteFileByFileName(oldFileName);
        }
        // 새 파일 저장
        return save(newFiles);
    }
}

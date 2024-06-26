package stninfo.stn_board_backend.repository.file;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FileRepository {

    List<String> save(MultipartFile[] multipartFiles) throws IOException;

    byte[] getFileByFileName(String fileName) throws IOException;

    boolean deleteFileByFileName(String fileName) throws IOException;
    List<String> updateFiles(MultipartFile[] newFiles, String[] oldFileNames) throws IOException;

}


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

    private static final String PATH = "/Users/user/Downloads/fileTest/";

    @Override
    public List<String> save(MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile file : files) {
            String fileName = String.format("%s=%s", UUID.randomUUID().toString(), file.getOriginalFilename());
            fileNames.add(fileName);
            file.transferTo(new File(PATH + fileName));
        }

        return fileNames;
    }

    @Override
    public byte[] getFileByFileName(String fileName) throws IOException {
        Path filePath = Paths.get(PATH + fileName);

        if (!Files.exists(filePath)) {
            throw new IOException("File not found");
        }

        return Files.readAllBytes(filePath);
    }
}

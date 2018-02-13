package training.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
class FileModelService {

    private final FileModelRepository fileModelRepository;

    FileModelService(FileModelRepository fileModelRepository) {
        this.fileModelRepository = fileModelRepository;
    }


    void addFile(MultipartFile multipartFile) throws IOException {
        FileModel fileModel = new FileModel(multipartFile.getOriginalFilename(), multipartFile.getContentType(),
                multipartFile.getBytes());
        fileModelRepository.save(fileModel);
    }

    FileModel findFileByName(String fileName){
        return fileModelRepository.findByFileName(fileName)
                .orElseThrow(() -> new RuntimeException(fileName));
    }
}

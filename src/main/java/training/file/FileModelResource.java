package training.file;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
class FileModelResource {

    private final FileModelService fileModelService;

    FileModelResource(FileModelService fileModelService) {
        this.fileModelService = fileModelService;
    }

    @PostMapping()
    ResponseEntity addFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileModelService.addFile(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    ResponseEntity getFile(@RequestParam String fileName){
        FileModel fileByName = fileModelService.findFileByName(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(fileByName.getFileName(), fileByName.getFileName());
        headers.setContentType(MediaType.parseMediaType(fileByName.getFileType()));
        return new ResponseEntity(fileByName.getData(), headers, HttpStatus.OK);
    }

}

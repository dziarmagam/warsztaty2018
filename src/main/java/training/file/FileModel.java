package training.file;

import javax.persistence.*;

@Entity
class FileModel {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String fileName;
    @Column
    private String fileType;
    @Column
    @Lob
    private byte[] data;

    FileModel(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    private FileModel() {
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getData() {
        return data;
    }
}

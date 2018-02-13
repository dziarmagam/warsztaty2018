package training.file;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface FileModelRepository extends CrudRepository<FileModel, Long> {
    Optional<FileModel> findByFileName(String fileName);
}

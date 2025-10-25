package com.educonnect.serviceImpl;

import org.hibernate.sql.ast.SqlTreeCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.annotation.Target;
import java.nio.file.*;

@Service
public class FileService {

    private final Path uploadDir;

    public FileService(@Value("${file.upload-dir}") String uploadDir) throws IOException {
        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.uploadDir);
    }

    public String saveProfileImg(MultipartFile file,Long userId) throws IOException{

        if(file.isEmpty() && file==null){
            throw new IllegalArgumentException("File is empty");
        }

        String original = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = "";

        int idx = original.lastIndexOf(".");
        if(idx>0){
            extension = original.substring(idx);
        }

        String filename = "User_"+ userId + extension;
        Path target = this.uploadDir.resolve(filename);

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return this.uploadDir.getFileName().toString()+"/"+filename;
    }

    //This method is for reading file bytes
    public byte[] readfilebyte(String storedPath) throws IOException{
        if(storedPath==null || storedPath.isBlank()){
            throw new IllegalArgumentException("No stored path");
        }
        Path filepath;
        if(storedPath.startsWith(this.uploadDir.getFileName().toString()+"/")){
            filepath = this.uploadDir.resolve(storedPath.substring(this.uploadDir.getFileName().toString().length()+1));
        }
        else{
            filepath = Paths.get(storedPath);
            if(!filepath.isAbsolute()){
                filepath = this.uploadDir.resolve(storedPath).normalize();
            }
        }
        if(!Files.exists(filepath)){
            throw  new NoSuchFileException(filepath.toString());
        }
        return Files.readAllBytes(filepath);
    }

    //This method determine the type of content
    public String detectContentType(String storedPath){

        String lower = storedPath.toLowerCase();
        if(lower.endsWith(".png")) return "image/png";
        if (lower.endsWith(".jpg")) return "image/jpg";
        return "image/jpeg";
    }
}

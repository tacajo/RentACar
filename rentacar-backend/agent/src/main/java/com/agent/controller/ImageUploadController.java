package com.agent.controller;

import com.agent.dto.ImageName;
import com.agent.model.ImageModel;
import com.agent.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "https://localhost:4201")
@RequestMapping(path = "image")
public class ImageUploadController {
    @Autowired
    ImageRepository imageRepository;
    @PostMapping("/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException, IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        imageRepository.save(img);
    }
    @GetMapping(path = { "/get/{imageName}" })
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
        System.out.println(imageName);
        ImageModel retrievedImage = imageRepository.findByName(imageName+".jpg").get();
        if(retrievedImage!=null) {
            ImageModel img = new ImageModel(retrievedImage.getName(), retrievedImage.getType(),
                    decompressBytes(retrievedImage.getPicByte()));
            return img;
        }
        else return null;
    }

    @PostMapping(path = { "/get" })
    public ImageModel getImageFromName(@RequestBody ImageName imageName) throws IOException {
        System.out.println(imageName.getName());
        ImageModel retrievedImage = imageRepository.findByName(imageName.getName()).get();
        if(retrievedImage!=null) {
            ImageModel img = new ImageModel(retrievedImage.getName(), retrievedImage.getType(),
                    decompressBytes(retrievedImage.getPicByte()));
            return img;
        }
        else return null;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch ( DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
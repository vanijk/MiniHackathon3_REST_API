package com.glab.minihackathon3_rest_api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glab.minihackathon3_rest_api.model.Movie;
import com.glab.minihackathon3_rest_api.repository.MovieRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.imageio.ImageIO;
@Service
public class MovieService {
    @Autowired
    private MovieRepositoryI movieRepositoryI;

    public void addMovie(String title) {
        try {
            String url = "http://www.omdbapi.com/?apikey=b79fdda2&t=" + title;
            RestTemplate rt = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Movie movie = mapper.readValue(rt.getForObject(url, String.class), Movie.class);
            System.out.println(movie.toString());

            URL url1 = new URL(movie.getPoster());
            Image image = ImageIO.read(url1);
            byte[] content = url1.openStream().readAllBytes();
            movie.setImageData(content);
            movieRepositoryI.save(movie);

        }catch(Exception e){
            System.out.println("Exception occured :"+e);
        }


    }





    public Movie getMovieDetails(String title){
       return movieRepositoryI.getReferenceByTitle(title);
    }
  //  public byte[] getImage(String name) {

       // byte[] image = decompressImage(movieRepositoryI.get().getImageData());
       // return image;
  //  }
    public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
        }
        return outputStream.toByteArray();
    }
}

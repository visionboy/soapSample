package com.example.soap.service;

import com.example.soap.dto.SortInfoDTO;
import com.example.soap.dto.VideoDTO;
import com.example.soap.entity.Video;
import com.example.soap.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@WebService(
        targetNamespace = "http://hihi.com",
        serviceName = "hihiService",
        endpointInterface = "com.example.soap.service.VideoService"
)
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<VideoDTO> getVideos() {
        List<Video> videos = videoRepository.findAll();
        
        return videos.stream()
                .map(video -> new VideoDTO(
                        video.getId(),
                        video.getUserId(),
                        video.getEngine(),
                        video.getSourceImage(),
                        video.getPrompt(),
                        video.getVideoUrl()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public VideoDTO getVideosById(Long id) {
        return videoRepository.findById(id)
                .map(video -> new VideoDTO(
                        video.getId(),
                        video.getUserId(),
                        video.getEngine(),
                        video.getSourceImage(),
                        video.getPrompt(),
                        video.getVideoUrl()
                ))
                .orElse(null);
    }

    @Override
    public VideoDTO getVideosByIdUid(Long id, String userId) {
        return videoRepository.findByIdAndUserId(id, userId)
                .map(video -> new VideoDTO(
                        video.getId(),
                        video.getUserId(),
                        video.getEngine(),
                        video.getSourceImage(),
                        video.getPrompt(),
                        video.getVideoUrl()
                ))
                .orElse(null);
    }

    @Override
    public List<SortInfoDTO> getSortInfo() {
        List<Video> videos = videoRepository.findAll();
        
        List<SortInfoDTO> result = new ArrayList<>();
        
        for (Video video : videos) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(video.getId()));
            row.add(video.getUserId());
            row.add(video.getEngine());
            row.add(video.getSourceImage());
            result.add(new SortInfoDTO(row));
        }
        
        return result;
    }
}

package com.example.soap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "engine")
    private String engine;

    @Column(name = "source_image")
    private String sourceImage;

    @Column(name = "prompt")
    private String prompt;

    @Column(name = "video_url")
    private String videoUrl;
}

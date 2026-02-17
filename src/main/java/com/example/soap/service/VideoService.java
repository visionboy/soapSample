package com.example.soap.service;

import com.example.soap.dto.SortInfoDTO;
import com.example.soap.dto.VideoDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService(targetNamespace = "http://hihi.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface VideoService {

    @WebMethod
    List<VideoDTO> getVideos();

    @WebMethod
    VideoDTO getVideosById(@WebParam(name = "id") Long id);

    @WebMethod
    List<SortInfoDTO> getSortInfo();
}

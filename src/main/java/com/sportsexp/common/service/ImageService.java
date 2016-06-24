package com.sportsexp.common.service;

import java.util.List;

import com.sportsexp.common.service.pojo.Images;
import com.sportsexp.common.service.vo.ImageInfo;

public interface ImageService {

	public boolean addImage(ImageInfo imageInfo);
	
	public boolean deleteImage(Images imageInfo);
	
	public boolean deleteImageforName(String imageInfo);
	
	public boolean updateImageforUserId(ImageInfo integer);
 
	public ImageInfo selectImageforId(Integer id) ;
 
	public List<Integer> selectImageforUserId(Integer id);
	
	public List<ImageInfo> selectImages(Images imageInfo);
	

	
}

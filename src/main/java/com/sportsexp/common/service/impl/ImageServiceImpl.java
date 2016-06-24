package com.sportsexp.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sportsexp.common.exception.ServiceException;
import com.sportsexp.common.service.ImageService;
import com.sportsexp.common.service.pojo.Images;
import com.sportsexp.common.service.vo.ImageInfo;


@Service
public class ImageServiceImpl extends BaseServiceImpl implements ImageService{

	private static final String  ADD_IMAGE="ImageDao.addImage";
	private static final String  SELECT_IMAGEFORID="ImageDao.selectImageforId";
	private static final String  SELECT_IMAGEFORUSERID="ImageDao.selectImageforUserId";
	private static final String  SELECT_IMAGES="ImageDao.selectImages";
	private static final String  DELETE_IMAGES="ImageDao.deleteImages"; 
	private static final String  DELETE_IMAGESFORNAME="ImageDao.deleteImageforName"; 
	private static final String  UPDATE_IMAGEFORUSERID   ="updateImageforUserId";
	@Override
	public boolean addImage(ImageInfo imageInfo) throws ServiceException{

		if(baseDao.save(ADD_IMAGE, imageInfo)>0)
			return true;
		return false;
	}

	@Override
	public boolean deleteImage(Images images){
		
		if(this.baseDao.delete(DELETE_IMAGES, images)>0)
			return true;
		return false;
	}
	
	@Override
	public boolean deleteImageforName(String name){
		
		if(this.baseDao.delete(DELETE_IMAGESFORNAME, name)>0)
			return true;
		return false;
	}

	@Override
	public ImageInfo selectImageforId(Integer id) {

		return this.baseDao.get(SELECT_IMAGEFORID, id);
	}
	@Override
	public List<Integer> selectImageforUserId(Integer id) {

		return this.baseDao.getList(SELECT_IMAGEFORUSERID, id);
	}

	@Override
	public List<ImageInfo> selectImages(Images images){

		return this.baseDao.getList(SELECT_IMAGES, images);
	}

	@Override
	public boolean updateImageforUserId(ImageInfo userId) {
		 
		if(this.baseDao.update(UPDATE_IMAGEFORUSERID, userId)>0)
			return true;
		return false;
	}

}

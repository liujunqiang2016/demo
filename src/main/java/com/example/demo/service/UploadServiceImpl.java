package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

@Service
public class UploadServiceImpl implements UploadService {
	
	@Autowired
    FastFileStorageClient storageClient;

    @Autowired
    ThumbImageConfig thumbImageConfig;
	

	@Override
	public void upload(String filePath) {
		StorePath storePath = uploadImageAndCrtThumbImage(filePath,createMetaData());
		System.out.println("group:"+storePath.getGroup());
		System.out.println("path:"+storePath.getPath());
		System.out.println("full path:"+storePath.getFullPath());
	}
	
	
	private StorePath uploadImageAndCrtThumbImage(String filePath, Set<MetaData> metaDataSet) {
        InputStream in = null;
        File file = new File(filePath);
        String fileExtName = FilenameUtils.getExtension(file.getName());
        long fileSize = file.length();
        try {
            in = new FileInputStream(file);
            return storageClient.uploadFile(in, fileSize, fileExtName, metaDataSet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }
	
	private Set<MetaData> createMetaData() {
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData("Author", "tobato"));
        metaDataSet.add(new MetaData("CreateDate", "2016-01-05"));
        return metaDataSet;
    }
	

}

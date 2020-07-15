package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.TestConfig;
import com.example.demo.domain.TestBean;
import com.example.demo.mapper.test1.Park1_Mapper;
import com.example.demo.mapper.test2.Park2_Mapper;
import com.example.demo.service.UploadService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;


@RestController
@RequestMapping("/")
public class TestController {
	
//	@Autowired
//	TestBean tb;
//	
//	@Value("${timeout}")
//    private String timeout;
	
//	@Autowired
//	UserMapper mapper;
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	Park1_Mapper park1;
	
	@Autowired
	Park2_Mapper park2;
	
	@Autowired
    FastFileStorageClient storageClient;
	
	@Value("${park2.datasource.url}")
    private String url;
	
	@RequestMapping("/test")
    public String test() {
//		System.out.println("timout:"+timeout);
		System.out.println("url:"+ url);
		//System.out.println(mapper.selectList(null));
		return "test";
	}
	
	@RequestMapping("/download")
    public String download() {
//		System.out.println("timout:"+timeout);
		String fileUrl = "group1/M00/00/00/wKgFgF8EDuWAD8n5ACfxYmSLDA881.docx";
		FileOutputStream out = null;
		try {
			StorePath storePath = StorePath.parseFromUrl(fileUrl);
			String group = storePath.getGroup();
			String path = storePath.getPath();
			byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
			
			File file = new File("E:\\日志2.docx");
			out = new FileOutputStream(file);
			IOUtils.write(bytes, out);
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}finally {
			IOUtils.closeQuietly(out);
		}
		return "test";
	}
	
	@RequestMapping("/upload")
    public String upload() {
//		System.out.println("timout:"+timeout);
		String filePath = "E:\\work\\后台开发\\后台开发\\项目打包、查看日志.docx";
		try {
			uploadService.upload(filePath);
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		return "test";
	}
	
	@RequestMapping("/user1")
    public String user1() {
		try {
          System.out.println(park1.getList());
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		return "test1";
	}
	
	@RequestMapping("/user2")
    public String user2() {
		try {
          System.out.println(park2.getList());
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		return "test2";
	}
}

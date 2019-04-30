package cn.gpa.zut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.gpa.zut.utils.FTPUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;


	@Controller
	@RequestMapping("/upload")
	public class FTPContriller {
	/*
	 * @value("${ftpHost}") private String host;
	 * 
	 * @value("${ftpUser}") private String userName;
	 * 
	 * @value("${ftpPwd}") private String userPwd;
	 * 
	 * @value("${ftpBasePath}") private String basePath;
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/uploadImg") public ResultEntity
	 * uploadImg(HttpServletRequest req) throws IOException {
	 * MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
	 * MultipartFile file = request.getFile("file");
	 * 
	 * if (file != null){ // 获取文件的后缀 String originalName =
	 * file.getOriginalFilename(); String suffix =
	 * originalName.substring(originalName.lastIndexOf(".")); // 时间戳+随机数生成文件名 String
	 * fileName =
	 * String.valueOf(System.currentTimeMillis())+(int)((Math.random()*9+1)*100000)+
	 * suffix;
	 * 
	 * // 将文件转化为字节流 InputStream is = file.getInputStream(); boolean uploadRes =
	 * FTPUtils.uploadFile(host, 21, userName, userPwd, basePath, "/", fileName,
	 * is); if (uploadRes){ return ResultEntity.ok(fileName); } }
	 * 
	 * return ResultEntity.build("1000","图片上传失败"); } }
	 */
}

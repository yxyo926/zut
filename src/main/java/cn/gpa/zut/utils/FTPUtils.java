package cn.gpa.zut.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import sun.net.ftp.FtpClient;

public class FTPUtils {
	/**
     * @param host      ftp服务器host
     * @param port      ftp服务器端口
     * @param username  ftp登录账号
     * @param password  ftp登录密码
     * @param basePath  ftp服务器基础目录
     * @param filePath  ftp服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename  上传到FTP服务器上的文件名
     * @param input     输入流
     * @return
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }
	
	
	
	
	
	
	/*
	 * file:待上传的文件对象 path:待上传保存的地址 newFileName:保存的文件名称及格式
	 * 
	 * 
	 * public static void UploadFile(File file, String path, String newFileName){
	 * //File file = new File("E:/学校文件/软件工程.xls"); //String newFileName =
	 * "软件工程.xls"; //创建ftp客户端 FTPClient ftpClient = new FTPClient();
	 * ftpClient.setControlEncoding("GBK"); String hostname = GetPath.hostname; int
	 * port = 21; String username = GetPath.username; String password =
	 * GetPath.password; try { //链接ftp服务器 ftpClient.connect(hostname, port); //登录ftp
	 * ftpClient.login(username, password); int reply = ftpClient.getReplyCode();
	 * System.out.println(reply); if (!FTPReply.isPositiveCompletion(reply)) {
	 * ftpClient.disconnect(); return ; }
	 * ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); String file_path =
	 * GetPath.getProjectRealPath() + path;
	 * //ftpClient.makeDirectory(file_path);//在root目录下创建文件夹 StringTokenizer s = new
	 * StringTokenizer(file_path, "/"); s.countTokens(); String pathName = ""; while
	 * (s.hasMoreElements()) { pathName = pathName + "/" + (String) s.nextElement();
	 * try { ftpClient.mkd(pathName); } catch (Exception e) { } } InputStream input
	 * = new FileInputStream(file); ftpClient.storeFile(file_path + "/" +
	 * newFileName, input);/ input.close(); ftpClient.logout(); } catch
	 * (SocketException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); }finally { if (ftpClient.isConnected()) { try {
	 * ftpClient.disconnect(); } catch (IOException ioe) { ioe.printStackTrace(); }
	 * } } }
	 * 
	 * 
	 * input:待上传的文件字节流 path:待上传保存的地址 newFileName:保存的文件名称及格式
	 * 
	 * 
	 * public static void UploadFile(InputStream input, String path, String
	 * newFileName) { // 创建ftp客户端 FTPClient ftpClient = new FTPClient();
	 * ftpClient.setControlEncoding("GBK"); String hostname = GetPath.hostname; int
	 * port = 21; String username = GetPath.username; String password =
	 * GetPath.password; try { // 链接ftp服务器 ftpClient.connect(hostname, port); //
	 * 登录ftp ftpClient.login(username, password); int reply =
	 * ftpClient.getReplyCode(); System.out.println(reply); if
	 * (!FTPReply.isPositiveCompletion(reply)) { ftpClient.disconnect(); return; }
	 * ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); String file_path =
	 * GetPath.getProjectRealPath() + path; //
	 * ftpClient.makeDirectory(file_path);//在root目录下创建文件夹 StringTokenizer s = new
	 * StringTokenizer(file_path, "/"); s.countTokens(); String pathName = ""; while
	 * (s.hasMoreElements()) { pathName = pathName + "/" + (String) s.nextElement();
	 * try { ftpClient.mkd(pathName); } catch (Exception e) { } }
	 * ftpClient.storeFile(file_path + "/" + newFileName, input);//
	 * 文件若是不指定就会上传到root目录下 input.close(); ftpClient.logout(); } catch
	 * (SocketException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); } finally { if (ftpClient.isConnected()) { try {
	 * ftpClient.disconnect(); } catch (IOException ioe) { ioe.printStackTrace(); }
	 * } } }
	 */
}
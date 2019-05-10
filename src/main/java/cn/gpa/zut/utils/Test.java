package cn.gpa.zut.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;  
  
public class Test {       
    private  FTPClient ftp;    
    /** 
     *  
     * @param path 上传到ftp服务器哪个路径下    
     * @param addr 地址 
     * @param port 端口号 
     * @param username 用户名 
     * @param password 密码 
     * @return 
     * @throws Exception 
     */  
    public  boolean connect(String path,String addr,int port,String username,String password) throws Exception {    
        boolean result = false;    
        ftp = new FTPClient();    
        int reply;    
        ftp.connect(addr,port);    
        ftp.login(username,password);    
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);    
        reply = ftp.getReplyCode();    
        if (!FTPReply.isPositiveCompletion(reply)) {    
            ftp.disconnect();    
            return result;    
        }    
        ftp.changeWorkingDirectory(path);    
        result = true;    
        return result;    
    }    
    /** 
     *  
     * @param file 上传的文件或文件夹 
     * @throws Exception 
     */      
    public void upload(File file) throws Exception{    
        if(file.isDirectory()){         
            ftp.makeDirectory(file.getName());              
            ftp.changeWorkingDirectory(file.getName());    
            String[] files = file.list();           
            for (int i = 0; i < files.length; i++) {    
                File file1 = new File(file.getPath()+"\\"+files[i] );    
                if(file1.isDirectory()){    
                    upload(file1);    
                    ftp.changeToParentDirectory();    
                }else{                  
                    File file2 = new File(file.getPath()+"\\"+files[i]);    
                    FileInputStream input = new FileInputStream(file2);    
                    ftp.storeFile(file2.getName(), input);    
                    input.close();                          
                }               
            }    
        }else{    
            File file2 = new File(file.getPath());    
            FileInputStream input = new FileInputStream(file2);    
            ftp.storeFile(file2.getName(), input);    
            input.close();      
        }    
    }  
    
    public boolean MultiUpload1(MultipartFile uploadFile) {
        boolean result = false;
        try {

            String fileName = new String(uploadFile.getOriginalFilename());
            System.out.println("wenjinming   "+fileName);
            fileName = "id" + "-" + fileName;// 构建上传到服务器上的文件名 20-文件名.后缀
            FTPFile[] fs = ftp.listFiles(fileName);
            if (fs.length == 0) {
                System.out.println("this file not exist ftp");
            } else if (fs.length == 1) {
                System.out.println("this file exist ftp");
                ftp.deleteFile(fs[0].getName());
            }
            InputStream is = uploadFile.getInputStream();
            result = ftp.storeFile(fileName, is);
            is.close();
        } catch (IOException e) {
            result = false;
            e.printStackTrace();
        } finally {
            try {
                ftp.disconnect();
                System.out.println("上传完成。。。");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean createDir(String dir){
		if(StringExtend.isNullOrEmpty(dir))
			return true;
		String d;
		try {
			//目录编码，解决中文路径问题
			dir="/zut/"+dir;
			d = new String(dir.toString().getBytes("GBK"),"iso-8859-1");
			//尝试切入目录
			if(ftp.changeWorkingDirectory(d))
				return true;
			dir = StringExtend.trimStart(dir, "/");
			dir = StringExtend.trimEnd(dir, "/");
			String[] arr =  dir.split("/");
			StringBuffer sbfDir=new StringBuffer();
			//循环生成子目录
			for(String s : arr){
				sbfDir.append("/");
				sbfDir.append(s);
				//目录编码，解决中文路径问题
				d = new String(sbfDir.toString().getBytes("GBK"),"iso-8859-1");
				//尝试切入目录
				if(ftp.changeWorkingDirectory(d))
					continue;
				if(!ftp.makeDirectory(d)){
					System.out.println("[失败]ftp创建目录："+sbfDir.toString());
					return false;
				}
				System.out.println("[成功]创建ftp目录："+sbfDir.toString());
			}
			//将目录切换至指定路径
			return ftp.changeWorkingDirectory(d);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
   public static void main(String[] args) throws Exception{  
     Test t = new Test();  
      t.connect("/zut/", "119.23.12.86", 21, "test", "123456");  
      File file = new File("F:/test.txt");  
      t.upload(file);  
      System.out.println("ok");
      
   }  
}  
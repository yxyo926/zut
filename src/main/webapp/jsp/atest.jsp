<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html>  
    <head>  
        <meta charset="utf-8" />  
        <title>测试</title>  
        <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" type="text/javascript" charset="utf-8"></script>  
    </head>  
    <body>  
        <input type="file" id="file" onchange="ajaxFileUpload();" />  
    </body>  
    <script>  
        function ajaxFileUpload() {  
            var form = new FormData;          //创建一个FormData对象  
            form.append('file',$("#file").get(0).files[0]);
            //将文件放到FormData对象中  
            console.log(form);  
            $.ajax({  
                url : "http://127.0.0.1:8080/upload/uploadImg",//访问路径  
                contentType: false,  
                processData: false,   
                type:"post",  
                data :form,  
                dataType : "json"  
                  
            }).success(function(data,status){  
                if(status=="success"){  
                    if(data.code==200){  
                        alert("文件上传成功");  
                    }  
                    if(data.code==400){  
                        alert("文件上传失败");  
                    }  
                }                    
            }).error(function(){  
                alert("服务器未响应");  
            });  
        }            
    </script>  
</html>  
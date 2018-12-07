package com.springboot.springbootdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Value("${upload.fileSize}")
    private String fileSize;//允许文件上传最大限制

    @Value("${upload.filePath}")
    private String filePath;//文件上传路径

    @GetMapping(value = "helloFile")
    public String helloFile(){

        return "UploadController";
    }

    @PostMapping(value="/upload")
    public Map<String,String> upload(@RequestParam("file") MultipartFile file){
        long s = file.getSize();
         /*if(file != null){
             throw new frilException("kkkkk","100");
         }*/
        System.out.println(s);
        Map<String,String> map = new HashMap<>();
        //判断文件是否存在
        if (file.isEmpty()) {
            map.put("code", "1");
            map.put("msg", "上传失败，请选择文件");
            LOGGER.info("===========上传失败，请选择文件:上传文件为空===========");
            return map;
        }
        //判断文件大小

        try {
            String fileName = file.getOriginalFilename();
            String filePath = "D:\\CBQ\\work\\";
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                LOGGER.info("========上传成功==========");
                map.put("code", "1");
                map.put("msg", "文件上传成功");
                return map;
            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
                map.put("code", "1");
                map.put("msg", "文件上传失败！");
                LOGGER.info("========文件上传失败！==========");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("================================");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 多文件上传
     */
    @PostMapping(value="/multiFileUpload")
    @ResponseBody
    public Map<String,String> multiFileUpload(@RequestParam("fileName") List<MultipartFile> files) {

        Map<String,String> map = new HashMap<>();
        List<String> lsit = new ArrayList<>();
        /*public @ResponseBody String multifileUpload(HttpServletRequest request){*/

        /*List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");*/
        //文件是否存在
        if(files.isEmpty()){
            map.put("code", "1");
            map.put("msg", "上传文件不存在");
            return map;
        }
        int filesize =Integer.parseInt(fileSize)*1024*1024;
        String path = filePath ;

        for(MultipartFile file:files){

            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String da = dateFormat.format(date);
            String fileName = file.getOriginalFilename();
            String[] str = fileName.split("\\.");
            int size = (int) file.getSize();
            if(file.isEmpty()){
                lsit.add(fileName);
                LOGGER.info("===========上传失败"+fileName+":文件为空===========");
            }
            if(filesize >= size && size > 0){
	            	/*if(file.isEmpty()){
		                return "false";
		            }else{*/
                File dest = new File(path + "/" + da+"."+str[str.length-1]);
		               /* Upload up = new Upload(file,dest);
		                Thread thread = new Thread(up);
		                thread.start();
		                System.out.println("=======================================");*/
                try {
                    //执行上传
                    file.transferTo(dest);
                    LOGGER.info("===========上传"+fileName+":成功===========");
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    lsit.add(fileName);
                    LOGGER.info("===========上传失败"+fileName+":写入失败===========");
                }
                /* }*/
            }else if (filesize <= size && size > 0){
                lsit.add(fileName);
                LOGGER.info("===========上传失败"+fileName+":文件过大===========");
            }
        }
        map.put("code", "1");
        map.put("msg", "上传文件");
        map.put("list", lsit.toString()+"/文件上传失败");
        return map;
    }

}

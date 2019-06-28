package com.mcally.cn.controller;
import com.mcally.cn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@CrossOrigin
public class HelloWord {
    @Autowired
    private TestService testService;


    @RequestMapping("/hello")
    public String Hello() {
        return "Hello Word  Mcally";
    }

    @RequestMapping("/list")
    public List<Map<String, Object>> get() {
        return testService.getDataList();
    }


    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, MultipartFile multipartFile) {
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        //0，判断是否为空
        if (multipartFile != null && !multipartFile.isEmpty()) {

            /**
             * 对文件名进行操作防止文件重名
             */
            //1，获取原始文件名
            String originalFilename = multipartFile.getOriginalFilename();
            //2,截取源文件的文件名前缀,不带后缀
            String fileNamePrefix = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            //3,加工处理文件名，原文件加上时间戳
            String newFileNamePrefix = fileNamePrefix + System.currentTimeMillis();
            //4,得到新文件名
            String newFileName = newFileNamePrefix + originalFilename.substring(originalFilename.lastIndexOf("."));

            //5,构建文件对象
            File file = new File(filePath+ newFileName);
            //6,执行上传操作
            try {
                multipartFile.transferTo(file);
                return "上传成功";
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败";
            }
        }
       return  null;
    }


    @RequestMapping(value="/uploadtest2")
    public String uploadtest2(HttpServletRequest request, @RequestParam("file")MultipartFile[] multipartFiles, Model model){//入参代表上传的文件

        //设置list存放数组
        List<String> fileNames = new ArrayList<>();
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        //0，判断是否为空
        if(multipartFiles!=null && multipartFiles.length>0)
        {
            for (MultipartFile multipartFile:multipartFiles)
            {
                if(multipartFile!=null && !multipartFile.isEmpty())
                {

                    /**
                     * 对文件名进行操作防止文件重名
                     */

                    //1，获取原始文件名
                    String originalFilename = multipartFile.getOriginalFilename();
                    //2,截取源文件的文件名前缀,不带后缀
                    String fileNamePrefix = originalFilename.substring(0,originalFilename.lastIndexOf("."));
                    //3,加工处理文件名，原文件加上时间戳
                    String newFileNamePrefix  = fileNamePrefix + System.currentTimeMillis();
                    //4,得到新文件名
                    String newFileName = newFileNamePrefix + originalFilename.substring(originalFilename.lastIndexOf("."));
                    //5,构建文件对象
                    File file = new File(filePath + newFileName);
                    //6,执行上传操作
                    try {
                        multipartFile.transferTo(file);
                        //上传成功，把 文件名 存入list里面
                        fileNames.add(newFileName);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        //list传送到前端
        model.addAttribute("fileNames",fileNames);

        return "system/plansubmit/upload2_success";
    }



}
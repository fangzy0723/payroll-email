package cn.com.sinosoft.payrollemail.controller;

import cn.com.sinosoft.payrollemail.service.GetExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by fangzy on 2018/8/11 10:43
 */
@RestController
public class UploadExcelController {

    @Autowired
    private GetExcelDataService getExcelDataService;

    @PostMapping("/uploadFile")
    public void addUser(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        getExcelDataService.getExcelDataToList(fileName, file);
    }
}

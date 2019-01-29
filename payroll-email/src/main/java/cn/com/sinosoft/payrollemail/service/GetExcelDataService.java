package cn.com.sinosoft.payrollemail.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by fangzy on 2018/8/11 11:00
 */
public interface GetExcelDataService {
    void getExcelDataToList(String fileName, MultipartFile file);
}

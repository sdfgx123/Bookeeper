package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.CloudDTO;
import poly.dto.MelonDTO;
import poly.service.ICloudService;

@Controller
public class CloudController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "CloudService")
	private ICloudService cloudService;
	
	/**
     * 교보문고 신간 크롤링하여 Collection 생성
     */
    @RequestMapping(value = "cloud/collectBookRank")
    @ResponseBody
    public String collectBookRank() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonRank Start!");

        cloudService.collectBookRank();

        log.info(this.getClass().getName() + ".collectMelonRank End!");

        return "success";
    }
    
    /**
     * 책 제목 데이터 가져오기
     */
    @RequestMapping(value = "cloud/getTitle")
    @ResponseBody
    public List<CloudDTO> getTitle() throws Exception {

        log.info(this.getClass().getName() + ".getTitle Start!");

        List<CloudDTO> rList = cloudService.getTitle();

        if (rList == null) {
            rList = new ArrayList<>();
        }

        log.info(this.getClass().getName() + ".getTitle End!");

        return rList;
    }
}

package poly.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.service.ICloudService;

@Controller
public class CloudController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "CloudService")
	private ICloudService cloudService;
	
	/**
     * 멜론 Top100 수집하기
     */
    @RequestMapping(value = "melon/collectMelonRank")
    @ResponseBody
    public String collectMelonRank() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonRank Start!");

        cloudService.collectBookRank();

        log.info(this.getClass().getName() + ".collectMelonRank End!");

        return "success";
    }
}

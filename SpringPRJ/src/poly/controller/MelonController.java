package poly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.MelonDTO;
import poly.dto.MelonSingerDTO;
import poly.dto.MelonSongDTO;
import poly.service.IMelonService;
import poly.service.IMelonServiceUsingDTO;

@Controller
public class MelonController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "MelonService")
	private IMelonService melonService;
	
	/**
     * 멜론 Top100 수집하기
     */
    @RequestMapping(value = "melon/collectMelonRank")
    @ResponseBody
    public String collectMelonRank() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonRank Start!");

        melonService.collectMelonRank();

        log.info(this.getClass().getName() + ".collectMelonRank End!");

        return "success";
    }
    
    /**
     * 멜론 데이터 가져오는 일반 화면
     */
    @RequestMapping(value = "melon/melonTop100")
    public String melonTop100() throws Exception {

        log.info(this.getClass().getName() + ".melonTop100 Start!");

        log.info(this.getClass().getName() + ".melonTop100 End!");

        return "/melon/melonTop100";
    }
    
    /**
     * 멜론 데이터 가져오기
     */
    @RequestMapping(value = "melon/getRank")
    @ResponseBody
    public List<MelonDTO> getRank() throws Exception {

        log.info(this.getClass().getName() + ".getRank Start!");

        List<MelonDTO> rList = melonService.getRank();

        if (rList == null) {
            rList = new ArrayList<>();
        }

        log.info(this.getClass().getName() + ".getRank End!");

        return rList;
    }
    
    /**
     * 가수의 노래 데이터 가져오는 일반 화면
     */
    @RequestMapping(value = "melon/melonSongForSinger")
    public String melonSongForSinger() throws Exception {

        log.info(this.getClass().getName() + ".melonSongForSinger Start!");

        log.info(this.getClass().getName() + ".melonSongForSinger End!");

        return "/melon/melonSongForSinger";
    }
    
    /**
     * 가수의 노래 데이터 가져오기
     */
    @RequestMapping(value = "melon/getSongForSinger")
    @ResponseBody
    public List<MelonSongDTO> getSongForSinger() throws Exception {

        log.info(this.getClass().getName() + ".getSongForSinger Start!");

        List<MelonSongDTO> rList = melonService.getSongForSinger();

        if (rList == null) {
            rList = new ArrayList<>();
        }

        log.info(this.getClass().getName() + ".getSongForSinger End!");

        return rList;
    }
    
    /**
     * 가수별 멜론 랭킹에 많이 등록된 순서대로 가져오는 일반 화면
     */
    @RequestMapping(value = "melon/melonSingerRank")
    public String melonSingerRank() throws Exception {

        log.info(this.getClass().getName() + ".melonSingerRank Start!");

        log.info(this.getClass().getName() + ".melonSingerRank End!");

        return "/melon/melonSingerRank";
    }
    
    /**
     * 가수별 멜론 랭킹에 많이 등록된 순서대로 가져오기
     */
    @RequestMapping(value = "melon/getRankForSinger")
    @ResponseBody
    public List<MelonSingerDTO> getRankForSinger() throws Exception {

        log.info(this.getClass().getName() + ".getRankForSinger Start!");

        List<MelonSingerDTO> rList = melonService.getRankForSinger();

        if (rList == null) {
            rList = new ArrayList<>();
        }

        log.info(this.getClass().getName() + ".getRankForSinger End!");

        return rList;
    }

}
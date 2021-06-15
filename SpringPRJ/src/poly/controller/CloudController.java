package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.CloudDTO;
import poly.dto.MelonDTO;
import poly.service.ICloudService;
import poly.service.IWordAnalysisService;

@Controller
public class CloudController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "CloudService")
	private ICloudService cloudService;
	
	@Resource(name = "WordAnalysisService")
	private IWordAnalysisService wordAnalysisService;
	
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
    
    // 책 정보 형태소 분석
    @RequestMapping(value = "cloud/analysis")
    @ResponseBody
    public Map<String, Integer> analysis() throws Exception {
    	log.info(this.getClass().getName() + ".analysis Start!");
    	String text = "유미의 세포들 세트(10-13권)(전4권)위대한 서양미술사닥터앤닥터 육아일기. 2: 신생아 돌보기부자의 패턴(양장본 HardCover)팀 하포드의 세상을 바꾼 51가지 물건우리가 몰랐던 백년 건강 동의보감만만한 집밥 레시피 162이런 수학은 처음이야. 2문명. 1스티커 페인팅북: 가면헬로카봇 국기 카드 100인소의 법칙. 3(한정판)(만화)(디앤씨웹툰비즈)대슬이 밥상초등 질문의 힘또라이 질량 보존의 법칙에서 살아남기왜 리더인가(양장본 HardCover)암은 병이 아니다(개정증보판)궤도의 밖에서, 나의 룸메이트에게(문학동네 청소년 53)Go Go 카카오프렌즈. 20: 한국. 3문명. 2";
    	Map<String, Integer> rMap = wordAnalysisService.doWordAnalysis(text);
    	if (rMap == null) {
    		rMap = new HashMap<String, Integer>();
    	}
    	return rMap;
    }
    
    @RequestMapping(value = "cloud/noun")
    @ResponseBody
    public List<String> noun() throws Exception {
    	log.info(this.getClass().getName() + ".noun Start!");
    	String text = "유미의 세포들 세트(10-13권)(전4권)위대한 서양미술사닥터앤닥터 육아일기. 2: 신생아 돌보기부자의 패턴(양장본 HardCover)팀 하포드의 세상을 바꾼 51가지 물건우리가 몰랐던 백년 건강 동의보감만만한 집밥 레시피 162이런 수학은 처음이야. 2문명. 1스티커 페인팅북: 가면헬로카봇 국기 카드 100인소의 법칙. 3(한정판)(만화)(디앤씨웹툰비즈)대슬이 밥상초등 질문의 힘또라이 질량 보존의 법칙에서 살아남기왜 리더인가(양장본 HardCover)암은 병이 아니다(개정증보판)궤도의 밖에서, 나의 룸메이트에게(문학동네 청소년 53)Go Go 카카오프렌즈. 20: 한국. 3문명. 2";
    	List<String> rList = wordAnalysisService.doWordNouns(text);
    	if (rList == null) {
    		rList = new ArrayList<>();
    	}
    	log.info(this.getClass().getName() + ".noun end!");
    	return rList;
    }
}

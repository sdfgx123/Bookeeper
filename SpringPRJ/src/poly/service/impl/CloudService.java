package poly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.CloudDTO;
import poly.dto.MelonDTO;
import poly.persistance.mongo.ICloudMapper;
import poly.service.ICloudService;
import poly.util.DateUtil;

@Service("CloudService")
public class CloudService implements ICloudService {

	@Resource(name = "CloudMapper")
	private ICloudMapper cloudMapper;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void collectBookRank() throws Exception {
		
		log.info(this.getClass().getName() + ".collectBookRank Start");
		
		List<CloudDTO> pList = new ArrayList<>();
		
		// 교보문고 크롤링을 위한 URL 지정
		String url = "http://www.kyobobook.co.kr/newproduct/newProductList.laf";
		
		Document doc;
		
		doc = Jsoup.connect(url).get();
		
		// <div class="service_list_song"> 이 태그 내에서 있는 HTML소스만 element에 저장됨
        Elements element = doc.select("ul.prd_list_type1");
        
        for (Element bookInfo : element.select("div.detail")) {

            // 크롤링을 통해 데이터 저장하기
            String word = bookInfo.select("div.title").text(); // 순위

            bookInfo = null;

            // MongoDB에 저장할 List 형태의 맞는 DTO 데이터 저장하기
            CloudDTO pDTO = new CloudDTO();
            pDTO.setWord(word);

            // 한번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
            pList.add(pDTO);

        }
		
	}
	
}

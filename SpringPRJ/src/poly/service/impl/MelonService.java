package poly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.nimbusds.jwt.util.DateUtils;

import poly.dto.MelonDTO;
import poly.persistance.mongo.IMelonMapper;
import poly.service.IMelonService;
import poly.util.DateUtil;

@Service("MelonService")
public class MelonService implements IMelonService {

	@Resource(name = "MelonMapper")
	private IMelonMapper melonMapper; // MongoDB에 저장할 Mapper

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int collectMelonRank() throws Exception {

		log.info(this.getClass().getName() + " .collectMelonRank start");

		int res = 0;

		List<MelonDTO> pList = new ArrayList<MelonDTO>();

		// 멜론 Top 100 중 50위까지 정보 가져오는 페이지
		String url = "https://www.melon.com/chart/day/index.htm";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// <div class="service_list_song"> 이 태그 내에 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.service_list_song");

		// Iterator를 사용하여 멜론 Top100의 50위까지 순위 정보를 가져오기
		Iterator<Element> rank50List = element.select("tr.lst50").iterator(); // 멜론 50위까지

		// 순위는 1위부터 50위까지 수집되기 때문에 반복문을 통해 데이터 저장
		while (rank50List.hasNext()) {

			Element songInfo = rank50List.next();

			// 크롤링을 통해 데이터 저장하기
			String rank = songInfo.select("span.rank").text(); // 순위
			String song = songInfo.select("div.elipsis a").eq(0).text(); // 노래
			String singer = songInfo.select("div.elipsis a").eq(1).text(); // 가수
			String album = songInfo.select("div.elipsis a").eq(3).text(); // 앨범

			songInfo = null;

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			MelonDTO pDTO = new MelonDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setRank(rank);
			pDTO.setSong(song);
			pDTO.setSinger(singer);
			pDTO.setAlbum(album);

			// 한번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}
		
		String colNm = "MelonTOP100_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		melonMapper.createCollection(colNm);

		// MongoDB에 데이터 저장하기
		melonMapper.insertRank(pList, colNm);

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다)
		log.info(this.getClass().getName() + " .collectMelonRank end");

		return res;
	}

}

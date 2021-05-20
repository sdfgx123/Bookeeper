package poly.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.MelonDTO;
import poly.persistance.mongo.IMelonMapper;
import poly.service.IMelonService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("MelonService")
public class MelonService implements IMelonService {

	// MongoDB에 저장할 Mapper
	@Resource(name = "MelonMapper")
	private IMelonMapper melonMapper;

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public int collectMelonSong() throws Exception {

		log.info(this.getClass().getName() + ".collectMelonRank Start!");
		
		// 데이터 수집시간
		String collectTime = DateUtil.getDateTime("yyyyMMddhhmmss");

		int res = 0;

		// 멜론 Top100 중 50위까지 정보 가져오는 페이지
		String url = "https://www.melon.com/chart/index.htm";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null; //

		doc = Jsoup.connect(url).get();

		// <div class="service_list_song"> 이 태그 내에서 있는 HTML소스만 element에 저장됨
		Elements element = doc.select("div.service_list_song");

		// Iterator을 사용하여 멜론차트 정보를 가져오기
		Iterator<Element> songList = element.select("div.wrap_song_info").iterator(); // 멜론 50위까지 차크

		while (songList.hasNext()) {

			Element songInfo = songList.next();

			// 크롤링을 통해 데이터 저장하기
			String song = CmmUtil.nvl(songInfo.select("div.ellipsis.rank01 a").text()); // 노래
			String singer = CmmUtil.nvl(songInfo.select("div.ellipsis.rank02 a").eq(0).text()); // 가수

			log.info("song : " + song);
			log.info("singer : " + singer);

			// 가수와 노래 정보가 모두 수집되었다면, 저장함
			if ((song.length() > 0) && (singer.length() > 0)) {
				
				MelonDTO pDTO = new MelonDTO();
				
				pDTO.setCollect_time(collectTime);
				pDTO.setSong(song);
				pDTO.setSinger(singer);
				
				melonMapper.insertSong(pDTO);
				
				pDTO = null;
			}
			
			songInfo = null;
		}
		
		doc = null;
		
		log.info(this.getClass().getName() + ".collectMelonSong End!");
		
		return res;
	}

	@Override
	public List<MelonDTO> getSongList() throws Exception {

		log.info(this.getClass().getName() + ".getSongList Start!");
		
		List<MelonDTO> rList = melonMapper.getSongList();
		
		if (rList == null) {
			rList = new LinkedList<MelonDTO>();
		}

		log.info(this.getClass().getName() + ".getSongList End!");

		return rList;
	}

	@Override
	public List<Map<String, Object>> getSingerSongCnt() throws Exception {

		log.info(this.getClass().getName() + ".getSingerSongCnt Start!");
		
		List<MelonDTO> rList = melonMapper.getSingerCnt();
		
		if (rList == null) {
			rList = new LinkedList<MelonDTO>();
		}
		
		List<Map<String, Object>> sList = new LinkedList<Map<String, Object>>();
		
		Iterator<MelonDTO> it = rList.iterator();
		
		while (it.hasNext()) {
			MelonDTO rDTO = it.next();
			
			if (rDTO == null) {
				rDTO = new MelonDTO();
			}
			
			String singer = CmmUtil.nvl(rDTO.getSinger());
			int songCnt = rDTO.getSingerCnt();
			
			// 전달되는 Map 구조
			Map<String, Object> sMap = new LinkedHashMap<String, Object>();
			
			sMap.put("singer", singer);
			sMap.put("songCnt", songCnt);
			
			sList.add(sMap);
			sMap = null;
			
		}
		
		log.info(this.getClass().getName() + ".getSingerSongCnt End!");
		
		return sList;

		
	}

}
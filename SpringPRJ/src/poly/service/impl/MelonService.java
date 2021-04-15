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
	private IMelonMapper melonMapper; // MongoDB�� ������ Mapper

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int collectMelonRank() throws Exception {

		log.info(this.getClass().getName() + " .collectMelonRank start");

		int res = 0;

		List<MelonDTO> pList = new ArrayList<MelonDTO>();

		// ��� Top 100 �� 50������ ���� �������� ������
		String url = "https://www.melon.com/chart/day/index.htm";

		// JSOUP ���̺귯���� ���� ����Ʈ ���ӵǸ�, �� ����Ʈ�� ��ü HTML �ҽ��� ������ ����
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// <div class="service_list_song"> �� �±� ���� �ִ� HTML �ҽ��� element�� �����
		Elements element = doc.select("div.service_list_song");

		// Iterator�� ����Ͽ� ��� Top100�� 50������ ���� ������ ��������
		Iterator<Element> rank50List = element.select("tr.lst50").iterator(); // ��� 50������

		// ������ 1������ 50������ �����Ǳ� ������ �ݺ����� ���� ������ ����
		while (rank50List.hasNext()) {

			Element songInfo = rank50List.next();

			// ũ�Ѹ��� ���� ������ �����ϱ�
			String rank = songInfo.select("span.rank").text(); // ����
			String song = songInfo.select("div.elipsis a").eq(0).text(); // �뷡
			String singer = songInfo.select("div.elipsis a").eq(1).text(); // ����
			String album = songInfo.select("div.elipsis a").eq(3).text(); // �ٹ�

			songInfo = null;

			// MongoDB�� ������ List ���¿� �´� DTO ������ �����ϱ�
			MelonDTO pDTO = new MelonDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setRank(rank);
			pDTO.setSong(song);
			pDTO.setSinger(singer);
			pDTO.setAlbum(album);

			// �ѹ��� �������� �����͸� MongoDB�� ������ List ������ ������ �����ϱ�
			pList.add(pDTO);
		}
		
		String colNm = "MelonTOP100_" + DateUtil.getDateTime("yyyyMMdd"); // ������ �÷��Ǹ�

		// MongoDB Collection �����ϱ�
		melonMapper.createCollection(colNm);

		// MongoDB�� ������ �����ϱ�
		melonMapper.insertRank(pList, colNm);

		// �α� ���(���� ���� �α׸� ���� �� �Լ��� �����ߴ��� �ľ��ϱ� �����ϴ�)
		log.info(this.getClass().getName() + " .collectMelonRank end");

		return res;
	}

}

package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.MelonDTO;
import poly.persistance.mongo.IMelonMapper;
import poly.util.CmmUtil;

@Component("MelonMapper")
public class MelonMapper implements IMelonMapper {

	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean createCollection(String colNm) throws Exception {

		log.info(this.getClass().getName() + " .createCollection start");
		
		boolean res = false;
		
		//������ ��ϵ� �÷��� �̸��� �����ϴ��� üũ�ϰ�, �����ϸ� ����  �÷��� ������
		if(mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);
		}
		
		//�÷��� ���� �� �ε��� ����, MongoDB���� ������ �������� ��Ŀ� �°� �ε����� �ݵ�� ��������!
		//������ ���� ���� ������ �������� ������, �ּ� 10���� �̻� ������ ����� �ӵ� ���̰� �� 10�� �̻� �߻���
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("rank", 1), "rankIdx");
		
		res = true;
		
		log.info(this.getClass().getName() + " .createCollection end");
		
		return res;
	}

	@Override
	public int insertRank(List<MelonDTO> pList, String colNm) throws Exception {

		log.info(this.getClass().getName() + " .insertRank start");
		
		int res = 0;
		
		if(pList == null) {
			
			pList = new ArrayList<MelonDTO>();
			
		}
		
		Iterator<MelonDTO> it = pList.iterator();
		
		while(it.hasNext()) {
			
			MelonDTO pDTO = (MelonDTO) it.next();
			
			if(pDTO == null) {
				
				pDTO = new MelonDTO();
				
			}
			
			mongodb.insert(pDTO, colNm);
			
		}
		
		res = 1;
		
		log.info(this.getClass().getName() + " .insertRank end");
		
		return res;
		
	}

	@Override
	public List<MelonDTO> getRank(String colNm) throws Exception {

		log.info(this.getClass().getName() + " .getRank start");
		
		//�����͸� ������ �÷��� ����
		DBCollection rCol = mongodb.getCollection(colNm);
		
		//�÷������κ��� ��ü ������ ��������
		Iterator<DBObject> cursor = rCol.find();
		
		//�÷������κ��� ��ü ������ ������ ���� List ���·� �����ϱ� ���� ���� ����
		List<MelonDTO> rList = new ArrayList<MelonDTO>();
		
		MelonDTO rDTO = null;
		
		while(cursor.hasNext()) {
			
			rDTO = new MelonDTO();
			
			final DBObject current = cursor.next();
			
			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); //�����ð�
			String rank = CmmUtil.nvl((String) current.get("rank")); //����
			String song = CmmUtil.nvl((String) current.get("song")); //�뷡����
			String singer = CmmUtil.nvl((String) current.get("singer")); //����
			String album = CmmUtil.nvl((String) current.get("album")); //�ٹ�
			
			log.info("song:"+song);
			log.info("singer:"+singer);
			log.info("album:"+album);
			
			
			rDTO.setCollect_time(collect_time);
			rDTO.setRank(rank);
			rDTO.setSong(song);
			rDTO.setSinger(singer);
			rDTO.setAlbum(album);
			
			rList.add(rDTO); //List�� ������ ����
			
			rDTO = null;
			
		}
		
		log.info(this.getClass().getName() + " .getRank end");
		
		return rList;
	}

}

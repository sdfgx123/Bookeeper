package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import poly.dto.MelonDTO;
import poly.persistance.mongo.IMelonMapper;

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
		
		log.info(this.getClass().getName() + " .insertRank start");
		
		return res;
	}

}

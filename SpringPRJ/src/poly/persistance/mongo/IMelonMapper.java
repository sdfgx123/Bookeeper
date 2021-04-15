package poly.persistance.mongo;

import java.util.List;

import poly.dto.MelonDTO;

public interface IMelonMapper {

	public boolean createCollection(String colNm) throws Exception;
	
	public int insertRank(List<MelonDTO> pList, String colNm) throws Exception;
	
	/**
	 * MongoDB 멜론 데이터 가져오기
	 * 
	 * @param colNm 가져올 컬렉션 이름
	 */
	public List<MelonDTO> getRank(String colNm) throws Exception;
}

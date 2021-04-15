package poly.persistance.mongo;

import java.util.List;

import poly.dto.MelonDTO;

public interface IMelonMapper {

	public boolean createCollection(String colNm) throws Exception;
	
	public int insertRank(List<MelonDTO> pList, String colNm) throws Exception;
	
}

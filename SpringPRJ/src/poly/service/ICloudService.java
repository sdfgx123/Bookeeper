package poly.service;

import java.util.List;

import poly.dto.CloudDTO;
import poly.dto.MelonDTO;

public interface ICloudService {

	// 교보문고 Top100 수집
	public void collectBookRank() throws Exception;
	
	/**
     * MongoDB 책 제목 데이터 가져오기
     */
    public List<CloudDTO> getTitle() throws Exception;
}

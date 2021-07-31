package poly.service;

import poly.dto.OcrDTO;

public interface IOcrService {

	// 이미지 파일로부터 문자 읽어오기
	OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception;
	
	void insertOcrInfo(OcrDTO pDTO) throws Exception;
}

package poly.service;

import java.util.List;

import poly.dto.LibDTO;

public interface ILibService {

	/**
     * MongoDB 멜론 데이터 가져오기
     */
    public List<LibDTO> getBookInfo(String id) throws Exception;
    
    /**
     * MongoDB _id 근거 책 데이터 호출
     */
    public List<LibDTO> getBookDetail(String id, String _id) throws Exception;
    
}

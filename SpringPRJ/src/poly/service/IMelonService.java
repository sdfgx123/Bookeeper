package poly.service;

import java.util.List;
import java.util.Map;

import poly.dto.MelonDTO;
import poly.dto.MelonSingerDTO;
import poly.dto.MelonSongDTO;

public interface IMelonService {

	// 멜론 Top100 순위 수집
	public void collectMelonRank() throws Exception;
	
	/**
     * MongoDB 멜론 데이터 가져오기
     */
    public List<MelonDTO> getRank() throws Exception;
    
    /**
     * MongoDB 가수의 노래 데이터 가져오기
     */
    public List<MelonSongDTO> getSongForSinger() throws Exception;
    
    /**
     * MongoDB 가수별 멜론 랭킹에 많이 등록된 순서대로 가져오기
     */
    public List<MelonSingerDTO> getRankForSinger() throws Exception;

}
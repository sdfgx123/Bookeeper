package poly.service;

import java.util.List;

import poly.dto.MelonDTO;
import poly.dto.MelonSingerDTO;
import poly.dto.MelonSongDTO;

public interface IMelonService {

	/**
	 * ��� Top 100 ���� �����ϱ�
	 */
	
	public int collectMelonRank() throws Exception;

	// MongoDB 멜론 데이터 가져오기
	public List<MelonDTO> getRank() throws Exception;
	
	/**
	 * MongoDB 가수의 노래 데이터 가져오기
	 */
	public List<MelonSongDTO> getSongForSinger() throws Exception;
	
	// 가수별 멜론 랭킹에 많이 등록된 순서대로 가져오기
	public List<MelonSingerDTO> getRankForSinger() throws Exception;
}

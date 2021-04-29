package poly.dto;

// 본 클래스의 목적은 수집된 멜론 TOP100 정보 중 방탄소년단 노래만 조회하기 위함임.
public class MelonSongDTO {

	private String song; // 노래제목
	private String rank; // 현재 랭킹
	
	public String getSong() {
		return song;
	}
	public void setSong(String song) {
		this.song = song;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}

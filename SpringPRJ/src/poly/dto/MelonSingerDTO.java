package poly.dto;

public class MelonSingerDTO {

	private int rank;
	private int song_cnt; // 링크에 올라간 노래의 수
	private String singer;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getSong_cnt() {
		return song_cnt;
	}
	public void setSong_cnt(int song_cnt) {
		this.song_cnt = song_cnt;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
}

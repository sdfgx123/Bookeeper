package poly.util;

import java.io.File;

public class FileUtil {

	/**
	 * 현재 날짜 기준 연월일 폴더 생성
	 * @param 파일 저장되는 root 폴더
	 * @return 파일이 저장되기 위해 생성된 전체 폴더 경로
	 */
	public static String mkdirForDate(String uploadDir) {
		// 폴더 경로
		String path = uploadDir + DateUtil.getDateTime("/yyyy/MM//dd");
		File Folder = new File(path);
		if (!Folder.exists()) {
			// 폴더 생성
			Folder.mkdirs();
		}
		return path;
	}
}

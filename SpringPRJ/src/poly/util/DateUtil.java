package poly.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * ��¥,�ð� ���
	 * 
	 * @param fm ��¥ ��� ����
	 * @return
	 */
	public static String getDateTime(String fm) {

		Date today = new Date();
		System.out.println(today);

		SimpleDateFormat date = new SimpleDateFormat(fm);

		return date.format(today);
	}

	/**
	 * ��¥, �ð� ���
	 * 
	 * @return �⺻���� ��-��-��
	 */
	public static String getDateTime() {
		return getDateTime("yyyy-MM-dd");
	}
}
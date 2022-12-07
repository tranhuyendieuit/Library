package library.common;

public class Validation {

	public static boolean isEmpty(String str) {

		return str != null && !Constant.BLANK.equals(str);
	}

}

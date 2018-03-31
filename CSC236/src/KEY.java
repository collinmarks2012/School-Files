public class KEY {

	public static void main(String[] args) {
		try {
			int i = 0;
			while ((i!='.') && (i!=-1)) {
				i = System.in.read();
				if (smallCap(i)) {
					i = i - 32;
					printer(i);
				} else if (bigCap(i)) {
					printer(i);
				} else if (i == ' ') {
						printer(i);
				} else if (i == '.') {
						printer(i);
				}
			}
			
		} catch (Exception e) {
			System.out.println(" Exception: " + e);
		}
	}

	private static void printer(int i) {
		System.out.print((char) i);
	}

	private static boolean bigCap(int c) {
		if (c >= 'A' && c <='Z') {
			return true;
		}
		return false;
	}

	private static boolean smallCap(int c) {
		if (c >= 'a' && c <='z') {
			return true;
		}
		return false;
	}
}

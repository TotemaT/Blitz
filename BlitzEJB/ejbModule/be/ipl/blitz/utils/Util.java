package be.ipl.blitz.utils;

public final class Util {
	public static void checkObject(Object o) {
		if (o == null)
			throw new NullPointerException();
	}

	public static void checkString(String s) {
		checkObject(s);
		if (s.trim().equals(""))
			throw new IllegalArgumentException("STRING EST NUUUUUULL");
	}

	public static void checkNegativeOrZero(double d) {
		if (d > 0.0)
			throw new IllegalArgumentException();
	}

	public static void checkPositiveOrZero(double d) {
		if (d < 0)
			throw new IllegalArgumentException();
	}

	public static void checkPositive(double d) {
		if (d <= 0.00001)
			throw new IllegalArgumentException();
	}

	public static void checkPositive(int i) {
		if (i <= 0)
			throw new IllegalArgumentException();
	}
	
	public static int modulo(int x, int y) {
		return ((x % y) + y) % y;
	}
}

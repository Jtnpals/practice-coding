package com.urline.dao;

import java.nio.charset.StandardCharsets;

public class Util {

	public static int parseInt(String l) {
		try {
			int r = Integer.parseInt(l);
			return r;
		} catch (Exception e) {
			return -1;
		}
	}

	public static String hangleEncoding(String l) {
		if (l == null || l.equals(""))
			return l;
		try {
			byte[] bs = "8859_1".getBytes(l);
			l = new String(bs, StandardCharsets.UTF_8);
		} catch (Exception e) {

		}
		return l;
	}
}

package dev.unit.obab.util;

import java.util.Random;

public final class RandomUtils {

	public static final int INVITE_CODE_LENGTH = 6;
	private static final Random random = new Random();

	public static String createInviteCode(){
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<INVITE_CODE_LENGTH ; ++i){
			builder.append(random.nextInt(10));
		}

		return builder.toString();
	}

}

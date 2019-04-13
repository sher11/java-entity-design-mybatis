package com.sharebooks.mybatis.customtypehandlers;

import java.nio.ByteBuffer;
import java.util.UUID;

public abstract class UUIDHelper {

	public static UUID fromBytes(byte[] bytes) {
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		long firstLong = bb.getLong();
		long secondLong = bb.getLong();
		return new UUID(firstLong, secondLong);
	}

	
	public static byte[] toBytes(UUID uuid) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		return bb.array();
	}

	
}
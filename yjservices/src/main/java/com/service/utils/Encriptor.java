package com.service.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;

public class Encriptor {
	private static Logger logger = Logger.getLogger(Encriptor.class);
	private static final int BASELENGTH = 255;
	private static final int LOOKUPLENGTH = 64;
	private static final int TWENTYFOURBITGROUP = 24;
	private static final int EIGHTBIT = 8;
	private static final int SIXTEENBIT = 16;
	private static final int SIGN = -128;
	private static final byte PAD = 61;
	private static byte[] base64Alphabet = new byte['每'];
	private static byte[] lookUpBase64Alphabet = new byte[64];

	public static String getMD5(String plainText) {
		String md5 = null;
		try {
			if (plainText != null) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(plainText.getBytes());
				byte[] b = md.digest();

				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					int i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				md5 = buf.toString();
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		}
		return md5;
	}

	public static String getCftMD5(String plainText) {
		String md5 = null;
		try {
			if (plainText != null) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(plainText.getBytes());
				byte[] b = md.digest();

				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					int i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				md5 = buf.toString();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static byte[] encodeBase64(byte[] binaryData) {
		int lengthDataBits = binaryData.length * 8;
		int fewerThan24bits = lengthDataBits % 24;
		int numberTriplets = lengthDataBits / 24;
		byte[] encodedData = null;

		if (fewerThan24bits != 0) {
			encodedData = new byte[(numberTriplets + 1) * 4];
		} else {
			encodedData = new byte[numberTriplets * 4];
		}

		byte k = 0;
		byte l = 0;
		byte b1 = 0;
		byte b2 = 0;
		byte b3 = 0;

		int encodedIndex = 0;
		int dataIndex = 0;
		int i = 0;

		for (i = 0; i < numberTriplets; i++) {
			dataIndex = i * 3;
			b1 = binaryData[dataIndex];
			b2 = binaryData[(dataIndex + 1)];
			b3 = binaryData[(dataIndex + 2)];

			l = (byte) (b2 & 0xF);
			k = (byte) (b1 & 0x3);

			encodedIndex = i * 4;
			byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 0xC0);
			byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte) (b2 >> 4) : (byte) (b2 >> 4 ^ 0xF0);
			byte val3 = (b3 & 0xFFFFFF80) == 0 ? (byte) (b3 >> 6) : (byte) (b3 >> 6 ^ 0xFC);

			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];

			encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | k << 4)];
			encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(l << 2 | val3)];
			encodedData[(encodedIndex + 3)] = lookUpBase64Alphabet[(b3 & 0x3F)];
		}

		dataIndex = i * 3;
		encodedIndex = i * 4;
		if (fewerThan24bits == 8) {
			b1 = binaryData[dataIndex];
			k = (byte) (b1 & 0x3);

			byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 0xC0);
			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
			encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(k << 4)];
			encodedData[(encodedIndex + 2)] = 61;
			encodedData[(encodedIndex + 3)] = 61;
		} else if (fewerThan24bits == 16) {
			b1 = binaryData[dataIndex];
			b2 = binaryData[(dataIndex + 1)];
			l = (byte) (b2 & 0xF);
			k = (byte) (b1 & 0x3);

			byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 0xC0);
			byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte) (b2 >> 4) : (byte) (b2 >> 4 ^ 0xF0);

			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
			encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | k << 4)];
			encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(l << 2)];
			encodedData[(encodedIndex + 3)] = 61;
		}

		return encodedData;
	}

	private static Key getDesKey(byte[] keyByte) {
		Key key = null;

		byte[] byteTemp = new byte[8];

		for (int i = 0; (i < byteTemp.length) && (i < keyByte.length); i++) {
			byteTemp[i] = keyByte[i];
		}
		key = new SecretKeySpec(byteTemp, "DES");
		return key;
	}

	public Key getDesKey2() {
		Key key = null;

		SecureRandom sr = new SecureRandom();
		try {
			KeyGenerator kg = KeyGenerator.getInstance("DES");

			kg.init(sr);

			key = kg.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return key;
	}

	public static String encriptDES(String keystr, String srcCode) {
		Key key = getDesKey(keystr.getBytes());
		StringBuffer sb = null;
		try {
			Cipher encriptCipher = Cipher.getInstance("DES");

			encriptCipher.init(1, key);

			byte[] desCode = encriptCipher.doFinal(srcCode.getBytes());

			sb = new StringBuffer(desCode.length * 2);
			for (int i = 0; i < desCode.length; i++) {
				int temp = desCode[i];

				if (temp < 0) {
					temp += 256;
				}

				if (temp < 16) {
					sb.append("0");
				}
				sb.append(Integer.toString(temp, 16));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String decriptDES(String encriptCode, String keystr) {
		if ((encriptCode == null) || (encriptCode.trim().length() == 0) || (keystr == null) || (keystr.length() == 0))
			return null;
		Key key = getDesKey(keystr.getBytes());
		Cipher decriptCipher = null;
		String decriptString = null;
		byte[] encriptByte = encriptCode.getBytes();

		byte[] decriptByte = new byte[encriptByte.length / 2];
		for (int i = 0; i < encriptByte.length; i += 2) {
			String strTmp = new String(encriptByte, i, 2);
			decriptByte[(i / 2)] = (byte) Integer.parseInt(strTmp, 16);
		}
		try {
			decriptCipher = Cipher.getInstance("DES");
			decriptCipher.init(2, key);

			byte[] outByte = decriptCipher.doFinal(decriptByte);
			decriptString = new String(outByte);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return decriptString;
	}

	static {
		for (int i = 0; i < 255; i++) {
			base64Alphabet[i] = -1;
		}
		for (int i = 90; i >= 65; i--) {
			base64Alphabet[i] = (byte) (i - 65);
		}
		for (int i = 122; i >= 97; i--) {
			base64Alphabet[i] = (byte) (i - 97 + 26);
		}
		for (int i = 57; i >= 48; i--) {
			base64Alphabet[i] = (byte) (i - 48 + 52);
		}

		base64Alphabet[43] = 62;
		base64Alphabet[47] = 63;

		for (int i = 0; i <= 25; i++) {
			lookUpBase64Alphabet[i] = (byte) (65 + i);
		}
		int i = 26;
		for (int j = 0; i <= 51; j++) {
			lookUpBase64Alphabet[i] = (byte) (97 + j);

			i++;
		}

		i = 52;
		for (int j = 0; i <= 61; j++) {
			lookUpBase64Alphabet[i] = (byte) (48 + j);

			i++;
		}

		lookUpBase64Alphabet[62] = 43;
		lookUpBase64Alphabet[63] = 47;
	}
}

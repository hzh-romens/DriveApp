package com.example.ausu.erpapp.utils;

import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public class Misc {
	
    private static final String ENUM_UNKNOWN_STR = "UNKNOWN";
    public static final int FILE_SLICE_LEN = 256 * 1024;

    private static char hexChar[] = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    
    private static String unvisibleCharPatern =
        "[\u0000 - \u0008, \u000c - \u001f]";
    
    // replaced with TextUtils.empty
//    public static boolean emptyString(String s) {
//        if (s == null || s.equals(""))
//            return true;
//        else
//            return false;
//    }
    
    public static boolean invalidEnum(Object obj) {
        if (obj == null)
            return true;
        
        if (obj instanceof Enum) {
            if (!obj.toString().equals(ENUM_UNKNOWN_STR))
                return false;
            else
                return true;
        }
        
        return true;
    }
    
    public static String replaceBlank(String origin, String replace) {
        if (TextUtils.isEmpty(origin))
            return origin;
        /*
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(origin);
        return m.replaceAll(replace);
        */
        
        return origin.replaceAll("\\s*|\t|\r|\n", replace);
    }
    
    public static String replaceBlank(String origin) {
        return replaceBlank(origin, "_");
    }
    
    public static String removeBlank(String origin) {
        return replaceBlank(origin, "");
    }
    
    public static String getFileMD5(String filePath) {
        InputStream fis = null;
        String md5 = null;

        try {
            fis = new FileInputStream(filePath);
            byte[] buffer = new byte[FILE_SLICE_LEN];
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, numRead);
            }
            fis.close();
            md5 = toHexString(messagedigest.digest());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return md5;
    }
    
    // get file 256k md5 value
    public static String getSliceMD5(String fileName, int sliceLength) {
        String md5 = null;
        InputStream fis = null;
        MessageDigest messagedigest;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(fileName);
            byte[] buffer = new byte[sliceLength];
            int numRead = fis.read(buffer);
            fis.close();
            if (numRead > 0) {
                messagedigest.update(buffer, 0, numRead);
                md5 = toHexString(messagedigest.digest());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }

        return md5;
    }
    
    public static long getCrc32(String filePath) {

        long checksum = 0;
        try {

            CheckedInputStream cis = null;
            try {
                // Computer CRC32 checksum
                cis = new CheckedInputStream(new FileInputStream(filePath),
                        new CRC32());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            byte[] buf = new byte[128];
            while (cis.read(buf) != -1);
            checksum = cis.getChecksum().getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return checksum;
    }
    
    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    // 
    /**
	 * 璁＄畻瀛楃涓茬殑MD5鍊硷紝榛樿缂栫爜涓篣TF-8
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getTextMD5(String str) {
		return getTextMD5(str, "UTF-8");
	}

	/**
	 * 璁＄畻瀛楃涓茬殑MD5鍊�
	 * 
	 * @param str
	 * @param charset
	 *            缂栫爜鏍煎紡
	 * @return
	 */
	public static byte[] getTextMD5(String str, String charset) {
		if (TextUtils.isEmpty(str)) {
			return null;
		}
		try {
			return getBytesMD5(str.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/**
	 * 璁＄畻MD5鍊�
	 * 
	 * @param buffer
	 * @return
	 */
	public static byte[] getBytesMD5(byte[] buffer) {
		MessageDigest digester;
		try {
			digester = MessageDigest.getInstance("MD5");
			digester.reset();
			digester.update(buffer);
			return digester.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * 灏嗗瓧鑺傛暟缁勮浆鎹㈡垚16杩涘埗琛ㄨ揪鐨勫瓧绗︿覆
	 * 
	 * @param bytes
	 * @return
	 */
	public final static String toHex(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			String str = Integer.toHexString(0xFF & b); // SUPPRESS CHECKSTYLE
			str = str.toUpperCase();
			if (str.length() == 1) {
				hexString.append("0");
			}
			hexString.append(str);
		}
		return hexString.toString();
	}
	
	/**
	 * md5鍔犲瘑
	 * @param str
	 * @return
	 */
	public static String getMd5Str(String str){
		byte[] bytes = getBytesMD5(str.getBytes());
		return toHex(bytes);
	}
	
}

package com.liqun.web;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MessageEncoder {
	
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private String publicSalt;

	public void setPublicSalt(String publicSalt) {
		this.publicSalt = publicSalt;
	}

	public String getPublicSalt() {
		return publicSalt;
	}
	
	public byte[] sha256(byte[] content) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256","BC");
			md.update(content);
			byte[] digest = md.digest();
			md.reset();
			return digest;
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}
	public String hashPlainCredentials(String plainCredentials) {
			
		String withsalt = publicSalt + plainCredentials;

//		System.out.println("credentialsWithPublicSalt= " + credentialsWithPublicSalt);
		// rbBUYhjC/HmIevbvs2s0g123456
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256","BC");
			md.update(withsalt.getBytes());
			byte[] digest = md.digest();
			md.reset();
			return Base64.encodeBase64String(digest);
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}

//		System.out.println("secretSha256= " + secretSha256);
		// qRsaYpPO1PjZd9t1gBuDBxGIhP+o08bk2pfPKmZsmbQ=
		
		
	}

	public IvParameterSpec generateRandomIV() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES","BC");
			kg.init(128);
			SecretKey sk = kg.generateKey();
			byte[] b = sk.getEncoded();
			return new IvParameterSpec(b);
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}

	public SecretKey generateAes128Key() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES","BC");
			kg.init(128);
			return kg.generateKey();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}
	
	public SecretKey generateAes256Key() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES","BC");
			kg.init(256);
			return kg.generateKey();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}
	
	public SecretKeySpec getAesSecretKey(byte[] secretKeySpecBytes) {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeySpecBytes, "AES");
		return secretKeySpec;
	}
	
	public IvParameterSpec getIV(byte[] ivParameterSpecBytes) {
		
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameterSpecBytes);
		return ivParameterSpec;
	}
	
	
	/**
	 * AES解密。
	 * 如果报异常说invalid key size， 大概是因为jdk没有安装jce_policy
	 * 
	 * @param content
	 * @param secretKey
	 * @param iv
	 * @return
	 */
	public byte[] aesEncrypt(byte[] content, SecretKey secretKey, IvParameterSpec iv) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			return cipher.doFinal(content);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public byte[] aesDecrypt(byte[] encryptBytes, SecretKey secretKey, IvParameterSpec iv) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			return cipher.doFinal(encryptBytes);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public KeyPair generateRsa1024KeyPair() {  
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA","BC");
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}

	public KeyPair generateRsa2048KeyPair() {  
		KeyPairGenerator keyPairGen;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA","BC");
			keyPairGen.initialize(2048);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}

	public RSAPrivateKey getRsaPrivateKey(byte[] privateKeyBytes) {
		try {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			KeyFactory keyFactory;
			keyFactory = KeyFactory.getInstance("RSA","BC");
			RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
			return privateKey;
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}
	
	public RSAPublicKey getRsaPublicKey(byte[] publicKeyBytes) {
		try {
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
			KeyFactory keyFactory;
			keyFactory = KeyFactory.getInstance("RSA","BC");
			RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] sign(byte[] content, PrivateKey privateKey) {
		try {
			Signature signature = Signature.getInstance("SHA256withRSA", "BC");
			signature.initSign(privateKey);
			signature.update(content);
			return signature.sign();
		} catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException
				| InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean verifySignature(byte[] content, PublicKey publicKey, byte[] sign) {
		try {
			Signature signature = Signature.getInstance("SHA256withRSA", "BC");
			signature.initVerify(publicKey);
			signature.update(content);
			return signature.verify(sign);
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException
				| InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public byte[] rsaEncryptByPublicKey(byte[] content, PublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding", "BC");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(content);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] rsaDecryptByPrivateKey(byte[] encrpted, PrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(encrpted);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| InvalidKeyException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * RSA最大加密明文大小
	 */
	public static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	public static final int MAX_DECRYPT_BLOCK = 128;
	
	/**
	 * 超长内容加密
	 * @param data
	 * @param key
	 * @return
	 */
	public byte[] rsaEncrypt2(byte[] data, Key key) {
		return null;
	}

	public String toQueryString (Map<String, Object> params) {
		
		List<NameValuePair> pairs = new LinkedList<>();
		params.forEach((k, v) ->{
			
			String vstring;
			
			if (v instanceof byte[]) {
				vstring = Base64.encodeBase64String((byte[])v);
			} else {
				vstring = ""+v;
			}
			pairs.add(new BasicNameValuePair(k, vstring));
		});
		
		return URLEncodedUtils.format(pairs, "utf-8");
	}
	
	
}

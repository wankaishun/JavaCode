package com.liqun.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	private static LinkedList<Document> member_list = new LinkedList<>();
	private static AtomicLong member_id_sequence = new AtomicLong(10086L);
	
	static {
		member_list.addAll( Arrays.asList(
				
			new Document("_id", member_id_sequence.getAndIncrement())
			.append("username", "mike")
			.append("slogan", "Have a nice day!")
			.append("password", "$2a$10$ER6M4ihxasGLJr6tX4c.fuQ31Ixhb3bmbkAWUVi9PEuDLM1Qmx.fe")
			.append("roles", Arrays.asList("user","admin"))
			.append("acct_bindings", Arrays.asList(
					
					new Document()
					.append("_id", new Document("ch", "ftpc").append("uid", "FTPC000000086"))
					.append("access_token", "")
					.append("access_token_expire", new Date())
					
			)),
			
			new Document("_id", member_id_sequence.getAndIncrement())
			.append("username", "bigcat")
			.append("slogan", "The quick brown fox jumps over the lazy dog.")
			.append("password", "$2a$10$eitH/NPj0ua5Uw19HkYPZ.Srj.VndLzC7ISO5PH6tCCSbtfraObUG")
			.append("roles", Arrays.asList("user"))
			.append("acct_bindings", Arrays.asList(
					
					new Document()
					.append("_id", new Document("ch", "ftpc").append("uid", "FTPC000000087"))
					.append("nickname", "FFF")
					.append("avatar", "")
					.append("access_token", "")
					.append("access_token_expire", new Date())
					
			)),
			
			new Document("_id", 7287787423846884238L)
			.append("username", "bigFish")
			.append("slogan", "Welcome to Sydney Fish Market.")
			.append("password", "$2a$10$luYAz8yBEfj3dn6Il3cT3u5z5jC/.OSAXZ6iCW7ZDyyrne5zdePye")
			.append("roles", Arrays.asList("user"))
				
		));

	}
	
	public Document findMemberByUsername(String username) {
//		System.out.println("find member by username:" + username);
		for (Document member : member_list) {
			if (member.containsKey("username") && member.get("username").equals(username)) {
				return member;
			}
		}
		return null;
	}
	
	public Document findMemberById(long uid) {
//		System.out.println("find member by id:" + uid);
		for (Document member : member_list) {
			if (member.containsKey("_id") && member.get("_id").equals(uid)) {
				return member;
			}
		}
		return null;
	}

	public Document findMemberByChannelUid(String channel, String channel_uid) {
		
		Document idnetifier = new Document("ch", channel).append("uid", channel_uid);
//		System.out.println("find member by channel uid : "+ idnetifier);
		
		for (Document member : member_list) {
			if (member.containsKey("acct_bindings") ) {
				@SuppressWarnings("unchecked")
				List<Document> acct_bindings = (List<Document>) member.get("acct_bindings");
				
				for (Document acct_binding : acct_bindings) {
					if (acct_binding.get("_id").equals(idnetifier)) {
						return member;
					}
				}
			}
		}
		return null;
	}
	
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		
		System.out.println("salt="+BCrypt.gensalt());
		
		genPassword();
		genPassword();
		genPassword();
		genPassword();
		genPassword();
		genPassword();
		
	}
	
	public static void genPassword() throws NoSuchAlgorithmException {
		
		String credentials = "123456";
		
		
		MessageDigest md = MessageDigest.getInstance("SHA");

		md.update(credentials.getBytes());
		byte[] digest = md.digest();
		md.reset();

		String secretSha256 = Base64.encodeBase64String(digest);
		System.out.println(secretSha256);
		//fEqNCco3Yq9h5ZUglD3CZJT4lBs=
		
		String hashed = BCrypt.hashpw(secretSha256, BCrypt.gensalt());
		System.out.println(hashed);
		
		
		
//		String hashAlgorithmName = "SHA-256";
//		SimpleHash result = new SimpleHash(hashAlgorithmName, credentials);
//		String hashPassword = Base64.getEncoder().encodeToString(result.getBytes());
//		String hashPassword = credentials;
//		System.out.println(hashPassword);
		
//		String hashed = BCrypt.hashpw(hashPassword, BCrypt.gensalt());
//		
//		System.out.println(hashed);
		
	}
}

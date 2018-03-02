package com.liqun.web;

import java.util.Date;

/**
 * 渠道用戶的信息
 * 
 * @author Michelangelo
 *
 */
public class ChannelAccountInfo {

	String channel;
	String channel_uid;
	String nickname;
	String slogan;
	String avatar;
	String access_token;
	Date access_token_expire;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannel_uid() {
		return channel_uid;
	}

	public void setChannel_uid(String channel_uid) {
		this.channel_uid = channel_uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Date getAccess_token_expire() {
		return access_token_expire;
	}

	public void setAccess_token_expire(Date access_token_expire) {
		this.access_token_expire = access_token_expire;
	}


}

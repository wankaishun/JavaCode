package com.liqun.web;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * 渠道账户的认证token，实质是channel + channel_uid
 * 
 * @author Michelangelo
 *
 */
public class ChannelAccountInfoToken implements AuthenticationToken {

	private static final long serialVersionUID = 1L;

	ChannelAccountInfo channelAcctInfo;

	public ChannelAccountInfoToken(ChannelAccountInfo channelAcctInfo) {
		this.channelAcctInfo = channelAcctInfo;
	}

	@Override
	public Object getPrincipal() {
		return "ch=" + channelAcctInfo.getChannel() + ",uid=" + channelAcctInfo.getChannel_uid();
	}

	@Override
	public Object getCredentials() {
		return channelAcctInfo;
	}

	public ChannelAccountInfo getChannelAcctInfo() {
		return channelAcctInfo;
	}
	
	

}

package com.liqun.web;

import org.apache.shiro.authc.AccountException;

/**
 * 已被渠道认可的账户未绑定本站用户。
 * @author Michelangelo
 *
 */
public class ChannelAccountUnbindedException extends AccountException {

	private static final long serialVersionUID = 1L;
	
	ChannelAccountInfo channelAcctInfo;

	public ChannelAccountUnbindedException(String message, ChannelAccountInfo channelAcctInfo) {
		super(message);
		this.channelAcctInfo = channelAcctInfo;
	}

	public ChannelAccountInfo getChannelAcctInfo() {
		return channelAcctInfo;
	}

}

package com.gdmc.trivia.android.util;

import android.nfc.cardemulation.HostApduService;

public class ServiceUtils {
	public static final String HostServicer = "https://gdmcmm-jqmobile.rhcloud.com";  //主页
	public static final String urlLogin = HostServicer + "/?json=users/login&dev=1";  //登录
	public static final String urlGetNonce = HostServicer + "/?json=get_nonce&dev=1&" +
			"controller=Users&method=create_user";  //获得令牌
	public static final String urlRegister = HostServicer + "/?json=users/create_user&dev=1" +
			"&&u=admin&p=gdmcmm";  //注册
	public static final String urlALLSecret = HostServicer + "/?json=get_posts&dev=1"; //全部秘密
}

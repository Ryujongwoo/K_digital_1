package com.tjoeun.springNaverLoginAPI.oauth;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

@Component
public class NaverLoginBO {
	
//	네이버 로그인 API 등록시 발급받은 Client ID, Client Secret과 Callback URL을 입력한다.
	private final static String CLIENT_ID = "cti6_Olz7XB49U08_lTK";
	private final static String CLIENT_SECRET = "26xxFUL2T4";
	private final static String REDIRECT_URI = "http://localhost:9090/springNaverLoginAPI/callback";
	
	private final static String SESSION_STATE = "oauth_state";
//	프로필 조회 API URL
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";

//	네이버 아이디로 인증 URL 생성 Method
	public String getAuthorizationUrl(HttpSession session) {
//		세션 유효성 검증을 위하여 난수를 생성한다.
		String state = generateRandomString();
//		생성한 난수 값을 session에 저장한다.
		setSession(session, state);
//		Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL을 생성한다.
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI).state(state) // 앞서 생성한 난수값을 인증 URL 생성시 사용한다.
				.build(NaverLoginApi.instance());
		return oauthService.getAuthorizationUrl();
	}

//	네이버 아이디로 Callback 처리 및 AccessToken 획득 Method
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
//		Callback으로 전달받은 세선 검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인한다.
		String sessionState = getSession(session);
		if (StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI).state(state).build(NaverLoginApi.instance());
//			Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득한다.
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

//	세션 유효성 검증을 위한 난수 생성기
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}

//	http session에 데이터 저장
	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}

//	http session에서 데이터 가져오기
	private String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}

//	Access Token을 이용하여 네이버 사용자 프로필 API를 호출
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI).build(NaverLoginApi.instance());
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}

}
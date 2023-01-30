package com.tjoeun.springNaverLoginAPI;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.tjoeun.springNaverLoginAPI.oauth.NaverLoginBO;

@Controller
public class LoginController {
	
//	NaverLoginBO
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

//	로그인 첫 화면 요청 메소드
	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
//		네이버 아이디로 인증 URL을 생성하기 위하여 naverLoginBO 클래스의 getAuthorizationUrl 메소드 호출
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		System.out.println("네이버: " + naverAuthUrl);
		model.addAttribute("url", naverAuthUrl);
		return "login";
	}

//	네이버 로그인 성공시 callback 호출 메소드
	@RequestMapping("/callback")
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {
		System.out.println("---------네이버 로그인 callback -----------");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
//		로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String 형식의 json 데이터
		System.out.println("apiResult: " + apiResult);

//		String 형식인 apiResult를 json 형태로 바꾼다.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
//		데이터 파싱
//		Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
//		nickname 파싱
//		String nickname = (String)response_obj.get("nickname");
		System.out.println("reponse_obj: " + response_obj);

//		파싱 값 세션에 저장
		session.setAttribute("sessionId",response_obj); //세션 생성
		model.addAttribute("result", apiResult);
		return "naverSuccess";
	}
	
//	로그아웃 메소드
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws IOException {
		System.out.println("네이버 logout");
		session.invalidate();
		return "redirect:login";
	}
	
}

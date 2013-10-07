package org.uengine.codi.platform;

import org.uengine.kernel.GlobalContext;

import com.daou.BizSend;
import com.daou.entity.SendMsgEntity;


public class SMS {
	
	static int balance = 100000;
	
	public void sendMessage(String phoneNumber, String message, String senderName, String receiverName){
		
		BizSend bs = new BizSend();
		SendMsgEntity sme = null;
		
		/* Console 로그 설정 */
		// Console 에서 로그를 확인할 경우 설정
		bs.setLogEnabled(true);

		/* 서버 연결 시작 & 인증 */
		// ip   = biz.ppurio.com = 211.189.43.25
		// port = 5000 / 15001
		
		String smsServer = GlobalContext.getPropertyString("sms.server", "biz.ppurio.com");
		String smsPort = GlobalContext.getPropertyString("sms.port", "5000");
		String smsUser = GlobalContext.getPropertyString("sms.user", "uengine");
		String smsPwd = GlobalContext.getPropertyString("sms.pwd", "18925ung");
		//bs.doBegin("biz.ppurio.com", 5000, "uengine", "18925ung");
		bs.doBegin(smsServer, Integer.parseInt(smsPort), smsUser, smsPwd);
		/* 메시지 정의 */
		// 다음의 setter 를 사용하여 필요한 정보를 정의
		// ex. SMS 의 경우, MSG_TYPE, DEST_PHONE, SEND_PHONE, MSG_BODY 정보를 정의
		sme = new SendMsgEntity();
		//sme.setCMID      ("");    // 데이터 id
		sme.setMSG_TYPE  (0 );    // 데이터 타입 (SMS 0 / WAP 1 / FAX 2 / PHONE 3 / SMS_INBOUND 4 / MMS 5)
		//sme.setSEND_TIME ("");    // 발송 (예약) 시간 (Unix Time, 정의하지 않을 경우 즉시 발송)
		sme.setDEST_PHONE(phoneNumber);    // 받는 사람 전화 번호
		sme.setDEST_NAME (receiverName);    // 받는 사람 이름
		//sme.setSEND_PHONE("025943345");    // 보내는 사람 전화 번호
		sme.setSEND_NAME (senderName);    // 보내는 사람 이름
		//sme.setSUBJECT   ("");    // (FAX/MMS) 제목, (SMS_INBOUND) 데이터 내용
		sme.setMSG_BODY  (message);    // 데이터 내용
		//sme.setWAP_URL   ("");    // (WAP) URL 주소
		//sme.setCOVER_FLAG(0 );    // (FAX) 표지 발송 옵션
		//sme.setSMS_FLAG  (0 );    // (PHONE) PHONE 실패 시 문자 전송 옵션
		//sme.setREPLY_FLAG(0 );    // (PHONE) 응답 받기 선택
		//sme.setRETRY_CNT (0 );    // (FAX/PHONE) 재시도 회수 (5~10분 간격: 최대 3회)
		//sme.setFAX_FILE  ("");    // (PHONE/FAX/MMS) 파일 전송시 파일 이름
		//sme.setVXML_FILE ("");    // (PHONE) 음성 시나리오 파일 이름

		/* 메시지 전송 */
		try {
			String msgid = "";
			msgid = bs.sendMsg(sme);    // @param  sme       SendMsgEntity
			                            // @retrun String    다우기술의 서버에서 정의한 message id

			System.out.println("msgid=" + msgid);
		} catch (Exception ex) {
			System.out.println("Failed to Send a Msg" +
					", " + ex.getMessage());
		}

		/* 서버 연결 종료 */
		bs.doEnd();
		
		Console.addLog("메시지가 정상적으로 발송되었습니다: message=" + message + ", toWhom=" + phoneNumber);
		Console.addLog("API호출 잔고는 " + balance-- + " 회 입니다.");

		
	}

}

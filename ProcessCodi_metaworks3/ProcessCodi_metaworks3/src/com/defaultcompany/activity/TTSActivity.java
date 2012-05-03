package com.defaultcompany.activity;

import org.uengine.contexts.KeyValueContext;
import org.uengine.contexts.SoundUrlContext;
import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.ExtSoundPlayActivity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessInstance;

public class TTSActivity extends DefaultActivity {
	
	private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;
	
	private String content;
	private String delemeter;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDelemeter() {
		return delemeter;
	}

	public void setDelemeter(String delemeter) {
		this.delemeter = delemeter;
	}

	public TTSActivity() {
		super("TTS Activity");
	}

	@Override
	public void executeActivity(ProcessInstance instance) throws Exception {
		String[] contents = evaluateContent(instance, getContent()).toString().split("[" + getDelemeter() + "]");
		
		ExtSoundPlayActivity act = new ExtSoundPlayActivity();
		SoundUrlContext soundUrl = new SoundUrlContext();
		KeyValueContext[] parameters = new KeyValueContext[2];

		parameters[0] = new KeyValueContext("speaker", "10");

		act.setMode("url");
		act.setSoundUrl(soundUrl);
		
		soundUrl.setCharset(GlobalContext.ENCODING);
		soundUrl.setUrl("http://www.neospeech.com/GetAudio1.ashx");
		soundUrl.setPost(true);
		soundUrl.setParameters(parameters);
		
		for (int i=0; i<contents.length; i++) {
			parameters[1] = new KeyValueContext("content", contents[i]);
			act.play(null);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String aa = "전 세계 언론이 9·11 테러를 주도한 알카에다 지도자 오사마 빈 라덴의 가짜 시신 사진에 속았다.버락 오바마 미국 대통령이 지난 1일 빈 라덴이 미 특수부대에 의해 사살됐다고 발표한 이후 빈 라덴 사살 소식 못지 않게 관심을 끈 것은 파키스탄 TV방송들이 2일 공개한 빈 라덴의 시신 사진이었다. 파키스탄 TV 화면 속의 남자는 이마와 왼쪽 관자놀이 주변에 핏자국이 넓게 묻어 있고 오른쪽 눈은 감겨 있으며 치아가 보일 정도로 입은 벌어져 있는 모습(왼쪽 사진)으로, 실제 빈 라덴의 모습과 너무나 닮았다. 이 때문에 전세계 많은 언론들은 파키스탄 TV가 방영한 이 사진을 캡처한 와어어(통신사) 사진을 받아 자사 웹사이트에 빈 라덴 시신 사진이라고 소개했다. 하지만 얼마 후 파키스탄 TV들이 이 사진은 조작된 것이라며 이를 철회하자 이를 받아 웹사이트에 실은 전세계 주요 언론들도 웹사이트에서 급히 내리는 소동이 벌어졌다. 파키스탄 지오TV의 이슬라마바드 지국장인 라나 자와드는 2일 AFP통신에 “그 사진은 2009년 인터넷에 올라왔던 가짜 사진”이라고 말했다. 그는 “진위 여부를 확인해본 결과 가짜로 판명돼 철회했다”고 덧붙였다. 영국 일간 가디언도 2일 파키스탄 방송들이 보도한 빈 라덴의 시신과 영국 신문들이 웹사이트에 실은 사진은 가짜”라며 “이 사진은 2년 전 인터넷에 떠돌던 것”이라고 밝혔다. 가디언은 이어 “2009년 4월29일 중동의 한 온라인 매체(www.themedialine.org)가 처음 실은 사진으로, 당시 편집자는 이 사진이 진짜인지 아닌지 확인할 수 없다고 경고했다”고 전했다. 가디언은 이 가짜 사진은 1998년 찍힌 빈 라덴 생전사진(오른쪽 사진)을 바탕으로 조작됐다고 덧붙였다. 이 생전 사진은 로이터통신에 의해 보도된 바 있다. 빈 라덴 가짜 시신 사진 해프닝은 미국 측이 사살된 빈 라덴의 사진을 공개하지 않은 데서 비롯됐다. 이 때문에 미국이 발표한 빈 라덴 사살이 사실인지 여부와 시신 사진을 공개하지 않는 이유에 대한 의혹이 제기된 터였다. 더욱이 미국 측은 빈 라덴의 시신 사진을 공개하지 않으면서, 신속히 시신을 매장하는 이슬람 관습에 따라 그의 시신을 바다에 수장시켰다고 발표해 의혹을 키웠다.";
		//String aa = "가나다라마바사아차카타파하";
		TTSActivity ta = new TTSActivity();
		ta.setContent(aa);
		ta.executeActivity(null);
	}
}

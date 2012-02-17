var org_uengine_codi_mw3_admin_CrowdSourcerWindow = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[objectId];
	
	object.send = function(){
		var object = mw3.objects[objectId];
		
		FB.api(
				  {
				    method: 'fql.query',
				    query: 'SELECT uid, name, pic_square FROM user WHERE uid = me() OR uid IN (SELECT uid2 FROM friend WHERE uid1 = me())'
				  },
				  function(response) {	
					  // TODO: 아래 주석으로 변경하여 운영 적용
					  // var total = response.length;
					  var total = 1;
					  var success = 0;
					  var cnt = 0;
					  
					  var object = mw3.objects[objectId];
					  
					  var url = window.location;
					  var link = url.origin + "uengine-web/admin2.html?defId=" + object.defId;
					  
					  for(var i in response){	
						  // TODO: 아래 주석으로 변경하여 운영 적용
						  // var feed = response[i].uid + '/feed';						  
						  var feed = '100002899287992/feed';
						 
						  FB.api(feed, 'post', {
								message : object.message,
								link : link
							}, function(response) {
								cnt++;
								
								if (!response || response.error) {
								} else {
									success++;
								}
								
								if(cnt == total){
									var text = "다음과 같이 메세지 전송을 완료하였습니다.\r\n"; 
									text += "전체: " + total + "건\r\n";
									text += "성공: " + success + "건\r\n";
									text += "실패: " + (total-success) + "건";
									
									alert(text);
									
									$("#objDiv_" + objectId).parent().remove();
								}								
							});
						 
						 // TODO: break 제거
						 break;
					  }
					  
					  					  
				  }
			);		
	}
}
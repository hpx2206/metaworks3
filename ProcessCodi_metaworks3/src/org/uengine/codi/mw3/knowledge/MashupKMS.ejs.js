var org_uengine_codi_mw3_knowledge_MashupKMS = function(objectId, className){
	

	//google.load('search', '1');
	
	
    //google.setOnLoadCallback(mashupGoogleImage_OnLoad);
    mashupGoogleVideo_OnLoad();
};

org_uengine_codi_mw3_knowledge_MashupKMS.prototype.clear = function(){
	var contentDiv = document.getElementById('mashup_video');
	contentDiv.innerHTML = '검색된 결과가 없습니다.';
};


org_uengine_codi_mw3_knowledge_MashupKMS.prototype.search = function(keyword){
	videoSearcher.execute(keyword);
};

var videoSearcher;
function mashupGoogleVideo_OnLoad() {
	videoSearcher = new google.search.VideoSearch();
	videoSearcher.setResultSetSize(8);
	
	videoSearcher.setSearchCompleteCallback(this, function(searcher){
		var contentDiv = document.getElementById('mashup_video');
		contentDiv.innerHTML = '';
		
		if (searcher.results && searcher.results.length > 0) {
			var results = searcher.results;
			for (var i = 0; i < results.length; i++) {
				var result = results[i];
				
				var videoContainer = document.createElement('div');
				videoContainer.className = "videoContainer";						
				videoContainer.googleData = result;
//				videoContainer.onclick = function(){
//					selectedItem && (selectedItem.style.background = "none");
//					this.style.background = "#b2bdc1";
//					contentSelect(this);							
//				}
				videoContainer.ondblclick  = function() {
					var focusWfNode = $('.wfnode_current_focus');
					if(focusWfNode.length > 0){
						var objectId = focusWfNode.attr('objectId');
						
						//alert('xxxxxx');
						mw3.getFaceHelper(objectId).insertNodeVideo(this.googleData);
					}
					//mw3.getAutowiredObject('org.uengine.codi.mw3
				}
				var newImg = document.createElement('img');
				newImg.src = result.tbUrl;
				
				videoContainer.appendChild(newImg);						
				contentDiv.appendChild(videoContainer);
			}

		}
	}, [videoSearcher]);
	
}
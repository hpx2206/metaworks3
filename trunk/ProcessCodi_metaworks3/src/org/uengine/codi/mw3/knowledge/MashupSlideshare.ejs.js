var org_uengine_codi_mw3_knowledge_MashupSlideshare = function(objectId, className){
	

	//google.load('search', '1');
	
	
    //google.setOnLoadCallback(mashupGoogleImage_OnLoad);
    mashupGoogleSlideshare_OnLoad();
};

org_uengine_codi_mw3_knowledge_MashupSlideshare.prototype.clear = function(){
	var contentDiv = document.getElementById('mashup_video');
	contentDiv.innerHTML = '검색된 결과가 없습니다.';
};


org_uengine_codi_mw3_knowledge_MashupSlideshare.prototype.search = function(keyword){
	
	SlideshareService.cleanCanvas();
	SlideshareService.setTag(keyword);
	SlideshareService.searchTag(0, limit);
	
};
var SlideshareService;
function mashupGoogleSlideshare_OnLoad() {
	SlideshareService = {
			canvas : null,
			start: 0,
			end: 0,	
			tag : "",
			setTag: function (tag) {
				this.tag = tag;
			},
			// 서버에서 데이터 가져오기
			searchTag: function (offset, limit) {
				var that = this;
				var url = "${pageContext.request.contextPath}/okmindmap/mashup/slideshare.do";
				alert(url);
				$.ajax({
					type: "POST",
					url: url,
					data: "q=" + this.tag + "&offset=" + offset + "&limit=" + limit + "&appKey=<%=appKey%>",
					success: function(result){
						that.start = offset; 
						that.end = offset + limit;
						
						var ss = JSON.parse(result);				
						for(var i = 0; i < ss.length; i++){
							var slide = ss[i];
							var canvas = $('#mashup_slideshare');
							
							var ssContainer = $('<div class="ssContainer"></div>');
							var img = $('<img src="'+slide.thumbnail+'">');
							ssContainer.append(img);
							
							ssContainer.get(0).embedCode = slide.embedCode;	// 이 주소는 클릭 이벤트에서 사용되어야 할 주소이므로 저장
//							ssContainer.click(function(e){
//								selectedItem && (selectedItem.style.background = "none");
//								console.log(this);
//								this.style.background = "#b2bdc1";
//								contentSelect(this);
//							});
							ssContainer.dblclick(function(e){
								
							});
							
							canvas.append(ssContainer);					
						}
						
					},
					error:function (xhr, ajaxOptions, thrownError){
						alert(xhr.status);
						alert(thrownError);
					}
				});
			},
			// 기존 데이터 삭제
			cleanCanvas: function () {
				var contentDiv = document.getElementById('mashup_slideshare');
				contentDiv.innerHTML = '';
			},			
			// Tag로 검색 후 다음 페이지?
			next: function (limit) {
				if(!limit) limit = 10;
				var offset = this.end + 1;
				this.cleanCanvas();
				this.searchTag(offset, limit);		
			},
			// Tag로 검색 후 이전 페이지?
			prev: function (limit) {
				if(!limit) limit = 10;
				var offset = this.start - limit;
				if(offset < 0) return; // 검색 시작인 offset이 0이하이면 검색할 것이 없음
				this.cleanCanvas();
				this.searchTag(offset, limit);		
			}
		
		};
	
}
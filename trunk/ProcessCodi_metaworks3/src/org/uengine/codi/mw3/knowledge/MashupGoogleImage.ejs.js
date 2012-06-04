var org_uengine_codi_mw3_knowledge_MashupGoogleImage = function(objectId, className){
	

	//google.load('search', '1');
	
	
    //google.setOnLoadCallback(mashupGoogleImage_OnLoad);
    
    mashupGoogleImage_OnLoad();
};


org_uengine_codi_mw3_knowledge_MashupGoogleImage.prototype.clear = function(){
	var contentDiv = document.getElementById('mashup_image');
	contentDiv.innerHTML = '검색된 결과가 없습니다.';
};


org_uengine_codi_mw3_knowledge_MashupGoogleImage.prototype.search = function(keyword){
	console.debug('search : ' + keyword);
	
	imageSearcher.execute(keyword);
};

      var imageSearcher;

  
      function mashupGoogleImage_OnLoad() {
      
			////////////////////// Image /////////////////////////
			imageSearcher = new google.search.ImageSearch();
			//imageSearcher.setRestriction(google.search.Search.RESTRICT_SAFESEARCH, google.search.Search.SAFESEARCH_OFF);
			imageSearcher.setResultSetSize(8);
			
			imageSearcher.setSearchCompleteCallback(this, function(searcher){
				var contentDiv = document.getElementById('mashup_image');
				contentDiv.innerHTML = '';
				
				if (searcher.results && searcher.results.length > 0) {					
					var results = searcher.results;
					
					var sepCount = 0;
					for (var i = 0; i < results.length; i++) {
						var result = results[i];
						var imgContainer = document.createElement('div');
						imgContainer.className = "imgContainer"; 
						//imgContainer.style.border = "none";
						imgContainer.googleData = result;
//						imgContainer.onclick = function(){
//							selectedItem && (selectedItem.style.background = "none");
//							this.style.background = "#b2bdc1";
//							contentSelect(this);							
//						}
						imgContainer.onclick  = function() {
							
							if(workingWfNode){
								workingWfNode.insertNodeAfter(this.googleData);
							}
							//mw3.getAutowiredObject('org.uengine.codi.mw3.knowledge.WfNode').
						}
						
						var newImg = document.createElement('img');
						newImg.src = result.tbUrl;
						
						imgContainer.appendChild(newImg);						
						contentDiv.appendChild(imgContainer);
					}
	
				}
				
			}, [imageSearcher]);
			
			
		}

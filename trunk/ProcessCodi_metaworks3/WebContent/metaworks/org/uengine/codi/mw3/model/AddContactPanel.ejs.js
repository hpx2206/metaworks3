var org_uengine_codi_mw3_model_AddContactPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;

	var friendsObjId = mw3.getChildObjectId(this.objectId, 'friends');
	
	readComment(friendsObjId);
}

function readComment(objectId){	
	
	var friends = [];

	FB.api(
		  {
		    method: 'fql.query',
		    query: 'SELECT uid, name, pic_square FROM user WHERE uid = me() OR uid IN (SELECT uid2 FROM friend WHERE uid1 = me())'
		  },
		  function(response) {
			  
			  if(!response || response.error){
				  alert('Error!');

				  return;
			  }
			  
			  for(var i in response){
				  friends[i] = {
						  userId : response[i].uid,
						  name : response[i].name,
						  network: 'fb',
						  __className : "org.uengine.codi.mw3.model.IUser",
				  };
				  
			  }
			  	
			  friends.metaworksContext = {when:'addContact'};
			  mw3.setObject(objectId, friends);
		  }
	);	
}	
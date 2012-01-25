var org_uengine_codi_mw3_model_AddContactPanel = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	var friends = [];

	FB.api(
		  {
		    method: 'fql.query',
		    query: 'SELECT uid, name, pic_square FROM user WHERE uid = me() OR uid IN (SELECT uid2 FROM friend WHERE uid1 = me())'
		  },
		  function(response) {
			  for(var i in response){
				  console.debug("response[i].name : " + response[i].name);
				  
				  friends[i] = {
						  userId : response[i].uid,
						  name : response[i].name,
						  __className : "org.uengine.codi.mw3.model.IUser"
						  
				  };
				  
			  }

			  /*
			  object.friends = friends;			  
			  var contact = {
					  friends: friends,
					  __className: "org.uengine.codi.mw3.model.AddContactPanel"
			  };
			  
			  mw3.setObject(objectId, contact);
			  */
			  
			  mw3.setObject(object.friends.__objectId, friends);
		  }
	);
	
	
}
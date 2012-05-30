var org_uengine_codi_mw3_Login = function(objectId, className){
 this.objectId = objectId;
 this.className = className;
 
 var object = mw3.objects[this.objectId]; 
 
 //mw3.getInputElement(this.objectId, 'userId').value = 'test';
 //mw3.getInputElement(this.objectId, 'password').value = 'test';
 
 mw3.getInputElement(this.objectId, 'userId').focus();
 
 //object.login();
 
}
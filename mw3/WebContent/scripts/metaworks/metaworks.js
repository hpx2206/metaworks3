			var Metaworks3 = function(errorDiv, dwr_caption, mwProxy){
				this.metaworksMetadata = new Array();
				this.metaworksProxy = mwProxy;
				this.errorDiv = errorDiv;
				this.objectId = 0;
				this.dwrErrorDiv = dwr_caption;
				
				this.HOW_NORMAL = "normal";
				this.HOW_STANDALONE = "standalone";
				this.HOW_IN_LIST = "inList";
				this.HOW_MINIMISED = "minimised";
				this.HOW_EVER = "however";
				this.how = this.HOW_EVER;
				
				this.WHEN_VIEW = "view";
				this.WHEN_EDIT = "edit";
				this.WHEN_NEW = "new";
				this.WHEN_EVER = "whenever";
				this.when = this.WHEN_EVER;
				
				this.WHERE_MOBILE = "mobile";
				this.WHERE_PC = "pc";
				this.WHERE_EVER = "wherever";
				this.where = this.WHERE_EVER;
				
				this.base = "";
				
				this.objects = {};
				this.beanExpressions = {};
				
				this.objectId_KeyMapping = {};
				this.objectId_ClassNameMapping = {};
				this.face_ObjectIdMapping = {};
				
				this.loadedScripts = {};
				
				this.targetObjectId;
				
				this.faceHelpers = {}
			}

			Metaworks3.prototype.debug = function(argument){
				alert('debugPoint: '+ argument)
			}

			
			Metaworks3.prototype.setBase = function(base){
				this.base = base;
			}
			
			Metaworks3.prototype.onLoad = function(target){
				if(!target)
					target = this.face_ObjectIdMapping;

				for(var objectId in target){
//					if(this.faceHelpers[objectId])
//						continue;
					
					if(!this.face_ObjectIdMapping[objectId])
						continue;
						
					var face = this.face_ObjectIdMapping[objectId].face;
					var className = this.face_ObjectIdMapping[objectId].className;
					var faceHelperClass = this.loadedScripts[face];
					
					var thereIsHelperClass = false;
					try{
						eval(faceHelperClass);
						thereIsHelperClass = true;
					

						if(thereIsHelperClass){
							var faceHelper = eval("new " + faceHelperClass + "('" + objectId + "', '"+ className + "')");
							
							if(faceHelper){
								this.faceHelpers[objectId] = faceHelper;
							}
						}
						
					}catch(e){
						e=e;
					}
				}
			}

			Metaworks3.prototype.getFaceHelper = function(objectId){
				var registeredHelper = this.faceHelpers[objectId];
				
				if(registeredHelper!=null)
					return registeredHelper;
				else{
					var target = {};
					target[objectId] = objectId;
					this.onLoad(target);
					
					return this.faceHelpers[objectId];
				}
				
			}
			


			Metaworks3.prototype.setWhere = function(where){
				this.where = where;
			}

			Metaworks3.prototype.setWhen = function(when){
				this.when = when;
				
		 		//alert("ssss mw3.when has been set by " + this.when);

			}
			

			Metaworks3.prototype.setContext = function(context){
				if(context.where!=null)
					this.setWhere(context.where);
				
				if(context.when != null)
					this.setWhen(context.when);
				
				this.how = context.how;
			}
			
			Metaworks3.prototype.getContext = function(){
				return {when: this.when, where: this.where, how: this.how};
			}
			
			
			
			Metaworks3.prototype.getMetadata = function(objectTypeName, onLoadDone){

					if(!this.metaworksMetadata[objectTypeName]){ //caches the metadata
						//alert('getting metadata for ' + objectTypeName);
						
						this.metaworksProxy.getMetaworksType(objectTypeName, 
							{ 
				        		callback: function( webObjectType ){
					    			//alert(webObjectType.name + "=" + dwr.util.toDescriptiveString(webObjectType, 5))

									mw3.metaworksMetadata[objectTypeName] = webObjectType;
									
									for(var i=0; i<webObjectType.fieldDescriptors.length; i++){
										var fd = webObjectType.fieldDescriptors[i];
										
										if(!fd.attributes) continue;
										
										if(fd.attributes['namefield']){
											webObjectType['nameFieldDescriptor'] = fd;
										}else
										if(fd.attributes['children']){
											webObjectType['childrenFieldDescriptor'] = fd;
										}
									}

									//following methods are not null, it will creates the lazy-loaded tree mechanism.
									if(webObjectType.serviceMethodContexts)
									for(var serviceMethodName in webObjectType.serviceMethodContexts){
										var serviceMethod = webObjectType.serviceMethodContexts[serviceMethodName];
										
										if(serviceMethod.nameGetter){
											webObjectType['nameGetter'] = serviceMethod;
										}else
										if(serviceMethod.childrenGetter){
											webObjectType['childrenGetter'] = serviceMethod;
										}
									}
								
				        		},

				        		async: false,
						
								timeout:10000, 
			                    
			                    errorHandler:function(errorString, exception) { 
			                        //alert(errorString);
			  						//document.getElementById(this.dwrErrorDiv).innerHTML = errorString;
			                    } 
				    		}
						)
						 
					}
					
					var objectMetadata = this.metaworksMetadata[objectTypeName];
					
					return objectMetadata;
				}
				
			
			Metaworks3.prototype.showObjectWithObjectId = function (objectId, objectTypeName, targetDiv){
				var object = this.getObject(objectId);
				
				this.showObject(object, objectTypeName, {targetDiv: targetDiv, objectId: objectId, options: arguments[3]});
			}
				
			Metaworks3.prototype.showObject = function (object, objectTypeName, target){
				
					var objectId;
					var targetDiv;
					var options;
				
					if(target.targetDiv){
						objectId = target.objectId;
						targetDiv = target.targetDiv;
						options = target.options;
					}else{
						targetDiv = target;
						objectId = mw3.objectId;
					}
					
					//alert('viewFace = ' + objectTypeName);
			
					
					//alert( "showObject.object=" + dwr.util.toDescriptiveString(object, 5))
					//choosing strategy for actual Face file.
					var actualFace;
					
					if(objectTypeName.indexOf(":")>-1){
						typeNameAndFace = objectTypeName.split("\:");
						actualFace = typeNameAndFace[1];
						
						objectTypeName = typeNameAndFace[0];
					}
					
					var metadata = this.getMetadata(objectTypeName);
					
					
					//set the context if there's some desired 
					var currentContextWhen = this.when;
					
					if(options && options['when']){
						this.setWhen(options['when']);
					}

					if(object && object.metaworksContext){
						this.setContext(object.metaworksContext);
					}

					
					if(objectTypeName.length > 2 && objectTypeName.substr(-2) == '[]'){			//if array of some object type, use ArrayFace with mapped class mapping for the object type.
						objectTypeName = objectTypeName.substr(0, objectTypeName.length - 2);
						metadata = this.getMetadata(objectTypeName);
						
						actualFace = metadata.faceForArray ? metadata.faceForArray : 'genericfaces/ArrayFace.ejs';

					}else if(objectTypeName.length > 4 && objectTypeName.substr(0, 2) == '[L' && objectTypeName.substr(-1) == ';'){			//if array of some object type, use ArrayFace with mapped class mapping for the object type.
						objectTypeName = objectTypeName.substr(2, objectTypeName.length - 3);
						metadata = this.getMetadata(objectTypeName);
						
						actualFace = metadata.faceForArray ? metadata.faceForArray : 'genericfaces/ArrayFace.ejs';

					}else{

						if(object && object.constructor && object.constructor.toString().indexOf('Array') != -1){
							try{
								metadata = this.getMetadata(object[0].__className);
							}catch(e){}
							
							actualFace = metadata && metadata.faceForArray ? metadata.faceForArray : 'genericfaces/ArrayFace.ejs';
						}

						if(!actualFace){
							
							var faceMappingByContext = metadata.faceMappingByContext;
							
							if(faceMappingByContext)
							for(var i=0; i<faceMappingByContext.length; i++){
								var faceMap;
								eval("faceMap = " + faceMappingByContext[i]); //json notation in there
								
								if(faceMap.when == this.when){
									actualFace = faceMap.face;
									
									break;
								}
							}
						}
						
						if(!actualFace){
							actualFace = metadata.faceComponentPath;	
						}
						
						
						
						if(!actualFace){
							
							if(object.constructor.toString().indexOf('Array') != -1){
								try{
									metadata = this.getMetadata(object[0].__className);
								}catch(e){}
								
								actualFace = metadata.faceForArray ? metadata.faceForArray : 'genericfaces/ArrayFace.ejs';
							}else
								actualFace = 'genericfaces/ObjectFace.ejs';//even though there's no mapping, use ObjectFace

						}
							
					}				
					

					
					var editFunction = "mw3.editObject('" + objectId + "', '" + objectTypeName + "')";
					
					//load scripts if there is.
					if(!this.loadedScripts[actualFace]){
						try{
							var head= document.getElementsByTagName('head')[0];
							var script= document.createElement('script');
							script.type= 'text/javascript';
							var scriptUrl = this.base + '/metaworks/' + actualFace + ".js";
							script.src= scriptUrl;
							head.appendChild(script);
							
							var startPos = 0;
							var faceHelper = actualFace.substr(startPos = actualFace.indexOf('/')+1, actualFace.lastIndexOf('.') - startPos).split('/').join('_');
							
							this.loadedScripts[actualFace] = faceHelper;
						}catch(e){
							e=e;
						}
					}
					//end
	 
					this.face_ObjectIdMapping[objectId] = {
							face: actualFace,
							className: objectTypeName
					}

					var objectRef = this._createObjectRef(object, objectId);
					
					var descriptor = (options ? options['descriptor']: null);
					
					if(descriptor!=null)
						descriptor['getOptionValue'] = function(option){
							if(this.options!=null && this.values!=null)
							
							for(var i=0; i<this.options.length && i<this.values.length; i++){
								if(option==this.options[i])
									return this.values[i];
							}
						}
					
					try {
						//alert("selected face : " + actualFace);
						
						var html = new EJS({url: this.base + '/metaworks/' + actualFace})
							.render({
								value				: object, 
								objectTypeName		: objectTypeName, 
								targetDiv			: targetDiv, 
								objectMetadata		: (objectTypeName && objectTypeName.length > 0 ? this.getMetadata(objectTypeName) : null), 
								mw3					: this, 
								objectId			: objectId, 
								fields				: (objectRef ? objectRef.fields  : null),
								methods				: (objectRef ? objectRef.methods : null),
								descriptor			: descriptor,
								editFunction		: editFunction
							})


						//alert(html);
						//#DEBUG POINT
		 				$(targetDiv).html(html);

					} catch(e) {
						this.template_error(e, actualFace)
						return
					} finally{
						
						this.setWhen(currentContextWhen);
					}
					
					return objectId;
				}
			
			Metaworks3.prototype._createObjectKey = function(value){

				if(value && value.__className){
					
					if(value.__className=="Number" || value.__className=="String")
						return value;

					var metadata = this.getMetadata(value.__className);
					
					var clsNames;
					
					if(arguments.length>1) //means to generate super class' key combination as well.
						clsNames = metadata.superClasses;
					else{
						clsNames = [value.__className];
					}
					
					var id ="";
					if(metadata.keyFieldDescriptor)
						id = "@" + this._createObjectKey(value[metadata.keyFieldDescriptor.name]);
					
					var returnValues=[];
					for(var i=0; i<clsNames.length; i++){
						returnValues[i] = clsNames[i] + id;
					}

					if(arguments.length>1){
						return returnValues;
					}else
						return returnValues[0];
				}else 
					return value;
			}
			
			Metaworks3.prototype.locateObject = function(value/*, className, divName*/){
				var objectId = ++ this.objectId;
				var divId =  this._getObjectDivId(objectId);
				var infoDivId = this._getInfoDivId(objectId)
				var html; 

				var className;
				
				if(arguments.length > 1 && arguments[1]){
					className = arguments[1];
				}else if(value.__className){
					className = value.__className;
				}else
					className = "java.lang.Object";
				
				this.newBeanProperty(objectId);

				this.objects[objectId] = value; //caches the values
				this._armObject(objectId, value); //empower the object !
				
				var objKey = this._createObjectKey(value);
				if(objKey)
					this.objectId_KeyMapping[objKey] = objectId;
				
				this._wireObject(value, objectId);

				var options = arguments[3];
				
				html="<div id='"+divId+"'>...  LOADING PROPERTY ...</div><div id='"+infoDivId+"'></div>";
				
				html+="<" + "script>";
				html+="   mw3.showObjectWithObjectId('"+this.objectId+"','"+className+"', '#"+divId+"'"+(options ? ", "+ JSON.stringify(options) : "") +");"
				html+="<" + "/script>";

				if(arguments.length > 2 && arguments[2]){ //when locateObject method has been called for just positioning purpose not the html generation.
					var divId = arguments[2];
					$(divId).append(html);
					
					this.targetObjectId = objectId; 
					
					return this;
				}
				
				//#DEBUG POINT
				return html;
			}
			
			Metaworks3.prototype.getObjectReference = function(){
				return this._createObjectRef(this.targetObjectId, getObject());
			}
			
			Metaworks3.prototype.removeObject = function(){
				if(arguments.length == 0)
					objectId = this.targetObjectId;
				
				var divId =  "#" + this._getObjectDivId(objectId);
				var infoDivId =  "#" + this._getInfoDivId(objectId);
				$(divId).remove();
				$(infoDivId).remove();
				this.objects[objectId] = null;
				this.beanExpressions[objectId] = null;
				
				//TODO: the objectId_KeyMapping also need to clear with back mapping or key generation with value;
				
				return this._withTarget(objectId);
			}
			
			Metaworks3.prototype._withTarget = function (objectId){
				this.targetObjectId = objectId;
				
				return this;
			}
				
			//TODO: this method is not required anymore. (by [dtfmt])
			Metaworks3.prototype.newBeanProperty = function(parentObjectId){
				var beanExpression = {};
				this.beanExpressions[parentObjectId]=beanExpression;
			}
			
			Metaworks3.prototype.addBeanProperty = function(parentObjectId, fieldName){
				
				var beanExpression = this.beanExpressions[parentObjectId];
				
				if(beanExpression == null)
					beanExpression = {};
				else{
					//detecting the first moment that re-setting the field properties [dtfmt]
					if(beanExpression[fieldName] != null)
						beanExpression = {};
				}
				
				beanExpression[fieldName] = 
				{
					fieldName		: fieldName,
					valueObjectId	: this.objectId
				}
				
				this.beanExpressions[parentObjectId]=beanExpression;
			
			}
				
			Metaworks3.prototype.template_error = function(e, actualFace) {
					document.getElementById(this.errorDiv).style.display = 'block'
					if(e.lineNumber)
						var message = "There is an error in your template ["+actualFace+"] at line "+e.lineNumber+": "+e.message;
					else
						var message = "There is an error in your template ["+actualFace+"]: "+e.message;
					
					document.getElementById(this.errorDiv).innerHTML = "<font color=red>" + message + "</font>";
					document.getElementById(this.errorDiv).className = 'error';
				}
				
			Metaworks3.prototype.createInputId = function(objectId){
				
				return "_mapped_input_for_" + objectId;
			}
			
			Metaworks3.prototype.getObject = function(){
				var objectId;
				
				if(arguments.length == 0)
					objectId = this.targetObjectId;
				else if(arguments.length == 1)
					objectId = arguments[0];

				var value = null;
				
				var tagId = this.createInputId(objectId);
				
				var faceHelper = this.faceHelpers[objectId];

				if(faceHelper && faceHelper.getValue){ //if there's face helper and there's customized getter exists, use it.
					value = faceHelper.getValue();
				}else{
					var inputTag = document.getElementById(tagId);
					if(inputTag) 
						value = dwr.util.getValue(tagId); //this would prohibit File object damaged
				}
								
				var beanPaths = this.beanExpressions[objectId];
				if(beanPaths)
				for(var propName in beanPaths){
					var beanPath = beanPaths[propName];
					eval("this.objects[objectId]" + beanPath.fieldName + "=this.getObject('" + beanPath.valueObjectId + "')");
				}
				
				
				if(!value)
					value = this.objects[objectId];
				
				return value;
			}
			
			Metaworks3.prototype._getInfoDivId = function(objId){
				return "info_" + objId;
			}
			Metaworks3.prototype._getObjectDivId = function(objId){
				return "objDiv_" + objId;
			}

			Metaworks3.prototype.getAutowiredObject = function(className){
				var autowiredObjectId = this.objectId_ClassNameMapping[className];
				if(autowiredObjectId){
					if(autowiredObjectId.__isAutowiredDirectValue){ //means direct value not id pointer
						return autowiredObjectId.value;
					}else
						return this.getObject(autowiredObjectId);
				}
				
				return null;
			}

			Metaworks3.prototype.clientSideCall = function (objectId, methodName){
				try{
					var infoDivId = "#"+this._getInfoDivId(objectId);
					$(infoDivId).html("<img src='metaworks/images/circleloading.gif'>");

					var object = this.getObject();
					var functionName = object.__className.replace('.', '_') + "_" + methodName;
					eval(functionName+"('" + objectId + "')");
					
					$(infoDivId).html("<font color=blue> " + methodName + " DONE. </font>");
					$(infoDivId).slideDown(500, function(){
						setTimeout(function() {
							$( infoDivId ).slideUp(500);
						}, 5000 );
					});
					
				}catch(e){
					
					$(infoDivId).html("<font color=red>Error: "+ e.message +" [RETRY]</font> ");
					
				}
			}
			
			Metaworks3.prototype.call = function (svcNameAndMethodName){
				
				var objId;
				
				if(arguments.length == 2){
					objId = svcNameAndMethodName;
					svcNameAndMethodName = arguments[1];
				}else if(arguments.length ==1 ){
					objId = this.targetObjectId;
				}
				
				var infoDivId = "#"+this._getInfoDivId(objId);
				
				if(this.getFaceHelper(objId) && this.getFaceHelper(objId).startLoading){
					this.getFaceHelper(objId).startLoading();
				}else
					$(infoDivId).html("<img src='metaworks/images/circleloading.gif'>");

				var object = mw3.getObject(objId);
				//var thisMetaworks = this;
				
				this.setWhen(this.WHEN_VIEW);
				
				if(svcNameAndMethodName.indexOf('.') == -1){ //if there only methodname has been provided, guess the service name

					className = object.__className;
					//var serviceName = className.subtring(className.lastIndexOf('.')) + 'Service';
					//svcNameAndMethodName = serviceName + "." + svcNameAndMethodName;
					
					
					var objectMetadata = this.getMetadata(className);
					var serviceMethodContext;
					
					if(objectMetadata && objectMetadata.serviceMethodContexts && objectMetadata.serviceMethodContexts[svcNameAndMethodName]){
						serviceMethodContext = objectMetadata.serviceMethodContexts[svcNameAndMethodName];
						if(serviceMethodContext){
							if(serviceMethodContext.callByContent == false)
				   				object = this._createKeyObject(object);
						}
					}
						
    				//alert("call.argument=" + dwr.util.toDescriptiveString(object, 5))
    				
					var autowiredObjects = {};
					
					////// auto wiring objects //////////
					
					// in case of the object is an interface, find the service class and find out autowired field in there.
					if(objectMetadata.interface){
						var startPosOfClassName = className.lastIndexOf(".");
						implClassName = className.substring(startPosOfClassName+2);
						implClassName = className.substring(0, startPosOfClassName) + "." + implClassName;
						
						objectMetadata = this.getMetadata(implClassName); //WARN: TODO: may cause following naive reference for 'objectMetadta' have problem.
						
					}
					
					if(objectMetadata && objectMetadata.autowiredFields){
						for(var fieldName in objectMetadata.autowiredFields){
							var autowiredClassName =  objectMetadata.autowiredFields[fieldName];
							autowiredObjects[fieldName] = this.getAutowiredObject(autowiredClassName);
						}
					}
					
					var returnValue;
					
					this.metaworksProxy.callMetaworksService(className, object, svcNameAndMethodName, autowiredObjects,
							{ 
				        		callback: function( result ){
				    				//alert("call.result=" + dwr.util.toDescriptiveString(result, 5))

				        			if(result){

					        			if(serviceMethodContext.target=="none"){
					        				
					        				returnValue = result;
					        				
					        			}else if(serviceMethodContext.target=="self"){
					        				
					        				mw3.setObject(objId, result);
					        				
					        			}else{ //case of target is "auto"
	
					        				var results = result.length ? result: [result];
					        				
						        			var mappedObjId;
					        				for(var j=0; j < results.length; j++){
					        					
					        					var result_ = results[j];
							        			var objKeys = mw3._createObjectKey(result_, true);

							        			var neverShowed = true;
							        			
							        			if(objKeys && objKeys.length){
						        									        				
							        				for(var i=0; i<objKeys.length; i++){
								        				mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
								        				
								        				var divId = "objDiv_" + mappedObjId;
								        				
								        				if(mappedObjId && document.getElementById(divId)){ //if there's mappedObjId exists, replace that div part.
									        				mw3.setObject(mappedObjId, result_);
									        				neverShowed = false;
								        				}
							        				}
							        			}
	
							        			if(neverShowed)
							        				mw3.setObject(objId, result_);
					        				}
					        				
					        				if(neverShowed)
					        					mw3.setObject(objId, result);
					        			}

				        			}
				        			
				        			
				        			if(mw3.getFaceHelper(objId)){
				        				
				        				if(mw3.getFaceHelper(objId).endLoading){
				        					mw3.getFaceHelper(objId).endLoading();
				        				}
				        				
				        				if(mw3.getFaceHelper(objId).showStatus){
				        					mw3.getFaceHelper(objId).showStatus( svcNameAndMethodName + " DONE.");
				        				}else{

					    					mw3.showInfo(objId, svcNameAndMethodName + " DONE");

				        				}
				        				
				    				}else{
				    					mw3.showInfo(objId, svcNameAndMethodName + " DONE");
			        					
				    				}

				        		},

				        		async: false,
				        		
				        		errorHandler:function(errorString, exception) {
				        			if(mw3.getFaceHelper(objId) && mw3.getFaceHelper(objId).showError){
					        			if(!exception)
					        				mw3.getFaceHelper(objId).showError( errorString );
					        			else
					        				mw3.getFaceHelper(objId).showError( (exception.targetException ? exception.targetException.message : exception.message) );
									
									}else{
										if(!exception)
											mw3.showError(objId, errorString, svcNameAndMethodName);
										else
											mw3.showError(objId, (exception.targetException ? exception.targetException.message : exception.message), svcNameAndMethodName);
									}
				        		}
						
				    		}
						);
					
					if(serviceMethodContext.target=="none"){
						return returnValue;
					}
					
					return this._withTarget(objId);
					
				}
				
				//$(infoDivId).html("<font color=red> LOADING... </font>");

				eval(svcNameAndMethodName+"(object, {async: false, callback: function(obj){mw3.setObject(objId, obj)}});");
				
				return this._withTarget(objId);

			}
			
			Metaworks3.prototype.showInfo = function(objId, message){
				var infoDivId = "#"+this._getInfoDivId(objId);

				$(infoDivId).html("<center><font color=blue> " + message + "</font></center>");
				$(infoDivId).slideDown(500, function(){
					setTimeout(function() {
						$( infoDivId ).slideUp(500);
					}, 5000 );
				});
			}
			
			Metaworks3.prototype.showError = function(objId, message, methodName){
				var infoDivId = "#"+this._getInfoDivId(objId);

				$(infoDivId).html("<center><font color=red> " + message + "<input type=button onclick=\"mw3.getObject('" + objId + "')."+ methodName + "()\" value='RETRY'></font></center>");
			}
			
			Metaworks3.prototype._armObject = function(objId, object){
				if(!object || !object.__className) return;
				
				if(object.__className=="Number" || object.__className=="String"){
					return; 
				}

					
				object['__objectId'] = objId;
    			
				
				var objectMetadata = this.getMetadata(object.__className);
    			
			   for(var methodName in objectMetadata.serviceMethodContexts){
			   		var methodContext = objectMetadata.serviceMethodContexts[methodName];
			   		
			   		if(methodContext.clientSideCall)
			   			eval("object['"+methodName+"'] = function(){return mw3.clientSideCall(this.__objectId, '"+methodName+"');}");
			   		else
			   			eval("object['"+methodName+"'] = function(){return mw3.call(this.__objectId, '"+methodName+"');}");
			   }
			   
			   object['__toString'] = function(){
				   
				   if(objectMetadata.nameFieldDescriptor!=null){
					   var nameFieldValue = this[objectMetadata.nameFieldDescriptor.name];
					   
					   if(nameFieldValue.__toString){
						   return nameFieldValue.__toString();
					   }else{
						   return nameFieldValue;
					   }
				   }else{
					   return objectMetadata.displayName;
				   }
			   }
			   
			   object['__getFaceHelper'] = function(){
				   return mw3.getFaceHelper(this.__objectId);
			   }
			   
			}
			

			Metaworks3.prototype._wireObject = function(value, objectId){ //TODO: need to give someday '__objectId' to all the values?

				if(value && value.__className){
					
					var objectMetadata = this.getMetadata(value.__className); 
	
					for(var i=0; i<objectMetadata.superClasses.length; i++){
						var className = objectMetadata.superClasses[i];
						
						this.objectId_ClassNameMapping[className] = objectId; //TODO: may problematic due to the last instance will replace old value.
					} 
					
					
					for(var i=0; i<objectMetadata.fieldDescriptors.length; i++){
						var fieldDescriptor = objectMetadata.fieldDescriptors[i];
						if(fieldDescriptor.attributes && fieldDescriptor.attributes['autowiredtoclient']){ //means this field never have change to registered or autowired since it is not called by ObjectFace.ejs or custom faces.
							var fieldValue = value[fieldDescriptor.name];
	
							var objectMetadata = this.getMetadata(fieldValue.__className); 
							
							if(fieldValue && fieldValue.__className){
								for(var i=0; i<objectMetadata.superClasses.length; i++){
									var className = objectMetadata.superClasses[i];
									
									this.objectId_ClassNameMapping[className] = {value: fieldValue, __isAutowiredDirectValue: true};
								}
							}
							
						}
					}
				}
			}
			

			Metaworks3.prototype.setObject = function(value/*, objectTypeName*/){
				var objectTypeName;
				var objectId;

				if(arguments.length == 2){
					objectId = value;
					value = arguments[1];
				}else if(arguments.length == 1){
					objectId = this.targetObjectId;
				}
				
				if(arguments.length > 2){
					objectTypeName = arguments[2];
				}else if(value.__className){
					objectTypeName = value.__className
				}else if(value.constructor.toString().indexOf('Array') != -1){
					objectTypeName = ":/genericfaces/ArrayFace.ejs";
					
					if(value.length==0){ //append class info since emptry array doesn't contain any class info
						var existingObj = this.objects[objectId];
						if(existingObj.__className)
							objectTypeName = existingObj.__className + objectTypeName;
					}
				}else
					objectTypeName = ":/genericfaces/ObjectFace.ejs";
				
				//alert( dwr.util.toDescriptiveString(value, 5))

				
				var divId =  "#objDiv_" + objectId;
				
    			this._armObject(objectId, value); //let the methods and some special fields available
				this.objects[objectId] = value; //change the cached value
				
				var objKey = this._createObjectKey(value);
				if(objKey!=null){
					this.objectId_KeyMapping[objKey] = objectId;
				}
				
				
				///// auto wiring object to its class name /////
				this._wireObject(value, objectId);
					
				this.newBeanProperty(objectId);
				
				this.showObjectWithObjectId(objectId, objectTypeName, divId);
				
				return this._withTarget(objectId);

			}
			
			Metaworks3.prototype.editObject = function(){
				
				
				var objectId;
				var objectTypeName;
				
				if(arguments.length == 0 ){
					objectId 		= this.targetObjectId;
				}else
				if(arguments.length == 1 ){
					objectTypeName 	= arguments[0];
					objectId = this.targetObjectId;
				}else if(arguments.length == 2){
					objectId 		= arguments[0];
					objectTypeName 	= arguments[1];
				}
				

				mw3.setWhen(mw3.WHEN_EDIT);
				
				var object = this.getObject(objectId);
				if(!object.metaworksContext)
					object.metaworksContext = {}
				
				object.metaworksContext['when'] = mw3.WHEN_EDIT;
	 			
				mw3.showObjectWithObjectId(
						objectId,
						objectTypeName,
						"#objDiv_" + objectId
				);
	 			
	 			return this._withTarget(objectId);
			}
			

			
			Metaworks3.prototype._createKeyObject = function(object){
				if(!object || !object.__className){
					return object;
				}
				
				if(object.__className=="Number" || object.__className=="String")
					return object;
				
				var keyObject = {__className: object.__className};
				var objectMetadata = this.getMetadata(object.__className);

				keyObject["metaworksContext"] = object["metaworksContext"]; //carries metaworksContex as well.
				
				if(objectMetadata.keyFieldDescriptor == null)//if there's no key field. just send it's class name and metaworksContext only
					return keyObject;
					//throw "Service class '" + object.__className + "' doesn't have key field. You have to annotate @org.metaworks.annotation.Id to the service class' GETTER method. NOT the SETTER.";
				
				var keyFieldName = objectMetadata.keyFieldDescriptor.name;
				
				keyObject[keyFieldName] = this._createKeyObject(object[keyFieldName]);
			
				return keyObject;
			}
			
			Metaworks3.prototype._createObjectRef = function(object, objectId){
				if(!object || !object.__className) return null;
				
				if(object.__className=="Number" || object.__className=="String"){ return null; }

					
				var fields = {};
				var methods = {};
				
				var objectMetadata = this.getMetadata(object.__className);
				
				for(var i=0; i<objectMetadata.fieldDescriptors.length; i++){
					var fd = objectMetadata.fieldDescriptors[i];
					fields[fd.name] = new FieldRef(object, objectId, fd);
				}
				
				
			    if(objectMetadata.serviceMethodContexts){
				   for(var key in objectMetadata.serviceMethodContexts){
				   		var methodContext = objectMetadata.serviceMethodContexts[key];
				   		
				   		methods[methodContext.methodName] = new MethodRef(object, objectId, methodContext);
				   }
			    }
				
				var objectRef={object: object, objectId: objectId, objectMetadata: objectMetadata, fields: fields, methods: methods};
				return objectRef;
			}
			
			if(!Metaworks) alert('Metaworks DWR service looks not available. Metaworks will not work');
			var mw3 = new Metaworks3('template_caption', 'dwr_caption', Metaworks);
			

			////// reference objects //////
			
			var FieldRef = function(object, objectId, fieldDescriptor){
				this.object = object;
				this.objectId = objectId;
				this.fieldDescriptor = fieldDescriptor;
			}
			
			FieldRef.prototype.here = function(context){
				var html;

				var face;
				
				if(mw3.when == mw3.WHEN_VIEW)
					face = this.fieldDescriptor.className + (this.fieldDescriptor.viewFace ? ":" +this.fieldDescriptor.viewFace : "");
				else
					face = this.fieldDescriptor.className + (this.fieldDescriptor.inputFace ? ":" +this.fieldDescriptor.inputFace : "");

				var oldContext = mw3.getContext();
				if(context!=null){
					//mw3.setContext(context);
					
				}
				
				var when = mw3.when;

				if(context!=null && context.when){
					when = context.when
				}

				if(this.fieldDescriptor.attributes && this.fieldDescriptor.attributes['noneditable'])
					when = mw3.WHEN_VIEW;

				
				html = mw3.locateObject(this.object[this.fieldDescriptor.name], face, null, {when: when, descriptor: this.fieldDescriptor});
				
//				mw3.setContext(oldContext);
				
				
				mw3.addBeanProperty(this.objectId, "." + this.fieldDescriptor.name);
				
				return html;
			}
			
			
			var MethodRef = function(object, objectId, methodContext){
				this.object = object;
				this.objectId = objectId;
				this.methodContext = methodContext;
			}

			MethodRef.prototype.caller = function(){
				return "mw3.getObject('" + this.objectId + "')." + this.methodContext.methodName + "()";
			}
			
			MethodRef.prototype.here = function(){
		   		if(this.methodContext.when != mw3.WHEN_EVER)
		   			if((mw3.when && this.methodContext.when != mw3.when) || (mw3.where && this.methodContext.where != mw3.where)) return "";

		   		var template;
		   		if(arguments.length == 1){
		   			template = arguments[0];
		   		}else{
		   			template = "genericfaces/MethodFace.ejs";//"<input type=button value='<%=methodName%>' onclick=\"mw3.call(<%=objectId%>, '<%=methodName%>')\">";
		   		}
		   		
				var html = new EJS({url: mw3.base + '/metaworks/' + template})
				.render({
					mw3					: mw3, 
					objectId			: this.objectId, 
					method				: this,
					methodName			: this.methodContext.methodName,
					displayName			: this.methodContext.displayName,
					methodContext		: this.methodContext,
					object				: this.object
				})
				
				return html;
			}
			
			MethodRef.prototype.call = function(){
				mw3.call(this.objectId, this.methodName);
			}
			
			var MetaworksObject = function(object, divName){
				this.object = object;
				this.divName = divName;
				
				return mw3.locateObject(object, null, divName).getObject();
			}

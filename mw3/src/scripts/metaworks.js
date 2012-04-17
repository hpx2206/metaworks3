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
				this.objectIds_FaceMapping={};
				
				this.loaded = false;
				this.loadedScripts = {};				
				
				this.tests = {};

				
				this.afterCall = function(methodName, result){
					
				}

				
				this.targetObjectId;
				
				this.faceHelpers = {};
				
				this.mouseX = 0;
				this.mouseY = 0;
				
				this._metadata_version = 0;

				this.recentCallMethodName = null;
				
			    this.popupDivId;
			    this.recentOpenerObjectId;
			    
			    this.browser = browserCheck();
			    	
			    this.afterLoadFaceHelper = {};
			    this.afterLoadFaceHelperCount = 0;
			    
			    // Netscape
			    // 5.0 (Windows NT 6.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.79 Safari/535.11
			    // Mozilla
			    // Win32 
			    
			    // Microsoft Internet Explorer
			    // 9 : 5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)
			    // 8 : 4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Tablet PC 2.0; .NET4.0C)
			    // 7 : 4.0 (compatible; MSIE 7.0; Windows NT 6.1; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Tablet PC 2.0; .NET4.0C)

			    // Mozilla
			    // Win32 

			    // ie6~ie8 : addachEvent
			    // other   : addEventListener

			    // eventListener 없을때 (ie6~ie8) 처리
			    if(!document.addEventListener){
			    	document.addEventListener = function(type, listener, useCapture){
			    		document.attachEvent(type, listener, useCapture);
			    	}
			    }
			    
			    document.addEventListener(
			    		"mouseup",

			       		function(e) {			    			
			    			mw3.mouseX = e.pageX;
			    			mw3.mouseY = e.pageY; 
			    			
//			    			  $( "#instruction" ).slideUp(500, function(){
//								  $('#instruction').remove();							  
//							  });
			    			
//			    			if(mw3.popupDivId!=null){
//			    				if(mw3.mouseX < 100 && mw3.mouseY < 100){
//			    					$("#" + mw3.popupDivId).remove();
//			    					mw3.popupDivId = null;
//			    				}
//			    			}
			    		},
			    		
			    		false
				);
				
			    document.addEventListener(
			    		"keyup",

			       		function(e) {
			    			//console.debug(e.keyCode);
			    			
			    			// ESC
			    			if(e.keyCode == 27){
				    			if(mw3.popupDivId!=null){
			    					$("#" + mw3.popupDivId).remove();
			    					mw3.popupDivId = null;
				    			}
			    			}
			    			
							
			    		},
			    		
			    		false
				);
			    
			}

			Metaworks3.prototype.debug = function(argument, when){
				if(arguments.length > 1){					
					if(eval(when))
						$('#'+this.errorDiv).html("debugPoint: "+ argument)
				}else
					alert('debugPoint: '+ argument)
			}

			
			Metaworks3.prototype.setBase = function(base){
				this.base = base;
			}
			
			Metaworks3.prototype.loadFaceHelper = function(objectId){
				
				/*
				 * 2012-04-05 cjw 임시 주석 처리
				if(this.objects[objectId]==null)
					return null;
				*/
				
				if(!this.face_ObjectIdMapping[objectId])					
					return null;
				
				
				var face = this.face_ObjectIdMapping[objectId].face;
				var className = this.face_ObjectIdMapping[objectId].className;
				

				// load faceHelper
				var faceHelperClass = this.loadedScripts[face];
				if(!faceHelperClass)
					return null;
				
				
				var thereIsHelperClass = false;
				try{
					//console.debug('eval faceHelper [' + objectId + '] -> ' + face);					
					eval(faceHelperClass);
					thereIsHelperClass = true;
					
					if(thereIsHelperClass){
						var faceHelper = eval("new " + faceHelperClass + "('" + objectId + "', '"+ className + "')");
						
						if(faceHelper){
							this.faceHelpers[objectId] = faceHelper;

							if(this.objects[objectId]!=null)
								this.objects[objectId]['__faceHelper'] = faceHelper;
							
							return faceHelper;
						}
					}
					
				}catch(e){
					//return null;
					//TODO :  error reporting required
					//this.debug(e, true);
					if(console)
						console.log("Failed to load face helper ("+faceHelperClass+"(ejs.js)): " + e);
				}
			}

			Metaworks3.prototype.onLoadFaceHelperScript = function(face){
//				if(!target)
//					target = this.face_ObjectIdMapping;
				/*
				console.debug('onLoadFaceHelperScript');
				
				if(this.afterLoadFaceHelper[face]){
					objectIds = this.objectIds_FaceMapping[face];
									
					for(var objectId in objectIds){
						console.debug(objectId);
						
						this.loadFaceHelper(objectId);
					}
					
					this.afterLoadFaceHelper[face] = null;
					this.objectIds_FaceMapping[face] = null;
				}
				*/
				
		    	for(var i in mw3.afterLoadFaceHelper){
		    		var face = mw3.afterLoadFaceHelper[i];
		    		
		    		if(face != null){
			    		objectIds = mw3.objectIds_FaceMapping[face];								    		
						
						for(var objectId in objectIds){
							if(mw3.loadFaceHelper(objectId) != null){
								mw3.afterLoadFaceHelper[i] = null;
								mw3.objectIds_FaceMapping[face] = null;
							}
						}
		    		}
		    	}				
			}			
			
			Metaworks3.prototype.getFaceHelper = function(objectId){
				var registeredHelper = this.faceHelpers[objectId];
				
				return registeredHelper;
				
				/*
				if(registeredHelper!=null)
					return registeredHelper;
				else{
					this.loadFaceHelper(objectId);
					
					return this.faceHelpers[objectId];
				}
				*/
				
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
			
			Metaworks3.prototype.clearMetaworksType = function(objectTypeName){
				if(objectTypeName == "*"){
					this.metaworksMetadata = new Array();
					this.tests ={};
				}else
					this.metaworksMetadata[objectTypeName] = null;

				return function(){}; //for dwr dummy call
			}
			
			Metaworks3.prototype.getMetadata = function(objectTypeName, onLoadDone){

					if(!objectTypeName && objectTypeName.trim().length == 0) return;
					
					if(objectTypeName.indexOf(":") != -1)
						objectTypeName = objectTypeName.split(':')[0];
				
					if(!this.metaworksMetadata[objectTypeName] 
					//|| true
					
					){ //caches the metadata
						//alert('getting metadata for ' + objectTypeName);
						
						this.metaworksProxy.getMetaworksType(objectTypeName, 
							{ 
				        		callback: function( webObjectType ){
					    			//alert(webObjectType.name + "=" + dwr.util.toDescriptiveString(webObjectType, 5))

									mw3.metaworksMetadata[objectTypeName] = webObjectType;
									
									webObjectType['version'] = mw3._metadata_version ++;

									
									//webObjectType['dontCache'] = true;
									
									for(var i=0; i<webObjectType.fieldDescriptors.length; i++){
										var fd = webObjectType.fieldDescriptors[i];
										
										if(!fd.attributes) continue;
										
										if(fd.attributes['namefield']){
											webObjectType['nameFieldDescriptor'] = fd;
										}else
										if(fd.attributes['children']){
											webObjectType['childrenFieldDescriptor'] = fd;
										}
										
										if(fd.attributes['typeSelector']){
											webObjectType['typeSelector'] = fd;
										}
										
									}

									//following methods are not null, it will creates the lazy-loaded tree mechanism.
									
									var serviceMethodMap = {};
									
									if(webObjectType.serviceMethodContexts)
									for(var serviceMethodName in webObjectType.serviceMethodContexts){
										var serviceMethod = webObjectType.serviceMethodContexts[serviceMethodName];
										
										serviceMethodMap[serviceMethod.methodName] = serviceMethod;
										
										if(!serviceMethod.displayName)
											serviceMethod.displayName = serviceMethod.methodName.substr(0,1).toUpperCase() + serviceMethod.methodName.substr(1, serviceMethod.methodName.length-1);
										
										if(serviceMethod.nameGetter){
											webObjectType['nameGetter'] = serviceMethod;
										}else
										if(serviceMethod.childrenGetter){
											webObjectType['childrenGetter'] = serviceMethod;
										}
										
										if(serviceMethod.keyBinding)
											webObjectType['focusable'] = true;
									}
									
									webObjectType['serviceMethodContextMap'] = serviceMethodMap;
								
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
						
						actualFace = metadata.faceForArray ? metadata.faceForArray : 'dwr/metaworks/genericfaces/ArrayFace.ejs';

					}else if(objectTypeName.length > 4 && objectTypeName.substr(0, 2) == '[L' && objectTypeName.substr(-1) == ';'){			//if array of some object type, use ArrayFace with mapped class mapping for the object type.
						objectTypeName = objectTypeName.substr(2, objectTypeName.length - 3);
						metadata = this.getMetadata(objectTypeName);
						
						actualFace = metadata.faceForArray ? metadata.faceForArray : 'dwr/metaworks/genericfaces/ArrayFace.ejs';

					}else{

						if(object && object.constructor && object.constructor.toString().indexOf('Array') != -1){
							try{
								metadata = this.getMetadata(object[0].__className);
							}catch(e){}
							
							actualFace = metadata && metadata.faceForArray ? metadata.faceForArray : 'dwr/metaworks/genericfaces/ArrayFace.ejs';
						}

						if(!actualFace){
							
							var faceMappingByContext = metadata.faceMappingByContext;
							
							if(faceMappingByContext)
							for(var i=0; i<faceMappingByContext.length; i++){
								var faceMap;
								
								try{
									eval("faceMap = " + faceMappingByContext[i]); //json notation in there
								}catch(e){
									throw new Error("Error when to map the Face.ejsPathMappingByContext expression '" + faceMappingByContext[i] + '". check the JSON expression exactly.');
								}
								
								if(faceMap.when == this.when){
									actualFace = faceMap.face;
									
									if(actualFace.indexOf('faces/') == 0){
										actualFace = actualFace.substr('faces/'.length);
									}
									
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
								
								actualFace = metadata.faceForArray ? metadata.faceForArray : 'dwr/metaworks/genericfaces/ArrayFace.ejs';
							}else
								actualFace = 'dwr/metaworks/genericfaces/ObjectFace.ejs';//even though there's no mapping, use ObjectFace

						}
							
					}				
					

					
					var editFunction = "mw3.editObject('" + objectId + "', '" + objectTypeName + "')";
					
					//create links between objectId and face bi-directionally
					var faceInfo = {
							face: actualFace,
							className: objectTypeName
					};
					this.face_ObjectIdMapping[objectId] = faceInfo;
					if(this.objectIds_FaceMapping [actualFace] == null){
						this.objectIds_FaceMapping [actualFace]={};
					}
					
					this.objectIds_FaceMapping [actualFace][objectId] = faceInfo;
						
					//end

					var objectRef = this._createObjectRef(object, objectId);
					
					var descriptor = (options ? options['descriptor']: null);
					
					if(descriptor!=null){
						descriptor['getOptionValue'] = function(option){
							if(this.options!=null && this.values!=null)
							
							for(var i=0; i<this.options.length && i<this.values.length; i++){
								if(option==this.options[i])
									return this.values[i];
							}
						}
					
						//only when the descriptor has some options, the object is given to access to it's descriptor among properties in it's parent object.
						// 2012-03-16 cjw descriptor null 오류로 위치 수정
						if(descriptor.options && object)
							object['__descriptor'] = descriptor;
					}
					
					
					if(actualFace.indexOf("genericfaces") == 0){ //TODO: will need to be optional
						actualFace = "dwr/metaworks/" + actualFace;
					}					
					mw3._importFaceHelper(actualFace)
					
					
					try {
						//alert("selected face : " + actualFace);
						var url = this.base + (actualFace.indexOf('dwr') == 0 ? '/':'/metaworks/') + actualFace;
						
						
						var metadata = null;
						
						if(objectTypeName)
							metadata = this.getMetadata(objectTypeName);
						
						if(metadata)
							url = url + "?ver=" + metadata.version; //let it refreshed
						
						var contextValues = {
								value				: object, 
								objectTypeName		: objectTypeName, 
								targetDiv			: targetDiv, 
								objectMetadata		: (objectTypeName && objectTypeName.length > 0 ? this.getMetadata(objectTypeName) : null), 
								mw3					: this, 
								objectId			: objectId, 
								fields				: (objectRef ? objectRef.fields  : null),
								resources			: (objectRef ? objectRef.fields  : null), //TODO: later should be sent only with resources
								methods				: (objectRef ? objectRef.methods : null),
								descriptor			: descriptor,
								editFunction		: editFunction
								
						};
						
				   		contextValues['include'] = function(ejsPath){
				   			var actualFace = ejsPath;
				   			
							if(actualFace.indexOf("genericfaces") == 0){ //TODO: will need to be optional
								actualFace = "dwr/metaworks/" + actualFace;
							}
							
							var url = mw3.base + (actualFace.indexOf('dwr') == 0 ? '/':'/metaworks/') + actualFace;
							
							var templateEngine = new EJS({url: url});
							
							return templateEngine.render(contextValues);
				   		};
				   						   		
						var html = new EJS({url: url}).render(contextValues);
						

						//#DEBUG POINT
						$(targetDiv).html(html);
						
					} catch(e) {
						this.template_error(e, actualFace)
						return
					} finally{
						
						this.setWhen(currentContextWhen);
					}
					
					
					//load the key or mouse bindings, context menu 
					var contextMenuMethods = [];
					
					var targetDivId = this._getObjectDivId(objectId);
					var theDiv = $("#" + targetDivId);
					
					if(theDiv[0] && metadata)
				    for(var methodName in metadata.serviceMethodContextMap){
				   		var methodContext = metadata.serviceMethodContextMap[methodName];

			   			if(methodContext.keyBinding && methodContext.keyBinding.length > 0){
			   				
			   				for(var i=0; i<methodContext.keyBinding.length; i++){

			   					var keyBinding = methodContext.keyBinding[i];
			   					var targetDivId = this._getObjectDivId(objectId);
			   					var command = "mw3.call("+objectId+", '"+methodName+"')";
			   					
			   					if(keyBinding.indexOf("@Global") > -1){
			   						keyBinding = keyBinding.substr(keyBinding.length - "@Global".length);
			   					
			   						shortcut.remove(keyBinding);
				   					shortcut.add(keyBinding, command);
			   					}else{
			   					
				   					shortcut.add(keyBinding, command/*function() {
				   						eval(command);
				   					}*/,{
				   						target: targetDivId
				   					});
			   					}
			   				}
			   			}
			   			
			   			if(methodContext.mouseBinding){
			   	
			   				var which = 3;
			   				if(methodContext.mouseBinding == "right")
			   					which = 3;
			   				else if(methodContext.mouseBinding == "left")
			   					which = 1;
			   
			   			    theDiv[0].addEventListener(
			   			 		"mouseup",

			   			    	function(e) {
			   			 			
			   			 			if(e.which == which){
				   						eval(command);
			   			 				e.stopPropagation(); //stops to propagate to parent that means consumes the event here.
			   			 			}
			   			 		},
			   			 		
			   			 		false //event bubbling, not the capturing
			   			 	);
			   			}
			   			
			   			if(methodContext.inContextMenu){
			   				contextMenuMethods[contextMenuMethods.length] = methodContext;
			   			}

				   }
				   
				   if(contextMenuMethods.length > 0){
					   
					   var menuItems = [];
						
					   for(var i=0; i<contextMenuMethods.length; i++){
						   var serviceMethodContext = contextMenuMethods[i];
					   		var command = "mw3.call("+objectId+", '"+serviceMethodContext.methodName+"')";
					   		
					   		if(serviceMethodContext.needToConfirm)
					   			command = "if (confirm(\'Are you sure to "+serviceMethodContext.displayName+" this?\'))" + command;
					   		
						   var menuItem = { 
								   text: serviceMethodContext.displayName + (serviceMethodContext.keyBinding ? '(' + serviceMethodContext.keyBinding[0] + ')' : ''), 
								   onclick: { fn: 
									   function(){
									   		eval(this._oOnclickAttributeValue.command);
									   },
									   command: command
								   } 
						   };
						   
						   menuItems[menuItems.length] = menuItem;
					   }
					   
//					   theDiv.append("<div id='contextmenu_" + objectId + "'></div>");
					   
					   YAHOO.util.Event.onContentReady(targetDivId, function () {
							new YAHOO.widget.ContextMenu(
								"_contextmenu_" + objectId,
								{
									zindex: 99,
									trigger: theDiv[0],
									itemdata: menuItems,
									lazyload: true
								}
							);
					   });
				   }
					
					
					
					
					
					
					
					
					
					
					
					
					//load scripts if there is.
//					var directFaceName = 'faces/' + objectTypeName.split('.').join('/') + ".ejs";
//					this._importFaceHelper(actualFace, //try to load face helper by using the class name directly first
//							function(){
							//mw3._importFaceHelper(actualFace)
//							}, 
//							directFaceName
//					);

					mw3.getFaceHelper(objectId);			
					//end

					return objectId;
				}
			
			Metaworks3.prototype._importFaceHelper = function(actualFace, onError){

				var initializingFaceHelper = function() {
					// console.debug("initializingFaceHelper --> " + actualFace);
					
					//TODO: may cause unnecessary javascript object creation - performance & memory waste
					//mw3.onLoadFaceHelperScript(actualFace);
					
					mw3.onLoadFaceHelperScript();					
					mw3.loaded = true;
				}

				var byClassLoader = actualFace.indexOf('dwr') == 0;
					
				var url = this.base + (byClassLoader ? '/':'/metaworks/') + actualFace + ".js";
				
//				   $.ajax({
//				        url: url,
//				        type:'GET',
//				        success:function(content,code)
//				        {
								
			   	// set call order
				var faceHelperIndex = 0;				
				if(this.afterLoadFaceHelperCount > 0){
					for(var i in this.afterLoadFaceHelper){
						if(this.afterLoadFaceHelper[i] != null && this.afterLoadFaceHelper[i] == actualFace){
							faceHelperIndex = i;
						}
					}
				}
				
				if(faceHelperIndex == 0){
					this.afterLoadFaceHelperCount++;
					
					faceHelperIndex = this.afterLoadFaceHelperCount;						
				}
				this.afterLoadFaceHelper[faceHelperIndex] = actualFace;

				// import script				
				if(!mw3.importScript(url, initializingFaceHelper))
					return false;
				
				var startPos = 0;

				var faceHelper;
				
				if(byClassLoader){
					
					if(actualFace.indexOf('genericfaces') != -1){
						startPos = 'dwr/metaworks/genericfaces/'.length;
						
					}else{
						startPos = 'dwr/metaworks/'.length;
					}
					
					faceHelper = actualFace.substr(startPos, actualFace.lastIndexOf('.') - startPos).split('/').join('_');
				}else
					faceHelper = actualFace.substr(0, actualFace.lastIndexOf('.')).split('/').join('_');
				
				mw3.loadedScripts[actualFace] = faceHelper; //now the namespace may not cause any collision.
				
				return true;
//				        },
//				        error:function(){
//				            if(onError) 
//				            	onError();
//				        }
//				    });        

			}
			
			Metaworks3.prototype.importScript = function(scriptUrl, afterLoadScript){
				
				if(!this.loadedScripts[scriptUrl]){
				   var result = false;
				   
				   mw3.loadedScripts[scriptUrl] = scriptUrl;
				   
				   //TODO: asynchronous processing performance issues later on
				   $.ajax({
					   async:false,
					   url: scriptUrl,
					   type:'GET',
					   success:function(){
						   	result = true;
							
							// add script url
							var head= document.getElementsByTagName('head')[0];
							var script= document.createElement('script');
							script.type= 'text/javascript';
							script.src= scriptUrl;
							head.appendChild(script);
							
							if(afterLoadScript){
								script.onload = afterLoadScript;
								
								script.onreadystatechange = function() { //for IE
									if (this.readyState == 'complete' || this.readyState == 'loaded') {
										
										afterLoadScript;
									}
								};
							}
					   },
					   
					   error:function(xhr){
						
						   //TODO: looks undesired validation or something is happening guessing by 
						   //      the successful request is treated as an error. 
						   //      It probably harmful to performance, so someday it should be fixed.
						   
						   if(xhr.status=='200'){
							   this.success();
						   }
						   
						   //alert(e.message);
					   }
				   });
				   
				   return result;
				}
			}
			
			Metaworks3.prototype.importStyle = function(url){
				if(!this.loadedScripts[url])
				try{
					var head= document.getElementsByTagName('head')[0];
					var css= document.createElement('link');
					css.type= 'text/css';
					css.rel = 'stylesheet';
					css.href= url;
					head.appendChild(css);
					this.loadedScripts[url] = url;
				}catch(e){
					e=e;
				}
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
					var j=0;
					for(var i=0; i<clsNames.length; i++){
						returnValues[j++] = clsNames[i] + id;
						
						if(metadata.keyFieldDescriptor)
							returnValues[j++] = clsNames[i]; //add placeholder candidates without @id again 							
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
				
				// 2012-04-04 cjw java.lang.String 일 경우 faceHelper 호출을 위해 공백으로 초기화
				if(value == null && className == 'java.lang.String')
					value = '';
					
				this.newBeanProperty(objectId);

				this.objects[objectId] = value; //caches the values
				this._armObject(objectId, value); //empower the object !
				
				var objKey = this._createObjectKey(value);
				if(objKey)
					this.objectId_KeyMapping[objKey] = objectId;
				
				this._wireObject(value, objectId);

				var options = arguments[3];
				
				var metadata = this.getMetadata(className);
				
				var elementTag = 
					(options && options['htmlTag'] ? 
						options['htmlTag'] 
						: 
						( metadata.faceOptions && metadata.faceOptions['htmlTag'] ? 
								metadata.faceOptions['htmlTag'] : "div"
						)
					);
				
				var elementClass = (metadata.faceOptions && metadata.faceOptions['htmlClass'] ? " class='" + metadata.faceOptions['htmlClass'] + "'": "");
				
				
				html="<" + elementTag + elementClass + " id='"+divId+ "'" + (metadata && metadata.focusable ? " tabindex='"+objectId+"'" : "") + " className='" + className + "'>...  LOADING PROPERTY ...</div><div id='"+infoDivId+"'></div>";
				
//				html+="<div id='"+divId+ "'" + (metadata && metadata.focusable ? " tabindex='"+objectId+"'" : "") + " className='" + className + "'>...  LOADING PROPERTY ...</div><div id='"+infoDivId+"'></div>";
				
				//TODO: This kind of wierd lazy invocation is required due to the EJS template should be called with cascade order. but we can change this part someday. all the childs should render themselves and just adding the id attribute after adding something 
				html+="<" + "script>";
				html+="   mw3.showObjectWithObjectId('"+this.objectId+"','"+className+"', '#"+divId+"'"+(options ? ", "+ JSON.stringify(options) : "") +");"
				html+="<" + "/script>";

				if(arguments.length > 2 && arguments[2]){ //when locateObject method has been called for just positioning purpose not the html generation.
					var divId = arguments[2];
					
					if(options && options['prepend'])
						$(divId).prepend(html);
					else
						$(divId).append(html);
					
					this.targetObjectId = objectId; 
					
					return this;
				}
				
				//#DEBUG POINT
				return html;
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
					objectTypeName = ":dwr/metaworks/genericfaces/ArrayFace.ejs";
					
					if(value.length==0){ //append class info since emptry array doesn't contain any class info
						var existingObj = this.objects[objectId];
						if(existingObj.__className)
							objectTypeName = existingObj.__className + objectTypeName;
					}
				}else
					objectTypeName = ":dwr/metaworks/genericfaces/ObjectFace.ejs";
				
				//alert( dwr.util.toDescriptiveString(value, 5))
				
				
				// 2012-03-27 cjw destroy event
    			if(this.objects[objectId] && this.getFaceHelper(objectId) && this.getFaceHelper(objectId).destroy)
        			mw3.getFaceHelper(objectId).destroy();
        			
				var divId =  "#objDiv_" + objectId;
				
    			this._armObject(objectId, value); //let the methods and some special fields available
				this.objects[objectId] = value; //change the cached value
				this.faceHelpers[objectId] = null;
				
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
			
			Metaworks3.prototype.getObjectReference = function(){
				return this._createObjectRef(this.targetObjectId, getObject());
			}
			
			Metaworks3.prototype.removeObject = function(objectId){
				if(arguments.length == 0)
					objectId = this.targetObjectId;				
				
				var divId =  "#" + this._getObjectDivId(objectId);
				var infoDivId =  "#" + this._getInfoDivId(objectId);
				
				// 2012-04-04 cjw destory 호출 후 removeObject
    			if(this.objects[objectId] && this.getFaceHelper(objectId) && this.getFaceHelper(objectId).destroy)
    				this.getFaceHelper(objectId).destroy();
				
				$(divId).remove();
				$(infoDivId).remove();		
				
				this.objects[objectId] = null;
				this.faceHelpers[objectId] = null;
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
				
				var beanPaths = this.beanExpressions[parentObjectId];
				if(beanPaths)
				for(var propName in beanPaths){
					var beanPath = beanPaths[propName];

					var obj = this.objects[beanPath.valueObjectId];

					var objKey = this._createObjectKey(obj);
					this.objectId_KeyMapping[objKey] = null;
					

					this.objects[beanPath.valueObjectId] = null;					
					this.faceHelpers[beanPath.valueObjectId] = null;
					
					this.newBeanProperty(beanPath.valueObjectId);
				}
				
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
											
				if(e.lineNumber){
					if(e.lineText)
						var message = "["+actualFace+"] at line "+e.lineNumber+": "+e.lineText+": "+e.message;
					else
						var message = "["+actualFace+"] at line "+e.lineNumber+": "+e.message;
				}else
					var message = "["+actualFace+"] "+e.message;
				
				document.getElementById(this.errorDiv).innerHTML = "<span><font color=#FB7524>" + message + "</font></span>";
				document.getElementById(this.errorDiv).className = 'error';
			}
				
			Metaworks3.prototype.createInputId = function(objectId){
				
				return "_mapped_input_for_" + objectId;
			}
			
			Metaworks3.prototype.getInputElement = function(objectId, propName){
				var beanPaths = mw3.beanExpressions[objectId];
				if(!beanPaths) return null;
				
				var beanPath = beanPaths["." + propName];
				if(!beanPath) return null;
				
				var inputId = mw3.createInputId(beanPath.valueObjectId); //try object.resourceName.__objectId
				
				var inputElement = document.getElementById(inputId); //will be "mw3.getInputElement(object.resoureceName)"

				return inputElement;
			}
			
			Metaworks3.prototype.getChildObjectId = function(parentObjectId, propName){
				var beanPaths = mw3.beanExpressions[parentObjectId];

				var beanPath = beanPaths["." + propName];
				if(!beanPath) return null;
				
				return beanPath.valueObjectId;
				
			}
			
			
			Metaworks3.prototype.getObject = function(){
				var objectId;
				
				if(arguments.length == 0)
					objectId = this.targetObjectId;
				else if(arguments.length == 1)
					objectId = arguments[0];

				var value = null;
				

				var faceHelper = this.getFaceHelper(objectId);

				if(faceHelper && faceHelper.getValue){ //if there's face helper and there's customized getter exists, use it.
					value = faceHelper.getValue();
				}else{
					value = this.getObjectFromUI(objectId);
				}

				//Since we believe getObjectFromUI is inherently getting the right data.
				if(value==null)
					value = this.objects[objectId];
				
				//sometimes the armed object may be unarmed by the user-defined facehelper.getValue() method
				if(value && !value.__objectId)
					this._armObject(objectId, value);
				
				return value;
			}
			
			Metaworks3.prototype.getObjectFromUI = function(objectId){
				var objectId;
				
				var value = null;
				
				var tagId = this.createInputId(objectId);
				
				var inputTag = document.getElementById(tagId);
				if(inputTag) 
					value = dwr.util.getValue(tagId); //this would prohibit File object damaged

				var beanPaths = this.beanExpressions[objectId];
				if(beanPaths)
				for(var propName in beanPaths){
					var beanPath = beanPaths[propName];
					eval("this.objects[objectId]" + beanPath.fieldName + "=this.getObject('" + beanPath.valueObjectId + "')");
				}
				
				//if(!inputTag && !beanPaths){
				if(value==null)
						value = this.objects[objectId];
					
				//}
				
				//sometimes the armed object may be unarmed by the user-defined facehelper.getValue() method
				if(value && !value.__objectId)
					this._armObject(objectId, value);
				
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
					$(infoDivId).html("<img src='dwr/metaworks/images/circleloading.gif'>");

					var object = this.getObject();
					var functionName = object.__className.replace('.', '_') + "_" + methodName;
					eval(functionName+"('" + objectId + "')");
					
					// 2012-04-12 cjw showInfo 로 변경
					this.showInfo( methodName + " DONE." );					
					/*
					$(infoDivId).html("<font color=blue> " + methodName + " DONE. </font>");
					$(infoDivId).slideDown(500, function(){
						setTimeout(function() {
							$( infoDivId ).slideUp(500);
						}, 5000 );
					});
					*/
					
				}catch(e){
					$(infoDivId).html("<font color=red>Error: "+ e.message +" [RETRY]</font> ");
					
				}
			}
			
			Metaworks3.prototype.call = function (svcNameAndMethodName){
				mw3.loaded = false;
				
//				mw3.debug("call start");

				var objId;
				
				if(arguments.length > 1){
					objId = svcNameAndMethodName;
					svcNameAndMethodName = arguments[1];
				}else if(arguments.length ==1 ){
					objId = this.targetObjectId;
				}
				
				// 2012-04-14 cjw 재귀호출 막음
				// var getAgain = (arguments.length > 2 ? (arguments[2] ? arguments[2] : true) : true);
				var getAgain = (arguments.length > 2 ? arguments[2] : true);
				var sync = (arguments.length > 3 ? arguments[3] : false);
				
				

//				if(typeof objId == 'number'){ //check if number
	
					var objectFromUI = this.getObjectFromUI(objId);
					
					if(objectFromUI.__faceHelper && getAgain){				
						var object = mw3.getObject(objId);
					}else{
						var object = objectFromUI;
					}
//				}else{
//				object = objId; //TODO: readability is bad.
//			}
				
				//var thisMetaworks = this;
				var divId = "objDiv_" + objId;
				
				this.setWhen(this.WHEN_VIEW);
				
				if(svcNameAndMethodName.indexOf('.') == -1){ //if there only methodname has been provided, guess the service name

					var className = object.__className;
					//var serviceName = className.subtring(className.lastIndexOf('.')) + 'Service';
					//svcNameAndMethodName = serviceName + "." + svcNameAndMethodName;
					
					
					var objectMetadata = this.getMetadata(className);
					var serviceMethodContext;
					
					if(objectMetadata && objectMetadata.serviceMethodContextMap && objectMetadata.serviceMethodContextMap[svcNameAndMethodName]){
						serviceMethodContext = objectMetadata.serviceMethodContextMap[svcNameAndMethodName];
						if(serviceMethodContext){
							if(serviceMethodContext.callByContent == false){
								if(serviceMethodContext.payload){
									var objectForCall = {__className: object.__className, metaworksContext: object.metaworksContext};
									for(var i in objectMetadata.fieldDescriptors){
										var fd = objectMetadata.fieldDescriptors[i];
										
										if(serviceMethodContext.payload[fd.name])
											objectForCall[fd.name] = object[fd.name];
									
									}
									
									object = objectForCall;
									
								}else
								
									object = this._createKeyObject(object); //default option
							}else
							if(serviceMethodContext.except){
								var objectForCall = {__className: object.__className, metaworksContext: object.metaworksContext};
								
								for(var i in objectMetadata.fieldDescriptors){
									var fd = objectMetadata.fieldDescriptors[i];
									
									if(!serviceMethodContext.except[fd.name])
										objectForCall[fd.name] = object[fd.name];
//									else
//										objectForCall[fd.name] = null;
								
								}
								
								object = objectForCall;

							}


						}
					}

					var infoDivId = "#"+this._getInfoDivId(objId);
					
					if(serviceMethodContext.target!="none"){
						
						if(this.getFaceHelper(objId) && this.getFaceHelper(objId).startLoading){
							this.getFaceHelper(objId).startLoading();
						}else{
							$(infoDivId).css('display', 'block').html("<img src='dwr/metaworks/images/circleloading.gif' align=middle> LOADING ...");
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
					
					var objectKey = this._createObjectKey(object);
					
					//This lets the called object doesn't have identifier, it should be focused to be set result if the result is it's type.
					if(objectKey && objectKey.indexOf("@")==-1)
						this.objectId_KeyMapping[objectKey] = objId;
						
					
					this.metaworksProxy.callMetaworksService(className, object, svcNameAndMethodName, autowiredObjects,
							{ 
				        		callback: function( result ){
				        			// 2012-03-19 cjw 기존 소스가 ejs.js 생성자 호출 보다 늦게 method 값을 할당하여 맨위로 올림
				        			mw3.recentCallMethodName = svcNameAndMethodName;
				        			
				    				//alert("call.result=" + dwr.util.toDescriptiveString(result, 5))
//				    				mw3.debug("call result");

				        			if(result){

					        			if(serviceMethodContext.target=="none"){
					        				
					        				returnValue = result;
					        				
					        			}else if(serviceMethodContext.target=="self"){
					        				
					        				mw3.setObject(objId, result);
					        				
					        			}else if(serviceMethodContext.target=="popup"){

					        				//store the recently added object Id for recent opener
					        				mw3.recentOpenerObjectId = objId;

					        				mw3.popupDivId = 'popup_' + objId;
					        				$('body').append("<div id='" + mw3.popupDivId + "' style='z-index:10;position:absolute; top:50px; left:10px'></div>");
					        				mw3.locateObject(result, null, '#' + mw3.popupDivId).targetDivId;
					        				
					        				//objId = mw3.targetObjectId;
					        			}else if(serviceMethodContext.target=="stick"){
							    			mw3.popupDivId = 'stick_' + objId;
					        				$('body').append("<div id='" + mw3.popupDivId + "' style='z-index:10;position:absolute; top:" + mw3.mouseY + "px; left:" + mw3.mouseX + "px'></div>");
					        				mw3.locateObject(result, null, '#' + mw3.popupDivId);

					        				//store the recently added object Id for recebt opener
					        				mw3.recentOpenerObjectId = objId;

					        				//objId = mw3.targetObjectId;
					        				
					        			}else if(serviceMethodContext.target=="opener" && mw3.recentOpenerObjectId){
					        				
					        				mw3.setObject(mw3.recentOpenerObjectId, result);
					        				
					        			}else{ //case of target is "auto"
					        			
	
					        				var results = result.length ? result: [result];
					        				
						        			var mappedObjId;
					        				for(var j=0; j < results.length; j++){
					        					
					        					var result_ = results[j];
							        			var objKeys = mw3._createObjectKey(result_, true);

							        			var neverShowed = true;
							        			
							        			
							        			if(objKeys && objKeys.length){
						        									        				
							        				for(var i=0; i<objKeys.length && neverShowed; i++){
							        					
								        				mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
								        				
								        				var mappedObjdivId = "objDiv_" + mappedObjId;
								        				
//								        				mw3.debug("render start");
								        		
								        				
								        				if(mappedObjId && document.getElementById(mappedObjdivId)){ //if there's mappedObjId exists, replace that div part.
								        					
								        					if(serviceMethodContext.target=="append"){
								        						mw3.locateObject(result_, null, "#"+mappedObjdivId);
								        					}else if(serviceMethodContext.target=="prepend"){
																mw3.locateObject(result_, null, "#"+mappedObjdivId, {prepend: true});
															}else{
								        						mw3.setObject(mappedObjId, result_);
								        					}
								        					
									        				neverShowed = false;
								        				}
							        				}
							        			}
							        			
	
							        			if(neverShowed){
//							        				mw3.debug("render again");

							        				if(serviceMethodContext.target=="append"){
						        						mw3.locateObject(result_, null, "#"+divId);
						        					}else if(serviceMethodContext.target=="prepend"){
														mw3.locateObject(result_, null, "#"+divId, {prepend: true});
													}else{
						        						mw3.setObject(objId, result_);
						        					}
						        					
						        					neverShowed = false;
							        			}
					        				}
					        				
					        				if(neverShowed){
//						        				mw3.debug("render again again");
					        					if(serviceMethodContext.target=="append"){
					        						mw3.locateObject(result, null, "#"+divId);
					        					}else if(serviceMethodContext.target=="prepend"){
													mw3.locateObject(result, null, "#"+divId, {prepend: true});
												}else{
					        						mw3.setObject(objId, result);
					        					}
					        				}
					        			}

				        			}

				        			//after call the request, the call-originator should be focused again.
				        			var sourceObjectIdNewlyGotten = mw3.objectId_KeyMapping[objectKey];
				        			if(sourceObjectIdNewlyGotten){
				        				// 2012-03-21 cjw 자동 focus 를 하지 않기 위해 수정
				        				//$("#objDiv_" + sourceObjectIdNewlyGotten).focus();
				        				//objId = sourceObjectIdNewlyGotten;
				        			}
				        			
				        			if(serviceMethodContext.target != "none"){
				        				mw3.onLoadFaceHelperScript();
					        			
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
					        			
				        			}
				        			
				        			mw3.loaded = true;				        			
				        			
				        			if(mw3.afterCall)
				        				mw3.afterCall(svcNameAndMethodName, result);
				        		},

				        		async: !sync && serviceMethodContext.target!="none",
				        		
				        		errorHandler:function(errorString, exception) {
				        			if(serviceMethodContext.target=="none")
				        				throw exception;
				        			
				        			if(mw3.objects[objId] && mw3.getFaceHelper(objId) && mw3.getFaceHelper(objId).showError){
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

					
					//CollectGarbage();
					
					if(serviceMethodContext.target=="none"){
						return returnValue;
					}
					
//					mw3.debug("call render done");
					
					if(serviceMethodContext.target=="popup" || serviceMethodContext.target=="stick")
						return this;

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
    			
			   for(var methodName in objectMetadata.serviceMethodContextMap){
			   		var methodContext = objectMetadata.serviceMethodContextMap[methodName];
			   		
			   		if(methodContext.clientSideCall)
			   			eval("object['"+methodName+"'] = function(){return mw3.clientSideCall(this.__objectId, '"+methodName+"');}");
			   		else{
			   			eval("object['"+methodName+"'] = function(getAgain){return mw3.call(this.__objectId, '"+methodName+"', getAgain);}");			   			
			   		}
			   }
			   
			   
			   object['__toString'] = function(){
				   
				   var metadata = objectMetadata;
				   var nameFieldValue = this;
				   
				   while(metadata && metadata.nameFieldDescriptor!=null){
					   nameFieldValue = nameFieldValue[metadata.nameFieldDescriptor.name];
					   
					   if(typeof nameFieldValue == 'string'){
						   return nameFieldValue;
						   
					   }else{
						   if(nameFieldValue && nameFieldValue.__className){
							   metadata = mw3.getMetadata(nameFieldValue.__className);
						   }else
							   metadata = null;
					   }
					   
				   }
			   
				   return objectMetadata.displayName;
				   
			   }
			   
			   object['__getFaceHelper'] = function(){
				   return mw3.getFaceHelper(this.__objectId);
			   }
			   
			}

			//TODO: looks better than _armObject
//			Metaworks3.prototype.armObject = function(object){
//				if(!object || !object.__className) return;
//				
//				if(object.__className=="Number" || object.__className=="String"){
//					return; 
//				}
//
//					
//				var objectMetadata = this.getMetadata(object.__className);
//    			
//			   for(var methodName in objectMetadata.serviceMethodContexts){
//			   		var methodContext = objectMetadata.serviceMethodContexts[methodName];
//			   		
//			   		if(methodContext.clientSideCall)
//			   			eval("object['"+methodName+"'] = function(){return mw3.clientSideCall(this, '"+methodName+"');}");
//			   		else
//			   			eval("object['"+methodName+"'] = function(getAgain){return mw3.call(this, '"+methodName+"', getAgain);}");
//			   }
//			   
//			   object['__toString'] = function(){
//				   
//				   if(objectMetadata.nameFieldDescriptor!=null){
//					   var nameFieldValue = this[objectMetadata.nameFieldDescriptor.name];
//					   
//					   if(nameFieldValue && nameFieldValue.__toString){
//						   return nameFieldValue.__toString();
//					   }else{
//						   return nameFieldValue;
//					   }
//				   }else{
//					   return objectMetadata.displayName;
//				   }
//			   }
//			   
//			}

			
			function showupInstruction(methodDivId, instruction, options){
				   	var methodDiv = $("#" + methodDivId);

				   	$('body').append("<div id='instruction' style='width:281px;height:79px;background-image:url(\"dwr/metaworks/images/waveStyle/instruction.png\");z-index:100;position:absolute; visibility: \"\"; top:"+ (methodDiv.offset().top - 50) +"px; left:"+ (methodDiv.offset().left + methodDiv.children()[0].offsetWidth - 10) +"px'> <table width=100% height=100% cellpadding=10><td width=10px></td><td><center><font color=white><b>" + instruction + "</b></font></center></td></table></div>");

					//$("#instruction").css({"top": methodDiv.offset().top + "px", "left": (methodDiv.offset().left + methodDiv.children()[0].offsetWidth + 10) + "px" });
//					$("#instruction").slideDown(500);
					
					$( "#instruction" ).effect( 'pulsate', 800 );
					
					if(options && options.onclick)
						$("#instruction").onclick(function(){options.onclick();});
					
//					setTimeout(function(){
//						$( "#instruction" ).effect( 'shake',300 );
//						
//						setTimeout(function(){
//							$( "#instruction" ).effect( 'shake',300 );
//						},700);				
//					},700);		

			}


			Metaworks3.prototype.test = function(objectId, testName, options){
				  $( "#instruction" ).slideUp(500, function(){
					  $('#instruction').remove();							  
				  });
				  

				var guidedTour = options && options['guidedTour'];
				
				if(options==null)
					options = {};
				
				if(!options['scenarioName'])
					options['scenarioName'] = testName;
				
				var value = this.objects[objectId];
				
				var objectMetadata = this.getMetadata(value.__className); 
				
//				var testStarter;
				
				
				if(!this.tests[value.__className]){
					
					this.tests[value.__className] = {};
					var testsForTheClass = this.tests[value.__className];
					
					for(var i=0; i<objectMetadata.fieldDescriptors.length; i++){
						var fieldDescriptor = objectMetadata.fieldDescriptors[i];
						if(fieldDescriptor.attributes){
							var scenario = fieldDescriptor.attributes['test'];
							
							if(scenario){
								for(var scenarioName in scenario){
									var test = scenario[scenarioName];
									
									test['fieldName'] = fieldDescriptor.name;
									
									testsForTheClass[scenarioName + "." + fieldDescriptor.name] = test;
									if(test.starter){
										testsForTheClass[testName] = test;
									}
								}
							}
							
						}
						
					}
					
				   for(var methodName in objectMetadata.serviceMethodContexts){
				   		var methodContext = objectMetadata.serviceMethodContexts[methodName];
				   		
				   		if(methodContext.attributes){
							var scenario = methodContext.attributes['test'];
							
							if(scenario){
								
								for(var scenarioName in scenario){
									var test = scenario[scenarioName];
								
									test['methodName'] = methodContext.methodName;
									testsForTheClass[test.scenario + "." + methodContext.methodName + "()"] = test;
									if(test.starter){
										testsForTheClass[testName] = test;
									}
								}
								
							}
							
						}
				   		
				   }
				}
				
				   
			   var testsForTheClass = this.tests[value.__className];
			   
			   if(testName.indexOf(".") == -1 && options && options.scenarioName && options.scenarioName!=testName){
				   testName = options.scenarioName + "." + testName;
			   }
			   
			   var test = testsForTheClass[testName];
			   
			   if(test==null){ alert('test is null');
			   
			   		return;
			   
			   }
			   
			   var next = (test.next && test.next[0] ? test.scenario + "." + test.next[0] : null);

			   if(test.fieldName){

				   if(guidedTour){
						var beanPaths = this.beanExpressions[value.__objectId];
						if(beanPaths){
							var beanPath = beanPaths["."+test.fieldName];

						   var fieldDivId = "objDiv_" + beanPath.valueObjectId;
						   //var fieldDiv = $("#" + fieldDivId);
						   
						   var instruction = (test.instruction && test.instruction[0] ? test.instruction[0] : "Enter Here");
							
						   if(next)
						   //next = "\""+next+"\"";
							   instruction = instruction + "<input type=button value='Next' onclick=\"mw3.test(" + value.__objectId + ", '" + next + "',{guidedTour:true})\">";
						   else{
							   instruction = instruction + "<input type=button value='Done !' onclick=\"$('#instruction').remove()\">";
							   
						   }
						   
						   
						   setTimeout(
								function(){
							   		showupInstruction(
						   				fieldDivId, 
						   				instruction
//						   				, 
//							   			{
//									   		onclick: function(){
//									   			mw3.test(, next, options);
//									   		}
//										}
							   		);
						   		}, 
						   		500
						   	);
						   
						}
				   }else{
					   
					   if(test.value!=null){					   
						   value[test.fieldName] = eval(test.value[0]);
						   this.setObject(objectId, value);
					   }

					   if(next && next.indexOf("autowiredObject.") >= 0){
					   		var posLastDot = next.lastIndexOf(".");
					   		
					   		var prefixLength = "autowiredObject.".length + next.indexOf(".") + 1;
					   		
					   		var className = next.substr(prefixLength, posLastDot - prefixLength);
					   		var methodName = next.substr(posLastDot + 1);
						   						   
						   value = this.getAutowiredObject(className);
						   
						   this.test(value.__objectId, methodName, options);
						   
					   } else{
						   value = this.objects[objectId];

						   this.test(value.__objectId, next, options);

					   }					   


				   }


					   
			   }else{
				   
				   
				   var returnValue;
				   
				   if(test.methodName){

					   if(guidedTour){
						   
						   var methodDivId = "method_" + value.__objectId + "_" + test.methodName;
						   var methodDiv = $("#" + methodDivId);
						   
						   
						   var instruction = (test.instruction && test.instruction[0] ? test.instruction[0] : "Click Here");

							
							setTimeout("showupInstruction('"+methodDivId+"','"+ instruction +"')", 700);
						  
					   }else{
						   
							  /// installing the call handler to continue after the call 
							  mw3.afterCall = function(methodName, result){
								  
								  if(methodName != test.methodName) return;


								   var next = (test && test.next && test.next[0] ? test.scenario + "." + test.next[0] : null);
								   
								   if(next==null){  // detect end!
									   mw3.afterCall = null;
									   
									  $( "#instruction" ).slideUp(500, function(){
										  $('#instruction').remove();							  
										   //TODO: done message should be here!
										   alert('Congratulations! Your guided Tour has been finished.');
									  });

									   return;
								   }
								   
								   if(next && next.indexOf("returnValue.") >= 0){
									   if(returnValue.metaworksMetadata){
										   value = mw3.objects[this.targetObjectId];
										   
									   }else{
										   value = returnValue;
									   }
									   
									   //next = next.substr("returnValue.".length);
									   
									   mw3.test(value.__objectId, testName, options);
								   }else if(next && next.indexOf("autowiredObject.") >= 0){
								   		var posLastDot = next.lastIndexOf(".");
								   		
								   		var prefixLength = "autowiredObject.".length + next.indexOf(".") + 1;
								   		
								   		var className = next.substr(prefixLength, posLastDot - prefixLength);
								   		var methodName = next.substr(posLastDot + 1);
								   
									   value = this.getAutowiredObject(className);
									   
									   mw3.test(value.__objectId, methodName, options);
									  
								   } else{
									   value = mw3.objects[objectId];
									   mw3.test(value.__objectId, next, options);
								   }
								   
								  
								   //mw3.afterCall = null;
							  };
						   
						   returnValue = this.call(value.__objectId, test.methodName, true, true); //sync call
					   }
					   
					   
					   

				   }
				   
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

							if(fieldValue && fieldValue.__className){
								var objectMetadata = this.getMetadata(fieldValue.__className); 
								
								for(var i=0; i<objectMetadata.superClasses.length; i++){
									var className = objectMetadata.superClasses[i];
									
									this.objectId_ClassNameMapping[className] = {value: fieldValue, __isAutowiredDirectValue: true};
								}
							}
							
						}
					}
				}
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
				
				//TODO: its kind of dummy.
				//mw3.loadFaceHelper(objectId);
	 			
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
			
			
			Metaworks3.prototype.isHidden = function(fd){
				if(fd.attributes){
					if(fd.attributes['hidden.when']){
						return (fd.attributes['hidden.when'] == this.when);
					}
						
					if(fd.attributes['show.when']){
						return (fd.attributes['show.when']!= this.when);
					} 
					
					if(fd.attributes['available.when']){
						return (fd.attributes['available.when'][this.when]==null);
					} 
					
					if(fd.attributes['available.where']){
						return (fd.attributes['available.where'][this.where]==null);
					} 
					
					if(fd.attributes['hidden']) 
						return true;
										
				} 
				
				return false;
			}
			
			Metaworks3.prototype.isHiddenMethod = function(method){
				
				if(method.methodContext.when != mw3.WHEN_EVER){
		   			if( (mw3.when && (method.methodContext.when.indexOf(mw3.when) == -1) ) 
		   					||
			   			(mw3.where && (method.methodContext.where!='wherever' && method.methodContext.where.indexOf(mw3.where) == -1) )
			   				
		   			)
	   				return true;
				}
				
	   			return false;
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
				if(mw3.isHidden(this.fieldDescriptor))
					return "";
				
				var html;
				
				var face;
				
				var value = this.object[this.fieldDescriptor.name];
				
				var className = value ? value.__className : null;
				
				if(value!=null && className==null){
					if(value.split)
						className = "java.lang.String";
					else
					if(typeof value == 'number')
						className = "java.lang.Number";
				}
				
				if(!className)
					className = this.fieldDescriptor.className;
					
				if(mw3.when == mw3.WHEN_VIEW)
					face = className + (this.fieldDescriptor.viewFace ? ":" +this.fieldDescriptor.viewFace : "");
				else
					face = className + (this.fieldDescriptor.inputFace ? ":" +this.fieldDescriptor.inputFace : "");

				var oldContext = mw3.getContext();
				if(context!=null){
					//mw3.setContext(context);
					
				}
				
				var when = mw3.when;

				if(context!=null && context.when){
					when = context.when
				}
				
				var designMode = (when == "__design");
				var designModeDepth2 = (when == "__design-depth2");
				
				if(this.fieldDescriptor.attributes){
					
					if(this.fieldDescriptor.attributes['resource']){
						if(designModeDepth2){
							when = mw3.WHEN_EDIT; //TODO: should not work for inner objects recursively.
						}else{
							when = mw3.WHEN_VIEW;
						}
						
						value = this.fieldDescriptor.attributes['resource'];
					}

					if(this.fieldDescriptor.attributes['noneditable'])
						when = mw3.WHEN_VIEW;
				}
				
				
				
				if(!designMode){ //means general mode
					var options = {when: when, descriptor: this.fieldDescriptor};
					if(context && context['htmlTag']) options['htmlTag'] = context['htmlTag'];
					
					html = mw3.locateObject(value, face, null, options);
				}else if(!designModeDepth2){ //means just design mode
					
					var options = {when: "__design-depth2", descriptor: this.fieldDescriptor};
					if(context && context['htmlTag']) options['htmlTag'] = context['htmlTag'];
					
					html = mw3.locateObject(value, face, null, options);
				}else // means this fields is within the designee 
					html = this.fieldDescriptor.displayName + " Here.";
				
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
		   			if( (mw3.when && (this.methodContext.when.indexOf(mw3.when) == -1) ) 
		   					||
			   				(mw3.where && (this.methodContext.where!='wherever' && this.methodContext.where.indexOf(mw3.where) == -1) )
			   				
		   			)
		   				return "";

		   			
		   		var template;
		   		if(arguments.length == 1){
		   			template = arguments[0];
		   		}else{
		   			template = "dwr/metaworks/genericfaces/MethodFace.ejs";//"<input type=button value='<%=methodName%>' onclick=\"mw3.call(<%=objectId%>, '<%=methodName%>')\">";
		   		}
		   		
		   		var contextValues = {
					mw3					: mw3, 
					objectId			: this.objectId, 
					method				: this,
					methodName			: this.methodContext.methodName,
					displayName			: this.methodContext.displayName,
					methodContext		: this.methodContext,
					object				: this.object
				}
		   		
				var templateEngine = new EJS({url: mw3.base + (template.indexOf('dwr') == 0 ? '/':'/metaworks/') + template, context: contextValues});
				
				var html = templateEngine.render(contextValues);		   		
		   		
		   		/*
				var template = new EJS({url: mw3.base + (template.indexOf('dwr') == 0 ? '/':'/metaworks/') + template, context: contextValues});
				template.debug_mode = true;
				
				var html = template.render(contextValues);
				*/
		   		
				html = "<div id=method_" + this.objectId + "_" + this.methodContext.methodName + ">" + html + "</div>";
				
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


			function getInternetVersion(ver) { 
				var rv = -1; // Return value assumes failure.      
				var ua = navigator.userAgent;  
				var re = null;
				if(ver == "MSIE"){
					re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
				}else{
					re = new RegExp(ver+"/([0-9]{1,}[\.0-9]{0,})");
				}
				if (re.exec(ua) != null){ 
					rv = parseFloat(RegExp.$1);
				} 
				return rv;  
			} 

			//브라우저 종류 및 버전확인  
			function browserCheck(){
				var name = "";
				var ver = 0;
				
				if(navigator.appName.charAt(0) == "N"){ 
					if(navigator.userAgent.indexOf("Chrome") != -1){
						name = "Chrome";						
					}else if(navigator.userAgent.indexOf("Firefox") != -1){
						name = "Firefox";
					}else if(navigator.userAgent.indexOf("Safari") != -1){
						name = "Safari";
					}
				}else if(navigator.appName.charAt(0) == "M"){
					name = "MSIE";
				}
				
				ver = getInternetVersion(name);
				
				return name + ' ' + ver;
			} 			
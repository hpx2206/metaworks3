var Metaworks3 = function(errorDiv, dwr_caption, mwProxy){
				this.fn = {};
				
				this.metaworksMetadata = new Array();
				this.metaworksProxy = mwProxy;
				this.errorDiv = errorDiv;
				this.objectId = 0;
				this.dwrErrorDiv = dwr_caption;
				
				this.templates = {};
				
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
				
				this.MEDIA_PC = "pc";
				this.MEDIA_MOBILE = "mobile";
				this.MEDIA_EVER = "wherever"
				this.media = this.MEDIA_PC;
				
				this.DEVICE_MOBILE = "mobile";
				this.DEVICE_PC = "pc";
				this.DEVICE_EVER = "wherever";
				this.device = this.DEVICE_PC;
				
				this.SCROLL_OPTION_1 = "1";
				this.SCROLL_OPTION_N = "n";
				this.scrollOption= this.SCROLL_OPTION_1;  //N...
				this.MESSAGE_LOADING = '...  LOADING PROPERTY ...';
				
				this.needToConfirmMessage = null;
						
				this.base = "";
				
				this.objects = {};
				this.metaworksContexts = {};
				this.beanExpressions = {};
				
				this.objectId_KeyMapping = {};
				this.objectId_ClassNameMapping = {};
				
				this.face_ObjectIdMapping = {};
				this.objectIds_FaceMapping={};
				
				this.loaded = false;
				this.loadedScripts = {};				
				
				this.tests = {};
				
				this.optimizeObjectMemory = false;

				
				this.afterCall = function(methodName, result){
					
				};

				
				this.targetObjectId;
				
				this.faceHelpers = {};
				
				this.mouseX = 0;
				this.mouseY = 0;
				
				this._metadata_version = 0;

				this.recentCallMethodName = null;
				this.recentCallObjectId = null;
				
			    this.popupDivId;
			    this.recentOpenerObjectId = [];
			    
			    this.browser = this.browserCheck();
			    	
			    this.afterLoadFaceHelper = {};
			    this.afterLoadFaceHelperCount = 0;
			    
			    this.isRecordingSession = false;
			    this.recording = [];
			    
			    this.recordingExceptClasses = {};
			    
			    this.testSet = {};
			    
			    this.dragging = false;
			    this.dragStartX = 0;
			    this.dragStartY = 0;
			    
			    /*
			     * metaworks service array
			     */
			    this.metaworksServices = [];
			    
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
			    var mouseUp = function(e){
			    	mw3.mouseX = e.pageX;
	    			mw3.mouseY = e.pageY;
	    			
	    			mw3.dragStartX = 0;
   			 		mw3.dragStartY = 0;
   					
   					if(mw3.dragObject && mw3.dragging){
   						$(mw3.dragObject).trigger('dragend');
   						
   						if(mw3.dragObject['dropCommand']){
   							mw3.dragObject['dropCommand'] = null;
   						}
   						
   						var objectId = mw3.dragObject['objectId'];
   						if(objectId && mw3.objects[objectId]){
   							var typeName = mw3.objects[objectId].__className;
   	 						$(".onDrop_" + typeName.split('.').join('_')).css("border-width", "").css("border","");		
   						}
   					}

 					mw3.dragObject = null;
 					mw3.dragging = false;
 					
 					if($("#__dragGuide").length > 0)
 						$("#__dragGuide").hide();

			    };
			    
			    this.dragObject = null;
			    this.dragGuide = $("#__dragGuide");
			    
			    var mouseMove = function(e){
			    	var mouseX = e.pageX;
	    			var mouseY = e.pageY; 
	    			
// 					if(mw3.dragObject && (
// 							!(mw3.dragObject.style.left > mouseX-10 && mw3.dragObject.style.left < mouseX+10) || 
// 							!(mw3.dragObject.style.top > mouseY-10 && mw3.dragObject.style.top > mouseY +10)|| 
// 							mw3.dragObject.style.left + mw3.dragObject.style.width < mouseX ||
//  							mw3.dragObject.style.top + mw3.dragObject.style.height < mouseY
// 						)){
   			 		if( (mw3.dragStartX != 0 && (mouseX > mw3.dragStartX + 30 || mouseX < mw3.dragStartX - 30 )) || 
   			 			(mw3.dragStartY != 0 && (mouseY > mw3.dragStartY + 30 || mouseY < mw3.dragStartY - 30) )){
 						
 						if(!mw3.dragging){
 	 						if(console)
 	 							console.log('drag  start');
 							 	 				
 	 						$(mw3.dragObject).trigger('dragstart');
 	 						
 							if(!$("#__dragGuide")[0]){
 								$('body').append("<div id='__dragGuide' style='position:absolute;top:100px;left:100px;background=#000000;width=40px;height=20px;z-index:999999'></div>");
 							}
 							
 	 						$("#__dragGuide").html(mw3.dragObject.innerHTML);

 	 						if(mw3.dragObject['dragCommand']){
 	 							eval(mw3.dragObject['dragCommand']);
// 	 							mw3.dragObject['dragCommand'] = null;
 	 						}
 	 						
 	 						mw3.dragging = true;
 	 						
 	 						var objectId = mw3.dragObject['objectId'];
 	 						if(objectId && mw3.objects[objectId]){
 	 							var typeName = mw3.objects[objectId].__className;
 	 	 						$(".onDrop_" + typeName.split('.').join('_')).css("border-width", "1px").css("border-style", "dashed").css("border-color", "orange");		
 	 						} 							
 						}
 						
						$("#__dragGuide").css("left", mouseX+1);
 						$("#__dragGuide").css("top",mouseY);
 						$("#__dragGuide").show();

 						
 						
/* 						*/
 						
 						//mw3.dragGuide.show();
 						
// 						mw3.dragObject.style.position='absolute';
// 						mw3.dragObject.style.top = mw3.mouseX; 
// 						mw3.dragObject.style.left = mw3.mouseY;
 						
 					}
			    };
			    			    
			    
			    var keyUp = function(e){
	    			//console.debug(e.keyCode);
	    			
	    			// ESC
	    			if(e.keyCode == 27){
		    			if(mw3.popupDivId!=null){
	    					e.preventDefault();

	    					$("#" + mw3.popupDivId).remove();
	    					mw3.popupDivId = null;
	    					mw3.dragging = false;
	    					
	    					if(mw3.dragObject){
	 	 						var objectId = mw3.dragObject['objectId'];
	 	 						if(objectId && mw3.objects[objectId]){
	 	 							var typeName = mw3.objects[objectId].__className;
	 	 	 						$(".onDrop_" + typeName.split('.').join('_')).css("border-width", "").css("border","");		
	 	 						}
	    					}
	    					$("#__dragGuide").hide();
	    					
		    			}
		    			
	    			}
	    			
	    			if(e.keyCode == 84 && e.shiftKey && e.ctrlKey){
	    				if (console && console.log) console.log(JSON.stringify(mw3.templates));
	    			}
	    			
	    			if(e.keyCode == 123 && e.shiftKey){ //F12 -- let the recorder starts for testing automation
	    				
		    			if(mw3.isRecordingSession){
		    				
		    				if (console && console.log) console.log(JSON.stringify(mw3.recording));
		    				alert('Recording Done. See the logs for recorded JSON.');
		    				this.recording=[];
		    				mw3.isRecordingSession = false;
		    				
		    			}else{
		    				mw3.isRecordingSession = true;
		    				alert("Recording Started.");
		    			}
	    			}

	    			if((e.keyCode == 122 || e.keyCode == 121) && e.shiftKey){ //F11 -- tester prompt 
	    				
	    				var testJSON = prompt("Enter testing JSON:");
	    				mw3.testSet["__test__"] = eval(testJSON);
	    				
	    				if(e.keyCode == 122)
	    					mw3.startTest("__test__");
	    				else
	    					mw3.startTest("__test__", {guidedTour: true});
	    				
	    			}
			    }
			    
			    // eventListener 없을때 (ie6~ie8) 처리
			    if(document.addEventListener){
			    	document.addEventListener("mouseup",mouseUp,false);
			    	document.addEventListener("mousemove",mouseMove,false);
			    	document.addEventListener("keyup",keyUp,false);
			    }
			    
			    if(document.attachEvent){
			    	document.attachEvent("mouseup",mouseUp);
			    	document.attachEvent("mousemove",mouseMove);			    	
			    	document.attachEvent("keyup",keyUp);
			    }
			}

			Metaworks3.prototype.debug = function(argument, when){
				if(arguments.length > 1){					
					if(eval(when))
						$('#'+this.errorDiv).html("debugPoint: "+ argument)
				}else if(console)
					console.log('debugPoint: '+ argument);
				else
					alert('debugPoint: '+ argument);
			}

			
			Metaworks3.prototype.setBase = function(base){
				this.base = base;
			}
			
			Metaworks3.prototype.loadFaceHelper = function(objectId, actualface){
				//console.log('loadFaceHelper : ' + actualface);
				
//				if(!mw3.objects[objectId]){
//					return null;
//				}
				
//				var clsName = mw3.objects[objectId].__className;
//				
//				return this.loadFaceHelperWithClsName(clsName);
//				
//			}
//			
//			Metaworks3.prototype.loadFaceHelperWithClsName = function(clsName){
				
				/*
				 * 2012-04-05 cjw 임시 주석 처리
				 */
				/*
				if(this.objects[objectId]==null)
					return null;
				*/
				
				if(!this.face_ObjectIdMapping[objectId])					
					return null;
				
				var face = null;
				var className = null;

				
				for(var i=0; i<this.face_ObjectIdMapping[objectId].length; i++){
					if(this.face_ObjectIdMapping[objectId][i].face == actualface){
						face = this.face_ObjectIdMapping[objectId][i].face;
						className = this.face_ObjectIdMapping[objectId][i].className;

						break;
					}
				}
				
				// load faceHelper
				var faceHelperClass = this.loadedScripts[face];
				
				if(typeof faceHelperClass == 'undefined')
					return false;
				else if(faceHelperClass == null)
					return true;
				
				var thereIsHelperClass = false;
				try{
					//console.debug('eval faceHelper [' + objectId + '] -> ' + face);					
					eval(faceHelperClass);
					thereIsHelperClass = true;
					
					if(thereIsHelperClass){
						try{
							var faceHelper = eval("new " + faceHelperClass + "('" + objectId + "', '"+ className + "')");
							
							if(faceHelper){
								this.faceHelpers[objectId] = faceHelper;
	
								if(faceHelper && faceHelper.loaded){
									faceHelper.loaded();
								}
								
								if(this.objects[objectId]!=null)
									this.objects[objectId]['__faceHelper'] = faceHelper;
								
								return true;
							}
						}catch(faceHelperLoadException){
						
						console.log(faceHelperLoadException);
						
							//TODO:
							//mw3.showError(objectId, faceHelperLoadException.message, 'init');
							if(console){
								var errMsg = faceHelperClass;
								
								if(faceHelperLoadException.lineNumber)
									errMsg += '(line ' + faceHelperLoadException.lineNumber + ')';
								
								errMsg += ' : ' + faceHelperLoadException.message;

								console.log(errMsg);
							}
						}
					}
					
				}catch(e){
					//return null;
					//TODO :  error reporting required
					//this.debug(e, true);
					//if(window.console){
						//console.log("Failed to load face helper ("+faceHelperClass+"(ejs.js)): " + e);
						//console.log(e);						
					//}
				}
			}

			Metaworks3.prototype.onLoadFaceHelperScript = function(){
//				if(!target)
//					target = this.face_ObjectIdMapping;
				/*
				//console.debug('onLoadFaceHelperScript');
				
				if(this.`aceHelper[face]){
					objectIds = this.objectIds_FaceMapping[face];
									
					for(var objectId in objectIds){
						//console.debug(objectId);
						
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
							var targetElement = document.getElementById(mw3._getObjectDivId(objectId));
							
							if(targetElement == null || targetElement.innerHTML == mw3.MESSAGE_LOADING)
								break;
							
							var object = mw3.objects[objectId];
							
							// object attr apply
							var htmlAttr = (object && object.__options && object.__options['htmlAttr'] ? object.__options['htmlAttr'] : null);
							
							if(htmlAttr)
								$(targetElement).attr(htmlAttr);
							
							var htmlAttrChild = (object && object.__options && object.__options['htmlAttrChild'] ? object.__options['htmlAttrChild'] : null);
							if(htmlAttrChild)
								$(targetElement).children(':first').attr(htmlAttrChild);
							
							if(mw3.loadFaceHelper(objectId, face)){
								mw3.afterLoadFaceHelper[i] = null;
								mw3.objectIds_FaceMapping[face] = null;

								if(object!=null && object.__className){
									var metadata = mw3.getMetadata(object.__className);
									
									// optimizing the object size
									
				        			if(object!=null && this.optimizeObjectMemory && metadata){
				        				
					        			for(var i in metadata.fieldDescriptors){
											var fd = metadata.fieldDescriptors[i];
											
											if(!fd.isKey && (!fd.attributes || !fd.attributes['keepAtClient']) && object[fd.name]!=null){
												object[fd.name] = null; //remove after rendering for memory saving.
											}
										}
				        			}
				        			
				        			//console.log(object.__className);
				        			mw3.serviceMethodBinding(objectId, object.__className);
								}
							}
						}
		    		}
		    	}				
			};			
			
			Metaworks3.prototype.getFaceHelper = function(objectId){
				var registeredHelper = this.faceHelpers[objectId];
				
				return registeredHelper;
//				
//		TODO: 맨밖에서 부터 호출되게만 된다면 문제없음...
				
//				if(registeredHelper!=null)
//					return registeredHelper;
//				else{
//					this.loadFaceHelper(objectId);
//					
//					return this.faceHelpers[objectId];
//				}
//				
				
			};
			


			Metaworks3.prototype.setWhere = function(where){
				this.where = where;
			};

			Metaworks3.prototype.setWhen = function(when){
				this.when = when;
			};

			Metaworks3.prototype.setHow = function(how){
				this.how = how;
			};
			
			Metaworks3.prototype.setMedia = function(media){
				this.media = media;
			};

			Metaworks3.prototype.setDevice = function(device){
				this.device = device;
			};

			Metaworks3.prototype.setScrollOption = function(scrollOption){
				this.scrollOption = scrollOption;
			};

			
			Metaworks3.prototype.setContext = function(context){
				if(context.where!=null)
					this.setWhere(context.where);
				
				if(context.when != null){
					this.setWhen(context.when);
				}
				
				if(context.how != null){
					this.how = context.how;
				}
			};
			
			Metaworks3.prototype.getContext = function(){
				return {when: this.when, where: this.where, how: this.how};
			};
			
			Metaworks3.prototype.clearMetaworksType = function(objectTypeName){
				if(objectTypeName == "*"){
					this.metaworksMetadata = new Array();
					this.tests ={};
				}else
					this.metaworksMetadata[objectTypeName] = null;

				return function(){}; //for dwr dummy call
			};
			
			Metaworks3.prototype.requestMetadataBatch = function(result){
				
//				var classNamesInTheObject = {};
//				
//				var collectingProperties = function(object){
//					for(var propName in object){
//						if(propName=='__className')
//							classNamesInTheObject[classNamesInTheObject.length] = object.__className;
//							
//						if(propName=='__objectId' || propName=='_prototype_') continue;
//							
//						collectingProperties(object[propName]);
//					}
//				}
				
			};
			
			Metaworks3.prototype.importClasses = function(objectTypeNames){
			
				this.metaworksProxy.getMetaworksTypes(objectTypeNames, 
					{ 
		        		callback: function( webObjectTypes ){
		        			for(var i=0; i < webObjectTypes.length; i++)
		        				mw3._storeMetadata(webObjectTypes[i]);
						
		        		},

		        		async: true,
				
						timeout:10000, 
	                    
	                    errorHandler:function(errorString, exception) {
	                    } 
		    		}
				);
				
			};
			
			Metaworks3.prototype.getMetadata = function(objectTypeName){

				if(!objectTypeName || objectTypeName == null || objectTypeName.trim().length == 0) return;
				
				if(objectTypeName.length > 2 && objectTypeName.substr(-2) == '[]'){			//if array of some object type, use ArrayFace with mapped class mapping for the object type.
					return;
				}
				
				if(objectTypeName.length > 4 && objectTypeName.substr(0, 2) == '[L' && objectTypeName.substr(objectTypeName.length-1) == ';'){			//if array of some object type, use ArrayFace with mapped class mapping for the object type.
					return;
				}
				
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

			        			mw3._storeMetadata(webObjectType);
							
			        		},

			        		async: false,
					
							timeout:10000, 
		                    
		                    errorHandler:function(errorString, exception) {
		                        //alert(errorString);
		                    	throw new Error(exception.javaClassName + ": " + exception.message);
		                    	
		  						//document.getElementById(this.dwrErrorDiv).innerHTML = errorString;
		                    } 
			    		}
					);
				}
				
				var objectMetadata = this.metaworksMetadata[objectTypeName];
				
				return objectMetadata;
			};
				
			
			Metaworks3.prototype._storeMetadata = function(webObjectType){
				
				var objectTypeName = webObjectType.name;
				mw3.metaworksMetadata[objectTypeName] = webObjectType;
				
				webObjectType['version'] = mw3._metadata_version ++;
				
				for(var i=0; i<webObjectType.fieldDescriptors.length; i++){
					var fd = webObjectType.fieldDescriptors[i];
					
					var superClasses = webObjectType.superClasses;
					
					for(var superClsIdx = 0; superClsIdx<superClasses.length; superClsIdx++){
						var className = superClasses[superClsIdx];

						try{
						
							if(fd.displayName==null || fd.displayName.toUpperCase()==fd.name.toUpperCase()){
								var messageKey = className + "." + fd.name;
								var message = mw3.getMessage(messageKey);
								
								if(messageKey!=message){
									
									fd.displayName = message;
									
									break;
								}
								
							}
						}catch(e){}
					}
					
					
					if(!fd.attributes) continue;
					
					if(fd.attributes['namefield']){
						webObjectType['nameFieldDescriptor'] = fd;
					}else
					if(fd.attributes['children']){
						webObjectType['childrenFieldDescriptor'] = fd;
					}
					if(fd.attributes['icon']){
						webObjectType['iconFieldDescriptor'] = fd;
					}
					if(fd.attributes['typeSelector']){
						webObjectType['typeSelector'] = fd;
					}
					
					for(var attributeName in fd.attributes){
						if(attributeName.indexOf('descriptor.') == 0)
							webObjectType[attributeName.substring(attributeName.indexOf('.')+1) + 'FieldDescriptor'] = fd;
					}
					
					
					fd['getOptionValue'] = function(option, defaultValue){
						if(this.options!=null && this.values!=null)
						
						for(var i=0; i<this.options.length && i<this.values.length; i++){
							if(option==this.options[i])
								return this.values[i];
						}
						
						if(defaultValue)
							return defaultValue;
					};
					
					webObjectType['getFieldNameForFieldDescriptorType'] = function(fieldDescriptorType){
						var fieldDescriptorName = fieldDescriptorType + 'FieldDescriptor';
						
						if(this[fieldDescriptorName]){
							return this[fieldDescriptorName].name;
						}
					}
					
				}

				//following methods are not null, it will creates the lazy-loaded tree mechanism.
				
				var serviceMethodMap = {};
				
				if(webObjectType.serviceMethodContexts)
				for(var i=0; i<webObjectType.serviceMethodContexts.length; i++){
					var serviceMethod = webObjectType.serviceMethodContexts[i];
					
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
				webObjectType['getOptionValue'] = function(option, defaultValue){
						if(this.faceOptions && this.faceOptions[option])
							return this.faceOptions[option];
						else if(defaultValue)
							return defaultValue;
				};

			};
			
			
			Metaworks3.prototype.showObjectWithObjectId = function (objectId, objectTypeName, targetDiv, options){
				var object = this.getObject(objectId);
				
				return this.showObject(object, objectTypeName, {targetDiv: targetDiv, objectId: objectId, options: options});
			};
				
			
			Metaworks3.prototype._template = function(url, contextValues){
				var templateEngine;
				if(mw3.templates[url]){
					templateEngine = mw3.templates[url];
				}else{

					if(mw3.usingTemplateEngine == 'jQote'){
						$.ajax(url, 							
							{
								async: false, 
								success:function(tmpl) {
									templateEngine = tmpl;
								}
							}
						);
						
						$.jqotec(templateEngine, null, url);
						
						//templateEngine = new EJS({url: url});
						mw3.templates[url] = url;
						templateEngine = url;
						
						
					}else{
						templateEngine = new EJS({url: url});
						mw3.templates[url] = templateEngine;
					}

				}
				
				if(mw3.usingTemplateEngine == 'jQote'){
					return $.jqote(templateEngine, contextValues);
				}else{
					return templateEngine.render(contextValues);	
				}
				
			};
			

			
			Metaworks3.prototype.showObject = function (object, objectTypeName, target){
					var objectId;
					var targetDiv;
					var options;
					
					if(target.objectId){
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
					var currentContextWhere = this.where;
					var currentContextHow = this.how;
					
					
					if(object && object.metaworksContext){
						this.setContext(object.metaworksContext);
					}

					if(options && options['when']){
						this.setWhen(options['when']);
					}
					
					if(options && options['how']){
						this.setHow(options['how']);
					}
					
					/*
					 * TODO : matching ejs context and ejs.js context
					if(object && object.metaworksContext){
						if(!object.metaworksContext.when)
							object.metaworksContext.when = this.when;
						
						if(!object.metaworksContext.where)
							object.metaworksContext.where = this.where;
						
						if(!object.metaworksContext.how)
							object.metaworksContext.how = this.how;
					}
					*/

					this.metaworksContexts[objectId] = {when:mw3.when, how:mw3.how, where:mw3.where};

					/*
					 * 2013/12/18 cjw
					 * object 에 view 당시의 metaworksContext 와 option 을 저장한다.
					 */
					if(this.objects[objectId]){
						this.objects[objectId]['__metaworksContexts'] = {when:mw3.when, how:mw3.how, where:mw3.where};
						this.objects[objectId]['__options'] = options;
					}
					
					
					if(!actualFace && options && options['ejsPath']){
						metadata = this.getMetadata(objectTypeName);
					
						actualFace = options['ejsPath'];						
					}else if(!actualFace && objectTypeName.length > 2 && objectTypeName.substr(-2) == '[]'){			//if array of some object type, use ArrayFace with mapped class mapping for the object type.
						objectTypeName = objectTypeName.substr(0, objectTypeName.length - 2);
						metadata = this.getMetadata(objectTypeName);
						
						actualFace = metadata.faceForArray ? metadata.faceForArray : 'dwr/metaworks/genericfaces/ArrayFace.ejs';

					}else if(!actualFace && objectTypeName.length > 4 && objectTypeName.substr(0, 2) == '[L' && objectTypeName.substr(-1) == ';'){			//if array of some object type, use ArrayFace with mapped class mapping for the object type.
						objectTypeName = objectTypeName.substr(2, objectTypeName.length - 3);
						metadata = this.getMetadata(objectTypeName);
						
						actualFace = metadata.faceForArray ? metadata.faceForArray : 'dwr/metaworks/genericfaces/ArrayFace.ejs';

					}else{

						if(!actualFace && object && object.constructor && object.constructor.toString().indexOf('Array') != -1){
							if(metadata && metadata.faceForArray){
								actualFace = metadata && metadata.faceForArray ? metadata.faceForArray : 'dwr/metaworks/genericfaces/ArrayFace.ejs';
							}else{
								try{
									metadata = this.getMetadata(object[0].__className);
								}catch(e){}
								
								actualFace = metadata && metadata.faceForArray ? metadata.faceForArray : 'dwr/metaworks/genericfaces/ArrayFace.ejs';
							}
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
								
								if(			
											(faceMap.when == null 	|| faceMap.when == this.when)
										&& 	(faceMap.where == null 	|| faceMap.where == this.where)
										&& 	(faceMap.how == null 	|| faceMap.how == this.how)
									){
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
					this.face_ObjectIdMapping[objectId] = [];
					this.face_ObjectIdMapping[objectId].push(faceInfo);					
					
					if(this.objectIds_FaceMapping [actualFace] == null){
						this.objectIds_FaceMapping [actualFace]={};
					}
					
					this.objectIds_FaceMapping [actualFace][objectId] = faceInfo;
						
					//end

					var objectRef = this._createObjectRef(object, objectId);
					
					var descriptor = (options ? options['descriptor']: null);
					
					if(descriptor!=null){
						descriptor['getOptionValue'] = function(option, defaultValue){
							if(this.options!=null && this.values!=null)
							
							for(var i=0; i<this.options.length && i<this.values.length; i++){
								if(option==this.options[i])
									return this.values[i];
							}
							
							if(defaultValue)
								return defaultValue;
						}
					
						//only when the descriptor has some options, the object is given to access to it's descriptor among properties in it's parent object.
						// 2012-03-16 cjw descriptor null 오류로 위치 수정
						if(descriptor.options && object)
							object['__descriptor'] = descriptor;
					}
					
					if(actualFace.indexOf("genericfaces") == 0){ //TODO: will need to be optional
						actualFace = "dwr/metaworks/" + actualFace;
					}					
					mw3._importFaceHelper(actualFace);
					
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
							editFunction		: editFunction,
							options				: options								
						};
						
				   		contextValues['include'] = function(ejsPath){
				   			var actualFace = ejsPath;
				   			
							if(actualFace.indexOf("genericfaces") == 0){ //TODO: will need to be optional
								actualFace = "dwr/metaworks/" + actualFace;
							}
							
							var faceInfo = {
									face: actualFace,
									className: objectTypeName
							};
							
							mw3.face_ObjectIdMapping[objectId].push(faceInfo);
							
							if(mw3.objectIds_FaceMapping [actualFace] == null){
								mw3.objectIds_FaceMapping [actualFace]={};
							}							
							mw3.objectIds_FaceMapping [actualFace][objectId] = faceInfo;
							
							mw3._importFaceHelper(actualFace);
							
							var url = mw3.base + (actualFace.indexOf('dwr') == 0 ? '/':'/metaworks/') + actualFace;

							return mw3._template(url, contextValues);
				   		};
				   		
				   		
						var html = mw3._template(url, contextValues);
						
						if(targetDiv == null)							
							return html;
						
						//#DEBUG POINT
						$(targetDiv).html(html);
						
						
						
						// optimizing the object size : moved to onLoadFaceHelper()
						
//	        			if(object!=null && this.optimizeObjectMemory && metadata){
//	        				
//		        			for(var i in metadata.fieldDescriptors){
//								var fd = metadata.fieldDescriptors[i];
//								
//								if(!fd.isKey && (!fd.attributes || !fd.attributes['keepAtClient']) && object[fd.name]!=null){
//									object[fd.name] = null; //remove after rendering for memory saving.
//								}
//							}
//	        			}

						
						//load the key or mouse bindings, context menu 
						
						/*
						var contextMenuMethods = [];
						
						var targetDivId = this._getObjectDivId(objectId);
						var theDiv = $("#" + targetDivId);
						
						if(theDiv[0] && metadata)
					    for(var methodName in metadata.serviceMethodContextMap){
					   		var methodContext = metadata.serviceMethodContextMap[methodName];

						    if(this.isHiddenMethodContext(methodContext, object) && !methodContext.bindingHidden)
							   continue;

						    // make call method
				   			var command = "if(mw3.objects['"+ objectId +"']!=null) mw3.call("+objectId+", '"+methodName+"')";
							if(methodContext.needToConfirm)
							    command = "if (confirm(\'Are you sure to "+mw3.localize(methodContext.displayName)+" this?\'))" + command;


							   
							if(methodContext.eventBinding && methodContext.eventBinding.length > 0){
						    	for(var i=0; i<methodContext.eventBinding.length; i++){
						    		var eventBinding = methodContext.eventBinding[i];
						    		
			   						for(var j=0; j<methodContext.bindingFor.length; j++){
			   							var bindingFor = methodContext.bindingFor[j];
			   							var bindingDivId;
			   							
			   							if('@this' == bindingFor){
			   								bindingDivId = '#' + targetDivId;
			   							}else if('@page' == bindingFor){
			   								bindingDivId = 'document';
			   							}else{
			   								var bindingFieldId = this.getChildObjectId(objectId, bindingFor);
			   								
			   								bindingDivId = '#' + mw3._getObjectDivId(bindingFieldId);
			   							}
			   							
			   							$(bindingDivId).bind(eventBinding, {command: command}, function(event){
			   								eval(event.data.command);
			   							});
			   						}
						    	}
						    }
						    
				   			if(methodContext.keyBinding && methodContext.keyBinding.length > 0){
				   				for(var i=0; i<methodContext.keyBinding.length; i++){
				   					var keyBinding = methodContext.keyBinding[i];
				   					
				   					if(keyBinding.indexOf("@Global") > -1){
				   						 //
				   						 //2013-03-25 cjw
				   						 //global 일때 keyBinding 문제 해결 
				   						 // 
				   						keyBinding = keyBinding.substr(0, keyBinding.length - "@Global".length);
				   					
				   						shortcut.remove(keyBinding);
					   					shortcut.add(keyBinding, command);
				   					}else{
					   					shortcut.add(keyBinding, commandfunction() {
					   						eval(command);
					   					}//,{
					   						target: targetDivId
					   					});
				   					}
				   				}
				   			}
				   			
				   			//mouse binding installation
				   			if(methodContext.mouseBinding){
				   				var which = 3;
				   				if(methodContext.mouseBinding == "right")
				   					which = 3;
				   				else if(methodContext.mouseBinding == "left")
				   					which = 1;

				   				if(methodContext.mouseBinding == "drag"){
				   					
				   					theDiv[0]['dragCommand'] = command;
				   					theDiv[0]['objectId'] = objectId;
				   					
				   					var mousedown = function(e){
				   			 			e.preventDefault();
				   			 			mw3.dragObject = this;
					   			 		mw3.dragStartX = e.pageX;
					   			 		mw3.dragStartY = e.pageY;
				   			 			e.stopPropagation();
				   			 		};
				   			 		
				   				    if(document.addEventListener){
					   					theDiv[0].addEventListener("mousedown", mousedown);
				   				    };
				   				    
				   				    if(document.attachEvent){
					   					theDiv[0].attachEvent("mousedown", mousedown);				   				    	
				   				    };

				   				}//end of case 'drag start'
				   				else if(methodContext.mouseBinding == "drag-enableDefault"){
				   					theDiv[0]['dragCommand'] = command;
				   					theDiv[0]['objectId'] = objectId;
				   					
				   					var mousedown = function(e){
				   			 			mw3.dragObject = this;
					   			 		mw3.dragStartX = e.pageX;
					   			 		mw3.dragStartY = e.pageY;
				   			 			e.stopPropagation();
				   			 		};
				   			 		
				   				    if(document.addEventListener){
					   					theDiv[0].addEventListener("mousedown", mousedown);
				   				    };
				   				    
				   				    if(document.attachEvent){
					   					theDiv[0].attachEvent("mousedown", mousedown);				   				    	
				   				    };
				   				    
				   				}//end of case 'drag start'
				   				else if(methodContext.mouseBinding == "drop"){
				   					theDiv[0]['dropCommand'] = command;
				   					theDiv[0]['objectId'] = objectId;
				   									   					
				   					var mouseup = function(e){
   			 							if(mw3.dragging){
   			 								eval(this['dropCommand']);
   			 								//this['dropCommand'] = null;
   			 								mw3.dragging = false;
	   			  	 						var objectId = mw3.dragObject['objectId'];
	   			  	 						
	   			 	 						if(typeof objectId != 'undefined' && mw3.objects[objectId]){
	   			 	 							var typeName = mw3.objects[objectId].__className;
	   			 	 	 						$(".onDrop_" + typeName.split('.').join('_')).css("border-width", "").css("border","");	
	   			 	 	 						
	   			 	 	 						//this['objectId'] = null;
	   			 	 						}

	   			 	 						$(mw3.dragObject).trigger('drop');
	   			 	 						$(mw3.dragObject).trigger('dragend');
   			 							}
   			 						};
				   					
				   				    if(document.addEventListener){
					   					theDiv[0].addEventListener("mouseup", mouseup);
				   				    };
				   				    
				   				    if(document.attachEvent){
					   					theDiv[0].attachEvent("mouseup", mouseup);				   				    	
				   				    };
				   				    
				   				}//end of case 'drop'
				   				else if(methodContext.mouseBinding == "dblclick"){
				   					theDiv[0]['mouseCommand'] = command;

				   					var dblclick = function(e) {
					   						eval(this['mouseCommand']);
				   			 				e.stopPropagation(); //stops to propagate to parent that means consumes the event here.
				   			 		};
				   			 		
				   			 		
				   				    if(document.addEventListener){
					   					theDiv[0].addEventListener("dblclick", dblclick, false);
				   				    };
				   				    
				   				    if(document.attachEvent){
					   					theDiv[0].attachEvent("dblclick", dblclick);				   				    	
				   				    };
				   				    
				   				    
				   				    
//				   					if(document.addEventListener){
//					   					theDiv[0].addEventListener("dblclick", dblclick);
//				   				    };
//				   				    
//				   				    if(document.attachEvent){
//					   					theDiv[0].attachEvent("dblclick", dblclick);				   				    	
//				   				    };
				   				}//end of case 'dbl-click'
				   				//case of general mouse click
				   				else{
				   					theDiv[0]['mouseCommand' + which] = command;

				   					var mouseup = function(e) {
				   			 			
				   			 			if(e.which == which){
					   				    	mw3.mouseX = e.pageX;
					   		    			mw3.mouseY = e.pageY;
				   			 				
					   		    			// click(mouse right) is contextmenu block
				   			 				if(which == 3){
				   			 					document.oncontextmenu = function() { return false; };
				   			 				}
				   			 				
					   						eval(this['mouseCommand' + e.which]);
				   			 				e.stopPropagation(); //stops to propagate to parent that means consumes the event here.
				   			 			}
				   			 		};
				   			 		
				   				    if(document.addEventListener){
					   					theDiv[0].addEventListener("mouseup", mouseup, false);
				   				    };
				   				    
				   				    if(document.attachEvent){
					   					theDiv[0].attachEvent("mouseup", mouseup);				   				    	
				   				    };
				   				}
				   				
				   				
				   			} // end of mouse binding installation
				   			
				   			if(methodContext.inContextMenu){
				   				contextMenuMethods[contextMenuMethods.length] = methodContext;
				   			}

					   }
						
						
						
					   
					   //install context menu
					   if(contextMenuMethods.length > 0){
						   var menuItems = [];
						   
						   for(var i=0; i<contextMenuMethods.length; i++){							   
							   var serviceMethodContext = contextMenuMethods[i];
							   
							   
							   // 상태에 따른 visible 처리
								if(serviceMethodContext.when != mw3.WHEN_EVER){
						   			if( (mw3.when && !((serviceMethodContext.when.indexOf(mw3.when + ',') != -1) || (serviceMethodContext.when.indexOf(',' + mw3.when) != -1) || (serviceMethodContext.when == mw3.when))) 
						   					||
							   			(mw3.where && (serviceMethodContext.where!='wherever' && serviceMethodContext.where.indexOf(mw3.where) == -1) )
							   				
						   			)
						   				continue;
								}
							   
								
							   var command = "mw3.call("+objectId+", '"+serviceMethodContext.methodName+"')";
						   		
							   if(serviceMethodContext.needToConfirm)
						   			command = "if (confirm(\'Are you sure to "+mw3.localize(serviceMethodContext.displayName)+" this?\'))" + command;
						   		
							   var menuItem = { 
									   text: mw3.localize(serviceMethodContext.displayName) + (serviceMethodContext.keyBinding ? '(' + serviceMethodContext.keyBinding[0] + ')' : ''), 
									   onclick: { fn: 
										   function(){
										   		eval(this._oOnclickAttributeValue.command);
										   },
										   command: command
									   } 
							   };
							   
							   menuItems[menuItems.length] = menuItem;
						   }
						   
//						   theDiv.append("<div id='contextmenu_" + objectId + "'></div>");
						   
						   if(menuItems.length){
							   if(typeof $('#' + targetDivId).attr('contextMenu') == 'undefined'){
								   YAHOO.util.Event.onContentReady(targetDivId, function () {
									   new YAHOO.widget.ContextMenu(
											"_contextmenu_" + objectId,
											{
												zindex: 2000,
												trigger: theDiv[0],
												itemdata: menuItems,
												lazyload: true
											}
										);
								   });
								   
								   $('#' + targetDivId).attr('contextMenu', 'true');
							   }
						   }
					   }	
					   
					   */
					   
					} catch(e) {
						this.template_error(e, actualFace)
						return
					} finally{
						this.setWhen(currentContextWhen);
						this.setWhere(currentContextWhere)
						this.setHow(currentContextHow)

					}
					
					

					
					
					
					
					
					
					
					
					
					
					
					
					//load scripts if there is.
//					var directFaceName = 'faces/' + objectTypeName.split('.').join('/') + ".ejs";
//					this._importFaceHelper(actualFace, //try to load face helper by using the class name directly first
//							function(){
							//mw3._importFaceHelper(actualFace)
//							}, 
//							directFaceName
//					);

//					mw3.getFaceHelper(objectId);			
					//end

					return objectId;
				};
			
			Metaworks3.prototype._importFaceHelper = function(actualFace, onError){

				var initializingFaceHelper = function() {
					// console.debug("initializingFaceHelper --> " + actualFace);
					
					//TODO: may cause unnecessary javascript object creation - performance & memory waste
					//mw3.onLoadFaceHelperScript(actualFace);
					
					//mw3.onLoadFaceHelperScript();					
					//mw3.loaded = true;
				};

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
				if(typeof this.loadedScripts[url] == 'undefined'){
					if(!mw3.importScript(url, initializingFaceHelper)){
						mw3.loadedScripts[actualFace] = null;
						
						return;
					}
					
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
					
//					return true;
//					        },
//					        error:function(){
//					            if(onError) 
//					            	onError();
//					        }
//					    });        


				}
			};
			
			Metaworks3.prototype.importScript = function(scriptUrl, afterLoadScript){

				var result = false;
				
				if(!this.loadedScripts[scriptUrl]){
				   mw3.loadedScripts[scriptUrl] = scriptUrl;
				
				   //TODO: asynchronous processing performance issues later on
				   $.ajax({
					   async:false,
					   url: scriptUrl,
					   type:'GET',
					   success:function(data, textStatus, jqXHR){
						   if(data && data.length > 0){
							   	result = true;
								
								// add script url
								var head= document.getElementsByTagName('head')[0];
								var script= document.createElement('script');
								script.type= 'text/javascript';
								script.src= scriptUrl;
								head.appendChild(script);
	
								//TODO: it is disabled since all the facehelper initialization is done by onLoadFaceHelperScript() explicitly for now.
	//							if(afterLoadScript){
	//								script.onload = afterLoadScript;
	//								
	//								script.onreadystatechange = function() { //for IE
	//									if (this.readyState == 'complete' || this.readyState == 'loaded') {
	//										
	//										afterLoadScript;
	//									}
	//								};
	//							}
								
								if(typeof afterLoadScript == 'function'){
									script.onload = afterLoadScript;
									
									script.onreadystatechange = function() { //for IE
										if (this.readyState == 'complete' || this.readyState == 'loaded') {
											afterLoadScript;
										}
									}
								}
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
				}else{
					if(typeof afterLoadScript == 'function')
						afterLoadScript();
				}
				
				return result;
			};
			
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
			};
			
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
					
					var id = "";					
					var ids = [];
					
					var returnValues=[];
					var j=0;
					
					// 2012-04-16 key value null 경우 undefined 안먹게 수정
					if(metadata.keyFieldDescriptor && value[metadata.keyFieldDescriptor.name])
						id = this._createObjectKey(value[metadata.keyFieldDescriptor.name], true);

					if(id instanceof Array){
					    ids = id;
					}else{
						ids.push(id);
					}
					
					for(var h=0; h<ids.length; h++){
						for(var i=0; i<clsNames.length; i++){
							if(ids[h])
								returnValues[j++] = clsNames[i] + '@' + ids[h];
							else
								returnValues[j++] = clsNames[i];
						}
					}

					//add placeholder candidates without @id again
					for(var i=0; i<clsNames.length; i++){
						if(metadata.keyFieldDescriptor)
							returnValues[j++] = clsNames[i];  													
					}
					
					
					
					if(arguments.length>1){						
						return returnValues;
					}else
						return returnValues[0];
				}else 
					return value;
			};
			
			Metaworks3.prototype.locateObject = function(value, className, divName, options, beanPropertyOption){
				var objectId = ++ this.objectId;
				var divId =  this._getObjectDivId(objectId);
				var infoDivId = this._getInfoDivId(objectId);
				var html; 
				
				var className;
				
				if(className){
				}else if(value.__className){
					className = value.__className;
				}else
					className = "java.lang.Object";
				
				// 2012-04-04 cjw java.lang.String 일 경우 faceHelper 호출을 위해 공백으로 초기화
				//if(value == null && className == 'java.lang.String')
				//	value = '';
					
				this.newBeanProperty(objectId);

				this.objects[objectId] = value; //caches the values
				this._armObject(objectId, value); //empower the object !
				
				
				// 2012-09-27 cjw 슈퍼클래스까지 keyMapping
				/*
				var objKey = this._createObjectKey(value);
				if(objKey)
					this.objectId_KeyMapping[objKey] = objectId;
				*/
				
				
				if(value && !(value instanceof Array) && typeof value == 'object'){
					var objKeys = this._createObjectKey(value, true);
					
					if(objKeys && objKeys.length){
						for(var i=0; i<objKeys.length; i++){
							this.objectId_KeyMapping[objKeys[i]] = objectId;
						}
					}
				}
				
				
				this._wireObject(value, objectId);
				
				if(beanPropertyOption)
					this.addBeanProperty(beanPropertyOption.objectId, ((beanPropertyOption.name.indexOf('[') != 0)?'.':'') + beanPropertyOption.name);
				
				var metadata = this.getMetadata(className);
				
				var elementTag = 
					(options && options['htmlTag'] ? 
						options['htmlTag'] 
						: 
						( metadata && metadata.faceOptions && metadata.faceOptions['htmlTag'] ? 
								metadata.faceOptions['htmlTag'] : "div"
						)
					);
				
				var elementClass = 
					(options && options['htmlClass'] ? 
							options['htmlClass'] 
							: 
							( metadata && metadata.faceOptions && metadata.faceOptions['htmlClass'] ? 
									metadata.faceOptions['htmlClass'] : ""
							)
						);

				
				if(metadata){
					if(metadata.onDropTypes){
						for(var typeName in metadata.onDropTypes){
							elementClass = elementClass + " onDrop_"+typeName.split('.').join('_');
						}
					}
					
				}
				
				var elementSubTag = "";
				
				if(elementTag == 'tr')
					elementSubTag = 'td';
				else if(elementTag == 'ul')
					elementSubTag = 'li';
				else if(elementTag == 'dl')
					elementSubTag = 'dd';
				
				// 2013-07-31 DOM 생성방법 수정 및 DOM 객체에 name 설정 추가
				var locateObjectDOM = $('<div>');
				var mainDOM = $('<' + elementTag + '>').attr({'id': divId, 'className': className, 'objectId': objectId});
				
				if(elementClass)
					mainDOM.addClass(elementClass);
				
				if(options && options['name'])
					mainDOM.attr('name', options['name']);
				
				if(metadata && metadata.focusable)
					mainDOM.attr('tabindex', objectId).css('outline-style', 'none');
				
				if(elementSubTag)
					$('<' + elementSubTag + '>').text(mw3.MESSAGE_LOADING).appendTo(mainDOM);
				else
					mainDOM.text(mw3.MESSAGE_LOADING);
				
				var scriptHTML = '';
				
				if(mw3.usingTemplateEngine == 'jQote'){
					var batchHTML = mw3.showObjectWithObjectId(this.objectId, className, null, options);
					
					mainDOM.html(batchHTML);
				}else{
					scriptHTML = '<script>mw3.showObjectWithObjectId('+this.objectId+',\"'+className+'\", \"#'+divId+'\"' + (options ? ',' + JSON.stringify(options) : '') + ');</script>';	
				}


				locateObjectDOM.append(mainDOM);
				locateObjectDOM.append($('<div>').attr('id', infoDivId))
				
				var html = locateObjectDOM.html() + scriptHTML;
								
				if(divName){ //when locateObject method has been called for just positioning purpose not the html generation.
					var divId = divName;
					
					if(options && options['prepend'])
						$(divId).prepend(html);
					else
						$(divId).append(html);
					
					this.targetObjectId = objectId; 
					
					return this;
				}
				
				//#DEBUG POINT
				return html;
			};
			
			Metaworks3.prototype.setObject = function(value/*, objectTypeName*/){
				var objectTypeName;
				var objectId;
				
				/*
				 * 2013/10/18 jinwon cho
				 * refactoring arguments logic
				 */
				if(arguments.length == 1){
					objectId = this.targetObjectId;
				}else{
					objectId = arguments[0];
					value = arguments[1];
					objectTypeName = arguments[2];
				}
				
				if(this.recentCallMethodName && objectId == this.recentCallObjectId){
					if(mw3.getFaceHelper(objectId) && mw3.getFaceHelper(objectId).endLoading){
						mw3.getFaceHelper(objectId).endLoading(this.recentCallMethodName);
					}else{
						mw3.endLoading(objectId, this.recentCallMethodName);
					}
					
					if(mw3.getFaceHelper(objectId) && mw3.getFaceHelper(objectId).showStatus){
						mw3.getFaceHelper(objectId).showStatus( this.recentCallMethodName + " DONE.");
					}else{
		
						mw3.showInfo(objectId, this.recentCallMethodName + " DONE");	
					}
				}
				
				if(objectTypeName){
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
				

				var divId =  "#objDiv_" + objectId;
				
				// 2012-03-27 cjw destroy event
    			if(this.objects[objectId]){
    				if(this.getFaceHelper(objectId) && this.getFaceHelper(objectId).destroy)
    					mw3.getFaceHelper(objectId).destroy();
    			}

    			// 2013-10-02 cjw unbind
    			$(divId).unbind();
    			
    			this._armObject(objectId, value); //let the methods and some special fields available
				this.objects[objectId] = value; //change the cached value
				this.faceHelpers[objectId] = null;
				
				
				/*
				 * 2013/01/18 jinwon
				 * super class 까지
				 */
				if(value && !(value instanceof Array) && typeof value == 'object'){
					var objKeys = this._createObjectKey(value, true);
					if(objKeys && objKeys.length){
						for(var i=0; i<objKeys.length; i++){
							this.objectId_KeyMapping[objKeys[i]] = objectId;
						}
					}
				}
			
				/*
				var objKey = this._createObjectKey(value);
				if(objKey!=null){
					this.objectId_KeyMapping[objKey] = objectId;
				}*/
				
				
				///// auto wiring object to its class name /////
				this._wireObject(value, objectId);
					
				this.newBeanProperty(objectId);
				
				this.showObjectWithObjectId(objectId, objectTypeName, divId);
				
				return this._withTarget(objectId);

			};
			
			Metaworks3.prototype.getObjectReference = function(){
				return this._createObjectRef(this.targetObjectId, getObject());
			};
			
			Metaworks3.prototype.removeObject = function(objectId, self){
				if(arguments.length == 0)
					objectId = this.targetObjectId;				
				
				var divId =  "#" + this._getObjectDivId(objectId);
				var infoDivId =  "#" + this._getInfoDivId(objectId);
				
				if(mw3.objects[objectId] && !mw3.objects[objectId]['__cached']){
					this.newBeanProperty(objectId);
					
					// 2012-04-04 cjw destroy 호출 후 removeObject
					var faceHelper = this.getFaceHelper(objectId);
					
	    			if(faceHelper && faceHelper.destroy)
	    				faceHelper.destroy();
					
					this.objects[objectId] = null;
					this.faceHelpers[objectId] = null;
					this.beanExpressions[objectId] = null;
					
					/*
					var parent = $(divId).parent();
					if(parent.hasClass('target_stick') || parent.hasClass('target_popup')){
						console.debug('remove popup');
						
						parent.remove();
					}
					*/
				}
				

				$(divId).triggerHandler('destroy');
				$(divId).remove();
				$(infoDivId).remove();

				//TODO: the objectId_KeyMapping also need to clear with back mapping or key generation with value;
				
				return this._withTarget(objectId);
			};
			
			Metaworks3.prototype._withTarget = function (objectId){
				this.targetObjectId = objectId;
				
				return this;
			};
				
			//TODO: this method is not required anymore. (by [dtfmt])
			Metaworks3.prototype.newBeanProperty = function(parentObjectId){
				var beanExpression = {};
				
				var beanPaths = this.beanExpressions[parentObjectId];
				if(beanPaths)
				for(var propName in beanPaths){
					var beanPath = beanPaths[propName];

					var obj = this.objects[beanPath.valueObjectId];

					if(obj && !(obj instanceof Array) && typeof obj == 'object'){
						var objKey = this._createObjectKey(obj);
						this.objectId_KeyMapping[objKey] = null;
					}
					
					// 2012-04-16 chlid destroy call
					var faceHelper = this.getFaceHelper(beanPath.valueObjectId);
					
					if(faceHelper && faceHelper.destroy)
						faceHelper.destroy();
					
					this.objects[beanPath.valueObjectId] = null;					
					this.faceHelpers[beanPath.valueObjectId] = null;
					
					this.newBeanProperty(beanPath.valueObjectId);
				}
				
				this.beanExpressions[parentObjectId]=beanExpression;
			};
			
			Metaworks3.prototype.addBeanProperty = function(parentObjectId, fieldName){
				//console.log('addBeanProperty : ' + parentObjectId + ' , ' + fieldName);
				
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
			};
				
			Metaworks3.prototype.template_error = function(e, actualFace) {
				var errorElement = document.getElementById(this.errorDiv);

				if(e.lineNumber){
					if(e.lineText)
						var message = "["+actualFace+"] at line "+e.lineNumber+": "+e.lineText+": "+e.message;
					else
						var message = "["+actualFace+"] at line "+e.lineNumber+": "+e.message;
				}else
					var message = "["+actualFace+"] "+e.message;

				
				if(errorElement){
					errorElement.style.display = 'block'
					errorElement.innerHTML = "<span><font color=#FB7524>" + message + "</font></span>";
					errorElement.className = 'error';
				}else{
					if(console)
						console.log(message);
					else
						alert(message);
				}
			};
				
			Metaworks3.prototype.createInputId = function(objectId){
				return "_mapped_input_for_" + objectId;
			};
			
			Metaworks3.prototype.getInputElement = function(objectId, propName){
				var beanPaths = mw3.beanExpressions[objectId];
				if(!beanPaths) return null;
				
				var beanPath = beanPaths["." + propName];
				if(!beanPath) return null;
				
				var inputId = mw3.createInputId(beanPath.valueObjectId); //try object.resourceName.__objectId
				
				var inputElement = document.getElementById(inputId); //will be "mw3.getInputElement(object.resoureceName)"

				return inputElement;
			};
			
			Metaworks3.prototype.getChildObjectId = function(parentObjectId, propName){
				var beanPaths = mw3.beanExpressions[parentObjectId];

				var beanPath = beanPaths["." + propName];
				if(!beanPath) return null;
				
				return beanPath.valueObjectId;
				
			};
			
			
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
			};
			
			Metaworks3.prototype.getObjectFromUI = function(objectId){
				var objectId;
				
				var value = null;
				
				var tagId = this.createInputId(objectId);
				
				var inputTag = document.getElementById(tagId);
				if(inputTag){
					value = dwr.util.getValue(tagId); //this would prohibit File object damaged
				}
				var beanPaths = this.beanExpressions[objectId];
				if(beanPaths)
				for(var propName in beanPaths){
					var beanPath = beanPaths[propName];
					
					try{
						eval("this.objects[objectId]" + beanPath.fieldName + "=this.getObject('" + beanPath.valueObjectId + "')");
					}catch(e){
						throw new Error("Error when to map the " + propName + " beanExpressions. check in " + mw3.objects[objectId].__className + " ejs template file. [" + beanPath.fieldName + "]");
					}
				}
				
				//if(!inputTag && !beanPaths){
				if(value==null)
						value = this.objects[objectId];
					
				//}
				
				//sometimes the armed object may be unarmed by the user-defined facehelper.getValue() method
				if(value && !value.__objectId)
					this._armObject(objectId, value);
				
				return value;
			};
			
			Metaworks3.prototype._getInfoDivId = function(objId){
				return "info_" + objId;
			};
			Metaworks3.prototype._getObjectDivId = function(objId){
				return "objDiv_" + objId;
			};
			Metaworks3.prototype._getMethodDivId = function(objId, methodName){
				return "method_" + objId + "_" + methodName;
			};


			Metaworks3.prototype.getAutowiredObject = function(className, requireExactOne){
				
				if(className.indexOf("@") > 0){
					if(requireExactOne){
						try{
							return this.objects[this.objectId_KeyMapping[className]];
							
						}catch(e){
							return null;
						}
					}else{
						
						var classNameAndId = className.split("@");
						className = classNameAndId[0];
						
						var id = classNameAndId[1];
						
						var metadata = this.getMetadata(className);
						
						var clsNames = metadata.superClasses;
						
						for(var i=0; i<clsNames.length; i++){
							var tryKey = clsNames[i] + "@" + id;
							
							var objectId = this.objectId_KeyMapping[tryKey];
							if(objectId)
								return this.objects[objectId];
						}
						
						if(metadata.keyFieldDescriptor){
							for(var i=0; i<clsNames.length; i++){
								var tryKey = clsNames[i];
								
								var objectId = this.objectId_KeyMapping[tryKey];
								if(objectId)
									return this.objects[objectId];
							}
						}
					}
				}
				
				
				var autowiredObjectId = this.objectId_ClassNameMapping[className];
				if(autowiredObjectId){
					if(autowiredObjectId.__isAutowiredDirectValue){ //means direct value not id pointer
						return autowiredObjectId.value;
					}else
						return this.getObject(autowiredObjectId);
				}
				
				return null;
			};
			
			Metaworks3.prototype.getBestObject = function(object){
				var objKeys = mw3._createObjectKey(object, true);
				
				if(objKeys && objKeys.length){
								        				
					for(var i=0; i<objKeys.length; i++){
						
						var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
			
						if(mappedObjId){
							return this.objects[mappedObjId];				
						}
						
					}
				}
			};


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
			};
			
			Metaworks3.prototype.startLoading = function(objId){
				var infoDivId = "#"+this._getInfoDivId(objId);
				
				$(infoDivId).css('display', 'block').html("<img src='dwr/metaworks/images/circleloading.gif' align=middle>");
			};
						
			Metaworks3.prototype.endLoading = function(objId){
				var infoDivId = "#"+this._getInfoDivId(objId);
				
				$(infoDivId).css('display', 'block').html("");
			};
    		
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
				
//				mw3.log('________call : ' + svcNameAndMethodName + '[' + objId + '] ---> ' + new Date().getTime());
				
				// 2012-04-14 cjw 재귀호출 막음
				// 2012-04-18 이전 수정 버전 문제로 재수정 (undefined 일때 true 가 되여야함)
				var getAgain = (arguments.length > 2 ? (typeof arguments[2] != 'undefined' ? arguments[2] : true) : true);				
				//var getAgain = (arguments.length > 2 ? arguments[2] : true);
				var sync = (arguments.length > 3 ? arguments[3] : false);
				// 2012-11-25 cjw add callback function
				var callback = (arguments.length > 4 ? arguments[4] : null);
				// 2012-11-25 cjw add callback function
				var test = (arguments.length > 5 ? arguments[5] : null);
				
//				if(typeof objId == 'number'){ //check if number
	
				if(test){
					var object = mw3.objects[objId];
				}else{
					var objectFromUI = this.getObjectFromUI(objId);
					
					if(objectFromUI.__faceHelper && getAgain){				
						var object = mw3.getObject(objId);
					}else{
						var object = objectFromUI;
					}
				}
//				}else{
//				object = objId; //TODO: readability is bad.
//			}

				//var thisMetaworks = this;
				var divId = "objDiv_" + objId;
				
				//if(object && object.metaworksContext)
				//	this.setContext(object.metaworksContext);
				//this.setWhen(this.WHEN_VIEW);
				
				if(svcNameAndMethodName.indexOf('.') == -1){ //if there only methodname has been provided, guess the service name

					var className = object.__className;
					//var serviceName = className.subtring(className.lastIndexOf('.')) + 'Service';
					//svcNameAndMethodName = serviceName + "." + svcNameAndMethodName;
					
					
					var objectMetadata = this.getMetadata(className);
					var serviceMethodContext;
					
					
					if(objectMetadata && objectMetadata.serviceMethodContextMap && objectMetadata.serviceMethodContextMap[svcNameAndMethodName]){
						serviceMethodContext = objectMetadata.serviceMethodContextMap[svcNameAndMethodName];
						
						
						if(serviceMethodContext && serviceMethodContext.validate == true){										
							if(!this.validObject(object)){
								if(this.getFaceHelper(objId) && this.getFaceHelper(objId).endLoading){
									this.getFaceHelper(objId).endLoading(svcNameAndMethodName);
								}else{
									this.endLoading(objId, svcNameAndMethodName);
								}
								
			        			if(this.getFaceHelper(objId) && this.getFaceHelper(objId).showStatus){
			        				this.getFaceHelper(objId).showStatus( svcNameAndMethodName + " INVALID.");
			        			}else{
			        				this.showInfo(objId, svcNameAndMethodName + " INVALID");	
			        			}							
								
			        			return false;						
							}
						}
						
						
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
							}else{
								
								var objectForCall = {__className: object.__className, metaworksContext: object.metaworksContext};

								//we have to copy all the field values for objectForCall since there's too many additional JSON fields for call methods.
								for(var i in objectMetadata.fieldDescriptors){
									var fd = objectMetadata.fieldDescriptors[i];
									
									if(object!=null && object[fd.name]!=null && (!serviceMethodContext.except || !serviceMethodContext.except[fd.name]))
										objectForCall[fd.name] = object[fd.name];
								}
									
								object = objectForCall;
								
							}

						}
					}else{
						if(console)
							console.log('serviceMethodContext is not found!');
						
						return;
					}

					var placeholder = null;
					if(serviceMethodContext && serviceMethodContext.target!="none"){
						var loader = serviceMethodContext.loader;						
						
						if(loader && serviceMethodContext.target=="popup" && loader[0].indexOf("java.lang.Object")==-1){

	        				try{
		        				mw3.popupDivId = 'popup_' + objId;
		        				$('body').append("<div id='" + mw3.popupDivId + "' class='target_popup' style='z-index:10;position:absolute; top:0px; left:0px'></div>");
		        				placeholder = mw3.locateObject({__className: loader[0]}, null, '#' + mw3.popupDivId).targetObjectId;

								mw3.onLoadFaceHelperScript();
	        				}catch(e){if(consol) console.log(e)}
						}
						
						if(placeholder && this.getFaceHelper(placeholder) && this.getFaceHelper(placeholder).startLoading){
							this.getFaceHelper(placeholder).startLoading(svcNameAndMethodName);
						}else if(placeholder){
							this.startLoading(placeholder, svcNameAndMethodName);
						}
						else if(this.getFaceHelper(objId) && this.getFaceHelper(objId).startLoading){
							this.getFaceHelper(objId).startLoading(svcNameAndMethodName);
						}else{
							this.startLoading(objId, svcNameAndMethodName);
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
							var autowiredField = objectMetadata.autowiredFields[fieldName];

							var autowiredClassName = autowiredField.field;
							var autowiredSelect = autowiredField.select;
							
							if(autowiredSelect != null && autowiredSelect.length > 0){
							     for(var i in this.objects){
							    	 
							    	 if(this.objects[i] && this.objects[i].__className){
							    		 var sameClass = false;
								    	 var autowiredObjectMetadata = this.getMetadata(this.objects[i].__className);
	 							    	 
								    	 for(var j=0; j<autowiredObjectMetadata.superClasses.length; j++){
								    		 if(autowiredObjectMetadata.superClasses[j] == autowiredClassName){
								    			 sameClass = true;
								    			 
								    			 break;
								    		 }
								    	 }
						    			 
						    			 if(sameClass){
							    			 var isSelect = false;
							    			 with(object){
							    				 var autowiredObject = this.objects[i];
							    				 isSelect = eval(autowiredSelect);
							    			 }

							    			 if(isSelect){
							    				 autowiredObjects[fieldName] = this.getObject(i);

							    				 break;
							    			 }
						    			 }
							    	 }
							     }
							}else{
								autowiredObjects[fieldName] = this.getAutowiredObject(autowiredClassName);	
								
								if(autowiredObjects[fieldName] && autowiredObjects[fieldName].__objectId)
									autowiredObjects[fieldName] = this.getObject(autowiredObjects[fieldName].__objectId);
							}
						}
					}
					

					var returnValue;
					
					var objectKey = this._createObjectKey(object);
					
					//This lets the called object doesn't have identifier, it should be focused to be set result if the result is it's type.
					if(objectKey && objectKey.indexOf("@")==-1)
						this.objectId_KeyMapping[objectKey] = objId;

					

					if(serviceMethodContext && serviceMethodContext.target!="none"){

						if(serviceMethodContext.loadOnce && serviceMethodContext['cachedObjectId']){
							return this.__showResult(object, this.objects[serviceMethodContext['cachedObjectId']], objId, svcNameAndMethodName, serviceMethodContext, placeholder, divId );
						}
					}
					
					
					var metaworksService = new MetaworksService(className, object, svcNameAndMethodName, autowiredObjects, objId, divId, placeholder, callback, sync, serviceMethodContext);
					this.metaworksServices.push(metaworksService);
					
					metaworksService.setIndex(this.metaworksServices.indexOf(metaworksService));
					
					returnValue = metaworksService.call();
					
					///// just after call, 
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

			};
			
			Metaworks3.prototype.showInfo = function(objId, message){
				var infoDivId = "#"+this._getInfoDivId(objId);

				$(infoDivId).html("<center><font color=eeeeee> " + message + "</font></center>");
				$(infoDivId).slideDown(500, function(){
					setTimeout(function() {
						$( infoDivId ).slideUp(500);
					}, 5000 );
				});
			};
			
			Metaworks3.prototype.showError = function(objId, message, methodName){
				if( this.getFaceHelper(objId) && this.getFaceHelper(objId).endLoading)
					this.getFaceHelper(objId).endLoading();	
				else
					mw3.endLoading();
				
				var infoDivId = "#"+this._getInfoDivId(objId);				
				$(infoDivId).html("<center><font color=red> " + message + "<input type=button onclick=\"mw3.getObject('" + objId + "')."+ methodName + "()\" value='RETRY'></font></center>");
			};
			
			Metaworks3.prototype.alert = function(message){
				alert(this.localize(message));
			};
			
			Metaworks3.prototype.showValidation = function(objId, inputObjectId, isValid, message){
				var infoDivId = "#"+this._getInfoDivId(objId);
				
				if(!isValid)
					$(infoDivId).html("<center><font color=red> " + mw3.localize(message) + "</font></center>");
				else
					$(infoDivId).html("");
			};
			
			
			Metaworks3.prototype._armObject = function(objId, object){
				if(!object || !object.__className) return;
				
				if(object.__className=="Number" || object.__className=="String"){
					return; 
				}


				if(object.__objectId==null && objId==null){//means needed to be stored in the objectId    //TODO: may cause dangling object and memory leak
					mw3.objects[objId = ++mw3.objectId] = object;
				}

				if(objId==null && object.__objectId!=null){
					objId = object.__objectId;
				}
				
				object['__objectId'] = objId;

				var objectMetadata = this.getMetadata(object.__className);
    			
			   for(var methodName in objectMetadata.serviceMethodContextMap){
			   		var methodContext = objectMetadata.serviceMethodContextMap[methodName];
			   		
			   		if(methodContext.clientSideCall)
			   			eval("object['"+methodName+"'] = function(){return mw3.clientSideCall(this.__objectId, '"+methodName+"');}");
			   		else{
			   			eval("object['"+methodName+"'] = function(getAgain, callback){return mw3.call(this.__objectId, '"+methodName+"', getAgain, false, callback);}");			   			
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
			   
			   object['getFaceHelper'] = object['__getFaceHelper']; //TODO: the previous __getFacehelper needed to be deprecated permantly some day.
			   
			};

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
				   	
				   	if(instruction.indexOf("$")==0){
				   		instruction = mw3.localize(instruction);
				   	}
				   	
				   	var targetObject = methodDiv.children()[0];
				   	
				   	if(!targetObject)
				   		targetObject = methodDiv[0];
				   	
				   	if(!targetObject && console)
				   		console.log("There's no div id with '"+methodDivId + "'");

				   	if(!targetObject.offsetWidth && console)
				   		console.log("There's no offset width with div id '"+methodDivId + "'");

				   	$('body').append("<div id='instructionR' onclick=\"this.style.display='none'\"><div class='instructionDisc'>" + instruction + "</div></div>");

					$("#instructionR").css({"top": methodDiv.offset().top - 60 + "px", "left": (methodDiv.offset().left + targetObject.offsetWidth - 350) + "px" });
//					$("#instruction").slideDown(500);
					
					$( "#instructionR" ).effect( 'pulsate', 300 );
					
					if(options && options.onclick)
						$("#instructionR").onclick(function(){options.onclick();});
					
//					setTimeout(function(){
//						$( "#instruction" ).effect( 'shake',300 );
//						
//						setTimeout(function(){
//							$( "#instruction" ).effect( 'shake',300 );
//						},700);				
//					},700);		

			};

			Metaworks3.prototype.startTest = function(scenarioName, options){
				this.test(null, scenarioName + "[0]", options);
			};

			Metaworks3.prototype.test = function(objectId, testName, options){
				  $( "#instructionR" ).slideUp(500, function(){
					  $('#instructionR').remove();							  
				  });
				  

				var guidedTour = options && options['guidedTour'];
				
				if(options==null)
					options = {};
				
				if(!options['scenarioName'])
					options['scenarioName'] = testName;
				
				var recordedTest = false;
				var testIndex =0;
				
			   if(objectId==null && testName.indexOf("[") > -1){ // in case that the test is recorded one
				   var scenarioNameAndIndex = testName.split("[");
				   var scenarioname = scenarioNameAndIndex[0];
				   testIndex = eval(scenarioNameAndIndex[1].split("]")[0]);

				   test = mw3.testSet[scenarioname][testIndex];
				   
				   if(test==null){
					   
					   if(console)
						   console.log('Test ' + scenarioname + ' successfully done.');
					   else
						   alert('Test ' + scenarioname + ' successfully done.');
					   
					   return;
				   }

				   test.scenario = scenarioname;

				   recordedTest = true;

			   }else{ // in case that the test is inside the annotation

					var value = this.objects[objectId];
					
					var objectMetadata = this.getMetadata(value.__className); 
					
	//				var testStarter;
					
					//tests is indexed test hashmap for easily finding test by class name
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
					
					//해당 시작한 클래스명에 대한 테스트들을 끌어옴. 근데, 이는 recorded 된 test들에 대해서는 별 의미가 없음.
				   var testsForTheClass = this.tests[value.__className];  
				   
				   if(testName.indexOf(".") == -1 && options && options.scenarioName && options.scenarioName!=testName){
					   testName = options.scenarioName + "." + testName;
				   }
			   
				   test = testsForTheClass[testName];
			   
				   if(test==null){
					   
						   alert('test is null. Please check your starter @Test name is same with the scenario name or set by starter=true.');
				   
				   			return;
				   }
			   }
			   
			   
			   var next = null;
			   if(recordedTest){
				   next = test.scenario + "[" + (testIndex+1) + "]";
				   
			   }else{
				   next = (test.next && test.next[0] ? test.scenario + "." + test.next[0] : null);
			   }

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
							   instruction = instruction + "<input type=button value='Done !' onclick=\"$('#instructionR').remove()\">";
							   
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


					   
			   }else{  //---- in case of method ------
				   
				   
				   var returnValue;
				   
				   if(test.methodName){
					   
						  /// installing the call handler to continue after the call 
						  mw3.afterCall = function(methodName, result){
							  
							  if(methodName != test.methodName) return;

							  if(recordedTest){
								  mw3.test(null, next, options);
								  
								  return;
							  }

							   //var next = (test && test.next && test.next[0] ? test.scenario + "." + test.next[0] : null);

							   if(next==null){  // detect end!
								   mw3.afterCall = null;
								   
								  $( "#instructionR" ).slideUp(500, function(){
									  $('#instructionR').remove();							  
									   //TODO: done message should be here!
									  
									  //var congratulations = "Congratulations! Your guided Tour has been finished.";
									  //congratulations = mw3.localize("$congratulations");
									   mw3.alert("$congratulations");
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

							   		if(className.indexOf("@") > -1){
							   			value = this.objects[this.objectId_KeyMapping[className]]; 
							   		}else{
							   			
									    value = this.getAutowiredObject(className);
							   			
							   		}
							   		
							   
								   
//								   var objectIdFirst = value.__objectId;
//								   for(var i=objectIdFirst; i>0; i--){
//									   
//								   }
								   
								  if(value)
									  mw3.test(value.__objectId, methodName, options);
								  
							   } else{
								   value = mw3.objects[objectId];
								   
								  if(value)
									  mw3.test(value.__objectId, next, options);
							   }

						   }
	   
					   //in case that the recorded test...
					   var enterValueContext = null;
					   
					   if(recordedTest && test.value!=null){
						   
						   objectId = this.objectId_KeyMapping[test.objectKey];
						   
						   if(objectId==null){
							   
							   var errorMsg = "[Test] During test [" + test.scenario + "], object key [" + test.objectKey + "] is not found. Quit the test.";
							   if(console)
								   console.log(errorMsg);
							   else
								   alert(errorMsg);
							   
							   return;
						   }
						   

						   //setting values for only existing property value except the null property
						   var value = this.objects[objectId];
						   var metadata = this.getMetadata(test.value.__className);
						   for(var idx in metadata.fieldDescriptors){
							   
							   var fieldDescriptor = metadata.fieldDescriptors[idx];
							   if(test.value[fieldDescriptor.name]){
								   enterValueContext = (enterValueContext ? enterValueContext + ", " : "") + fieldDescriptor.displayName + " : " + test.value[fieldDescriptor.name];
								   
								   value[fieldDescriptor.name] = test.value[fieldDescriptor.name];								   
							   }
							   
						   }
						   
					   }
							   

					   if(guidedTour){
						   
						   if(recordedTest){
							   test.instruction = [];
							   test.instruction[0] = ((enterValueContext && false) ? "Enter " + enterValueContext + "\n and " : "") + "Click " + test.methodName + ".";
						   }
						   
						   var methodDivId = "method_" + value.__objectId + "_" + test.methodName;
						   var methodDiv = $("#" + methodDivId);
						   
						   
						   var instruction = (test.instruction && test.instruction[0] ? test.instruction[0] : "Click Here");

							
							setTimeout("showupInstruction('"+methodDivId+"','"+ instruction +"')", 700);
						  
					   }else{
						   

						   returnValue = this.call(value.__objectId, test.methodName, true, true, null, true); //sync call
					   }
					   
					   
				   }  
				   
			   }
				
			};

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
								var objectMetadataFD = this.getMetadata(fieldValue.__className); 
								
								for(var j=0; j<objectMetadataFD.superClasses.length; j++){
									var className = objectMetadataFD.superClasses[j];
									
									this.objectId_ClassNameMapping[className] = {value: fieldValue, __isAutowiredDirectValue: true};
								}
							}
							
						}
					}
				}
			};
			


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
				

				var when = mw3.when;
				
				mw3.setWhen(mw3.WHEN_EDIT);
				
				var object = this.getObject(objectId);
				
				if('java.lang.String' != objectTypeName){
					if(!object.metaworksContext)
						object.metaworksContext = {}
				
					object.metaworksContext['when'] = mw3.WHEN_EDIT;
				}
	 			
				mw3.showObjectWithObjectId(
						objectId,
						objectTypeName,
						"#objDiv_" + objectId
				);
				
				mw3.setWhen(when);
				
				//TODO: its kind of dummy.
				//mw3.loadFaceHelper(objectId);
	 			
	 			return this._withTarget(objectId);
			};
			

			
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
			};
			
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
			};
			
			Metaworks3.prototype.isHidden = function(fd){

				if(this.when == "__design"){
					return !fd.attributes['resource']; 
				}
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

					if(fd.attributes['available.how']){
						return (fd.attributes['available.how'][this.how]==null);
					} 

					if(fd.attributes['available.condition'] && object){
						for(var key in fd.attributes['available.condition']){
							var condition = fd.attributes['available.condition'][key]; 
							var validateCondition = true;
			    			if(condition != null){
			    				with(object)
			    					validateCondition = !eval(condition);
			    			}			
			    			
			    			if(!validateCondition)
			    				return validateCondition;
						}
						
						return true;
					}					
					
					if(fd.attributes['hidden'] || this.when == '___hidden___') 
						return true;
										
				} 
				
				return false;
			};
			
			Metaworks3.prototype.isHiddenMethod = function(method){
				/*
				if(method.methodContext.when != mw3.WHEN_EVER){
		   			if( (mw3.when && (method.methodContext.when.indexOf(mw3.when) == -1) ) 
		   					||
			   			(mw3.where && (method.methodContext.where!='wherever' && method.methodContext.where.indexOf(mw3.where) == -1) )
			   				
		   			)
	   				return true;
				}
				*/
				
				return this.isHiddenMethodContext(method.methodContext, method.object);
			};
			
			Metaworks3.prototype.isHiddenMethodContext = function(methodContext, object){
				
				if(methodContext.attributes){
					if(methodContext.attributes['hidden.when']){
						return (methodContext.attributes['hidden.when'][this.when]!=null);
					} 
					
					if(methodContext.attributes['hidden.where']){
						return (methodContext.attributes['hidden.where'][this.where]!=null);
					} 
	
					if(methodContext.attributes['hidden.how']){
						return (methodContext.attributes['hidden.how'][this.how]!=null);
					}
				}
								
				if(methodContext.when != null && methodContext.when.indexOf('whenever|') == -1){
					if(methodContext.when.indexOf(mw3.when + '|') == -1)
						return true;						
				}
				
				if(methodContext.where != null && methodContext.where.indexOf('wherever|') == -1){
					if(methodContext.where.indexOf(mw3.where + '|') == -1)
						return true;						
				}

				if(methodContext.how != null){
					if(methodContext.how.indexOf(mw3.how + '|') == -1)
						return true;						
				}
				
				if(methodContext.attributes && methodContext.attributes['available.condition'] && object){
					for(var key in methodContext.attributes['available.condition']){
						var condition = methodContext.attributes['available.condition'][key]; 
						var validateCondition = true;
		    			if(condition != null){
		    				with(object)
		    					validateCondition = !eval(condition);
		    			}			
		    			
		    			if(!validateCondition)
		    				return validateCondition;
					}
					
					return true;
				}
				
				return false;				
			}
			
			Metaworks3.prototype.isHiddenMethodForCache = function(cacheContext, methodContext){
				
				if(methodContext.when != null && methodContext.when.indexOf('whenever|') == -1){
					if(methodContext.when.indexOf(cacheContext.when + '|') == -1)
						return true;						
				}
				
				if(methodContext.where != null && methodContext.where.indexOf('wherever|') == -1){
					if(methodContext.where.indexOf(cacheContext.where + '|') == -1)
						return true;						
				}

				if(methodContext.how != null){
					if(methodContext.how.indexOf(cacheContext.how + '|') == -1)
						return true;						
				}
								
				if(methodContext.when == '___hidden___')
					return true;

				return false;				
			}
			
			Metaworks3.prototype.validationCondition = function (validator, object){
				var validationCondition = true;
				
    			var validationConditionExpression = validator.condition;    			
    			if(validationConditionExpression != null){
    				with(object){
    					validationCondition = eval(validationConditionExpression);
    				}
    			}			
    			
    			return validationCondition;
			}
			
			Metaworks3.prototype.validationAvailableUnder = function (validator, object){
				var validationAvailableUnder = false;
				
				if(validator){
					if(validator instanceof Array){
						for(var i=0; i<validator.length; i++){
			    			var validationAvailableUnderExpression = validator[i].availableUnder;    			
			    			if(validationAvailableUnderExpression != null){
			    				with(object){
			    					validationAvailableUnder = eval(validationAvailableUnderExpression);
			    				}
			    			}								
						}
					}else{
		    			var validationAvailableUnderExpression = validator.availableUnder;    			
		    			if(validationAvailableUnderExpression != null){
		    				with(object){
		    					validationAvailableUnder = eval(validationAvailableUnderExpression);
		    				}
		    			}						
					}
				}
				
    			return validationAvailableUnder;
			};
			
			Metaworks3.prototype.validField = function (objId, fieldName){
				var object = this.getObject(objId);
				
				return this.validObject(object, fieldName);
			}
			
			Metaworks3.prototype.validObject = function(object, name){
				var metadata = this.getMetadata(object.__className);
				var isValid = true;
				var objId = object.__objectId;

				if(metadata && metadata.fieldDescriptors && metadata.fieldDescriptors.length > 0){
					var fieldOrder = [];
					
					if(metadata.faceOptions && metadata.faceOptions.fieldOrder){
						var fieldOrderValue = metadata.faceOptions.fieldOrder;
						var newFieldOrder = [];
						
						if(fieldOrderValue.indexOf(',')>0){
							tempOrder = fieldOrderValue.split(',');
						}else{
							tempOrder.push(fieldOrderValue);
						}
						
						for (var i=0; i<tempOrder.length; i++){
							var tempSubOrder = [];
							
							if(tempOrder[i].indexOf('-') > -1){
								tempSubOrder = tempOrder[i].split('-');						
							}else{
								tempSubOrder.push(tempOrder[i]);
							}
							
							for (var j=0; j<tempSubOrder.length; j++){
								for(var k = 0; k < metadata.fieldDescriptors.length; ++k){
									var fd = metadata.fieldDescriptors[k];
									
									if(fd.name == tempSubOrder[j]){
										fieldOrder.push(k);
										
										break;
									}
								}
							}
								
						}
						
						for(var i = 0; i < metadata.fieldDescriptors.length; i++){
							var isExistField = false;
							
							for (var j=0; j<fieldOrder.length; j++){
								if(i == fieldOrder[j]){
									isExistField = true;
									
									break;
								}
							}
							
							if(!isExistField)
								fieldOrder.push(i);
							
						}
					}else{
						for(var i = 0; i < metadata.fieldDescriptors.length; ++i){
							fieldOrder.push(i);
						}
					}
										
					for(var i = 0; i < fieldOrder.length; i++){					   
						if(!isValid)
							continue;
						
						var fd = metadata.fieldDescriptors[fieldOrder[i]];
					   
						if(typeof name != 'undefined' && fd.name != name)
							continue
							
						try {
							var isObject = object[fd.name] instanceof Object;
							var isArray = Object.prototype.toString.call(object[fd.name]) == '[object Array]';
						   
							if(isArray){
								for(var j=0; j<object[fd.name].length; j++){
									if(!isValid)
										continue;
								   
									if(object[fd.name][j].__className)
										if(!this.validObject(object[fd.name][j]))
											isValid = false;									   
								}
							}else{
								if(isObject && fd.name == 'metaworksContext')						   
									continue;
								   
								if(fd.attributes.validator && object.__objectId) {
						    		for(var j = 0; j < fd.attributes.validator.length; ++j) {						    			
						    			var validator = fd.attributes.validator[j];
						    			
						    			if(validator.availableUnder == null || validator.availableUnder == ''){
						    				if(this.validationCondition(validator, object)){
								    			var result = this.validation(object, fd.name, validator);
								    			var inputObjectId = this.getChildObjectId(object.__objectId, fd.name);
								    			var message;
								    			
						    					if(!result)				    						
						    						message = this.getValidationMessage(fd, validator)
						    					
												if(this.getFaceHelper(objId) && this.getFaceHelper(objId).showValidation)
													this.getFaceHelper(objId).showValidation(inputObjectId, result, message);
												else
													this.showValidation(objId, inputObjectId, result, message);
												
												if(!result){
													isValid = false;
													
													break;
												}
						    				}
						    			}
						    		}
							   }
							   
							   if(isObject){
								   if(object[fd.name].__className){
									   if(this.validationAvailableUnder(fd.attributes.validator, object)){
										   if(!this.validObject(object[fd.name]))
											   isValid = false;
									   }
								   }
							   }   
						   }

				    	} catch(e) {
				    		if(window.console)
				    			console.log('error = ' + e);
				    	}
				   }
				}
				
				return isValid;
			};
			
			Metaworks3.prototype.validation = function(object, name, validator){
			
				var validation = true;
				var value = object[name];
				
				if(validator.name == 'notnull'){
					if(value == null || value == '')
						validation = false;
				}else if(validator.name == 'null'){
					if(value != null && value != '')
						validation = false;
				}else if(validator.name == 'maxbyte' && validator.options){
					var len = 0;
			        for (var i = 0; i < value.length; i++) {
			            if (value.charCodeAt(i) > 128) {
			                len++;
			            }
			            len++;
			        }			
					if(len > Number(validator.options[0]))
						validation = false;				
					
				}else if(validator.name == 'maxlength' && validator.options){
					if(value.length > validator.options[0])
						validation = false;					
				}else if(validator.name == 'minlength' && validator.options){					
					if(value.length < validator.options[0])
						validation = false;					
				}else if(validator.name == 'numberzero'){
					if(value == 0)
						validation = false;
				}else if(validator.name == 'regularexpression'){
					if(validator.options){
						for(var i=0; i<validator.options.length; i++){
							var exp = eval(validator.options[i]);
							if(!exp.test(value)){
								validation = false;
								
								break;
							}								
						}
					}
					
				}else if(validator.name == 'condition'){
					if(validator.options){
						for(var i=0; i<validator.options.length; i++){
							var validationCondition = validator.options[i];					
		    				with(object){
		    					validation = !(eval(validationCondition));
		    				}							
						}
					}
				}else if(validator.name == 'asserttrue'){
					if(value != true)
						validation = false;
					
				}else if(validator.name == 'assertfalse'){
					if(value != false)
						validation = false;
				}

				return validation;
			};
			
			Metaworks3.prototype.getValidationMessage = function(fd, validator){
				var message = null;
				
				if(typeof validator.message != 'undefined' && validator.message != null && validator.message.length > 0)
					message = validator.message;	
				else{
					if(validator.name == 'isnull')
						message = fd.displayName + '\'s fields is null.';
					else if(validator.name == 'maxbyte')
						message = fd.displayName + '\'s byte is greater than ' + validator.options[0] + '.';
					else if(validator.name == 'maxlength')
						message = fd.displayName + '\'s length value is greater than ' + validator.options[0] + '.';
					else if(validator.name == 'minlength' && validator.options)
						message = fd.displayName + '\'s value is less than ' + validator.options[0] + '.';
					else if(validator.name == 'numberzero')
						message = fd.displayName + '\'s value is zero.';
					else if(validator.name == 'regularexpression')
						message = fd.displayName + '\'s Regular expression failed inspection.';
				}
				
				return message;
			};
			
			
			Metaworks3.prototype.log = function(object){
				if(window.console)
					console.log(object);				
			};
			
			//브라우저 종류 및 버전확인  
			Metaworks3.prototype.browserCheck = function(){
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
			}; 	
			
			Metaworks3.prototype.localize = function(){
				//var message = original;
				var message = arguments[0];
				
				if(message != null && typeof message =='string' && message.indexOf('$')==0 && this.getMessage) {
					message = message.substring(1);
					message = this.getMessage(message);
				}
				
				return message;
			};
			
			Metaworks3.prototype.getMessage = function(message){
				return message;
			};
			Metaworks3.prototype.metadataBundle = function(message){
				return message;
			};
			
			Metaworks3.prototype.clone = function(obj) {
			    // Handle the 3 simple types, and null or undefined
			    if (null == obj || "object" != typeof obj) return obj;
			    
			    // Handle Date
			    if (obj instanceof Date) {
			        var copy = new Date();
			        copy.setTime(obj.getTime());
			        return copy;
			    }

			    // Handle Array
			    if (obj instanceof Array) {
			        var copy = [];
			        for (var i = 0, len = obj.length; i < len; i++) {
			            copy[i] = this.clone(obj[i]);
			        }
			        return copy;
			    }

			    // Handle Object
			    if (obj instanceof Object) {
			        var copy = {};
			        for (var attr in obj) {
			            if (attr != '__faceHelper' && obj.hasOwnProperty(attr)) copy[attr] = this.clone(obj[attr]);
			        }
			        return copy;
			    }

			    throw new Error("Unable to copy obj! Its type isn't supported.");
			};
						
			if(!Metaworks) alert('Metaworks DWR service looks not available. Metaworks will not work');
			if(!$().jquery) alert('JQuery library not installed. Metaworks will not work');
			
			var mw3 = new Metaworks3('template_caption', 'dwr_caption', Metaworks);
			
			mw3.windowFocus = true;
			mw3.windowActiveElement;

			try{
				mw3.windowActiveElement = document.activeElement;
				
				if(mw3.browser.indexOf('MSIE') > -1){
					$(document).focusin(function(event){
						mw3.windowFocus = true;
					}).focusout(function(event){
						if (mw3.windowActiveElement != document.activeElement) {
							mw3.windowActiveElement = document.activeElement;
							return;
						}
						mw3.windowFocus = false;
					});
				}else{
					$(window).focus(function(event){
						mw3.windowFocus = true;
					}).blur(function(event){
						if (mw3.windowActiveElement != document.activeElement) {
							mw3.windowActiveElement = document.activeElement;
							return;
						}
						mw3.windowFocus = false;
					});				
				}
			}catch(e){				
			}

			
			Metaworks3.prototype.serviceMethodBinding = function(objectId, objectTypeName){
				//load the key or mouse bindings, context menu 
				var contextMenuMethods = [];
				var object = mw3.objects[objectId];
				var metadata = null;
				
				if(objectTypeName)
					metadata = mw3.getMetadata(objectTypeName);
				
				var faceHelper = mw3.getFaceHelper(objectId);
				
				var targetDivId = mw3._getObjectDivId(objectId);
				var theDiv = $('#' + targetDivId);
				
				if(theDiv[0] && metadata)
			    for(var methodName in metadata.serviceMethodContextMap){
			   		var methodContext = metadata.serviceMethodContextMap[methodName];
			   	
			   		//console.log('try : ' + methodName);
				    if(mw3.isHiddenMethodForCache(mw3.metaworksContexts[objectId], methodContext) && !methodContext.bindingHidden)
					   continue;

				    //console.log('pass : ' + methodName);
				    //console.log(methodContext);
				    
				    // make call method
		   			var command = "if(mw3.objects['"+ objectId +"']!=null) mw3.call("+objectId+", '"+methodName+"')";
					if(methodContext.needToConfirm){
						if(this.needToConfirmMessage){
							command = "if (confirm(\'" + mw3.localize(mw3.needToConfirmMessage, methodContext.displayName) + "'))" + command;
						}else{
							command = "if (confirm(\'Are you sure to "+mw3.localize(methodContext.displayName)+" this?\'))" + command;
						}
					}
					
				    if(methodContext.eventBinding && methodContext.eventBinding.length > 0){
				    	for(var i=0; i<methodContext.eventBinding.length; i++){
				    		var eventBinding = methodContext.eventBinding[i];
				    		
	   						for(var j=0; j<methodContext.bindingFor.length; j++){
	   							var bindingFor = methodContext.bindingFor[j];
	   							var bindingDivId;
	   							
	   							if('@this' == bindingFor){
	   								bindingDivId = '#' + targetDivId;
	   							}else if('@page' == bindingFor){
	   								bindingDivId = 'document';
	   							}else{
	   								var bindingFieldId = mw3.getChildObjectId(objectId, bindingFor);
	   								
	   								bindingDivId = '#' + mw3._getObjectDivId(bindingFieldId);
	   							}
	   							
	   							$(bindingDivId).bind(eventBinding, {command: command}, function(event){
	   								eval(event.data.command);
	   							});
	   						}
				    	}
				    }
				    
		   			if(methodContext.keyBinding && methodContext.keyBinding.length > 0){
		   				for(var i=0; i<methodContext.keyBinding.length; i++){
		   					var keyBinding = methodContext.keyBinding[i];
		   					
		   					if(keyBinding.indexOf("@Global") > -1){
		   						/*
		   						 * 2013-03-25 cjw
		   						 * global 일때 keyBinding 문제 해결 
		   						 */ 
		   						keyBinding = keyBinding.substr(0, keyBinding.length - "@Global".length);
		   					
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
		   			
		   			//mouse binding installation
		   			if(methodContext.mouseBinding){
		   				var which = 3;
		   				if(methodContext.mouseBinding == "right")
		   					which = 3;
		   				else if(methodContext.mouseBinding == "left")
		   					which = 1;

		   				if(methodContext.mouseBinding == "drag" || methodContext.mouseBinding == "drag-enableDefault"){
		   					if(faceHelper && faceHelper.draggable){
		   						faceHelper.draggable(command);
		   					}else{
		   						theDiv[0]['dragCommand'] = command;
			   					theDiv.draggable({
				   				      appendTo: "body",
				   				      helper: "clone",
				   				      zIndex: 100,
					   				  start: function(event, ui) {
					   					var className = $(this).attr('className');
					   					$(".onDrop_" + className.split('.').join('_')).css("border-width", "1px").css("border-style", "dashed").css("border-color", "orange");
					   					
					   					eval(this['dragCommand']);
					   			      },
					   			      drag: function(event, ui) {
					   			      },
					   			      stop: function() {
						   					var className = $(this).attr('className');
						   					
						   					$(".onDrop_" + className.split('.').join('_')).css("border-width", "").css("border-style", "").css("border-color", "");
					   			      }
				   				    });				   						
		   					}
		   				}//end of case 'drag start'
		   				else if(methodContext.mouseBinding == "drop"){
		   					theDiv[0]['dropCommand'] = command;
		   					
		   					theDiv.droppable({
		   						greedy: true,
		   						drop: function(event, ui){
		   							$(this).removeClass('ui-state-active');
		   							
		   							eval(this['dropCommand']);		   							
		   						},
		   						
		   						over: function(event, ui){
		   							var className = ui.draggable.attr('classname');
		   							
		   							if(className){
			   							var dropName;		   							
			   							if(className.indexOf('.') > -1)
			   								dropName = "onDrop_" + className.split('.').join('_');
			   							else
			   								dropName = className;
			   							
			   							if($(this).hasClass(dropName)){
			   								$(this).addClass('ui-state-active');
			   							}
		   							}
		   						},
		   						
		   						out : function(event, ui){
		   							$(this).removeClass('ui-state-active');
		   						}
		   					});
		   				}//end of case 'drop'
		   				
		   				//case of general mouse click
		   				else{
		   					theDiv[0]['mouseCommand' + which] = command;

							// click(mouse right) is contextmenu block
			   			 	if(which == 3){
			   			 		theDiv[0].oncontextmenu = function() { return false; };
			   			 	}
			   			 				
		   					var mouseup = function(e) {
		   			 			
		   			 			if(e.which == which){
			   				    	mw3.mouseX = e.pageX;
			   		    			mw3.mouseY = e.pageY;
		   			 				
			   						eval(this['mouseCommand' + e.which]);
			   									   						
		   			 				e.stopPropagation(); //stops to propagate to parent that means consumes the event here.
		   			 			}
		   			 		};
		   			 		
		   			 		$(theDiv[0]).bind('mouseup', mouseup);
		   				}
		   				
		   				
		   			} // end of mouse binding installation
		   			
		   			if(methodContext.inContextMenu){
		   				contextMenuMethods[contextMenuMethods.length] = methodContext;
		   			}

			   }
			   
			   //install context menu
			   if(contextMenuMethods.length > 0){				   
				   var menuItems = [];
				   
				   for(var i=0; i<contextMenuMethods.length; i++){							   
					   var serviceMethodContext = contextMenuMethods[i];
					   
					   // context menu enable/disable 처리에 대한 faceHelper assist function 추가					   
					   if(mw3.getFaceHelper(objectId) && mw3.getFaceHelper(objectId).ableContextMenu && !mw3.getFaceHelper(objectId).ableContextMenu(serviceMethodContext.methodName))
						   continue;
					   
					   
					   // 상태에 따른 visible 처리
					   /*
						if(serviceMethodContext.when != mw3.WHEN_EVER){
				   			if( (mw3.when && !((serviceMethodContext.when.indexOf(mw3.when + ',') != -1) || (serviceMethodContext.when.indexOf(',' + mw3.when) != -1) || (serviceMethodContext.when == mw3.when))) 
				   					||
					   			(mw3.where && (serviceMethodContext.where!='wherever' && serviceMethodContext.where.indexOf(mw3.where) == -1) )
					   				
				   			)
				   				continue;
						}
						*/
					   
						
					   var command = "mw3.call("+objectId+", '"+serviceMethodContext.methodName+"')";
				   		
					   if(serviceMethodContext.needToConfirm) {						   
						   if(this.needToConfirmMessage){							   
							   command = "if (confirm(\'"+ mw3.localize(mw3.needToConfirmMessage, serviceMethodContext.displayName) +"\'))" + command;
						   }
						   else {
							   command = "if (confirm(\'Are you sure to "+mw3.localize(serviceMethodContext.displayName)+" this?\'))" + command;
						   }
					   }
				   		
					   var menuItem = { 
							   text: mw3.localize(serviceMethodContext.displayName) + (serviceMethodContext.keyBinding ? '(' + serviceMethodContext.keyBinding[0] + ')' : ''), 
							   onclick: { fn: 
								   function(){
								   		eval(this._oOnclickAttributeValue.command);
								   },
								   command: command
							   } 
					   };
					   
					   menuItems[menuItems.length] = menuItem;
				   }
				   
//				   theDiv.append("<div id='contextmenu_" + objectId + "'></div>");
				   
				   if(menuItems.length){
					   if(typeof $('#' + targetDivId).attr('contextMenu') == 'undefined'){
						   YAHOO.util.Event.onContentReady(targetDivId, function () {
							   new YAHOO.widget.ContextMenu(
									"_contextmenu_" + objectId,
									{
										zindex: 2000,
										trigger: theDiv[0],
										itemdata: menuItems,
										lazyload: true
									}
								);
						   });
						   
						   $('#' + targetDivId).attr('contextMenu', 'true');
					   }
				   }
			   }		
			}
			
			Metaworks3.prototype.showPopop = function(objId, serviceMethodContext, result){
				$('body').append("<div id='" + mw3.popupDivId + "' class='target_" + serviceMethodContext.target + "' style='position: absolute; z-index:10; top:" + mw3.mouseY + "px; left:" + mw3.mouseX + "px'></div>");
				
				mw3.locateObject(result, null, '#' + mw3.popupDivId);
			};

			Metaworks3.prototype.showStick = function(objId, serviceMethodContext, result){
				$('body').append("<div id='" + mw3.popupDivId + "' class='target_" + serviceMethodContext.target + "' style='position: absolute; z-index:10; top:" + mw3.mouseY + "px; left:" + mw3.mouseX + "px'></div>");
				
				mw3.locateObject(result, null, '#' + mw3.popupDivId);
				
				// stick mode is auto close
				if(serviceMethodContext.target == 'stick')
					closeOutsideContainer(mw3.popupDivId);				

			};
			Metaworks3.prototype.showOverPopop = function(objId, serviceMethodContext, result){
				var zIndex = 10;
				if( serviceMethodContext.target=="popupOverPopup" ){
					var modalWindow = $('.ui-dialog');
					if(modalWindow.length > 0){
						zIndex = $(modalWindow[modalWindow.length-1]).css('z-index');
						zIndex = String(Number(zIndex)+1);
					}else{
						zIndex = 101;
					}		
				}
				
				$('body').append("<div id='" + mw3.popupDivId + "' class='target_" + serviceMethodContext.target + "' style='z-index:"+zIndex+";position:absolute; top:" + mw3.mouseY + "px; left:" + mw3.mouseX + "px'></div>");
				
				mw3.locateObject(result, null, '#' + mw3.popupDivId);				
				
			};

			Metaworks3.prototype.getValueForFieldDescriptor = function(object, type){
				if(object && object.__className){
					var metadata = mw3.getMetadata(object.__className);
					
					var fieldName = metadata.getFieldNameForFieldDescriptorType(type);
					
					if(fieldName){
						if(typeof object[fieldName] == 'object')
							return this.getValueForFieldDescriptor(object[fieldName], type);
						else
							return object[fieldName];
					}else{
						return metadata.displayName;
					}
				}
				
				return null;

			};
			
			Metaworks3.prototype.getObjectNameValue = function(object){
				return this.getValueForFieldDescriptor(object, 'name');
			};

			Metaworks3.prototype.getObjectKeyValue = function(object){
				return this.getValueForFieldDescriptor(object, 'key');
			};

			
			////// reference objects //////
			
			var FieldRef = function(object, objectId, fieldDescriptor){
				this.object = object;
				this.objectId = objectId;
				this.fieldDescriptor = fieldDescriptor;
			};
			
			
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
					
				var oldContext = mw3.getContext();
				if(context!=null){
					//mw3.setContext(context);
					
				}
				
				var parentWhen = mw3.when;
				var when = null;				

				if(context!=null && context.when){
					when = context.when;
				}else{
					when = parentWhen;
				}
				
				if(when == mw3.WHEN_VIEW)
					face = className + (this.fieldDescriptor.viewFace ? ":" +this.fieldDescriptor.viewFace : "");
				else
					face = className + (this.fieldDescriptor.inputFace ? ":" +this.fieldDescriptor.inputFace : "");

				
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

					// when 에 따른 noneditable
					if(this.fieldDescriptor.attributes['noneditable']){
						if(this.fieldDescriptor.attributes['noneditable.when']){
							if(this.fieldDescriptor.attributes['noneditable.when'][mw3.when] != null)
								when = mw3.WHEN_VIEW;
						}else if(this.fieldDescriptor.attributes['noneditable.where']){
							if(this.fieldDescriptor.attributes['noneditable.where'][mw3.where] != null)
								when = mw3.WHEN_VIEW;
						}else if(this.fieldDescriptor.attributes['noneditable.how']){
							if(this.fieldDescriptor.attributes['noneditable.how'][mw3.how] != null)
								when = mw3.WHEN_VIEW;
						}else{
							when = mw3.WHEN_VIEW;
						}
						
						if(typeof context == 'undefined')
							context = {};
						
						context['when'] = when;
					}					
				}
				
				var options = {descriptor: this.fieldDescriptor};
				// set name for dom
				options['name'] = this.fieldDescriptor.name;
				
				if(context){
					if(context['htmlTag']) options['htmlTag'] = context['htmlTag'];					
					if(context['htmlAttr']) options['htmlAttr'] = context['htmlAttr'];					
					if(context['htmlAttrChild']) options['htmlAttrChild'] = context['htmlAttrChild'];
					if(context['ejsPath']) options['ejsPath'] = context['ejsPath'];
				}
							
				var beanPropertyOption = {objectId: this.objectId, name: this.fieldDescriptor.name};
							
				if(!designMode){ //means general mode
					if(when && context && context.when) // && parentWhen && when != parentWhen)
						options['when'] = when;

					html = mw3.locateObject(value, face, null, options, beanPropertyOption);
				}else if(!designModeDepth2){ //means just design mode
					options['when'] = '__design-depth2';
					
					html = mw3.locateObject(value, face, null, options, beanPropertyOption);
				}else // means this fields is within the designee 
					html = this.fieldDescriptor.displayName + " Here.";
				
//				mw3.setContext(oldContext);
				
				
				//mw3.addBeanProperty(this.objectId, "." + this.fieldDescriptor.name);
				
				
				
				return html;
			};
			
			
			var MethodRef = function(object, objectId, methodContext){
				this.object = object;
				this.objectId = objectId;
				this.methodContext = methodContext;
			};

			MethodRef.prototype.caller = function(){				
				
				if(mw3.needToConfirmMessage) {
					return 'if(event && event.stopPropagation)	 event.stopPropagation(); else if(window.event) window.event.cancelBubble = true;'+(this.methodContext.needToConfirm ? 'if (confirm(\'' + mw3.localize(mw3.needToConfirmMessage, this.methodContext.displayName) + '\'))':'')  + 'mw3.getObject(' + this.objectId + ').' + this.methodContext.methodName + '()';
				} else {
					return 'if(event && event.stopPropagation)	 event.stopPropagation(); else if(window.event) window.event.cancelBubble = true;'+(this.methodContext.needToConfirm ? 'if (confirm(\'Are you sure to ' + this.methodContext.displayName + ' this?\'))':'')  + 'mw3.getObject(' + this.objectId + ').' + this.methodContext.methodName + '()';
				}
				
				//return 'window.event.stopPropagation();'+(this.methodContext.needToConfirm ? 'if (confirm(\'Are you sure to ' + this.methodContext.displayName + ' this?\'))':'')  + 'mw3.getObject(' + this.objectId + ').' + this.methodContext.methodName + '()';
			};
			
			MethodRef.prototype.here = function(){
				if(mw3.isHiddenMethod(this))					
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
			};
			
			MethodRef.prototype.call = function(){
				mw3.call(this.objectId, this.methodName);
			};
			
			
			var MetaworksObject = function(object, divName){
				this.object = object;
				this.divName = divName;
				
				return mw3.locateObject(object, null, divName).getObject();
			};


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
			
			function closeOutsideContainer(divId){
				$('#' + divId).one('destroy', {divId: divId}, function(event){
					$(this).remove();
					
					$('body').unbind('mousedown.cos_' + event.data.divId);
				});
				
				$('body').bind('mousedown.cos_' + divId, {divId: divId}, function(event){
					var container = $('#' + event.data.divId);
					
					var result = isMouseInContanier(container, event);				
					if(!result)
						$(container).trigger('destroy');
				});
			};
			
			function isMouseInContanier(container, event){
				var containerOffset = container.offset();
				if( containerOffset ){
					containerOffset.right = parseInt(containerOffset.left) + container.width();
					containerOffset.bottom = parseInt(containerOffset.top) + container.height();
	
					if ('select-one' == event.srcElement.type ||
						((containerOffset.left <= event.pageX && event.pageX <= containerOffset.right) && 
						(containerOffset.top <= event.pageY && event.pageY <= containerOffset.bottom))){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
			
			Date.prototype.format = function(f) {
			    if (!this.valueOf()) return " ";
			 
			    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
			    var d = this;
			     
			    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
			        switch ($1) {
			            case "yyyy": return d.getFullYear();
			            case "yy": return (d.getFullYear() % 1000).zf(2);
			            case "MM": return (d.getMonth() + 1).zf(2);
			            case "dd": return d.getDate().zf(2);
			            case "E": return weekName[d.getDay()];
			            case "HH": return d.getHours().zf(2);
			            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
			            case "mm": return d.getMinutes().zf(2);
			            case "ss": return d.getSeconds().zf(2);
			            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
			            default: return $1;
			        }
			    });
			};
			 
			String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
			String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
			Number.prototype.zf = function(len){return this.toString().zf(len);
};

var MetaworksService = function(className, object, svcNameAndMethodName, autowiredObjects, objId, divId, placeholder, callback, sync, serviceMethodContext){
	this.faceHelperQueue = [];
	this.index = null;
	
	this.setIndex = function(index){
		this.index = index;
	};
	
	this.__showResult = function(object, result, objId, svcNameAndMethodName, serviceMethodContext, placeholder, divId, callback ){
		var startTime = new Date().getTime();
//		mw3.log('__showResult : [' + objId + ']' + svcNameAndMethodName + ' ---> ' + new Date().getTime());		
		
		mw3.requestMetadataBatch(result);
		
		// 2012-03-19 cjw 기존 소스가 ejs.js 생성자 호출 보다 늦게 method 값을 할당하여 맨위로 올림
		mw3.recentCallMethodName = svcNameAndMethodName;
		mw3.recentCallObjectId = objId;
		
		if(mw3.isRecordingSession && !mw3.recordingExceptClasses[object.__className]){
			
			var objectKey = mw3._createObjectKey(object);
			var next = "autowiredObject." + objectKey + "." + svcNameAndMethodName;
			
			mw3.recording[mw3.recording.length] = {
				//next: next,
				value: object,
				objectKey: objectKey,
				methodName: svcNameAndMethodName//,
				//scenario: "testScenario"
			};
		}
		
		//alert("call.result=" + dwr.util.toDescriptiveString(result, 5))
//		mw3.debug("call result");

		if(result){
			if(serviceMethodContext.target=="none"){
				// none mode is object return
			}else if(serviceMethodContext.target=="self"){
				mw3.setObject(objId, result);
								
			}else if(serviceMethodContext.target=="popup" || serviceMethodContext.target=="stick" || serviceMethodContext.target=="popupOverPopup"){
				//store the recently added object Id for recent opener
				mw3.recentOpenerObjectId.push(objId);
				
				mw3.popupDivId = serviceMethodContext.target + '_' + objId;
				
				$('#' + mw3.popupDivId).remove();

				if(placeholder)
					mw3.removeObject(placeholder);
				
				if(serviceMethodContext.target == 'popup'){
					mw3.showPopop(objId, serviceMethodContext, result);
				}else if(serviceMethodContext.target == 'popupOverPopup'){
					mw3.showOverPopop(objId, serviceMethodContext, result);
				}else{
					mw3.showStick(objId, serviceMethodContext, result);
				}
								
				$('#' + mw3._getObjectDivId(result.__objectId)).one('destroy', function(){
					mw3.recentOpenerObjectId.pop();
				});
			}else if(serviceMethodContext.target=="opener" && mw3.recentOpenerObjectId.length > 0){
				mw3.setObject(mw3.recentOpenerObjectId[mw3.recentOpenerObjectId.length - 1], result);

				
			}else{ //case of target is "auto"
				var results = result.length ? result: [result];
				
    			var mappedObjId;
				for(var j=0; j < results.length; j++){
					var result_ = results[j];
					
					if(result_ == null)
						continue;
					
        			var objKeys = mw3._createObjectKey(result_, true);
        			var neverShowed = true;
        			
        			if(serviceMethodContext.target == 'auto'){
	        			if(objKeys && objKeys.length){
	        				for(var i=0; i<objKeys.length && neverShowed; i++){
		        				mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
		        				
		        				var mappedObjdivId = "objDiv_" + mappedObjId;
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
        			}

        			if(neverShowed){
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
		
		// 2012-04-16 faceHelper call change
		if(serviceMethodContext.target != "none"){
			if(serviceMethodContext.loadOnce){
				result['__cached'] = true;
				serviceMethodContext['cachedObjectId'] = mw3.targetObjectId;
			}
			    				
			mw3.onLoadFaceHelperScript();
			
			if(mw3.getFaceHelper(objId) && mw3.getFaceHelper(objId).endLoading){
				mw3.getFaceHelper(objId).endLoading(svcNameAndMethodName);
			}else{
				mw3.endLoading(objId, svcNameAndMethodName);
			}
				
			//TODO: why different method name?  showStatus and showInfo
			if(mw3.getFaceHelper(objId) && mw3.getFaceHelper(objId).showStatus){
				mw3.getFaceHelper(objId).showStatus( svcNameAndMethodName + " DONE.");
			}else{

				mw3.showInfo(objId, svcNameAndMethodName + " DONE");	
			}
		}
		
		if(callback && typeof callback == 'function')
			callback();
		
		if(mw3.afterCall)
			mw3.afterCall(svcNameAndMethodName, result);
		
		//mw3.log('++showResult : [' + objId + ']' + svcNameAndMethodName + ' ---> ' + (new Date().getTime() - startTime));
		
		return result;
	};
	
	this.call = function(){
		var returnValue = null;
		var metaworksServiceIndex = this.index;
		var loaded = false;
		
		mw3.metaworksProxy.callMetaworksService(className, object, svcNameAndMethodName, autowiredObjects,
				{ 
	        		callback: function(result){	   	        			
	        			returnValue = result;
	        			
	        			if(serviceMethodContext.target != "none"){
	        				var metaworksService = mw3.metaworksServices[metaworksServiceIndex];
	        				metaworksService.__showResult(object, result, objId, svcNameAndMethodName, serviceMethodContext, placeholder, divId, callback);
	        				mw3.metaworksServices[metaworksServiceIndex] = null;
	        			} 
	        		},

	        		async: !sync && serviceMethodContext.target!="none",
	        		
	        		errorHandler:function(errorString, exception) {
	        			if(serviceMethodContext.target=="none")
	        				throw exception;
	        			
        				if(placeholder){
        					mw3.removeObject(placeholder);
        				}
	        			
	        			if(mw3.objects[objId] && mw3.getFaceHelper(objId) && mw3.getFaceHelper(objId).showError){
		        			if(!exception)
		        				mw3.getFaceHelper(objId).showError( errorString, svcNameAndMethodName );
		        			else
		        				mw3.getFaceHelper(objId).showError( (exception.targetException ? exception.targetException.message : exception.message), svcNameAndMethodName );
						
						}else{
							if(!exception)
								mw3.showError(objId, errorString, svcNameAndMethodName);
							else
								mw3.showError(objId, (exception.targetException ? exception.targetException.message : exception.message), svcNameAndMethodName, exception);
						}
	        		}
			
	    		}
			);
		
		return returnValue;
	};

};
			
			

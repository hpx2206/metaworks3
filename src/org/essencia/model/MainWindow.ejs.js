var org.essencia.model.MainWindow = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.objectDivId = mw3._getObjectDivId(this.objectId);
    this.objectDiv = $('#' + this.objectDivId);
    
    this.object = mw3.objects[this.objectId];
    this.objectMetadata = mw3.getMetadata(this.className);

    if(this.objectMetadata.faceOptions && this.objectMetadata.faceOptions.tabClass)
        this.tabClass = this.objectMetadata.faceOptions.tabClass;
    else
        this.tabClass = 'org.uengine.codi.mw3.ide.CloudTab';
    
    this.divClass = this.tabClass.split('.').join('_');
    
    if(this.object == null)
        return true;
    
    //      'height': '100%',
    //'position': 'relative',
    this.objectDiv.css({
        'padding-bottom': '35px'
    }).addClass('mw3_layout');
    
    this.lastIndex = this.object.tabs.length;
};

org.essencia.model.MainWindow.prototype = {
    loaded : function(){
        this.bind();
        
        this.objectDiv.find('.' + this.divClass + ':first').trigger('click');
    },
    bind : function(){
        var objectId = this.objectId;
        
        this.objectDiv.find('.' + this.divClass).unbind();
        this.objectDiv.find('.' + this.divClass).bind('removetab', function(event){
            var id = $(this).attr('id');
            
            mw3.getFaceHelper(objectId).remove(id.substring(id.indexOf('_')+1));
        });
        this.objectDiv.find('.' + this.divClass).bind('click selecttab', function(event){
            var id = $(this).attr('id');

            mw3.getFaceHelper(objectId).select(id.substring(id.indexOf('_')+1));
        });
    },
    toAppend : function(object){
        var tabId = null;
        var tabName = '';

        var autowiredTab = mw3.getAutowiredObject(this.tabClass + '@'+object.id, true);
        
        if(autowiredTab){
            autowiredTab.getFaceHelper().select();
        }else{
            var tabMetadata = mw3.getMetadata(object.__className);
            
            if(object.name){
                tabName = object.name;
            }else{
                tabName = tabMetadata.displayName;
            }
            
            var tab = {
                __className : this.tabClass,
                name : tabName,
                id : object.id,
                type : object.type,
                parentId : this.objectId,
                className : object.__className
            };
            
            
            this.lastIndex = this.lastIndex + 1;
            var id = this.objectId + '_' + this.lastIndex;
    
            $('<div>').addClass(this.divClass).attr('id', 'top_' + id).appendTo(this.objectDiv.find('.boxtoprightTab').first()).html(mw3.locateObject(tab, tab.__className, null));
            $('<div>').attr('id', 'bottom_' + id).css({'height' : '100%'}).appendTo(this.objectDiv.find('.contentcontainer').first()).html(mw3.locateObject(object, object.__className, null));
            
            this.bind();
            this.select(id);
        }
    },
    
    select : function(id){
        $('.' + this.divClass).removeClass('focus');        
        $('#top_' + id).siblings().removeClass('selected').end().addClass('selected').addClass('focus');
        $('#bottom_' + id).siblings().hide().end().show();
    },
    
    remove : function(id){
        var nextFocus = $('#top_' + id).prev();
        if(nextFocus.length == 0)
            nextFocus = $('#top_' + id).next();
        
        var tabDivObj = $('#top_' + id).children(':first');
        
        if(tabDivObj){
            var tabObjectId = tabDivObj.attr('objectId');
            var tabObject = mw3.getObject(tabObjectId);
            var tabMetadata = mw3.getMetadata(tabObject.__className);
            
            if(tabMetadata.keyFieldDescriptor){
                tabId = tabObject[tabMetadata.keyFieldDescriptor.name];
            }
            
            var tab = mw3.getAutowiredObject(this.tabClass + '@'+tabId, true);
            
            if(tab)
                mw3.removeObject(tab.__objectId);
            
            var content = mw3.getAutowiredObject(tabObject.className + '@'+tabId, true);
            if(content)
                mw3.removeObject(content.__objectId);
            
            if(nextFocus.length > 0)
                nextFocus.trigger('selecttab');
        }
    }
};
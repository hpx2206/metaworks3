var org_uengine_codi_mw3_model_PublicServiceIntroduceTabInfo = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = '#objDiv_' + objectId;
    this.div = $(this.divId);
    
    this.object = mw3.objects[this.objectId];
    
    $('#objDiv_' + objectId).addClass('mw3_tab').addClass('mw3_layout').attr('objectId', objectId);
    
    $('#tabs_' + objectId).tabs({
        show: function(event, ui){
            if(mw3.getFaceHelper(objectId))
                mw3.getFaceHelper(objectId).resize(ui);
        }
    });
    
    var objectMetadata = mw3.getMetadata(className);
    
    if(objectMetadata && objectMetadata.faceOptions && objectMetadata.faceOptions['tabsBottom'] == 'true'){
        $('#tabs_' + objectId).addClass('tabs-bottom');
        
        // fix the classes
        $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
          .removeClass( "ui-corner-all ui-corner-top" )
          .addClass( "ui-corner-bottom" );
     
        // move the nav to the bottom
        $( ".tabs-bottom .ui-tabs-nav" ).appendTo( ".tabs-bottom" );
        
    }   

    var faceHelper = this;
    
    faceHelper.load();
    
};

org_uengine_codi_mw3_model_PublicServiceIntroduceTabInfo.prototype = {
    load : function() {
        var object = mw3.objects[this.objectId];
        var options = {
            togglerLength_open: 0, 
            spacing_open:       0, 
            spacing_closed:     0,
            center__onresize:   'mw3.getFaceHelper('+this.objectId+').resizeChild()'
        }
        
        this.layout = $('#objDiv_' + this.objectId).layout(options);
    },
    
    destroy : function(){
        $('#tabs_' + this.objectId).tabs('destroy');
    },
    
    resize : function(ui){
        if(this.layout){
            this.layout.resizeAll();
            
            this.resizeChild(ui);
        }
    },
    
    resizeChild : function(ui){
        $(ui.panel).find('.mw3_resize').each(function(index, value){
            var layoutId = value.getAttribute('objectId');
            
            if(layoutId)
                mw3.getFaceHelper(layoutId).resize();
        });
    },
    
    addTab : function(appendobject) {
        var tabSize = this.object.introList.length;
        var div_href = "tab_"+this.objectId+"_"+tabSize;
            
        // li 붙이기.
        if(appendobject.__className == 'org.uengine.codi.mw3.model.PublicServiceIntroduceTab') {
           
            var li_ClassName = "ui-state-default ui-corner-top";        
            var li_Id = "tabIcon_"+appendobject.name;
            var li_href = "tab_"+this.objectId+"_"+(tabSize + 1);
            
            var target = $('.ui-tabs-nav').find('li').eq(tabSize-1);
            target.after('<li class="'+li_ClassName+'"><a id="'+li_Id+'" href="#'+li_href+'">'+appendobject.name+'</a></li>');
            
             // tab을 붙이자 마자 객체를 push 해야 한다.
            if(!this.object.introList)
                this.object.introList = [];
                
            this.object.introList.push(appendobject);
            
            // 마지막 li를 다시 찾는다.
            this.object = mw3.objects[this.objectId];
            tabSize = this.object.introList.length;
            
            // 그 후 li 의 a 링크를 찾는다. 그리고 a의 html에 locateObject
            target = $('.ui-tabs-nav').find('li').eq(tabSize-1).find('a');
            target.html(mw3.locateObject(appendobject, appendobject.__className));
            
        // div 붙이기    
        } else {
        
            var div_style = "height: 100%";
            var div_class = "ui-tabs-panel ui-widget-content ui-corner-bottom";
        
            // locateObject 작업
            var html = mw3.locateObject(appendobject);
            
            // 껍데기 div를 만들고 그 div를 찾아서 locateObject
            $('#tabs_' + this.objectId).children('div').append( "<div id= '" + div_href + "' style='" + div_style + "' class='" + div_class + "'></div>" );
            $('#tabs_' + this.objectId).find('div').last().append(html);
            
        }
        
        // div와 li를 생성하고 객체를 push 한 다음 다시 추가된 객체의 사이즈를 찾고
        // + 버튼 앞의 객체에 이벤트를 부여한다.
        this.object = mw3.objects[this.objectId];   
        tabSize = this.object.introList.length;
        
        // div click 이벤트 처리
        var targetDiv = $("#tabs_"+this.objectId);
        targetDiv.tabs("option", "active", tabSize - 1);
        
    },
    
    refreshTab : function(appendobject) {
        
    },
    
    toAppend : function(appendobject) {
        
       if(appendobject.__className == 'org.uengine.codi.mw3.model.PublicServiceIntroduce' ||
            appendobject.__className == 'org.uengine.codi.mw3.model.PublicServiceIntroduceTab') {
            
           this.addTab(appendobject);
           
       } else {
           this.refreshTab(appendobject);
       }
       
    }
    
};


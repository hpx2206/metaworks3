var org_uengine_codi_mw3_model_PublicServiceIntroduceTabPanel = function(objectId, className){
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

org_uengine_codi_mw3_model_PublicServiceIntroduceTabPanel.prototype = {
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
        var li_ClassName = "ui-state-default ui-corner-top";        
        var li_Id = "tabIcon_"+appendobject.tabName;
        var li_href = "tab_"+this.objectId+"_"+(tabSize + 1);
        var div_style = "height: 100%";
        var div_class = "ui-tabs-panel ui-widget-content ui-corner-bottom";
        
        var target = $('.ui-tabs-nav').find('li').eq(tabSize-1);
        target.after('<li class="'+li_ClassName+'"><a id="'+li_Id+'" href="#'+li_href+'">'+appendobject.tabName+'</a></li>');
        
        // tab을 객체에 직접 locateObject
        mw3.locateObject(appendobject);
        
        // div 붙이기
        $('#tabs_' + this.objectId).children('div').append( "<div id= '" + li_href + "' style='" + div_style + "' class='" + div_class + "'><p></p></div>" );
        
        
        // tab을 붙이자 마자 객체를 push 해야 한다.
        if(!this.object.introList)
            this.object.introList = [];
            
        this.object.introList.push(appendobject);
        console.log(this.object.introList);
        
        
        // div를 생성하고 객체를 push 한 다음 다시 추가된 객체의 사이즈를 찾고
        // + 버튼 앞의 객체에 이벤트를 부여한다.
        tabSize = this.object.introList.length;
        
        var targetDiv = $("#tabs_"+this.objectId);
        var targetLi = $('.ui-tabs-nav').find('li').eq(tabSize-1);
        
        targetDiv.tabs("option", "active", tabSize - 1);
        
    },
    
    toAppend : function(appendobject) {
        
       if(appendobject.__className == 'org.uengine.codi.mw3.model.PublicServiceIntroduce') {
            this.addTab(appendobject);
            
       }
    }
    
};


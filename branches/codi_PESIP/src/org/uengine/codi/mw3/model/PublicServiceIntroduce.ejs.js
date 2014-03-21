var org_uengine_codi_mw3_model_PublicServiceIntroduce = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.objectDivId = mw3._getObjectDivId(this.objectId);
    
    this.object = mw3.objects[this.objectId];
    
    var faceHelper = this;
    
    faceHelper.divClick();
    faceHelper.divDblClick();
};

org_uengine_codi_mw3_model_PublicServiceIntroduce.prototype = {
    addService : function(appendobject){
    
        var options = {};
        options['htmlAttr'] =  {'style': 'cursor: default;' };
        
        if(this.object.sectors.length == 0) 
            $('#introTable_'+this.objectId+' tr:last').after('<tr><th width="10%">'+mw3.locateObject(appendobject, appendobject.__className, null, options)+'</th></tr>');
        
        else {
        
            for(var i = 0; i <= this.object.sectors.length; i++) {
                
                if(i == 0) {
                    $('#introTable_'+this.objectId+' tr:last').after('<tr><th width="10%">'+mw3.locateObject(appendobject, appendobject.__className, null, options)+'</th></tr>');
                    
                } else {
                
                    // 첫번째는 반드시 column의 항목들을 그려야 하는데 if문을 사용하여 0을 써버리면 else문에서는
                    // 당연 1이 증가해서 온다. 그런데 object의 배열은 0부터 시작한다.
                    // 그래서 강제로 -1을 하여 그려준다. 문제는 없다.
                    var sector = this.object.sectors[i-1];
                    $('#introTable_'+this.objectId+' tr:last th:last').after('<td class="item" sector="'+sector.id+'" service="'+appendobject.id+'" width="10%"></td>');
                    
                }
            }
            
            this.divClick();
            this.divDblClick();
            
        }
        if(!this.object.sectors)
            this.object.sectors = [];
            
        this.object.services.push(appendobject);
        
    },
    
    addSector : function(appendobject){
    
        var options = {};
        options['htmlAttr'] =  {'style': 'cursor: default;' };
        
        if(this.object.services.length == 0) {
            $('#introTable_'+this.objectId+' tr:first th:last').after('<th width="10%">'+mw3.locateObject(appendobject, appendobject.__className, null, options)+'</th>');
        }
        
        else {
            
            for(var i = 0; i <= this.object.services.length; i++) {
                
                if(i == 0) { 
                    $('#introTable_'+this.objectId+' tr:first th:last').after('<th width="10%">'+mw3.locateObject(appendobject, appendobject.__className, null, options)+'</th>');
               
                } else {
                    
                    // 첫번째는 반드시 row의 항목들을 그려야 하는데 if문을 사용하여 0을 써버리면 else문에서는
                    // 당연 1이 증가해서 온다. 그런데 object의 배열은 0부터 시작한다.
                    // 그래서 강제로 -1을 하여 그려준다. 문제는 없다.
                    var service = this.object.services[i-1];
                    $('#introTable_'+this.objectId+' tr:eq('+i+') th:last').after('<td class="item" sector="'+appendobject.id+'" service="'+service.id+'" width="10%"></td>');
                }
                
            }
            
            this.divClick();
            this.divDblClick();
            
        }
        if(!this.object.services)
            this.object.services = [];
            
        this.object.sectors.push(appendobject);
        
    },
    
    addItem : function(appendobject){
    
        for(var i = 0; i < this.object.sectors.length; i++) {
            var sector = this.object.sectors[i];
            
            // sector 의 id 값이 appendobject 의 sectorid 와 같을 때 
            // service 를 검사해야 한다. 
            if(appendobject.sectorId == sector.id) {
            
                for(var j = 0; j < this.object.services.length; j++) {
                    var service = this.object.services[j];
                    
                    // 그리고 service 까지 같으면 div에 붙인다.
                    // 그런데 빈 tr, td 와 row, column 들 때문에 i 와 j 값은 항상 1이 작다. 설계를 잘못해서
                    // 어쩔 수가 없다. + 1 을 해줘야 한다.
                    if(appendobject.serviceId == service.id) {
                       
                        var options = {};
                        options['htmlAttr'] =  {'style': 'width: 50%; cursor: pointer;' };
                    
                        $('#introTable_'+this.objectId+' tr:eq('+(j+1)+') th:eq('+(i+1)+')').append(mw3.locateObject(appendobject, appendobject.__className, null, options));
                        
                    }
                    
                }
               
            }
            
        } 
        
         if(!this.object.item)
            this.object.item = [];
            
        this.object.item.push(appendobject);   
    },
    
    toAppend : function(appendobject) {
        
        if(appendobject.__className == 'org.uengine.codi.mw3.model.PublicServiceIntroduceCode'){
            if('SECTOR' == appendobject.parentId){
                this.addSector(appendobject);
                
            } else {
                this.addService(appendobject);
                
            }
            
        } else {
            this.addItem(appendobject);
        }
    },
    
    divClick : function() {
    
        $('#' + this.objectDivId + ' td.item').click(function() {
        
            $(this).closest('table').find('td').removeClass('selected');
            $(this).addClass('selected');
            
        });
    },
    
    divDblClick : function() {
    
        $('#' + this.objectDivId + ' td.item').bind('dblclick', {objectId: this.objectId}, function(event, ui) {

            this.sectorId = [];
            this.serviceId = [];
         
            sectorId = $(this).attr('sector');
            serviceId= $(this).attr('service');
            
            var objectId = event.data.objectId;
            var info = mw3.getObject(objectId);
            
            var item = {
                __className : 'org.uengine.codi.mw3.model.PublicServiceIntroduceItem',
                tab : info.id,
                sectorId : sectorId,
                serviceId : serviceId 
            };
            
            mw3.locateObject(item);
            item.addItem();
            
        });
    }
};
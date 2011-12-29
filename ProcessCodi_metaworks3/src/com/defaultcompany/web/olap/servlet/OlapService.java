package com.defaultcompany.web.olap.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.ejb.RemoveException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uengine.components.activityfilters.OlapEtlFilter;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ActivityInstanceContext;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerFactoryBean;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.dao.DefaultConnectionFactory;
import org.uengine.util.dao.GenericDAO;
import org.uengine.util.dao.IDAO;

public class OlapService extends HttpServlet {

    private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        ProcessManagerFactoryBean factoryBean = new ProcessManagerFactoryBean();
        ProcessManagerRemote pm = null;
        String instanceId = null;
        ProcessInstance instance = null;
        try {
            pm = factoryBean.getProcessManager();
            
            IDAO instanceDAO = GenericDAO.createDAOImpl(
                    DefaultConnectionFactory.create(),
                    "select * from bpm_procinst order by instid",
                    IDAO.class);                       
            instanceDAO.select();
            
            response.getWriter().print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            while(instanceDAO.next()) {
                response.getWriter().print("***************************************************");
                instanceId = instanceDAO.getString("instid");
                
                response.getWriter().print("section 1 instanceID: " + instanceId);
                
                instance = pm.getProcessInstance(instanceId);
                
                Vector vec = instance.getActivitiesDeeply(null);
                
                response.getWriter().print("section 2 Activity Size: " + vec.size());
                for(int i=0; i < vec.size(); i++) {
                    response.getWriter().print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    response.getWriter().print("section 3 Activity Count: " + i);
                    Activity activity = ((ActivityInstanceContext)vec.get(i)).getActivity();
                    
                    OlapEtlFilter oef = new OlapEtlFilter();
                    response.getWriter().print("section 4 OlapEtlFilter  afterExecute Start");
                    oef.afterExecute(activity, instance);
                    
                    if(activity.getStatus(instance).equals(Activity.STATUS_COMPLETED)) {
                        response.getWriter().print("section 5 OlapEtlFilter afterComplete Start");
                        oef.afterComplete(activity, instance);
                    }
                    response.getWriter().print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                }
                response.getWriter().print("***************************************************");
            }
            response.getWriter().print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            //close CachedRowset
           	instanceDAO.releaseResource();
            
            pm.applyChanges();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                pm.remove();
            } catch (RemoveException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

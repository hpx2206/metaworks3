package org.uengine.facebook.activities;

import java.util.*;

import org.apache.commons.httpclient.methods.GetMethod;
import org.metaworks.*;
import org.metaworks.inputter.*;
import org.uengine.facebook.api.Facebook;
import org.uengine.kernel.*;
import org.uengine.persistence.dao.WorkListDAO;
import org.uengine.persistence.processinstance.ProcessInstanceDAO;
import org.uengine.processdesigner.ProcessDesigner;
import org.uengine.util.dao.GenericDAO;

import net.sf.json.*;

public class WriteFacebookActivity extends DefaultActivity  {

 private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;
  
 public static void metaworksCallback_changeMetadata(Type type) {

  type.getFieldDescriptor("FacebookLinkName").setDisplayName("페이스북 링크이름");
  type.getFieldDescriptor("FacebookLinkURL").setDisplayName("페이스북 링크주소");
  
  //어느 그룹에 올릴 것인지 선택
  FieldDescriptor my_group;
  my_group = type.getFieldDescriptor("Mygroup");
  my_group.setDisplayName("포스팅 대상 그룹 선택");
  my_group.setInputter(new SelectInput());
  
  
  //그룹에 올릴 것인지 관리자의 담벼락에 올릴 것인지 선택
  FieldDescriptor group_select;
  group_select = type.getFieldDescriptor("Groupselect");
  group_select.setDisplayName("포스팅 대상");
  RadioInput groupselect = new RadioInput(
    new String[]{
      "내 그룹", "내 담벼락", "친구 담벼락"
    },
    
    new Object[]{
      "groupappkey", "myappkey", "friendappkey"
    }
  
  );
  group_select.setInputter(groupselect);
  
  //친구 목록을 가져와 볼까?
  FieldDescriptor friend_lists;
  friend_lists = type.getFieldDescriptor("Friendlists");
  friend_lists.setDisplayName("포스팅 대상 친구 선택");
  friend_lists.setInputter(new SelectInput());
 }
 
 String myappkey;
 String access_token;
 String groupselect;
 String mygroup;
 String friendlists; // add friendlist
 ProcessVariable fb_message;
 ProcessVariable facebookLinkURL;
 ProcessVariable facebookLinkName;
 
 public ProcessVariable getFacebookLinkURL() {
  return facebookLinkURL;
 }
 
 public void setFacebookLinkURL(ProcessVariable facebookLinkURL) {
  this.facebookLinkURL = facebookLinkURL;
 }

 public ProcessVariable getFacebookLinkName() {
  return facebookLinkName;
 }
 
 public void setFacebookLinkName(ProcessVariable facebookLinkName) {
  this.facebookLinkName = facebookLinkName;
 }
 
 public String getMyappkey() {
  return myappkey;
 }

 public void setMyappkey(String myappkey) {
  this.myappkey = myappkey;
 }

 public String getAccess_token() {
  return access_token;
 }

 public void setAccess_token(String access_token) {
  this.access_token = access_token;
 }
 
 public ProcessVariable getFb_message() {
  return fb_message;
 }

 public void setFb_message(ProcessVariable fb_message) {
  this.fb_message = fb_message;
 }

 public WriteFacebookActivity(){
  super();
  setName("Facebook");
  //setMyappkey("137354366353197");
  //setAccess_token("137354366353197|329594b60dc96ba82bacded1.0-100001517300062|lhUAFHiPNvKEzF1FG8wMrFEF7AY");
 }
  
 public String getFriendlists() {
  return friendlists;
 }

 public void setFriendlists(String friendlists) {
  this.friendlists = friendlists;
 }

 public String getMygroup() {
  return mygroup;
 }

 public void setMygroup(String mygroup) {
  this.mygroup = mygroup;
 }


 public String getGroupselect() {
  return groupselect;
 }

 public void setGroupselect(String groupselect) {
  this.groupselect = groupselect;
 }

 protected void executeActivity(ProcessInstance instance)throws Exception {
 
  access_token = getAccess_token();
  myappkey = getMyappkey();
  
  String fb_message = (String)instance.get("",getFb_message().getName());
  String facebookName = (String)instance.get("", getFacebookLinkName().getName());
  String facebookURL = (String)instance.get("", getFacebookLinkURL().getName());
  
  Map<String, String> data = new HashMap<String, String>();  
  data.put("message", fb_message);  
  data.put("name", facebookName);  
  data.put("link", facebookURL);   
  data.put("picture", "http://cfile25.uf.tistory.com/image/165BBF484E4BBD8F3402C0");   
  
  if("myappkey".equals(getGroupselect())){
   //담벼락에 포스팅 할 경우 
   Facebook graph_api = new Facebook(myappkey, access_token);  
   JSONObject result = graph_api.sendPostToMyWall(data);  
   System.out.println(result);
  }else 
  //선택한 그룹으로 포스팅 할 경우
  if("groupappkey".equals(getGroupselect())){
   String groupkey = getMygroup();
   Facebook graph_api = new Facebook(groupkey, access_token);  
   JSONObject result = graph_api.sendPostToMyGroup(data);  
     }else 
  //선택한 친구 담벼락에 포스팅 할 경우
  if("friendappkey".equals(getGroupselect())){
   String friendkey = getFriendlists();
   Facebook graph_api = new Facebook(friendkey, access_token);  
   JSONObject result = graph_api.sendPostToMyFriend(data);  
     }
  
  fireComplete(instance);
 }
 
 public ValidationContext validate(Map options) {
  ValidationContext vc =  super.validate(options);
  
  if(getMyappkey()==null){
   vc.add("관리자 App ID/API Key를 입력하세요");
  }
  
  if(getAccess_token()==null){
   vc.add("관리자 액세스 토큰을 입력하세요");
  }
  
  if(getGroupselect()==null){
   vc.add("관리자 포스팅 유형을 입력하세요");
  }
  
  if(getFb_message()==null){
   vc.add("메세지를 맵핑해 주세요");
  }
  return vc;
 }
}
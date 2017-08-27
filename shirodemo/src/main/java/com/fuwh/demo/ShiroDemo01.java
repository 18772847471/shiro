package com.fuwh.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroDemo01 {
	
	private static Logger log=LoggerFactory.getLogger(ShiroDemo01.class);
	public static void main(String[] args) {
		//ȡ��SecurityManager����
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		//ȡ��SecurityManagerʵ��
		SecurityManager securityManager=factory.getInstance();
		//��securityManager�󶨵�SecurityUtil
		SecurityUtils.setSecurityManager(securityManager);
		
		/*	����Ϊֹ���򵥵�shiro���������ú���	*/

		//ȡ�õ�ǰ�û�
		Subject currentUser=SecurityUtils.getSubject();
		//ȡ�õ�ǰ�û���session
		Session session=currentUser.getSession();
		//������web������һ��ʹ��session
		session.setAttribute("attr","value");
		log.info("ȡ�õ�ֵ��"+session.getAttribute("attr").toString());
		
		//ʹ��shiro�����е�½��֤
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token=new UsernamePasswordToken("fuwh","fuwh1234");
			try {
				currentUser.login(token);
				log.info("��½�ɹ�������");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("��֤ʧ��...");
			}
		}
		
		log.info("��ɫ��֤��ʼ....");
		if(currentUser.hasRole("admin")) {
			log.info("��ǰ�û���admin��ɫ");
		}else {
			log.info("��ǰ�û�û��admin��ɫ");
		}
		
		log.info("Ȩ����֤��ʼ....");
		if(currentUser.isPermitted("lightsaber:debug")) {
			log.info("��ǰ�û���lightsaber:debugȨ��");
		}else {
			log.info("��ǰ�û�û��lightsaber:debugȨ��");
		}
		
		currentUser.logout();
	}
}

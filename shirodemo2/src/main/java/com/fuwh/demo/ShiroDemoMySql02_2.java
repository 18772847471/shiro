package com.fuwh.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroDemoMySql02_2 {
	
	private static Logger log=LoggerFactory.getLogger(ShiroDemoMySql02_2.class);
	public static void main(String[] args) {
		//ȡ��SecurityManager����
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro_jdbc_my_sql_2.ini");
		//ȡ��SecurityManagerʵ��
		SecurityManager securityManager=factory.getInstance();
		//��securityManager�󶨵�SecurityUtil
		SecurityUtils.setSecurityManager(securityManager);

		/*	����Ϊֹ���򵥵Ĵ�mysql���ݿ��ȡrealm��Ϣ��shiro���������ú���	*/
		
		//ȡ�õ�ǰ�û�
		Subject currentUser=SecurityUtils.getSubject();
		
		//ʹ��shiro�����е�½��֤
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token=new UsernamePasswordToken("fuwh","123");
			try {
				currentUser.login(token);
				log.info("��½�ɹ�������");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("��֤ʧ��...");
			}
		}
		
		currentUser.logout();
	}
}

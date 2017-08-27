package com.fuwh.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroDemoMyJdbc03 {
	
	private static Logger log=LoggerFactory.getLogger(ShiroDemoMyJdbc03.class);
	public static void main(String[] args) {
		//ȡ��SecurityManager����
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro_my_jdbc.ini");
		//ȡ��SecurityManagerʵ��
		SecurityManager securityManager=factory.getInstance();
		//��securityManager�󶨵�SecurityUtil
		SecurityUtils.setSecurityManager(securityManager);

		/*	����Ϊֹ���򵥵Ĵ�mysql���ݿ��ȡrealm��Ϣ��shiro���������ú���	*/
		
		//ȡ�õ�ǰ�û�
		Subject subject=SecurityUtils.getSubject();
		
		//ʹ��fuwh�����е�½��֤
				if(!subject.isAuthenticated()) {
					UsernamePasswordToken token=new UsernamePasswordToken("fuwh","123456");
					try {
						subject.login(token);
						log.info(token.getPrincipal()+"��½�ɹ�������");
						if(subject.hasRole("admin")) {
							log.info(token.getPrincipal()+"��admin�Ľ�ɫ");
							Permission p1=new WildcardPermission("bumen:*:query");
							if(subject.isPermitted(p1)) {
								log.info(token.getPrincipal()+"��bumen:*:query��Ȩ��");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.error("��֤ʧ��...");
					}
				}
				subject.logout();
				
				log.debug("*****************************************************************");
				
				if(!subject.isAuthenticated()) {
					UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
					try {
						subject.login(token);
						log.info(token.getPrincipal()+"��½�ɹ�������");
						if(subject.hasRole("yuangong")) {
							log.info(token.getPrincipal()+"��yuangong�Ľ�ɫ");
							Permission p1=new WildcardPermission("bumen:diwubu:query");
							if(subject.isPermitted(p1)) {
								log.info(token.getPrincipal()+"��bumen:diwubu:query��Ȩ��");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.error("��֤ʧ��...");
					}
				}
				subject.logout();
				
				log.debug("*****************************************************************");
				
				if(!subject.isAuthenticated()) {
					UsernamePasswordToken token=new UsernamePasswordToken("lisi","12");
					try {
						subject.login(token);
						log.info(token.getPrincipal()+"��½�ɹ�������");
						if(subject.hasRole("yuangong")) {
							log.info(token.getPrincipal()+"��yuangong�Ľ�ɫ");
							Permission p1=new WildcardPermission("bumen:diwubu:update");
							if(subject.isPermitted(p1)) {
								log.info(token.getPrincipal()+"��bumen:diwubu:update��Ȩ��");
							}else {
								log.info(token.getPrincipal()+"û��bumen:diwubu:update��Ȩ��");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.error("��֤ʧ��...");
					}
				}
				subject.logout();
	}
}

package com.fuwh.demo;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.SimpleByteSource;

public class HashServiceDemo {

	public static void main(String[] args) {
		/*
		 * ����һ��HashService
		 * Ĭ������£�
		 * ɢ���㷨��SHA-512 
		 * ѭ��������1
		 * �����ɹ���
		 */
		DefaultHashService service=new DefaultHashService();
		service.setHashAlgorithmName("SHA-512");//���ü����㷨��Ĭ�Ͼ������
		service.setPrivateSalt(new SimpleByteSource("123"));//����˽��
		service.setGeneratePublicSalt(true);//�������ɹ���
		service.setRandomNumberGenerator(new SecureRandomNumberGenerator());//���ù��ε����ɷ�ʽ
		service.setHashIterations(1);//���ü��ܴ���
		
		/*
		 * ����һ��HashRequest���������HashService������Ҫ��һЩ��Ϣ��
		 */
		HashRequest request=new HashRequest.Builder()
				.setAlgorithmName("MD5")
				.setSalt("12345")
				.setSource("pass1234")
				.setIterations(2)
				.build();
		
		System.out.println(service.computeHash(request).toString());
	}
}

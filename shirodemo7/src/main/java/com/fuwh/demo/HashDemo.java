package com.fuwh.demo;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class HashDemo {

	public static void main(String[] args) {
		String password="pass1234";
		
		/*
		 * Md5ɢ�н���,ͨ��������������
		 * ��ɢ�н��ܵ�ʱ�򣬿���ָ���Σ�salt���ͼ��ܵĴ���
		 * ��������߼��ܵĸ��Ӷȣ���Ϊ������Md5���ܻ��ǿ��ܱ��ƽ�
		 * ���ǣ�����һ��ֻ��ϵͳ֪�����ξͻ����ϲ��ᱻ�ƽ���
		 * ���ܴ�����������߼��ܵĸ��Ӷ�
		 */
		Md5Hash md5Hash1=new Md5Hash(password);
		Md5Hash md5Hash2=new Md5Hash(password, "123");
		Md5Hash md5Hash3=new Md5Hash(password, "123",2);
		System.out.println("Md5����--�����Σ�"+md5Hash1);
		System.out.println("Md5����--���Σ�"+md5Hash2);
		System.out.println("Md5����--����--���μ��ܣ�"+md5Hash3);
		
		/*
		 * Sha256Hash
		 */
		Sha256Hash sha256Hash1=new Sha256Hash(password);
		Sha256Hash sha256Hash2=new Sha256Hash(password, "123");
		Sha256Hash sha256Hash3=new Sha256Hash(password, "123",2);
		System.out.println("Sha256Hash����--�����Σ�"+sha256Hash1);
		System.out.println("Sha256Hash����--���Σ�"+sha256Hash2);
		System.out.println("Sha256Hash����--����--���μ��ܣ�"+sha256Hash3);
	}
}

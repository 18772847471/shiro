package com.fuwh.demo;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

public class CodecDemo {

	public static void main(String[] args) {
		String password="pass1234";
		
		/*
		 * Base64���ṩ��һЩbase64��ʽ�ı���ͽ������
		 */
		System.out.println("Base64���ܺ�"+Base64.encodeToString(password.getBytes()));
		System.out.println("Base64���ܺ�"+Base64.decodeToString(Base64.encodeToString(password.getBytes())));
		
		/*
		 * Hex���ṩ��һЩʮ�����Ƶı���ͽ������
		 */
		System.out.println("Hex�����"+Hex.encodeToString(password.getBytes()));
		System.out.println("Hex�����"+new String(Hex.decode(Hex.encode(password.getBytes()))));
	}
}
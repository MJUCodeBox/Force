package Enum_Injector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Injector {
	
	//Text ��� : "Key(val1, val2....) + �ƹ��ų� + Enter"
	//��� �� : enum�� implements InjectTarget�Ұ� / enum�� setVal�� ���� �� ���� ���� ��.
	
	// User
	static final char DILIMETER = ',', VALUESTART = '(', VALUEEND = ')';
	static final String ErrorMSG1 = "INJECTOR ERROR : �ּ� ", ErrorMSG2 = "�� ", ErrorMSG3 = "�� ���� �����ϴ�."; 
	
	// System
	static Scanner sc;
	static final int tableAddress = 0, keyValue = 1, front = 0, back = 1;
	static Vector<InjectTarget[]> injectTargets = new Vector<InjectTarget[]>();
	
	public interface InjectTarget{
		public void setVal(String[] val);//enum�� ���߾� �����.
		public String[] getInfo();// {tableAddress, keyValue} ���� �����ڿ��� ���� �� ��.
	}
	
	public static void inject(InjectTarget target) {
		String[] info = target.getInfo();
		try {Injector.sc = new Scanner(new File(info[tableAddress]));}
		catch (FileNotFoundException e) {e.printStackTrace();}

		String nowLine, nowKey;
		boolean keyNotFound = true;
		while(keyNotFound) {
			if(sc.hasNextLine()) {
				nowLine = sc.nextLine();
				nowKey = nowLine.split("\\"+VALUESTART)[front];//\\�� ���Խ� ���� ���ڿ��� �������� �߻��ϴ� ������ ���� ����.
				if(nowKey.equals(info[keyValue])){
					target.setVal(nowLine.split("\\"+VALUESTART)[back].split("\\"+VALUEEND)[front].split("\\"+DILIMETER));
					keyNotFound = false;
				}
			}else {
				System.out.println(ErrorMSG1+info[tableAddress]+ErrorMSG2+info[keyValue]+ErrorMSG3);
				keyNotFound = false;
			}
		}
	}
	
	public static void registeAll(InjectTarget[][] targetArrs) {
		for(InjectTarget[] targetArr : targetArrs) {
			Injector.registe(targetArr);
		}
	}
	
	public static void registe(InjectTarget[] targets) {injectTargets.add(targets);}
	public static void injectRegisted() {
		for(InjectTarget[] targetArr : injectTargets) {
			for(InjectTarget target : targetArr) {
				Injector.inject(target);
			}
		}
	}
}
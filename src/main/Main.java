package main;

import Global_KeyListener.KeyBoard;
import view.MainFrame;

public class Main {

	public static void main(String[] args) {
		//defender function �̱�. �ֱ� ��⳪ ���� ��, / ��ο쵵. -> ����. Some How ���� ����.
		//2.5D Draw ~ ���� (����) ����ǰ� �غ�
		
		System.out.println("white virus");
		KeyBoard.init();
		//enum injector ���� �ȵ��.
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.initialize();
	}

}

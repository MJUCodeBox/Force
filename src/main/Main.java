package main;

import Global_KeyListener.KeyBoard;
import view.MainFrame;

public class Main {

	public static void main(String[] args) {
		//defender function 뽑기. 애기 쏘기나 고정 등, / 드로우도. -> 중지. Some How 개발 시작.
		//2.5D Draw ~ 벽좀 (도넛) 적용되게 해봐
		
		System.out.println("white virus");
		KeyBoard.init();
		//enum injector 맘에 안들어.
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.initialize();
	}

}

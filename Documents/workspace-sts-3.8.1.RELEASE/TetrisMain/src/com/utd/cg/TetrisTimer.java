package com.utd.cg;
import java.util.TimerTask;

/**
 * 
 * @author monishaelumalai
 *
 */
public class TetrisTimer extends TimerTask {

	private Tetris game;
	public TetrisTimer(Tetris game) {
		this.game = game;
	}
	@Override
	public void run() {
		game.timer();
	}

}

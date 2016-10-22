package com.utd.cg.angn3;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author monishaelumalai
 *
 */

public class Start{
	JPanel jp;
	JFrame f;
	JTextField Mi,Ni,Si;
	JLabel M,N,S;
	JButton btn;
	
	public static void main(String args[]){
		new Start();
	}
	Start(){
		
		f=new JFrame();
		f.setSize(600,600);
		jp=new JPanel();
		jp.setLayout(null);
		M=new JLabel("Enter Score factor");
		M.setBounds(20, 116, 150, 26);
		Mi=new JTextField();
		Mi.setColumns(6);
		Mi.setBounds(150, 116, 246, 26);
		
		N=new JLabel("Enter Number of rows");
		N.setBounds(20, 216, 150, 26);
		Ni=new JTextField();
		Ni.setColumns(6);
		Ni.setBounds(150, 216, 246, 26);
		
		S=new JLabel("Enter Speed factor");
		S.setBounds(20, 316, 150, 26);
		Si=new JTextField();
		Si.setColumns(6);
		Si.setBounds(150, 316, 246, 26);
		
		btn =new JButton("start");
		btn.setBounds(220, 416, 146, 26);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				test();
			}
		});
		
		jp.add(M);
		jp.add(Mi);
		jp.add(N);
		jp.add(Ni);
		jp.add(S);
		jp.add(Si);
		jp.add(btn);
		f.add(jp);
		f.setVisible(true);
		jp.setVisible(true);
		
	}
	public void test(){
		jp.setVisible(false);
		int score,row;
		float speed;
		//System.out.println("Inputs obtained : "+Mi.getText()+" "+Ni.getText()+" "+Si.getText());
		TetrisMain t;
		if(Mi.getText()==null||Ni.getText()==null||Si.getText()==null||Mi.getText().equals("")||Ni.getText().equals("")||Si.getText().equals("")){
			t=new TetrisMain();	
		}
		else{
		score=Integer.parseInt(Mi.getText());
		row=Integer.parseInt(Ni.getText());
		speed=Float.parseFloat(Si.getText());
		System.out.println("Input : "+score+" "+row+" "+speed);
		t=new TetrisMain(score,row,speed);
		}
		f.add(t);
		t.setVisible(true);
		f.setVisible(true);
	}
	
}

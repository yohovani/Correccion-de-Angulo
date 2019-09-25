/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Correccion;

/**
 *
 * @author yohovani
 */
public class pixel {
	private int x;
	private int y;
	private double Xprima;
	private double Yprima;
	private double grade;

	public pixel(int x, int y, double grade) {
		this.x = x;
		this.y = y;
		this.grade = grade;
		this.Xprima();
		this.Yprima();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public void Xprima(){
		this.Xprima = this.x - this.y*Math.tan(this.grade);
	}
	
	public void Yprima(){
		this.Yprima = y;
	}

	public double getXprima() {
		return Xprima;
	}

	public double getYprima() {
		return Yprima;
	}
	
	
}

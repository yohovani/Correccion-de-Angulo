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
public class test {
	public static void main(String argv[]){
		Correccion c  = new Correccion();
		c.correccion();
		c.draw_image();
		c.save_image("resultado.png");
		System.out.print("");
	}
}

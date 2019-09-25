/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Correccion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author yohovani
 */
public class Correccion {
	private BufferedImage image;
	private BufferedImage resultado;
	private int width, height;
	private ArrayList<pixel> moments;
	
	public Correccion(){
		this.image = ImageManager.toBufferedImage(ImageManager.openImage());
		this.moments = new ArrayList<pixel>();
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.resultado =  new BufferedImage(this.width*4,this.height,BufferedImage.TYPE_INT_RGB);
	}
	
	public void correccion(){
		for(int i=0;i<this.width;i++){
			for(int j=0;j<this.height;j++){
				double m11 = this.getMRed(3, i, j);
				double m02 = this.getMRed(4, i, j);
				double m20 = this.getMRed(5, i, j);
				if(m11 != 0){
					double angulo = Math.atan((2*m11)/(m20-m02))/2;
					this.moments.add(new pixel(i,j,angulo));	
				}

			}
		}
	}
	
	public double getMRed(int opcion,int x, int y){
		switch(opcion){
			case 0:{
				//M_00
				Color aux = new Color(this.image.getRGB(x, y));
				double m = x*y*aux.getRed();
				return m;
			}
			case 1:{
				//M_01
				Color aux = new Color(this.image.getRGB(x, y));
				double m = x*(Math.pow(y, 2)/2)*aux.getRed();
				return m;
			}
			case 2:{
				//M_10
				Color aux = new Color(this.image.getRGB(x, y));
				double m = y*(Math.pow(x, 2)/2)*aux.getRed();
				return m;
			}
			case 3:{
				//M_11
				Color aux = new Color(this.image.getRGB(x, y));
				double m = 0.25*Math.pow(x, 2)*Math.pow(y, 2)*aux.getRed();
				return m;
			}
			case 4:{
				//M_02
				Color aux = new Color(this.image.getRGB(x, y));
				double m = (Math.pow(y, 3)/3)*x*aux.getRed();
				return m;
			}
			case 5:{
				//M_20
				Color aux = new Color(this.image.getRGB(x, y));
				double m = (Math.pow(x, 3)/3)*y*aux.getRed();
				return m;
			}
		}		
		return 0;
	}
	
	public void draw_image(){
		for(int i=0;i<this.width;i++){
			for(int j=0;j<this.height;j++){
				this.resultado.setRGB(i, j, Color.BLACK.getRGB());
			}
		}
		int aux = this.moments.size();
		for(int i=0;i<aux;i++){
			int X = (int)this.moments.get(i).getXprima();
			int Y = (int)this.moments.get(i).getY();
			if(X < this.resultado.getWidth() && Y < this.resultado.getWidth() && X > 0){
				this.resultado.setRGB(X, Y, Color.WHITE.getRGB());	
			}
		}
		ImageGUI img = new ImageGUI(this.image);
		ImageGUI im = new ImageGUI(this.resultado);
	}
	
	public void save_image(String name){
		try {
			File outputfile = new File(name);
			ImageIO.write(this.resultado, "png", outputfile);
		} catch (IOException ex) {
			Logger.getLogger(Correccion.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

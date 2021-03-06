/*
 * Copyright (c) 2014. Matthew Campbell <matthew.campbell@mq.edu.au>, David R. Damerell <david@nixbioinf.org>.
 *
 * This file is part of GlycanBuilder Vaadin Release and its affliated projects EUROCarbDB, UniCarb-DB and UniCarbKB.
 *
 * This program is free software free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GlycanBuilder Vaadin Release is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License (LICENSE.txt) for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GlycanBuilder Vaadin Release.  If not, see <http ://www.gnu.org/licenses/>.
 */
package ac.uk.icl.dell.vaadin.canvas.shapes;

import java.awt.Color;
import java.awt.Rectangle;

import org.eurocarbdb.application.glycanbuilder.ResidueStyle;
import ac.uk.icl.dell.vaadin.canvas.basiccanvas.BasicCanvas;
import ac.uk.icl.dell.vaadin.canvas.hezamu.canvas.Canvas;

public abstract class BaseShape {
	double x,y,w,h;
	ResidueStyle style;
	Canvas canvas;
	boolean selected;
	
	public BaseShape(double x, double y, double w, double h, ResidueStyle style,Canvas canvas,boolean selected){
		this.x=x; this.y=y; this.w=w; this.h=h; this.style=style; this.canvas=canvas; this.selected=selected;
	}
	
	public void paint(){
		canvas.saveContext();
		
		if(selected){
			canvas.setLineWidth(2);
		}else{
			canvas.setLineWidth(1);
		}
		
		setShapeFillStyle();
		paintShape();
		
		if(selected){
			canvas.setGlobalAlpha(0.6);
		}
		
		setInternalShapeFillStyle();
		paintFillShape();
		
		//if(selected){
		//	paintSelected();
		//}
		canvas.restoreContext();
	}
	
	protected void paintSelected(){
		canvas.saveContext();
		canvas.setGlobalAlpha(0.4);
		canvas.fillRect(x, y, w, h);
		setStrokeColour(style.getShapeColor());
		canvas.strokeRect(x, y, w, h);
		canvas.restoreContext();
	}
	
	protected void internalShapeFull(){
		throw new UnsupportedOperationException();
	}
	
	protected void internalShapeLeft(){
		throw new UnsupportedOperationException();
	}
	
	protected void internalShapeRight(){
		throw new UnsupportedOperationException();
	}
	
	protected void internalShapeTop(){
		throw new UnsupportedOperationException();
	}
	
	protected void internalShapeBottom(){
		throw new UnsupportedOperationException();
	}
	
	protected void internalShapeTopLeft(){
		throw new UnsupportedOperationException();
	}
	
	protected void internalShapeTopRight(){
		throw new UnsupportedOperationException();
	}
	
	protected void internalShapeBottomRight(){
		throw new UnsupportedOperationException();
	}
	
	protected void internalShapeBottomLeft(){
		throw new UnsupportedOperationException();
	}
	
	protected void paintFillShape(){
		String fillstyle   = style.getFillStyle();
		
	    if(fillstyle.equals("empty")){
	        return;
	    }
	    
	    if(fillstyle.equals("full")){
	    	internalShapeFull();
	    }else if(fillstyle.equals("left")){
	    	internalShapeLeft();
	    }else if(fillstyle.equals("top")){
	    	internalShapeTop();
	    }else if(fillstyle.equals("right")){
	    	internalShapeRight();
	    }else if(fillstyle.equals("bottom")){
	    	internalShapeBottom();
	    }else if(fillstyle.equals("topleft")){
	    	internalShapeTopLeft();
	    }else if(fillstyle.equals("topright")){
	    	internalShapeTopRight();
	    }else if(fillstyle.equals("bottomright")){
	    	internalShapeBottomRight();
	    }else if(fillstyle.equals("bottomleft")){
	    	internalShapeBottomLeft();
	    }else if(fillstyle.equals("circle")){
	    	canvas.beginPath();
	    	canvas.arc(x+w/2., y+h/2., w/8.,0*Math.PI,Math.PI*2,false);
        	canvas.closePath();
        	canvas.fill();
        	setFillStyle(style.getShapeColor());
        	canvas.stroke();
	    }else if(fillstyle.equals("checkered")){
	    	canvas.setLineWidth(0.5);
	    	canvas.beginPath();
	    	canvas.moveTo(x+w/2., y);
	    	canvas.lineTo(x+w/2., y+h);
	    	canvas.moveTo(x, y+h/2.);
	    	canvas.lineTo(x+w, y+h/2.);
        	canvas.closePath();
        	setFillStyle(style.getShapeColor());
        	canvas.stroke();
	    }
	    
	    
	    
	    //else{
//	    	double cx = x+w/2.;
//	        double cy = y+h/2.;
//	        double rx = w/6.;
//	        double ry = h/6.;
//	        if( fillstyle.equals("circle") ){
//	        	canvas.beginPath();
//	        	canvas.arc(x+w/2., y+h/2., w/2.,0*Math.PI,Math.PI*2,false);
//	        	canvas.closePath();
//	        	canvas.fill();
//	        	setFillStyle(style.getShapeColor());
//	        	canvas.stroke();
//	        }
//	    }
    	
	        
	    
//	    if( fillstyle.equals("left") )
//	        return new Rectangle2D.Double(x,y,w/2.,h);
//	    if( fillstyle.equals("top") )
//	        return new Rectangle2D.Double(x,y,w,h/2.);
//	    if( fillstyle.equals("right") )
//	        return new Rectangle2D.Double(x+w/2.,y,w/2.,h);
//	    if( fillstyle.equals("bottom") )
//	        return new Rectangle2D.Double(x,y+h/2.,w,h/2.);

//	    if( fillstyle.equals("topleft") ) 
//	        return createTriangle(x,y,x+w,y,x,y+h);
//	    if( fillstyle.equals("topright") ) 
//	        return createTriangle(x,y,x+w,y,x+w,y+h);
//	    if( fillstyle.equals("bottomright") ) 
//	        return createTriangle(x+w,y,x+w,y+h,x,y+h);
//	    if( fillstyle.equals("bottomleft") ) 
//	        return createTriangle(x,y,x+w,y+h,x,y+h);
//
//	    double cx = x+w/2.;
//	    double cy = y+h/2.;
//	    double rx = w/6.;
//	    double ry = h/6.;
//	    if( fillstyle.equals("circle") )
//	        return new Ellipse2D.Double(cx-rx,cy-ry,2.*rx,2.*ry);
//	    if( fillstyle.equals("checkered") )
//	        return createCheckered(x,y,w,h);
//	    if( fillstyle.startsWith("arc") ) {
//	        Vector<String> tokens = TextUtils.tokenize(fillstyle,"_");
//	        int first_pos = Integer.parseInt(tokens.elementAt(1));
//	        int last_pos  = Integer.parseInt(tokens.elementAt(2));
//	        return createArc(x,y,w,h,first_pos,last_pos);
//	    }
//	        
//
//	    return null;
	}
	
	protected abstract void paintShape();
	
	final protected void setFillStyle(Color colour){
		canvas.setFillStyle(colour.getRed(),colour.getGreen(), colour.getBlue());
	}
	
	final protected void setStrokeColour(Color colour){
		canvas.setStrokeStyle(colour.getRed(),colour.getGreen(), colour.getBlue());
	}
	
	final protected void setShapeFillStyle(){
		setFillStyle(style.isFillNegative() ? style.getFillColor() : Color.WHITE);
	}
	
	final protected void setInternalShapeFillStyle(){
		setFillStyle(style.isFillNegative() ? Color.WHITE : style.getFillColor());
	}
	
	final static protected void createTriangle(double x1,double y1, double x2, double y2, double x3, double y3,Canvas canvas){
		canvas.beginPath();
		canvas.moveTo(x1, y1);
		canvas.lineTo(x2, y2);
		canvas.lineTo(x3, y3);
		canvas.closePath();
	}
	
	final static protected void createTriangle(double angle, double x, double y, double w, double h,Canvas canvas){
		double x1,y1,x2,y2,x3,y3;
		
	    if(angle>=-Math.PI/4. && angle<=Math.PI/4.){
	        //pointing right
	    	x1=(x+w);
	    	y1=(y+h/2);
	    	
	    	x2=x;
	    	y2=(y+h);
	    	
	    	x3=x;
	    	y3=y;
	    }else if(angle>=Math.PI/4. && angle<=3.*Math.PI/4.){
	    	//pointing down
	    	x1=x+w/2;
	    	y1=y+h;
	    	
	    	x2=x;
	    	y2=y;
	    	
	    	x3=x+w;
	    	y3=y;
	    }else if(angle>=-3.*Math.PI/4. && angle<=-Math.PI/4. ){
	    	//pointing up
	        x1=x+w/2;
	        y1=y;
	        
	        x2=x+w;
	        y2=y+h;
	        
	        x3=x;
	        y3=y+h;
	    }else{
	        //pointing left
	    	x1=x;
	    	y1=y+h/2;
	    	
	    	x2=x+w;
	    	y2=y+h;
	    	
	    	x3=x+w;
	    	y3=y;
	    }
	    
	    createTriangle(x1, y1, x2, y2, x3, y3, canvas);
	}
	
	final static public void createTriangle(double x,double y,double w,double h,Canvas canvas){
		createTriangle(-3.*Math.PI/4, x, y, w, h, canvas);
	}
	
	final static public void createDiamond(double x,double y,double w,double h,Canvas canvas){
		createDiamond(x, y, w, h, canvas, true);
	}
	
	final static public void createDiamond(double x,double y,double w,double h,Canvas canvas,boolean setPath){
		if((w%2)==1){
	        w++;
		}
	    if((h%2)==1){
	        h++;
	    }
	    
	    if(setPath){
	    	canvas.beginPath();
	    }
	    
	    canvas.moveTo(x+w/2, y);
	    canvas.lineTo(x+w, y+h/2);
	    canvas.lineTo(x+w/2, y+h);
	    canvas.lineTo(x, y+h/2);
	    
	    if(setPath){
	    	canvas.closePath();
	    }
	}
	
	final static public void createHexagon(double x,double y,double w,double h,Canvas canvas){
		double rx = w/2.;
	    double ry = h/2.;
	    double cx = x+w/2.;
	    double cy = y+h/2.;
	        
	    double step = Math.PI/3.; 
	    
	    canvas.beginPath();
	    canvas.moveTo(cx+rx*Math.cos(0*step),cy+ry*Math.sin(0*step));
	    
	    for(int i=1; i<=6; i++ ) {
	        canvas.lineTo(cx+rx*Math.cos(i*step),cy+ry*Math.sin(i*step));
	    }
	    canvas.closePath();
	}
	
	final static void createHeptagon(double x,double y,double w,double h,Canvas canvas){
		//createTriangle(x, y, w, h, canvas);
		
		double rx = w/2.;
		double ry = h/2.;
		double cx = x+w/2.;
		double cy = y+h/2.;
		        
		double step = Math.PI/3.5;        
		canvas.beginPath();
		
		canvas.moveTo(cx+rx*Math.cos(0*step-Math.PI/2.),cy+ry*Math.sin(0*step-Math.PI/2.));
		
		for(int i=1; i<=7; i++ ) {
			canvas.lineTo(cx+rx*Math.cos(i*step-Math.PI/2.),cy+ry*Math.sin(i*step-Math.PI/2.));
		}
		canvas.closePath();
	}
	
	final static void createStar(double x,double y,double w,double h,Canvas canvas, int points){
		double rx = w/2.;
	    double ry = h/2.;
	    double cx = x+w/2.;
	    double cy = y+h/2.;
	        
	    double step = Math.PI/(double)points;         
	    double nstep = Math.PI/2.-2.*step;

	    double mrx = rx/(Math.cos(step)+Math.sin(step)/Math.tan(nstep));
	    double mry = ry/(Math.cos(step)+Math.sin(step)/Math.tan(nstep));

	    canvas.beginPath();
	    canvas.moveTo(cx+rx*Math.cos(0*step-Math.PI/2.), cy+ry*Math.sin(0*step-Math.PI/2.));
	    
	    for(int i=1; i<=2*points; i++ ) {
	        if((i%2)==0){
	        	canvas.lineTo(cx+rx*Math.cos(i*step-Math.PI/2.), cy+ry*Math.sin(i*step-Math.PI/2.));
	        }else{
	        	canvas.lineTo(cx+mrx*Math.cos(i*step-Math.PI/2.), cy+mry*Math.sin(i*step-Math.PI/2.));
	        }
	    }
	    canvas.closePath();
	}
	
	final static public void createPentagon(double x,double y,double w,double h,Canvas canvas){
		double rx = w/2.;
		double ry = h/2.;
		double cx = x+w/2.;
		double cy = y+h/2.;
		        
		double step = Math.PI/2.5;
		
		canvas.beginPath();
		canvas.moveTo(cx+rx*Math.cos(0*step-Math.PI/2.), cy+ry*Math.sin(0*step-Math.PI/2.));
		for(int i=1; i<=5; i++ ) {
	       canvas.lineTo(cx+rx*Math.cos(i*step-Math.PI/2.),cy+ry*Math.sin(i*step-Math.PI/2.));
	    }      
		canvas.closePath();
	}
	
	final static public void createHatDiamond(double x,double y,double w,double h,Canvas canvas){
		canvas.beginPath();
		createDiamond(x, y, w, h, canvas,false);

		canvas.moveTo(x-2, y+h/2-2);
		canvas.lineTo(x+w/2-2, y-2);
	    
	    canvas.closePath();
	}
	
	final static public void createRHatDiamond(double x,double y,double w,double h,Canvas canvas){
		canvas.beginPath();
		
		createDiamond(x,y,w,h,canvas,false);
		
		canvas.moveTo(x+w+2, y+h/2-2);
		canvas.lineTo(x+w/2+2, y-2);
	    
	    canvas.closePath();
	}
	
	final static public void createRhombus(double x,double y,double w,double h,Canvas canvas){
		canvas.beginPath();
		
		canvas.moveTo(x+0.50*w, y);
		canvas.lineTo(x+0.85*w, y+0.50*h);
		canvas.lineTo(x+0.50*w, y+h);
		canvas.lineTo(x+0.15*w, y+0.50*h);
		
		canvas.closePath();
	}
	
	//AS
	final static public int midx(Rectangle r) {
		return (r.x+(r.width/2));
	}

	//AS
	final static public int midy(Rectangle r) {
		return (r.y+(r.height/2));
	}
	
	public static void paintDashedLine(BasicCanvas canvas,int x1,int y1,int x2,int y2){	
		//This new implementation is based on the answers to the stackoverflow question below.
		//http://stackoverflow.com/questions/4576724/dotted-stroke-in-canvas

	    int dx=(x2-x1);
	    int dy=(y2-y1);
	    
	    //What's the orientation of the triangle, i.e. y steps x or x steps y
	    boolean xSlope=(Math.abs(dx) > Math.abs(dy));
	  
	    //get the gradient y2-y1/x2-x1 or x2-x1/y2-y1
	    double gradient;
	    
	    boolean xSlopeDown=false;
	    boolean ySlopeDown=false;
	    if(xSlope){
	    	gradient=dy / (double) dx;
	    	
	    	xSlopeDown=dx<0;
	    }else{
	    	gradient=dx / (double) dy;
	    	
	    	ySlopeDown=dy<0;
	    }
	    
	    //convert to double for positioning
	    double x=x1;
	    double y=y1;
	    
	    //Initial position
	    canvas.moveTo(x, y);
	    
	    //The length of the dashed line "c" is equal to the square root of a^2+b^2
	    double dashedLineLength=Math.sqrt(dx*dx+dy*dy);
	    
	    //how much of the line is left to go
	    double distRemaining=dashedLineLength;
	    
	    boolean penUpOperation=false;
	    
	    int dashLineLength=2;
	    
	    int numDashes=(int) (dashedLineLength / dashLineLength);
	    
	    if(dashedLineLength % dashLineLength >0){
	    	numDashes++;
	    }
	    
	    for(int i=0;i<numDashes;i++){
	        double dashLength=Math.min(distRemaining, dashLineLength); //set dashLine length to either the default or what ever is left
	        
	        //calculate where x2 and y2 are based on c (dash length) and the gradient we need on the dashed line
	        double step=Math.sqrt(dashLength*dashLength / (1+gradient*gradient));
	        if(xSlope){
	            if(xSlopeDown){
	            	step=-step;
	            }
	            
	            x+=step;
	            y+=gradient*step;
	        }else{
	            if(ySlopeDown){
	            	step=-step;
	            }
	            
	            x+=gradient*step;
	            y+=step;
	        }
	        
	        if(penUpOperation){
	        	canvas.moveTo(x, y);
	        }else{
	        	canvas.lineTo(x, y);
	        }
	        
	       distRemaining-=dashLength;
	       penUpOperation=!penUpOperation;
	    }
	}
	
	static public void createEnd(BasicCanvas canvas,double angle, double x, double y, double w, double h) {
    	double rx = w/2.;
    	double ry = h/2.;
    	double cx = x+w/2.;
    	double cy = y+h/2.;

    	// start point
    	double x1 = cx+rx*Math.cos(angle-Math.PI/2.);
    	double y1 = cy+ry*Math.sin(angle-Math.PI/2.);

    	// end point
    	double x2 = cx+rx*Math.cos(angle+Math.PI/2.);
    	double y2 = cy+ry*Math.sin(angle+Math.PI/2.);

    	// ctrl point 1
    	double cx1 = cx+0.5*rx*Math.cos(angle-Math.PI/2.);
    	double cy1 = cy+0.5*ry*Math.sin(angle-Math.PI/2.);
    	double tx1 = cx1+0.5*rx*Math.cos(angle-Math.PI);
    	double ty1 = cy1+0.5*ry*Math.sin(angle-Math.PI);

    	// ctrl point 2
    	double cx2 = cx+0.5*rx*Math.cos(angle+Math.PI/2.);
    	double cy2 = cy+0.5*ry*Math.sin(angle+Math.PI/2.);
    	double tx2 = cx2+0.5*rx*Math.cos(angle);
    	double ty2 = cy2+0.5*ry*Math.sin(angle);    

    	canvas.beginPath();
		canvas.moveTo(x1, y1);
		canvas.bezierCurveTo(tx1, ty1, tx2, ty2, x2, y2);
		canvas.stroke();
		canvas.closePath();
    }
	
	public static void createBracket(BasicCanvas canvas,double angle, double x, double y, double w, double h) {        
    	double rx = w/2.;
    	double ry = h/2.;
    	double cx = x+w/2.;
    	double cy = y+h/2.;

    	// first start point
    	double x11 = cx+rx*Math.cos(angle-Math.PI/2.)+0.2*rx*Math.cos(angle);
    	double y11 = cy+ry*Math.sin(angle-Math.PI/2.)+0.2*ry*Math.sin(angle);

    	// first ctrl point 1
    	double tx11 = cx+0.9*rx*Math.cos(angle-Math.PI/2.)+0.2*rx*Math.cos(angle-Math.PI);
    	double ty11 = cy+0.9*ry*Math.sin(angle-Math.PI/2.)+0.2*ry*Math.sin(angle-Math.PI);

    	// first ctrl point 2;
    	double tx21 = cx+0.1*rx*Math.cos(angle-Math.PI/2.)+0.2*rx*Math.cos(angle);
    	double ty21 = cy+0.1*ry*Math.sin(angle-Math.PI/2.)+0.2*ry*Math.sin(angle);

    	// first end point
    	double x21 = cx+0.2*rx*Math.cos(angle-Math.PI);
    	double y21 = cy+0.2*ry*Math.sin(angle-Math.PI);

    	canvas.beginPath();
		canvas.moveTo(x11, y11);
		canvas.bezierCurveTo(tx11, ty11, tx21, ty21, x21, y21);
		canvas.stroke();
		canvas.closePath();

    	// second start point
    	double x12 = cx+rx*Math.cos(angle+Math.PI/2.)+0.2*rx*Math.cos(angle);
    	double y12 = cy+ry*Math.sin(angle+Math.PI/2.)+0.2*ry*Math.sin(angle);

    	// second ctrl point 1
    	double tx12 = cx+0.9*rx*Math.cos(angle+Math.PI/2.)+0.2*rx*Math.cos(angle-Math.PI);
    	double ty12 = cy+0.9*ry*Math.sin(angle+Math.PI/2.)+0.2*ry*Math.sin(angle-Math.PI);

    	// second ctrl point 2;
    	double tx22 = cx+0.1*rx*Math.cos(angle+Math.PI/2.)+0.2*rx*Math.cos(angle);
    	double ty22 = cy+0.1*ry*Math.sin(angle+Math.PI/2.)+0.2*ry*Math.sin(angle);

    	// second end point
    	double x22 = cx+0.2*rx*Math.cos(angle-Math.PI);
    	double y22 = cy+0.2*ry*Math.sin(angle-Math.PI);

    	canvas.beginPath();
		canvas.moveTo(x12, y12);
		canvas.bezierCurveTo(tx12, ty12, tx22, ty22, x22, y22);
		canvas.stroke();
		canvas.closePath();
    }
	
	static public void createSquareBracket(BasicCanvas canvas,double angle, double x, double y, double w, double h) {    
    	double r = Math.min(w,h);
    	double cx = x+w/2.;
    	double cy = y+h/2.;

    	// first point
    	double x1 = cx+r*Math.cos(angle-Math.PI/2.)+r/4.*Math.cos(angle+Math.PI);
    	double y1 = cy+r*Math.sin(angle-Math.PI/2.)+r/4.*Math.sin(angle+Math.PI);
    	
    	canvas.beginPath();
    	canvas.moveTo(x1, y1);

    	// second point
    	double x2 = cx+r*Math.cos(angle-Math.PI/2.);
    	double y2 = cy+r*Math.sin(angle-Math.PI/2.);
    	
    	canvas.lineTo(x2, y2);

    	// third point
    	double x3 = cx+r*Math.cos(angle+Math.PI/2.);
    	double y3 = cy+r*Math.sin(angle+Math.PI/2.);
    	
    	canvas.lineTo(x3, y3);

    	// fourth point
    	double x4 = cx+r*Math.cos(angle+Math.PI/2.)+r/4.*Math.cos(angle+Math.PI);
    	double y4 = cy+r*Math.sin(angle+Math.PI/2.)+r/4.*Math.sin(angle+Math.PI);
    	canvas.lineTo(x4, y4);

    	// close shape
    	
    	canvas.lineTo(x3, y3);
    	canvas.lineTo(x2, y2);
    }
}

package modules.notePad;

import javafx.scene.paint.Color;

public class NotePadInstance {
	private String text;
	private boolean isItalics;
	private boolean isBold;
	private double rValue;
	private double gValue;
	private double bValue;

	public NotePadInstance() {
		this("", false, false, 1, 1, 1);
	}

	
	
	public NotePadInstance(String text, boolean isItalics, boolean isBold, double rValue, double gValue,
			double bValue) {
		this.text = text;
		this.isItalics = isItalics;
		this.isBold = isBold;
		this.rValue = rValue;
		this.gValue = gValue;
		this.bValue = bValue;
	}



	public double getrValue() {
		return rValue;
	}

	public void setrValue(double rValue) {
		this.rValue = rValue;
	}

	public double getgValue() {
		return gValue;
	}

	public void setgValue(double gValue) {
		this.gValue = gValue;
	}

	public double getbValue() {
		return bValue;
	}

	public void setbValue(double bValue) {
		this.bValue = bValue;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isItalics() {
		return isItalics;
	}

	public void setItalics(boolean isItalics) {
		this.isItalics = isItalics;
	}

	public boolean isBold() {
		return isBold;
	}

	public void setBold(boolean isBold) {
		this.isBold = isBold;
	}

}

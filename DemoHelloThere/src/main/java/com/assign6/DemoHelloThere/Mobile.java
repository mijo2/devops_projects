package com.assign6.DemoHelloThere;

public class Mobile{
	private int price;
	private Camera camera;
	private Screen screen;
	private Speaker speaker;
	
	public Mobile() {
		System.out.println("Mobile is created yay...");
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
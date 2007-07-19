package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;

/**
 * A rudimentry test of loading SVG from inkscape
 *
 * @author kevin
 */
public class InkscapeTest extends BasicGame {
	/** The renderer doing the work */
	private SimpleDiagramRenderer[] renderer = new SimpleDiagramRenderer[4];
	/** The zoom */
	private float zoom = 1;
	/** The x location */
	private float x;
	/** The y location */
	private float y;
	
	/**
	 * Create a new test for inkscape loading
	 */
	public InkscapeTest() {
		super("Inkscape Test");
	}

	/**
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		container.getGraphics().setBackground(Color.white);
		
		renderer[0] = new SimpleDiagramRenderer(InkscapeLoader.load("testdata/svg/orc.svg"));
		renderer[1] = new SimpleDiagramRenderer(InkscapeLoader.load("testdata/svg/head2.svg"));
		renderer[2] = new SimpleDiagramRenderer(InkscapeLoader.load("testdata/svg/head3.svg"));
		renderer[3] = new SimpleDiagramRenderer(InkscapeLoader.load("testdata/svg/santa.svg"));
		
		container.getGraphics().setBackground(new Color(0.5f,0.7f,1.0f));
	}

	/**
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer, int)
	 */
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyDown(Input.KEY_Q)) {
			zoom += (delta * 0.01f);
			if (zoom > 10) {
				zoom = 10;
			}
		}
		if (container.getInput().isKeyDown(Input.KEY_A)) {
			zoom -= (delta * 0.01f);
			if (zoom < 0.1f) {
				zoom = 0.1f;
			}
		}
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			x += (delta * 0.1f);
		}
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			x -= (delta * 0.1f);
		}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			y += (delta * 0.1f);
		}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			y -= (delta * 0.1f);
		}
	}

	/**
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.scale(zoom,zoom);
		g.translate(x, y);
		g.scale(0.3f,0.3f);
		renderer[0].render(g);
		g.scale(1/0.3f,1/0.3f);
		g.translate(300, 0);
		renderer[1].render(g);
		g.translate(100, 300);
		g.scale(0.7f,0.7f);
		renderer[2].render(g);
		g.scale(1/0.7f,1/0.7f);
		g.translate(-300, 0);
		renderer[3].render(g);
		g.resetTransform();
//		g.scale(0.3f,0.3f);
//		g.translate(50,50);
//		renderer.render(g);
	}
	
	/**
	 * Entry point to our simple test
	 * 
	 * @param argv The arguments passed in
	 */
	public static void main(String argv[]) {
		try {
			AppGameContainer container = new AppGameContainer(new InkscapeTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

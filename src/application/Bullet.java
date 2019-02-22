package application;

import java.time.Duration;
import java.time.LocalDateTime;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet 
{
	public int id = 0;
    public double center_x = 0.0;
    public double center_y = 0.0;
    public double radius = 5;
    public double speed = 0.5; //pixels per game tick
    public double speed_x = 0.8;
    public double speed_y = 0.6;
    public int damage = 100;
    public int life = 3000;
    public Color bullet_color = Color.RED;
    public boolean alive = true;
	
	public Bullet(double center_x1, double center_y1, double speed_x1, double speed_y1, double radius1)
	{
	    this.center_x = center_x1;
	    this.center_y = center_y1;
	    this.speed_x = speed_x1;
	    this.speed_y = speed_y1;
	    this.radius = radius1;
	   
	}
	public Bullet()
	{
	    
	}
	public void update()
	{
		center_x += speed*speed_x;
		center_y += speed*speed_y;
		life--;
		if(life < 0)
		{
			alive = false;
		}
	}
	public void draw(GraphicsContext gc)
	{
		if(alive)
		{
		gc.setFill(bullet_color);
        gc.fillOval(center_x-radius, center_y-radius, 2*radius, 2*radius);
		}
	}

}

package application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player 
{
	public int id = 0;
	public double center_x = 0.0;
    public double center_y = 0.0;
    public double click_x = 0.0;
    public double click_y = 0.0;
    public double radius = 20;
    public double speed = 0.1; //pixels per game tick
    //public double speed_x = 0.8;
    //public double speed_y = 0.6;
    public int hp = 1000;
    public int rate_of_fire = 150; //how many game ticks between fire
    public int ticks_from_fire = rate_of_fire;
    public boolean shoot_to_update = false;
    public boolean click = false;
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public String direction = "NO";
    public boolean alive = true;
    public int width = 0;
    public int height = 0;
    public Color player_color = Color.BLUE;
    public Bullet b = new Bullet();
    
    public Player(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    public void update_direction()
    {
        String dircode = "";
        
        if(up)dircode = dircode + "1";
        if(!up)dircode = dircode + "0";
        
        if(down)dircode = dircode + "1";
        if(!down)dircode = dircode + "0";
        
        if(left)dircode = dircode + "1";
        if(!left)dircode = dircode + "0";
        
        if(right)dircode = dircode + "1";
        if(!right)dircode = dircode + "0";
        
        switch(dircode)
        {
        case "0000":
        direction = "NO";
        break;
        
        case "0001":
        direction = "RIGHT";
        break;
        
        case "0010":
        direction = "LEFT";
        break;
        
        case "0011":
        direction = "NO";
        break;
        
        case "0100":
        direction = "DOWN";
        break;
        
        case "0101":
        direction = "DOWNRIGHT";
        break;
        
        case "0110":
        direction = "DOWNLEFT";
        break;
        
        case "0111":
        direction = "DOWN";
        break;
        
        case "1000":
        direction = "UP";
        break;
        
        case "1001":
        direction = "UPRIGHT";
        break;
        
        case "1010":
        direction = "UPLEFT";
        break;
        
        case "1011":
        direction = "UP";
        break;
        
        case "1100":
        direction = "NO";
        break;
        
        case "1101":
        direction = "RIGHT";
        break;
        
        case "1110":
        direction = "LEFT";
        break;
        
        case "1111":
        direction = "NO";
        break;
        
        default:
        direction = "NO";
        }
       
    }
    
    public void update_coordinates()
    {
    	switch(direction)
        {
        case "UP":
        	center_y -= speed;
        	break;
        
        case "DOWN":
            center_y += speed;
            break;
            
        case "LEFT":
            center_x -= speed;
            break;
            
        case "RIGHT":
            center_x += speed;
            break;
            
        case "NO":
            
            break;
        case "UPLEFT":
            center_x -= speed;
            center_y -= speed;
            break;
        case "UPRIGHT":
            center_x += speed;
            center_y -= speed;
            break;
            
        case "DOWNLEFT":
            center_x -= speed;
            center_y += speed;
            break;
            
        case "DOWNRIGHT":
            center_x += speed;
            center_y += speed;
            break;
        
        default:
        
        }
    	if(center_x > this.width)center_x = (double)this.width;
    	if(center_x < 0)center_x = 0.0;
    	if(center_y > this.height)center_y = (double)this.height;
    	if(center_y < 0)center_y = 0.0;
    }
    public void update_hp(CopyOnWriteArrayList<Bullet> bullets)
    {
    	for(Bullet b: bullets)
    	{
    		if(!(b.id==this.id))
    		{
    		if(Math.sqrt(b.center_x*b.center_x+b.center_y*b.center_y)<(b.radius+this.radius))
    		{
    			this.hp -= b.damage;
    			if(this.hp < 0)
    			{
    				this.alive = false;
    			}
    			b.alive = false;
    		}
    		}
    	}
    }
    public Bullet shoot()
    {
    	Bullet b = new Bullet();
    	b.center_x = this.center_x;
    	b.center_y = this.center_y;
    	double gipot = Math.sqrt((click_x-b.center_x)*(click_x-b.center_x)+(click_y-b.center_y)*(click_y-b.center_y));
    	double dx = (click_x-b.center_x)/gipot;
    	double dy = (click_y-b.center_y)/gipot;
    	b.speed_x = dx;
    	b.speed_y = dy;
    	
    	return b;
    }
    public void update_shoot()
    {
    	
    	ticks_from_fire++;
    	
    	if(ticks_from_fire > rate_of_fire)
    	{
    		b.center_x = this.center_x;
        	b.center_y = this.center_y;
        	double gipot = Math.sqrt((click_x-b.center_x)*(click_x-b.center_x)+(click_y-b.center_y)*(click_y-b.center_y));
        	double dx = (click_x-b.center_x)/gipot;
        	double dy = (click_y-b.center_y)/gipot;
        	b.speed_x = dx;
        	b.speed_y = dy;
    		shoot_to_update = true;
    		
    	}
    	if(click)
		{
		
		ticks_from_fire = 0;
		click = false;
		}
    	
    	
    	
    }
    public void update_f()
    {
    	ticks_from_fire++;
    }
    public void update(CopyOnWriteArrayList<Bullet> bullets)
    {
    	update_direction();
    	update_coordinates();
    	update_hp(bullets);
    	ticks_from_fire++;
    	//update_shoot();
    }
    public void draw(GraphicsContext gc)
	{
		gc.setFill(player_color);
        gc.fillOval(center_x-radius, center_y-radius, 2*radius, 2*radius);
        //gc.strokeText(b.speed_x + " " + b.speed_y, 100, 100);
	}

}

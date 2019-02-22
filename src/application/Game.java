package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game
{
	int counter = 0;
	public CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<Player> players_to_remove = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<Bullet> bullets_to_remove = new CopyOnWriteArrayList<>();
	int time_between_tick_in_millisecons = 1;
	int start_delay_in_milliseconds = 3000;
	int width = 1000;
    int height = 1000;
    Color player_color = Color.GREEN;
    Color bullet_color = Color.RED;
    boolean game_continue = true;
    Timer timer;
    Canvas can1;
    public Game(Canvas can)
    {
    	this.can1 = can;
    	timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() 
        {
        @Override
        public void run() 
        {
        	counter++;
            if(game_continue)
            {
            	for(Bullet b: bullets)
            	{
            		if(!(b==null))
            		{
            		b.update();
            		if(!b.alive)
            		{
            			bullets_to_remove.add(b);
            		}
            		}
            	}
            	for(Player p: players)
            	{
            		if(!(p==null))
            		{
            		p.update(bullets);
            		if(!p.alive)
            		{
            			players_to_remove.add(p);
            		}
            		
            		}
            	}
            	bullets.removeAll(bullets_to_remove);
            	bullets_to_remove.clear();
            	players.removeAll(players_to_remove);
            	players_to_remove.clear();
            	if(counter % 16 == 0)
            	{
            		
            	
            	GraphicsContext gc = can1.getGraphicsContext2D();
            	gc.clearRect(0, 0, width, height);
            	for(Bullet b: bullets)
            	{
            		if(!(b==null))
            		{
            		b.draw(gc);
            		}
            	}
            	for(Player p: players)
            	{
            		if(!(p==null))
            		{
            		p.draw(gc);
            		}
            	}
            	}
            }
        }},start_delay_in_milliseconds, time_between_tick_in_millisecons);
    }
    
}

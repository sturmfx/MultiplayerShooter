package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Canvas can = new Canvas(1000,1000);
			Game g = new Game(can);
			
			g.players.add(new Player(1000,1000));
			BorderPane root = new BorderPane();
			root.getChildren().add(can);
			Scene scene = new Scene(root,1000,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.setOnKeyPressed
	        (
	            new EventHandler<KeyEvent>()
	            {
	                @Override
	                public void handle(KeyEvent e)
	                {
	                   if(KeyCode.A == e.getCode())
	                   {
	                       g.players.get(0).left = true;
	                       e.consume();
	                   }
	                   if(KeyCode.D == e.getCode())
	                   {
	                	   g.players.get(0).right = true;
	                       e.consume();
	                   }
	                   if(KeyCode.W == e.getCode())
	                   {
	                       g.players.get(0).up = true;
	                       e.consume();
	                   }
	                   if(KeyCode.S == e.getCode())
	                   {
	                	   g.players.get(0).down = true;
	                       e.consume();
	                   }
	                   e.consume();
	                }
	            }
	        );
	        scene.setOnKeyReleased
	        (
	            new EventHandler<KeyEvent>()
	            {
	                @Override
	                public void handle(KeyEvent e)
	                {
	                   if(KeyCode.A == e.getCode())
	                   {
	                	   g.players.get(0).left = false;
	                       e.consume();
	                   }
	                   if(KeyCode.D == e.getCode())
	                   {
	                	   g.players.get(0).right = false;
	                       e.consume();
	                   }
	                   if(KeyCode.W == e.getCode())
	                   {
	                	   g.players.get(0).up = false;
	                       e.consume();
	                   }
	                   if(KeyCode.S == e.getCode())
	                   {
	                	   g.players.get(0).down = false;
	                       e.consume();
	                   }
	                   e.consume();
	                }
	            }
	        );
	        scene.setOnMousePressed(new EventHandler<MouseEvent>() 
	        {
	            public void handle(MouseEvent me) 
	            {
	            	double click_x = me.getX();
	            	double click_y = me.getY();
	            	if(g.players.get(0).ticks_from_fire > g.players.get(0).rate_of_fire)
	            	{
	            		g.players.get(0).ticks_from_fire = 0;
	            		Bullet b = new Bullet();
	            		b.center_x = g.players.get(0).center_x;
	                	b.center_y = g.players.get(0).center_y;
	                	double gipot = Math.sqrt((click_x-b.center_x)*(click_x-b.center_x)+(click_y-b.center_y)*(click_y-b.center_y));
	                	double dx = (click_x-b.center_x)/gipot;
	                	double dy = (click_y-b.center_y)/gipot;
	                	b.speed_x = dx;
	                	b.speed_y = dy;
	            		g.bullets.add(b);
	            	}
	                
	            }
	        });
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

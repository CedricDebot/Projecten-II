
import gui.Attitude;
import gui.Beginscherm;
import gui.Dashboard;
import gui.Rijtechniek;
import gui.VerkeersTechniek;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class StartUp extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Beginscherm beginscherm = new Beginscherm();
        Attitude attitude = new Attitude();
        //Rijtechniek rijtechniek = new Rijtechniek();
        Scene scene = new Scene(beginscherm, 1024, 768);
        beginscherm.setScene(scene);

       // Beginscherm beginscherm = new Beginscherm();
        //beginscherm.setScene(scene);

        stage.setScene(scene);
        stage.setTitle("App rijschool");
        scene.getStylesheets().add("css/Beginscherm.css");
        stage.show();
        
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}

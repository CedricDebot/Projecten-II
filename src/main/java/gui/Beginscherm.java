package gui;

import domein.Leerling;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class Beginscherm extends HBox {

//    private ObservableList<String> names = FXCollections.observableArrayList("Cédric", "Robin", "Dries", "Milton");
    
    private ObservableList<Leerling> leerlingen = FXCollections.observableArrayList();
    private ListView lijstLeerlingen = new ListView();

    private Scene scene;

    public Beginscherm() {
        //Labels
        VBox labels = new VBox();
        labels.setId("labels");
        HBox naamLeerling = new HBox();
        naamLeerling.setId("naamLeerling");
        Label naam = new Label("Naam: ");
        TextField naamTF = new TextField();
        naamTF.setId("textField");
        naamLeerling.getChildren().addAll(naam, naamTF);
        HBox inschrijvingLeerling = new HBox();
        inschrijvingLeerling.setId("inschrijvingLeerling");
        Label inschrijvingsnummer = new Label("Inschrijvingsnummer:");
        TextField inschrijvingsnummerTF = new TextField();
        inschrijvingsnummerTF.setId("textField");
        inschrijvingLeerling.getChildren().addAll(inschrijvingsnummer, inschrijvingsnummerTF);
        labels.getChildren().addAll(naamLeerling, inschrijvingLeerling);

        //ButtonLeft
        HBox buttons = new HBox();
        buttons.setId("buttons");
        Button nieuw = new Button("Nieuw");
        Button start = new Button("Start");

        buttons.getChildren().addAll(nieuw, start);

        //Feedback
        Label feedbackInlog = new Label("");
        feedbackInlog.setId("feedbackLogin");
        //ZoekScherm
        VBox zoekscherm = new VBox();
        zoekscherm.setId("zoekscherm");
        zoekscherm.getChildren().addAll(labels, buttons, feedbackInlog);

        
        //ListViewShenanigans
        VulLeerlingen();

        lijstLeerlingen.setId("lijstLeerlingen");
        lijstLeerlingen.setItems(leerlingen);
//        lijstLeerlingen.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
//
//            @Override
//            public ObservableValue<Boolean> call(String item) {
//                return null;
//            }
//        }));
        
            lijstLeerlingen.setCellFactory(new Callback<ListView<Leerling>, ListCell<Leerling>>(){
 
            @Override
            public ListCell<Leerling> call(ListView<Leerling> p) {
                 
                ListCell<Leerling> cell = new ListCell<Leerling>(){
 
                    @Override
                    protected void updateItem(Leerling l, boolean bln) {
                        super.updateItem(l, bln);
                        if (l != null) {
                            setText(l.getVoorNaam());
                        }
                    }
                }; 
                return cell;
            }
        });

        //ButtonsRight
        HBox buttonsRight = new HBox();
        buttonsRight.setId("buttonsRight");
        Button verwijder = new Button("Verwijder");
        verwijder.setId("btnVerwijder");
        Button sync = new Button("Synchroniseer");
        sync.setId("btnSync");
        buttonsRight.getChildren().addAll(verwijder, sync);

        //ButtonsAllesVerwijderen
        Button allesVerwijderen = new Button("Alles verwijderen");
        allesVerwijderen.setId("btnAllesVerwijderen");

        //NieuweLeerling
        VBox nieuweLeerling = new VBox();

        nieuweLeerling.setId("schermNieuweLeerling");

        Label titel = new Label("Nieuwe Leerling");
        titel.setId("listViewTitle");
        Label nr = new Label("InschrijvingsNr:");
        TextField inputNr = new TextField();
        inputNr.setId("textField");
        Label famNaam = new Label("Famillienaam:");
        TextField inputFamillienaam = new TextField();
        inputFamillienaam.setId("textField");
        Label famillienaamFout = new Label();
        famillienaamFout.setId("foutboodschap");
        famillienaamFout.setVisible(false);
        Label Voornaam = new Label("Voornaam:");
        TextField inputVoornaam = new TextField();
        inputVoornaam.setId("textField");
        Label voornaamFout = new Label();
        voornaamFout.setId("foutboodschap");
        Label Email = new Label("Email:");
        TextField inputEmail = new TextField();
        inputEmail.setId("textField");
        Label emailFout = new Label();
        emailFout.setId("foutboodschap");
        Button foto = new Button("Foto");
        Label feedback = new Label("");

        HBox fotoEnLabel = new HBox();
        fotoEnLabel.setId("fotoEnLabel");
        fotoEnLabel.getChildren().addAll(foto, feedback);

        nieuweLeerling.getChildren().addAll(titel, nr, inputNr, famNaam,
                inputFamillienaam, famillienaamFout, Voornaam, inputVoornaam, voornaamFout, Email, inputEmail, emailFout, fotoEnLabel);

        //buttonsNieuweLeerling
        Button ok = new Button("Ok");
        ok.setId("btnVerwijder");
        Button annuleer = new Button("Annuleer");
        annuleer.setId("btnSync");
        HBox knoppenNieuw = new HBox();
        knoppenNieuw.setId("buttonsRight");

        knoppenNieuw.getChildren().addAll(ok, annuleer);

        //schermNieuwLeerling
        VBox rightNieuw = new VBox();
        rightNieuw.setId("right");
        rightNieuw.getChildren().addAll(titel, nieuweLeerling, knoppenNieuw);

        //LinkerScherm
        VBox left = new VBox();
        left.setId("left");

        Image auto = new Image("images/logo_mobix_app.png");
        ImageView autoImg = new ImageView();
        autoImg.setImage(auto);
        autoImg.setId("autoImg");

//        //lay-out (nog proberen in css te zetten)
        autoImg.setFitWidth(230);
        autoImg.setFitHeight(230);

        left.getChildren().addAll(zoekscherm, autoImg);

        //RechterScherm
        VBox right = new VBox();
        right.setId("right");

        Label listViewTitle = new Label("Leerlingen");
        listViewTitle.setId("listViewTitle");

        right.getChildren().addAll(listViewTitle, lijstLeerlingen, buttonsRight, allesVerwijderen);

        getChildren().addAll(left, right);

        nieuw.setOnAction(e -> {
            if (getChildren().contains(right)) {
                getChildren().remove(right);
                getChildren().add(rightNieuw);
            }
        });

        naamTF.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                ZoekFunctie((String) oldValue, (String) newValue);
            }
        });

        ok.setOnAction(e -> {
//            if(inputNr.getText().isEmpty()) {
//                feedback.setText("Je moet het inschrijvingsNr invullen.");
//            } else 
            if (inputFamillienaam.getText().isEmpty()) {
                famillienaamFout.setVisible(true);
                famillienaamFout.setText("Famillienaam is niet ingevuld!");
                if (!inputVoornaam.getText().isEmpty() && !inputEmail.getText().isEmpty() && !validateEmail(inputEmail.getText())) {
                    return;
                }
            }
            if (inputVoornaam.getText().isEmpty()) {
                voornaamFout.setText("Voornaam is niet ingevuld!");
                if (!inputFamillienaam.getText().isEmpty() && !inputEmail.getText().isEmpty() && !validateEmail(inputEmail.getText())) {
                    return;
                }
            }
            if (inputEmail.getText().isEmpty()) {
                emailFout.setText("E-mailadres is niet ingevuld!");
                if (!inputFamillienaam.getText().isEmpty() && !inputVoornaam.getText().isEmpty() && !validateEmail(inputEmail.getText())) {
                    return;
                }
            }
            if (!validateEmail(inputEmail.getText())) {
                emailFout.setText("Het e-mailadres is niet correct.");              
                    return;

            }
            Leerling leerling = new Leerling(inputNr.getText(), inputFamillienaam.getText(), inputVoornaam.getText(), inputEmail.getText(), null);
//            names.add(leerling.getVoorNaam());

            if (getChildren().contains(rightNieuw)) {
                getChildren().remove(rightNieuw);
                getChildren().add(right);
            }

        });

        start.setOnAction(e -> {
            if(lijstLeerlingen.getSelectionModel().getSelectedItem() == null){
                feedbackInlog.setText("Geen leerling geselecteerd.");
           }else{            
                Leerling leerling = (Leerling) lijstLeerlingen.getSelectionModel().getSelectedItem();
                //haal leerling op uit db/backend
                
            
            Dashboard dashboard = new Dashboard(leerling);
            dashboard.setScene(scene);
            scene.setRoot(dashboard);
            }
        });

        //textfields nog leeg maken 
        annuleer.setOnAction(e -> {
            getChildren().remove(rightNieuw);
            getChildren().add(right);
        });
    }

    public void ZoekFunctie(String oldVal, String newVal) {
        //if (oldVal != null && (newVal.length() <= oldVal.length())) {
        lijstLeerlingen.setItems(leerlingen);
        //}

        newVal = newVal.toLowerCase();

        ObservableList<Leerling> searchNames = FXCollections.observableArrayList();
        for (Leerling leerling : leerlingen) {
            String entryText = leerling.getVoorNaam();
            if (entryText.toLowerCase().contains(newVal)) {
                searchNames.add(new Leerling(null, null, leerling.getVoorNaam(), null, new Image("images/testLeerlingen/1.png")));
            }
        }
        lijstLeerlingen.setItems(searchNames);
    }

    public boolean validateEmail(String email) {
        System.out.printf(email);
        Pattern ptr = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}");
        return ptr.matcher(email).matches();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void VulLeerlingen() {
        Image CedricFoto = new Image("images/testLeerlingen/1.png");
        Leerling Cedric = new Leerling(null, null, "Cédric", null, CedricFoto);
        leerlingen.add(Cedric);
        
        Image RobinFoto = new Image("images/testLeerlingen/pepe.jpg");
        Leerling Robin = new Leerling(null, null, "Robin", null, RobinFoto);
        leerlingen.add(Robin);
        
        Image DriesFoto = new Image("images/testLeerlingen/2.png");
        Leerling Dries = new Leerling(null, null, "Dries", null, DriesFoto);
        leerlingen.add(Dries);
        
        Image MiltonFoto = new Image("images/testLeerlingen/3.png");
        Leerling Milton = new Leerling(null, null, "Milton", null, MiltonFoto);
        leerlingen.add(Milton);
    }

}
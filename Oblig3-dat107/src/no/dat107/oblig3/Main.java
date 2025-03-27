package no.dat107.oblig3;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

    	AnsattDAO ansattDAO = new AnsattDAO();
    	AvdelingDAO avdelingDAO = new AvdelingDAO();
    	Meny.start();
    	
    Ansatt ansatt1 = ansattDAO.finnAnsattMedId(5);
    	//Prøve å skrive ut avdeling med ansattID
    	//avdelingDAO.finnAvdelingMedId(ansatt1.getId()).skrivUtMedAnsatte();


//        Ansatt ansatt = new Ansatt("Andreas", "Ak", "Kaartinen", LocalDate.of(2020, 1, 1), "Utvikler", 60000.0);
//        System.out.println(ansatt);
//
//        
//        int id = ansattDAO.saveAnsatt(ansatt); //For å lagre inputs!!
//        System.out.println(ansatt);
//        
//        System.out.println("Returnert ID: " + id);
//        
//        System.out.print(ansattDAO.finnAnsattMedId(2));
    	//ansattDAO.deleteAnsatt(ansattDAO.finnAnsattMedId(3));
    }
}

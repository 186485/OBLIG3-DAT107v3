package no.dat107.oblig3;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Meny {
	 
	
	
	public static void start() {
		boolean fortsett = true;
	    while (fortsett) {
	
	    	String valgStr = JOptionPane.showInputDialog(null, 
            "Velg et alternativ:\n" +
            "1: Søk etter ansatt id\n" +
            "2: Søk etter ansatt ved initialer\n" +
            "3: Skriv ut alle ansatte\n" +
            "4: Oppdater ansatt sin stilling\n" +
            "5: Oppdater ansatt sin lønn\n" +
            "6: Legge til en ny ansatt\n" +
            "Ditt valg:");
	
    if (valgStr == null || valgStr.isEmpty()) {
        break; 
    }
    int valg = Integer.parseInt(valgStr);
    
    switch(valg) {
    
    case 1:
    	String ansatt = JOptionPane.showInputDialog("Oppgi ansattnr");
    	int ansattNR = Integer.parseInt(ansatt);
    	AnsattDAO ansattDAO = new AnsattDAO();
    	JOptionPane.showInternalMessageDialog(null, ansattDAO.finnAnsattMedId(ansattNR));
    	break;
    case 2:
    	String brukernavn = JOptionPane.showInputDialog("Oppgi brukernavn");
    	AnsattDAO ansattDAO2 = new AnsattDAO();
    	JOptionPane.showInternalMessageDialog(null, ansattDAO2.finnAnsatteMedBrukernavn(brukernavn));
    	break;
    case 3:
    	AnsattDAO ansattDAO3 = new AnsattDAO();
    	JOptionPane.showInternalMessageDialog(null, ansattDAO3.retrieveAlleAnsatte());
    	break;
    case 4:
    	String ansattOP = JOptionPane.showInputDialog("Oppgi ansattnr");
    	int ansattNROP = Integer.parseInt(ansattOP);

    	AnsattDAO oppdaterDAO = new AnsattDAO();
    	Ansatt ansatt2 = oppdaterDAO.finnAnsattMedId(ansattNROP);

    	if (ansatt2 != null) {
    	    String stilling = JOptionPane.showInputDialog("Oppgi ny stilling");
    	    ansatt2.setStilling(stilling);  
    	    oppdaterDAO.updateAnsatt(ansatt2);  
    	    JOptionPane.showMessageDialog(null, "Stilling oppdatert til: " + stilling);
    	} else {
    	    JOptionPane.showMessageDialog(null, "Fant ingen ansatt med ansattnr: " + ansattNROP);
    	}
    	break;
    case 5:
    	String ansattLONN = JOptionPane.showInputDialog("Oppgi ansattnr");
    	int ansattNRLONN = Integer.parseInt(ansattLONN);

    	AnsattDAO oppdaterLONN = new AnsattDAO();
    	Ansatt ansatt3 = oppdaterLONN.finnAnsattMedId(ansattNRLONN);

    	if (ansatt3 != null) {
    	    String LONN = JOptionPane.showInputDialog("Oppgi ny LØNN");
    	    double LONNDEC = Double.parseDouble(LONN);
    	    ansatt3.setMonedslonn(LONNDEC);  
    	    oppdaterLONN.updateAnsatt(ansatt3);  
    	    JOptionPane.showMessageDialog(null, "Lønn oppdatert til: " + LONN);
    	} else {
    	    JOptionPane.showMessageDialog(null, "Fant ingen ansatt med ansattnr: " + ansattNRLONN);
    	}
    	break;
    case 6:
    	String navn;
    	String initialer;
    	String etternavn;
        LocalDate ansettelse = LocalDate.now();
        String stilling;
        double lonn;
        
        navn = JOptionPane.showInputDialog("Oppgi fornavn");
        initialer = JOptionPane.showInputDialog("Oppgi initialer");
        etternavn = JOptionPane.showInputDialog("Oppgi etternavn");
        JOptionPane.showMessageDialog(null, "Dato idag: "+ ansettelse);
        stilling = JOptionPane.showInputDialog("Oppgi stilling");
        String lonnTXT = JOptionPane.showInputDialog("Oppgi lonn");
        lonn = Double.parseDouble(lonnTXT);
        Ansatt ansatt4 = new Ansatt(navn, initialer, etternavn, ansettelse, stilling, lonn);
        AnsattDAO ansattDAO4 = new AnsattDAO();
        ansattDAO4.saveAnsatt(ansatt4);
        JOptionPane.showMessageDialog(null, "Du har nå lagt til denne ansatte: \n"+ansatt4.toString());
        break;
        
    default:
        JOptionPane.showMessageDialog(null, "Ugyldig valg. Prøv igjen.");
    }
    
	 }
	
	}
}



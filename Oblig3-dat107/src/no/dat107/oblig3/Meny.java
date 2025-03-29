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
            "7: Søk på avdeling\n" +
            "8: Flytt ansatte til ny avdeling\n" +
            "9: Lag ny avdeling\n" +
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
    	JOptionPane.showInternalMessageDialog(null, ansattDAO.finnAnsattMedId(ansattNR).toString().replace("[", " ").replace("]", " "));
    	break;
    case 2:
    	String brukernavn = JOptionPane.showInputDialog("Oppgi brukernavn");
    	AnsattDAO ansattDAO2 = new AnsattDAO();
    	JOptionPane.showInternalMessageDialog(null, ansattDAO2.finnAnsatteMedBrukernavn(brukernavn).toString().replace("[", " ").replace("]", " "));
    	break;
    case 3:
    	AnsattDAO ansattDAO3 = new AnsattDAO();
    	JOptionPane.showInternalMessageDialog(null, ansattDAO3.retrieveAlleAnsatte().toString().replace("[", " ").replace("]", " "));
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

        
        AnsattDAO ansattDAO1 = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();

        
        navn = JOptionPane.showInputDialog("Oppgi fornavn:");
        initialer = JOptionPane.showInputDialog("Oppgi initialer:");
        etternavn = JOptionPane.showInputDialog("Oppgi etternavn:");
        JOptionPane.showMessageDialog(null, "Dato idag: " + ansettelse);
        stilling = JOptionPane.showInputDialog("Oppgi stilling:");
        String lonnTXT = JOptionPane.showInputDialog("Oppgi lønn:");
        lonn = Double.parseDouble(lonnTXT);

      
        String avdIdTXT = JOptionPane.showInputDialog("Oppgi avdeling ID:");
        int avdId = Integer.parseInt(avdIdTXT);
        
        
        Avdeling avdeling = avdelingDAO.finnAvdelingMedId(avdId);

        
        if (avdeling == null) {
            JOptionPane.showMessageDialog(null, "Ugyldig avdeling ID. Ansatt ble ikke lagt til.");
            break;
        }

      
        Ansatt nyAnsatt = new Ansatt(navn, initialer, etternavn, ansettelse, stilling, lonn, avdeling);

     
        ansattDAO1.saveAnsatt(nyAnsatt);

        
        JOptionPane.showMessageDialog(null, "Du har nå lagt til denne ansatte: \n" + nyAnsatt.toString().replace("[", " ").replace("]", " "));
        break;
        
    case 7:
    	String ansattAvd = JOptionPane.showInputDialog("Oppgi AVD-ID");
    	int ansattAvdINT = Integer.parseInt(ansattAvd);
    	AnsattDAO avdelingDAO2 = new AnsattDAO();
    	AnsattDAO ansattDAOSjef = new AnsattDAO();
    	AvdelingDAO avdelingSjef = new AvdelingDAO();
    	
    	
    	int sjefAnsattNR = avdelingSjef.finnAvdelingMedId(ansattAvdINT).getSjef();

    	JOptionPane.showInternalMessageDialog(null,"Sjefen er: "+ ansattDAOSjef.finnAnsattMedId(sjefAnsattNR).toString().replace("[", " ").replace("]", " ") +
    			" \n \n "+avdelingDAO2.finnAnsattIAvdeling(ansattAvdINT).toString().replace("[", " ").replace("]", " "));
    	
    	
    	break;
        
    case 8:
    	 AnsattDAO ansattDAO8 = new AnsattDAO();
         AvdelingDAO avdelingDAO8 = new AvdelingDAO();
    	      
         		//Spør bruker om ansattnr
    	        String ansattIdTxt2 = JOptionPane.showInputDialog("Oppgi ansattID:");
    	        int ansattId8 = Integer.parseInt(ansattIdTxt2);
    	             
    	        //Henter riktig ansatt
    	        Ansatt ansatt8 = ansattDAO8.finnAnsattMedId(ansattId8);
    	        if (ansatt8 == null) {
    	            JOptionPane.showMessageDialog(null, "Ansatt ikke funnet!");
    	            break;
    	        }

    	        //Sjekk om de er sjef, kan ikke bytte for sjefen
    	        if (avdelingDAO8.erAnsattSjef(ansattId8)) {
    	            JOptionPane.showMessageDialog(null, "Ansatte du har valgt er sjef, prøv igjen!");
    	            break;
    	        }

    	        //Hent ny avdeling
    	        String nyAvdTxt = JOptionPane.showInputDialog("Oppgi ny avdelings-ID:");
    	        int nyAvdId = Integer.parseInt(nyAvdTxt);
    	        Avdeling nyAvdeling = avdelingDAO8.finnAvdelingMedId(nyAvdId);

    	        if (nyAvdeling == null) {
    	            JOptionPane.showMessageDialog(null, "Ugyldig avdelings-ID.");
    	            break;
    	        }
    	        //Oppdatere ansatt / flytte over til ny valgt avdeling
    	        ansattDAO8.oppdaterAvdeling(ansattId8, nyAvdId);
    	        JOptionPane.showMessageDialog(null, ansatt8.toString() + "\n er nå flyttet til avdeling med id "+ nyAvdTxt);
    	        	break;
    case 9:
    	 		AnsattDAO ansattDAO9 = new AnsattDAO();
    			AvdelingDAO avdelingDAO9 = new AvdelingDAO();
    			String AvdNavn = JOptionPane.showInputDialog("Oppgi navn på ny avdeling:");
    			String Ansatt9 = JOptionPane.showInputDialog("Oppgi ansattID på ny sjef til ny avdeling:");
    			int sjefID9 = Integer.parseInt(Ansatt9);
    				
    	        Ansatt ansatt9 = ansattDAO9.finnAnsattMedId(sjefID9);
    	        if (ansatt9 == null) {
    	            JOptionPane.showMessageDialog(null, "Ansatt ikke funnet!");
    	            break;
    	        }
    			
    				if (avdelingDAO9.erAnsattSjef(sjefID9)) {
    					JOptionPane.showMessageDialog(null, "Ansatte du har valgt er allerede sjef, prøv igjen!");
    					break;
    					}
    				avdelingDAO9.leggTilNyAvdelingMedSjef(AvdNavn, ansatt9.getId());
    				
    			JOptionPane.showMessageDialog(null,"Du har nå opprettet avdeling "+ AvdNavn +" med denne ansatte som sjef: \n"+ ansatt9.toString());
    				break;
    				
      
    default:
        JOptionPane.showMessageDialog(null, "Ugyldig valg. Prøv igjen.");
    }
    
	 }
	
	}
}



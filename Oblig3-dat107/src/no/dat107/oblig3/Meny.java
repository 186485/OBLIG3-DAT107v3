package no.dat107.oblig3;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

public class Meny {

	public static void start() {
		boolean fortsett = true;
		while (fortsett) {

			String valgStr = JOptionPane.showInputDialog(null,
					"Velg et alternativ:\n" + "1: Søk etter ansatt id\n" + "2: Søk etter ansatt ved initialer\n"
							+ "3: Skriv ut alle ansatte\n" + "4: Oppdater ansatt sin stilling\n"
							+ "5: Oppdater ansatt sin lønn\n" + "6: Legge til en ny ansatt\n" + "7: Søk på avdeling\n"
							+ "8: Flytt ansatte til ny avdeling\n" + "9: Lag ny avdeling\n" + "10: Lag nytt prosjekt \n"
							+ "11: Legg til prosjektdeltagelse \n" + "12: Oppdater total timer jobbet i prosjekt: \n"
							+ "13: Vis informasjon om ett prosjekt \n" + "Ditt valg:");

			if (valgStr == null || valgStr.isEmpty()) {
				break;
			}
			int valg = Integer.parseInt(valgStr);

			switch (valg) {

			case 1:
				String ansatt = JOptionPane.showInputDialog("Oppgi ansattnr");
				int ansattNR = Integer.parseInt(ansatt);
				AnsattDAO ansattDAO = new AnsattDAO();
				JOptionPane.showInternalMessageDialog(null,
						ansattDAO.finnAnsattMedId(ansattNR).toString().replace("[", " ").replace("]", " "));
				break;
			case 2:
				String brukernavn = JOptionPane.showInputDialog("Oppgi brukernavn");
				AnsattDAO ansattDAO2 = new AnsattDAO();
				JOptionPane.showInternalMessageDialog(null,
						ansattDAO2.finnAnsatteMedBrukernavn(brukernavn).toString().replace("[", " ").replace("]", " "));
				break;
			case 3:
				AnsattDAO ansattDAO3 = new AnsattDAO();
				JOptionPane.showInternalMessageDialog(null,
						ansattDAO3.retrieveAlleAnsatte().toString().replace("[", " ").replace("]", " "));
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

				JOptionPane.showMessageDialog(null, "Du har nå lagt til denne ansatte: \n"
						+ nyAnsatt.toString().replace("[", " ").replace("]", " "));
				break;

			case 7:
				String ansattAvd = JOptionPane.showInputDialog("Oppgi AVD-ID");
				int ansattAvdINT = Integer.parseInt(ansattAvd);
				AnsattDAO avdelingDAO2 = new AnsattDAO();
				AnsattDAO ansattDAOSjef = new AnsattDAO();
				AvdelingDAO avdelingSjef = new AvdelingDAO();

				int sjefAnsattNR = avdelingSjef.finnAvdelingMedId(ansattAvdINT).getSjef();

				JOptionPane.showInternalMessageDialog(null,
						"Sjefen er: "
								+ ansattDAOSjef.finnAnsattMedId(sjefAnsattNR).toString().replace("[", " ").replace("]",
										" ")
								+ " \n  Ansatte i avdelingen er listet under:\n "
								+ avdelingDAO2.finnAnsattIAvdeling(ansattAvdINT).toString().replace("[", " ")
										.replace("]", " "));

				break;

			case 8:
				AnsattDAO ansattDAO8 = new AnsattDAO();
				AvdelingDAO avdelingDAO8 = new AvdelingDAO();

				// Spør bruker om ansattnr
				String ansattIdTxt2 = JOptionPane.showInputDialog("Oppgi ansattID:");
				int ansattId8 = Integer.parseInt(ansattIdTxt2);

				// Henter riktig ansatt
				Ansatt ansatt8 = ansattDAO8.finnAnsattMedId(ansattId8);
				if (ansatt8 == null) {
					JOptionPane.showMessageDialog(null, "Ansatt ikke funnet!");
					break;
				}

				// Sjekk om de er sjef, kan ikke bytte for sjefen
				if (avdelingDAO8.erAnsattSjef(ansattId8)) {
					JOptionPane.showMessageDialog(null, "Ansatte du har valgt er sjef, prøv igjen!");
					break;
				}

				// Hent ny avdeling
				String nyAvdTxt = JOptionPane.showInputDialog("Oppgi ny avdelings-ID:");
				int nyAvdId = Integer.parseInt(nyAvdTxt);
				Avdeling nyAvdeling = avdelingDAO8.finnAvdelingMedId(nyAvdId);

				if (nyAvdeling == null) {
					JOptionPane.showMessageDialog(null, "Ugyldig avdelings-ID.");
					break;
				}
				// Oppdatere ansatt / flytte over til ny valgt avdeling
				ansattDAO8.oppdaterAvdeling(ansattId8, nyAvdId);
				JOptionPane.showMessageDialog(null,
						ansatt8.toString() + "\n er nå flyttet til avdeling med id " + nyAvdTxt);
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
				} else {
					avdelingDAO9.leggTilNyAvdelingMedSjef(AvdNavn, ansatt9.getId());
					JOptionPane.showMessageDialog(null, "Du har nå opprettet avdeling " + AvdNavn
							+ " med denne ansatte som sjef: \n" + ansatt9.toString());
					break;
				}

			case 10:
				ProsjektDAO nyttProsjekt = new ProsjektDAO();
				String prosjektNavn = JOptionPane.showInputDialog("Oppgi navn på nytt prosjekt");
				String prosjektBeskrivelse = JOptionPane.showInputDialog("Oppgi beskrivelse på nytt prosjekt");

				nyttProsjekt.opprettProsjekt(prosjektNavn, prosjektBeskrivelse);
				JOptionPane.showMessageDialog(null,
						"Du har nå opprettet et nytt prosjekt med dette navnet: " + prosjektNavn);
				break;

			case 11:
				AnsattDAO ansattDAO11 = new AnsattDAO();
				ProsjektDAO prosjektDAO11 = new ProsjektDAO();

				String ansattIDSTR = JOptionPane.showInputDialog("Oppgi ansattnr:");
				String prosjektIDSTR = JOptionPane.showInputDialog("Oppgi prosjektID");
				String timerSTR = JOptionPane.showInputDialog("Oppgi antall timer:");
				String rolle = JOptionPane.showInputDialog("Oppgi din rolle i prosjektet!");

				int ansattID = Integer.parseInt(ansattIDSTR);
				int prosjektID = Integer.parseInt(prosjektIDSTR);
				int timer = Integer.parseInt(timerSTR);

				// Hent ansatt og prosjekt fra databasen
				Ansatt ansatt11 = ansattDAO11.finnAnsattMedId(ansattID);
				Prosjekt prosjekt11 = prosjektDAO11.finnProsjektMedId(prosjektID);

				// Sjekk at ansatt og prosjekt faktisk finnes
				if (ansatt11 == null) {
					JOptionPane.showMessageDialog(null, "Fant ikke ansatt med ID: " + ansattID);
					return;
				}
				if (prosjekt11 == null) {
					JOptionPane.showMessageDialog(null, "Fant ikke prosjekt med ID: " + prosjektID);
					return;
				}

				// Opprett prosjektdeltagelse
				ProsjektDeltagelse deltagelse11 = new ProsjektDeltagelse(ansatt11, prosjekt11, timer, rolle);

				// Lagre prosjektdeltagelsen i databasen
				ProsjektDeltagelseDAO deltagelseDAO11 = new ProsjektDeltagelseDAO();
				deltagelseDAO11.lagreProsjektDeltagelse(deltagelse11);

				JOptionPane.showMessageDialog(null, "Ansatt " + ansatt11.getNavn() + " registrert i prosjekt "
						+ prosjekt11.getNavn() + " med " + timer + " timer.");

				break;

			case 12:

				String ansattTimerSTR12 = JOptionPane.showInputDialog("Oppgi total antall timer brukt:");
				int ansattTimer = Integer.parseInt(ansattTimerSTR12);

				String ansattIDTimerSTR12 = JOptionPane.showInputDialog("Oppgi ansattnr:");
				int ansattIDTimer12 = Integer.parseInt(ansattIDTimerSTR12);

				String prosjektIDSTR12 = JOptionPane.showInputDialog("Oppgi prosjektID");
				int prosjektID12 = Integer.parseInt(prosjektIDSTR12);

				AnsattDAO ansattDAO12 = new AnsattDAO();
				ProsjektDAO prosjektDAO12 = new ProsjektDAO();
				ProsjektDeltagelseDAO prosjektDeltagelseDAO12 = new ProsjektDeltagelseDAO();

				Ansatt ansatt12 = ansattDAO12.finnAnsattMedId(ansattIDTimer12);
				Prosjekt prosjekt12 = prosjektDAO12.finnProsjektMedId(prosjektID12);

				if (ansatt12 == null) {
					JOptionPane.showMessageDialog(null, "Fant ikke ansatt med ID: " + ansattIDTimer12);
					break;
				}
				if (prosjekt12 == null) {
					JOptionPane.showMessageDialog(null, "Fant ikke prosjekt med ID: " + prosjektID12);
					break;
				}

				ProsjektDeltagelse prosjektDeltagelse12 = prosjektDeltagelseDAO12.finnProsjektDeltagelse(ansatt12,
						prosjekt12);

				if (prosjektDeltagelse12 == null) {
					JOptionPane.showMessageDialog(null, "Ansatt er ikke tilknyttet dette prosjektet.");
					break;
				}

				prosjektDeltagelse12.setTimer(ansattTimer);

				prosjektDeltagelseDAO12.oppdaterProsjektDeltagelse(prosjektDeltagelse12);

				JOptionPane.showMessageDialog(null,
						"Timer for ansatt " + ansatt12.getNavn() + " " + ansatt12.getEtternavn() + " i prosjekt "
								+ prosjekt12.getNavn() + " er oppdatert til " + ansattTimer + " timer.");

				break;

			case 13:
				ProsjektDAO prosjektDAO13 = new ProsjektDAO();
				ProsjektDeltagelseDAO deltagelse13 = new ProsjektDeltagelseDAO();
				String prosjektNR13STR = JOptionPane.showInputDialog("Skriv inn prosjektID:");
				int prosjektNR13 = Integer.parseInt(prosjektNR13STR);

				Prosjekt prosjekt13 = prosjektDAO13.finnProsjektMedId(prosjektNR13);

				if (prosjekt13 != null) {
					int prosjektid13 = prosjekt13.getId();
					JOptionPane.showMessageDialog(null,
							"Navn: " + prosjekt13.getNavn() + "\nBeskrivelse: " + prosjekt13.getBeskrivelse() + "\n"
									+ deltagelse13.finnAlleDeltagelserForProsjekt(prosjektid13));
				} else {
					JOptionPane.showMessageDialog(null, "ProsjektID ikke funnet");
					break;

				}
				break;

			default:
				JOptionPane.showMessageDialog(null, "Tallet er ikke en mulighet!");
				break;

			}

		}

	}
}

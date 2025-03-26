package no.dat107.oblig3;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        AnsattDAO ansattDAO = new AnsattDAO();

        Ansatt ansatt = new Ansatt("Andreas", "AndyInnebandy03", "Kaartinen", LocalDate.of(2020, 1, 1), "Utvikler", 60000.0);
        System.out.println(ansatt);

        
        int id = ansattDAO.saveAnsatt(ansatt);;
        System.out.println(ansatt);
        
        System.out.println("Returnert ID: " + id);
    }
}

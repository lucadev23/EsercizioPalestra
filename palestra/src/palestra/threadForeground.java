package palestra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lucadev23
 */
public class threadForeground extends Thread{
    private gestionePalestra gestore;
    private BufferedReader tastiera;
    
    public threadForeground(gestionePalestra gestore){
        this.gestore=gestore;
        tastiera=new BufferedReader(new InputStreamReader(System.in));
    }
    
    
    @Override
    public void run(){
        gestore.caricaCorsi();
        gestore.caricaPrenotazioni();
        while(true){
            switch(menu()){
                case 0:
                    System.exit(0);
                case 1:
                    gestore.eliminaCorso();
                    break;
                case 2:
                    gestore.aggiungiNuovoCorso();
                    break;
                case 3:
                    gestore.aggiungiNuovaPrenotazione();
                    break;
                case 4:
                    gestore.cancellarePrenotazione();
                    break;
                case 5:
                    gestore.stampaCorsi();
                    break;
                case 6:
                    gestore.stampaTotalePrenotazioni();
                    break;
                case 7:
                    gestore.stampaNumeroPostiDisponibili();
                    break;
                case 8:
                    gestore.stampaRicavo();
                    break;
                default:
                    break;
                    
            }
        }
    }
    
    public int menu(){
        System.out.print("----Menu----\n"
                + "1 - Elimina un corso\n"
                + "2 - Aggiungi corso\n"
                + "3 - Inserisci prenotazione\n"
                + "4 - Cancella prenotazione\n"
                + "5 - Stampa Corsi\n" //l'ho aggiunto io
                + "6 - Stampa totale prenotazioni\n"
                + "7 - Stampa posti diponibili\n"
                + "8 - Stampa ricavo totale\n"
                + "0 - Esci\n"
                + "Scelta: ");
        try{
            return Integer.parseInt(tastiera.readLine());
        }catch(IOException ioe){
            System.out.println("Errore IO\n");
            System.exit(-1);
        }
        catch(NumberFormatException nfe){
            System.out.println("Devi inserire un valore numerico\n");
            return -1;
        }
        return -1;
    }
}

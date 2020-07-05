package palestra;

/**
 *
 * @author lucadev23
 */
public class threadBackground extends Thread{
    private gestionePalestra gestore;
    
    
    public threadBackground(gestionePalestra gestore){
        this.gestore=gestore;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                gestore.calcolaTotalePrenotazioni();
                gestore.calcolaNumeroPostiDisponibili();
                gestore.calcolaRicavo();
                this.sleep(5000);
            }catch(InterruptedException iex){
                System.out.println("Chiusura del Thread Background");
            }
        }
    }
}

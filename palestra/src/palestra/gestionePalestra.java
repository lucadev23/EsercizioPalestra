package palestra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 *
 * @author lucadev23
 */
public class gestionePalestra {
    private LinkedList<corso> corsi;
    private BufferedReader tastiera;
    private String totalePrenotazioni, postiDisponibili, ricavoString;
    
    public gestionePalestra(){
        corsi = new LinkedList<corso> ();
        tastiera=new BufferedReader(new InputStreamReader(System.in));
        totalePrenotazioni=postiDisponibili=ricavoString="";
    }
    
    public synchronized void stampaRicavo(){
        System.out.println(ricavoString);
    }
    
    public synchronized void calcolaRicavo(){
        ricavoString="--Totale Ricavo--\n";
        int i=1;
        float ricavo=0;
        for(corso c: corsi){
            ricavo+=c.getRicavo();
            ricavoString+=i+" - "+c.getNomeCorso()+" ricavo -> "+c.getRicavo()+"\n";
            i++;
        }
        ricavoString+="Totale -> "+ricavo+"\n";
    }
    
    public synchronized void stampaNumeroPostiDisponibili(){
        System.out.println(postiDisponibili);
    }
    
    public synchronized void calcolaNumeroPostiDisponibili(){
        postiDisponibili="--Totale Posti Disponibili--\n";
        int i=1;
        for(corso c: corsi){
            postiDisponibili+=i+" - "+c.getNomeCorso()+" posti disponibili -> "+c.getNumeroPostiDisponibili()+"\n";
            i++;
        }
    }
    
    public synchronized void stampaTotalePrenotazioni(){
        System.out.println(totalePrenotazioni);
    }
    
    public synchronized void calcolaTotalePrenotazioni(){
        totalePrenotazioni="--Totale Prenotazioni--\n";
        int i=1;
        int totale=0;
        for(corso c: corsi){
            totale+=c.getNumeroPrenotazioni();
            totalePrenotazioni+=i+" - "+c.getNomeCorso()+" prenotazioni -> "+c.getNumeroPrenotazioni()+"\n";
            i++;
        }
        totalePrenotazioni+="Totale prenotati -> "+totale+"\n";
    }
    
    public synchronized void cancellarePrenotazione(){
        System.out.print("Inserisci nome e cognome del cliente: ");
        try{
            String nomeCognome = tastiera.readLine();
            for(corso c: corsi){
                if(c.cancellaPrenotazione(nomeCognome)){
                    System.out.println("Prenotazione eliminata!");
                    return;
                }
            }
            System.out.println("Nessuna prenotazione eliminata!");
        }catch(IOException ioe){
            System.out.println("Errore IO");
            System.exit(-1);
        }
    }
    
    public synchronized boolean esisteCorso(String nomeCorso){
        for(corso c: corsi){
            if(c.getNomeCorso().equalsIgnoreCase(nomeCorso))
                return true;
        }
        return false;
    }
    
    public synchronized void aggiungiNuovaPrenotazione(){
        try{
            System.out.print("Inserisci nome del corso: ");
            String nomeCorso = tastiera.readLine();
            if(!esisteCorso(nomeCorso)){
                System.out.println("Un corso con questo nome non esiste");
                return;
            }
            System.out.print("Inserisi nome e cognome del liente: ");
            String nomeCognomeCliente = tastiera.readLine();
            System.out.print("Inserisci la data (formato aaaammgg): ");
            String data = tastiera.readLine();
            for(corso c: corsi){
                if(c.getNomeCorso().equalsIgnoreCase(nomeCorso)){
                    c.aggiungiPrenotazione(new prenotazione(nomeCognomeCliente,data));
                    System.out.println("Prenotazione aggiunta!");
                }
            }
        }
        catch(IOException ioe){
            System.out.println("Errore IO\n");
            System.exit(-1);
        }
        catch(LimitePostiException lpe){
            System.out.println("Limite posti raggiunto!\n");
            return;
        }
    }
    
    public synchronized void aggiungiNuovoCorso(){
        try{
            System.out.print("Inserisci nome del corso: ");
            String nomeCorso = tastiera.readLine();
            System.out.print("Inserisci nome e cognome dell'istruttore: ");
            String nomeCognomeIstruttore = tastiera.readLine();
            System.out.print("Inserisci capienza max: ");
            int capienzaMax = Integer.parseInt(tastiera.readLine());
            System.out.print("Inserisci tariffa mensile: ");
            float tariffa = Float.parseFloat(tastiera.readLine());
            corsi.add(new corso(nomeCorso,nomeCognomeIstruttore,capienzaMax,tariffa));
            System.out.println("Corso aggiunto!");
        }
        catch(NumberFormatException nfe){
            System.out.println("Errore conversioni dei dati\n");
            System.exit(-1);
        }
        catch(IOException ioe){
            System.out.println("Errore IO\n");
            System.exit(-1);
        }
        
    }
    
    public synchronized void stampaCorsi(){
        int i=0;
        System.out.println("--Corsi--\n");
        for(corso c: corsi){
            i++;
            System.out.println(i+" - "+c.getNomeCorso()+"\n");
        }
    }
    
    public synchronized void eliminaCorso(){
        String risposta;
        System.out.println("Quale corso vuoi eliminare?\n");
        stampaCorsi();
        System.out.print("Scelta: ");
        try{
            risposta=tastiera.readLine();
            for(corso c: corsi){
                if(c.getNomeCorso().equalsIgnoreCase(risposta)){
                    corsi.remove(new corso(risposta,null,0,0.0f));
                    System.out.println("Il corso "+risposta+" è stato eliminato\n");
                    return;
                }
            }
            System.out.println("Nessun corso trovato col nome di '"+risposta+"'\n");
        }catch(IOException ioe){
            System.out.println("Errore IO\n");
            System.exit(-1);
        }
        
        
    }
    
    public synchronized void aggiungiPrenotazione(String nomeCorso, prenotazione p){
        for(corso c: corsi){
            if(c.getNomeCorso().equalsIgnoreCase(nomeCorso)){
                try{
                    c.aggiungiPrenotazione(p);
                    System.out.println("Prenotazione aggiunta!");
                }catch(LimitePostiException lpe){
                    System.out.println("Limite posti raggiunto!\n");
                    return;
                }
                
            }
        }
    }
    
    
    public synchronized void aggiungiPrenotazioneDaFile(String nomeCorso, prenotazione p){
        for(corso c: corsi){
            if(c.getNomeCorso().equalsIgnoreCase(nomeCorso)){
                try{
                    c.aggiungiPrenotazione(p);
                }catch(LimitePostiException lpe){
                    System.out.println("Limite posti raggiunto!\n");
                    return;
                }
                
            }
        }
    }
    
    public synchronized void caricaPrenotazioni(){
        BufferedReader fpPrenotazioni;
        try{
            fpPrenotazioni = new BufferedReader(new FileReader("prenotazioni.txt"));
            String nomeCognomeCliente, nomeCorso, data;
            nomeCognomeCliente=fpPrenotazioni.readLine();
            while(nomeCognomeCliente!=null){
                nomeCorso = fpPrenotazioni.readLine();
                data=fpPrenotazioni.readLine();
                aggiungiPrenotazioneDaFile(nomeCorso,new prenotazione(nomeCognomeCliente, data));
                nomeCognomeCliente=fpPrenotazioni.readLine();
            }
        }
        catch(FileNotFoundException fex){
            System.out.println("File 'corsi.txt' non trovato!");
            System.exit(-1);
        }
        catch(NumberFormatException nfe){
            System.out.println("Errore conversioni dei dati da file");
            System.exit(-1);
        }
        catch(IOException ioe){
            System.out.println("Errore IO");
            System.exit(-1);
        }
    }
    
    
    public synchronized void caricaCorsi(){
        BufferedReader fpCorsi;
        try{
            fpCorsi = new BufferedReader(new FileReader("corsi.txt"));
            String nomeCorso, nomeCognomeIstruttore;
            int capienzaMax;
            float tariffa;
            nomeCorso=fpCorsi.readLine();
            while(nomeCorso!=null){
                nomeCognomeIstruttore=fpCorsi.readLine();
                capienzaMax=Integer.parseInt(fpCorsi.readLine());
                tariffa=Float.parseFloat(fpCorsi.readLine());
                corsi.add(new corso(nomeCorso,nomeCognomeIstruttore,capienzaMax,tariffa));
                nomeCorso=fpCorsi.readLine();
            }
        }
        catch(FileNotFoundException fex){
            System.out.println("File 'corsi.txt' non trovato!");
            System.exit(-1);
        }
        catch(NumberFormatException nfe){
            System.out.println("Errore conversioni dei dati da file");
            System.exit(-1);
        }
        catch(IOException ioe){
            System.out.println("Errore IO");
            System.exit(-1);
        }
    }
}

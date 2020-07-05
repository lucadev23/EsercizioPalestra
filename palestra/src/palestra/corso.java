package palestra;

import java.util.LinkedList;

/**
 *
 * @author lucadev23
 */
public class corso {
    private String nomeCorso;
    private String nomeCognomeIstruttore;
    private int capienzaMax;
    private float tariffaMensile;
    private LinkedList<prenotazione> prenotazioni;
    private int numeroPrenotazioni;
    
    public corso(String nomeCorso, String nomeCognomeIstruttore, int capienzaMax, float tariffaMensile){
        this.nomeCorso=nomeCorso;
        this.nomeCognomeIstruttore=nomeCognomeIstruttore;
        this.capienzaMax=capienzaMax;
        this.tariffaMensile=tariffaMensile;
        this.numeroPrenotazioni=0;
        prenotazioni=new LinkedList<prenotazione>();
    }
    
    public float getRicavo(){
        return (numeroPrenotazioni*tariffaMensile);
    }
    
    public int getNumeroPostiDisponibili(){
        return capienzaMax-numeroPrenotazioni;
    }
    
    public int getNumeroPrenotazioni(){
        return this.numeroPrenotazioni;
    }
    
    public boolean cancellaPrenotazione(String nomeCognome){
        for(prenotazione p: prenotazioni){
            if(p.getNomeCognome().equalsIgnoreCase(nomeCognome)){
                prenotazioni.remove(new prenotazione(nomeCognome,null));
                numeroPrenotazioni--;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean equals(Object o){
        return this.nomeCorso.equalsIgnoreCase( ( ( (corso)o ).getNomeCorso() ) );
    }
    
    public void aggiungiPrenotazione(prenotazione p)throws LimitePostiException{
        if(numeroPrenotazioni<capienzaMax){
            prenotazioni.add(p);
            numeroPrenotazioni++;
        }
        else{
            throw new LimitePostiException();
        }
    }
    
    public String getNomeCorso(){
        return nomeCorso;
    }
    
}

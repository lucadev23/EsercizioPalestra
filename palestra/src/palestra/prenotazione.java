package palestra;

/**
 *
 * @author lucadev23
 */
public class prenotazione {
    private String nomeCognomeCliente;
    private String dataPrenotazione; //aaaammgg
    
    public prenotazione(String nomeCognomeCliente, String dataPrenotazione){
        this.nomeCognomeCliente=nomeCognomeCliente;
        this.dataPrenotazione=dataPrenotazione;
    }
    
    public String getNomeCognome(){
        return this.nomeCognomeCliente;
    }
    
    @Override
    public boolean equals(Object o){
        return this.nomeCognomeCliente.equalsIgnoreCase( ((prenotazione)o).getNomeCognome() );
    }
}

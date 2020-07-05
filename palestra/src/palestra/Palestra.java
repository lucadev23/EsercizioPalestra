package palestra;

/**
 *
 * @author lucadev23
 */
public class Palestra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gestionePalestra gestore = new gestionePalestra();
        
        threadForeground tf = new threadForeground(gestore);
        threadBackground tb = new threadBackground(gestore);
        
        tf.start();
        tb.start();
    }
    
}

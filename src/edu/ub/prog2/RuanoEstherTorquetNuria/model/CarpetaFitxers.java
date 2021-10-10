package edu.ub.prog2.RuanoEstherTorquetNuria.model;
import edu_ub_prog2_RuanoEstherTorquetNuria.vista.AplicacioUB1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * La classe CarpetaFitxers conté un ArrayList de fitxers i un la mida màxima 
 * d'aquesta.
 * Els mètodes permeten afegir, treure elements i conèixer l'estat de la Carpeta.
 */
public class CarpetaFitxers {
    //Atributs
    private ArrayList<FitxerMultimedia> carpeta; 
    private int maxFitxers;

    
    /**Constructor per defecte:
     Assigna a l'atribut carpeta un ArrayList pel qual ha reservat un espai de
     100 FitxersMultimedia i assigna a maxFitxers el valor de 100. */
    public CarpetaFitxers() {
        carpeta = new ArrayList<FitxerMultimedia>(100);
        /*
        Ara s'ha reservat memòria per a 100 fitxers multimèdia però si se'n 
        volguéssin afegir més a la carpeta no hi hauria problema, ja que és
        memòria dinàmica.
        Per això es crea l'atribut maxFitxers amb la finalitat de controlar la 
        mida de la carpeta.
        */
        maxFitxers = 100;
    }
    
    /**Constructor amb paràmetres:
    * @param capacitat desitjada de la CarpetaFitxers
    * Assigna a l'atribut carpeta un ArrayList pel qual ha reservat l'espai
    * passat com a paràmetre de FitxersMultimedia i assigna a maxFitxers 
    * el valor indicat. */
    public CarpetaFitxers(int capacitat){
        maxFitxers = capacitat;
        carpeta = new ArrayList<FitxerMultimedia>(capacitat);
    }
    
    /**
     * Retorna quans elements té l'ArrayList
     * @return int
     */
    public int getSize(){
        return carpeta.size();
    }
    
    /**
    * Mitjançant l'atribut maxFitxers, s'evita que la carpeta excedeixi la 
    * mida màxima estipulada.  
    * Fa servir el mètode .add dels ArrayList per afegir l'element a la
    * primera posició lliure, si n'hi ha.
    * 
    * @param fitxer a afegir
    * @throws IndexOutOfBoundsException si la carpeta està plena
    */
    public void addFitxer(File fitxer){
        if (carpeta.size() < maxFitxers) {
            carpeta.add((FitxerMultimedia) fitxer);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
    * Elimina la primera ocurrència del Fitxer demanat
    * 
    * @param fitxer a eliminar
    * @throws FileNotFoundException si el fitxer no es troba a la carpeta 
    */
    public void removeFitxer(File fitxer) throws FileNotFoundException{
        /*
        El mètode .remove esborra la primera ocurrència de fitxer i desplaça tots
        els fitxers que el segueixin a l'ArrayList una posició enrere, de manera
        que totes les posicions lliures queden al final.
        També retorna un booleà que és True si ha trobat el fitxer i l'ha esborrat
        i pel contrari False si després de recórrer l'ArrayList no ha trobat cap
        coincidència amb el fitxer passat per paràmetre
        */
        boolean b = carpeta.remove((FitxerMultimedia) fitxer);
        if (!b) {
            throw new FileNotFoundException();
        }
    }
    
    /** 
     * Retorna el fitxer que es troba a la posició passada per paràmetre
     * 
     * @param position (índex) de l'arxiu que es vol conèixer
     * @return File
     * @throws IndexOutOfBoundsException si no hi ha cap element a la posició
     * o la posició és major que la mida de l'ArrayList
    */
    public File getAt(int position){
        return carpeta.get(position);
    }
    
    /**
    * Recorre l'ArrayList esborrant l'element que hi ha en cada posició. 
    */
    public void clear(){
        carpeta.clear();
    }
    
    /**
    * Retorna True si la carpeta està plena, False en cas contrari
    * 
    * @return boolean
    */
    public boolean isFull(){
        return (carpeta.size() == maxFitxers);
    }
    
    /**
    * Retorna, seguint el format indicat, un String amb els elements de 
    * la carpeta de fitxers. 
    * 
    * @return String
    */
    @Override
    public String toString(){
        String resultat = "Carpeta Fitxers: \n" + "=================\n";
        for (int i = 0; i < carpeta.size(); i++) {
            resultat = resultat + "\n[" + (i + 1) + "] " + carpeta.get(i).toString();
        }
        return resultat;
    
    }
}

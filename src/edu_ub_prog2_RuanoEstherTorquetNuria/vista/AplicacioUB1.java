package edu_ub_prog2_RuanoEstherTorquetNuria.vista;
import edu.ub.prog2.RuanoEstherTorquetNuria.model.*;
import edu.ub.prog2.utils.Menu;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * La classe AplicacioUB conté la lògica del programa. Crea un objecte del tipus
 * Menu i una CarpetaFixers. Cada opció del menu respon a un mètode de la carpeta
 * de fitxers.
 * Aquesta classe recull la informació per fer les crides pertinents als mètodes
 * de la carpeta de fitxers i els executa. 
 * És la classe amb la qual interactua l'usuari
 */
public class AplicacioUB1 {
    
    private CarpetaFitxers carpetaFitxers;
    static private enum OpcionsMenuPrincipal {ADD_FITXERS, REMOVE_FITXERS, 
    SHOW_FITXERS, EXIT,GET_SIZE_FITXERS, GET_AT_FITXERS, CLEAR_FITXERS, IS_FULL_FITXERS};
    static private String[] descMenuPrincipal = {
        "Afegir fitxer multimèdia", 
        "Eliminar fitxer multimèdia",
        "Mostra carpeta",
        "Sortir",
        "Retornar nombre d'elements de la carpeta", 
        "Retornar el fitxer a la posició indicada", 
        "Eliminar tots els elements de la carpeta", 
        "Indicar si la carpeta està plena"
    };
    
    /**
     * Constructor sense paràmentres.
     * Crea l'Scanner, instancia l'aplicacioUB1 i crida al mètode manager d'aquesta
     * {@link #manager(java.util.Scanner)}
     */
    public void gestioAplicacioUB(){
        // atributs i variables
        Scanner sc = new Scanner(System.in);
        // Creem un objecte principal
        AplicacioUB1 app = new AplicacioUB1();
        // Iniciem la gestio del menu principal de l'aplcicacio
        this.manager(sc);
    }
    
    /**
     * En primer lloc crida al constructor adequat de la carpeta de fitxers. 
     * Seguidament crea una instància de la classe Menu
     * Fent servir els mètodes pertinents mostra les opcions a l'usuari i executa
     * les accions apropiades per dur a terme allò seleccionat
     * 
     * @param sc
     */
    private void manager(Scanner sc) {
        /*
        Demanem a l'usuari si vol fer servir el constructor per defecte o el que 
        té paràmetres. En aquest cas assegurem que el valor de la mida sigui vàlid
        */
        System.out.println("Vols una carpeta de 100 fitxers? (S/N)");
        String tria = sc.next();
        while(!tria.equals("S") && !tria.equals("N")){
            System.out.println("Introdueix S o N");
            tria = sc.next();
        }
        if (tria.equals("S")) {
            carpetaFitxers = new CarpetaFitxers();
        } else {
            System.out.println("Indica la mida");
            int mida = sc.nextInt();
            while (mida <= 0) {
                System.out.println("Mida no vàlida");
                mida = sc.nextInt();
            }
            carpetaFitxers = new CarpetaFitxers(mida);
        }
        

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu
        Menu<OpcionsMenuPrincipal> menu=new Menu<OpcionsMenuPrincipal>("Menu Principal",OpcionsMenuPrincipal.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opcio des del menu i fem les accions pertinents
        OpcionsMenuPrincipal opcio = null;
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessaries
            switch(opcio) {
                case ADD_FITXERS:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1: Afegir un fitxer");
                    //implementar opcio
                    addFitxerInfo(sc);
                    break;
                case REMOVE_FITXERS:
                    System.out.println("Has triat l'opció 2: Eliminar un fitxer");
                    removeFitxer(sc);
                    break;
                case SHOW_FITXERS:
                    System.out.println("Has triat l'opció 3: Mostra carpeta");
                    System.out.println(carpetaFitxers.toString());
                    break;
                case EXIT:
                    System.out.println("Fins aviat!");
                    break;                    
                case GET_SIZE_FITXERS:
                    System.out.println("Has triat l'opció 5: Retornar nombre d'elements de la carpeta");
                    System.out.println("El nombre d'elements en la carpeta és: "+ carpetaFitxers.getSize());
                    break;
                case GET_AT_FITXERS:
                    System.out.println("Has triat l'opció 6: Retornar el fitxer a la posició indicada");
                    getFitxer(sc);
                    break;
                case CLEAR_FITXERS:
                    System.out.println("Has triat l'opció 7: Eliminar tots els elements de la carpeta");
                    carpetaFitxers.clear();
                    break;
                case IS_FULL_FITXERS:
                    System.out.println("Has triat l'opció 8: Indicar si la carpeta està plena");
                    if (carpetaFitxers.isFull()) {
                        System.out.println("Està plena ");
                    } else {
                        System.out.println("No està plena");
                    }
                    break;
                
            }

        } while(opcio!=OpcionsMenuPrincipal.EXIT);
    }
    
    /*********************************************/
    /*************  Metodes privats  *************/
    /*********************************************/  
    
    /**
     * Recull per teclat l'adreça i la descripcio del fitxer que es vol afegir. 
     * Comprova que sigui adient i crea el fitxer. Si hi ha algun error en el
     * proces, recull una excepcio.
     * 
     *@param sc
     */
    private void addFitxerInfo(Scanner sc){
        System.out.println("El format ha de ser C://CarpetaFitxer//NomDelFitxer.extensió");
        System.out.println("Introdueix el camí del fitxer");
        String cami = sc.nextLine();  
        while(!(cami.contains("//") && cami.contains("."))){
            System.out.println("Camí no vàlid\nIntrodueix el camí del fitxer");
            cami = sc.nextLine();
        }
        System.out.println("Introdueix la descripció del fitxer");
        String descripcio = sc.nextLine();  
        FitxerMultimedia fitxer = new FitxerMultimedia(cami, descripcio);
        try {
            carpetaFitxers.addFitxer(fitxer);
            System.out.println("Fitxer afegit amb èxit!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Has excedit la mida màxima. ");
        }
    }
    
    /**
     * Recull per teclat l'adreça i la descripcio del fitxer que es vol eliminar. 
     * Comprova que sigui adient i elimina el fitxer. Si hi ha algun error en el
     * proces, recull una excepcio.
     * 
     *@param sc
     */
    private void removeFitxer(Scanner sc){
        System.out.println("El format ha de ser C://CarpetaFitxer//NomDelFitxer.extensió");
        System.out.println("Introdueix el camí del fitxer");
        String cami = sc.nextLine();
        while(!(cami.contains("//") && cami.contains("."))){
            System.out.println("Camí no vàlid\nIntrodueix el camí del fitxer");
            cami = sc.nextLine();
        }
        System.out.println("Introdueix la descripció del fitxer");
        String descripcio = sc.nextLine(); 
        FitxerMultimedia fitxer = new FitxerMultimedia(cami, descripcio);
        try {
            carpetaFitxers.removeFitxer(fitxer);
            System.out.println("Fitxer eliminat amb èxit!\n");
        } catch (FileNotFoundException e) {
            System.out.println("Aquest fitxer no es troba a la carpeta. ");
        }
    }
    
    /**
     * Recull per teclat l'index del fitxer al qual es vol accedir.
     * En retorna la informacio fent servir el metode .toString del FitxerMultimedia
     * Si hi ha algun error en el proces, recull una excepcio
     * 
     *@param sc
     */
    private void getFitxer(Scanner sc) {
        System.out.println("Indica l'índex del fitxer que vols veure: ");
        int pos = sc.nextInt();
        try {
            System.out.println(carpetaFitxers.getAt(pos - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            //Aquesta excepció arriba tant si la posició és buida com si l'index 
            //és major que la mida de la carpeta
            System.out.println("No hi ha elements en aquesta posició");
        }
}
}

        
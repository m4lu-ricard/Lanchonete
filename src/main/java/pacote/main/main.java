package pacote;

import pacote.controller.LanchoneteController;

/**
 * Classe principal que inicia o sistema de lanchonete.
 * Atende ao padrão GRASP Controller ao delegar o controle ao LanchoneteController.
 */
public class Main {
    public static void main(String[] args) {
        LanchoneteController controller = new LanchoneteController();
        controller.iniciarSistema();
    }
}

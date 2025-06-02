package utils;

public class PasswordLeakChecker {
    public static void verificarVazamento(String senha) {
        System.out.println("Consultando possíveis vazamentos da senha com Have I Been Pwned...");
        boolean vazada = HIBPClient.senhaVazada(senha);

        if (vazada) {
            System.out.println("⚠️ Essa senha já foi comprometida em vazamentos!");
        } else {
            System.out.println("Nenhum vazamento detectado para essa senha.");
        }
    }
}
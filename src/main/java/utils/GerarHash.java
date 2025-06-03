package utils;

import org.mindrot.jbcrypt.BCrypt;

// Classe principal que representa GerarHash
public class GerarHash {
// Método que executa main
    public static void main(String[] args) {
        String senha = "admin123";
        String hash = BCrypt.hashpw(senha, BCrypt.gensalt(12));
        System.out.println("Hash gerado: " + hash);
    }
}

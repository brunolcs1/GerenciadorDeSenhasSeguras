package auth;

import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private final String usuarioPadrao = "admin";

    // Hash gerado a partir da senha "admin123"
    private final String senhaHashArmazenada = "$2a$12$bct8S0BLM8S.Pzk2BuLQfulDbiua8/qEjvenY2pKgOJWxICM5Q88m";

    public boolean login(Scanner scanner) {
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (usuario.equals(usuarioPadrao) && BCrypt.checkpw(senha, senhaHashArmazenada)) {
            TwoFactorAuth twoFA = new TwoFactorAuth();
            return twoFA.run2FA(usuario);
        }

        System.out.println("Credenciais incorretas. Tente novamente.");
        return false;
    }
}

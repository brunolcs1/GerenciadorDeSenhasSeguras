package auth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import java.util.Scanner;

// Classe principal que representa TwoFactorAuth
public class TwoFactorAuth {
// Método auxiliar: GoogleAuthenticator
    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();
// Atributo privado
    private final GoogleAuthenticatorKey key;

// Construtor ou método público: TwoFactorAuth
    public TwoFactorAuth() {
        this.key = gAuth.createCredentials();
    }

// Construtor ou método público: getSecret
    public String getSecret() {
        return key.getKey();
    }

// Construtor ou método público: getQRCode
    public String getQRCode(String user, String issuer) {
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(issuer, user, key);
    }

// Construtor ou método público: validateCode
    public boolean validateCode(int code) {
        return gAuth.authorize(key.getKey(), code);
    }

// Construtor ou método público: run2FA
    public boolean run2FA(String user) {
        String qrCode = getQRCode(user, "PasswordManager");
        System.out.println("Escaneie este QR Code no Google Authenticator:");
        System.out.println(qrCode);

        System.out.print("Digite o código do app: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int code;
        try {
            code = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Insira um número válido para continuar.");
            return false;
        }

        boolean valid = validateCode(code);
        if (valid) {
            System.out.println("Código válido! Acesso permitido.");
        } else {
            System.out.println("Código inválido! Acesso negado.");
        }
        return valid;
    }
}
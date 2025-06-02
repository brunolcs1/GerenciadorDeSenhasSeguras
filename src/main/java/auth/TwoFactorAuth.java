package auth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import java.util.Scanner;

public class TwoFactorAuth {
    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();
    private final GoogleAuthenticatorKey key;

    public TwoFactorAuth() {
        this.key = gAuth.createCredentials();
    }

    public String getSecret() {
        return key.getKey();
    }

    public String getQRCode(String user, String issuer) {
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(issuer, user, key);
    }

    public boolean validateCode(int code) {
        return gAuth.authorize(key.getKey(), code);
    }

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
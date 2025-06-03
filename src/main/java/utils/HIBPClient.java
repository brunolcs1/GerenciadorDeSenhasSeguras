package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

// Classe principal que representa HIBPClient
public class HIBPClient {

// Construtor ou método público: senhaVazada
    public static boolean senhaVazada(String senha) {
        try {
            String hash = sha1(senha).toUpperCase();
            String prefixo = hash.substring(0, 5);
            String sufixo = hash.substring(5);

            URL url = new URL("https://api.pwnedpasswords.com/range/" + prefixo);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("User-Agent", "PasswordManagerApp");

            BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            while ((linha = in.readLine()) != null) {
                if (linha.startsWith(sufixo)) {
                    System.out.println("Senha encontrada na lista de vazamentos!");
                    in.close();
                    return true;
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Erro ao verificar a senha: " + e.getMessage());
        }

        return false;
    }

// Método auxiliar: sha1
    private static String sha1(String senha) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] bytes = md.digest(senha.getBytes("UTF-8"));

        StringBuilder resultado = new StringBuilder();
        for (byte b : bytes) {
            resultado.append(String.format("%02x", b));
        }
        return resultado.toString();
    }
}

package crypto;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

// Classe principal que representa CryptoService
public class CryptoService {

// Construtor ou método público: criptografar
    public static String criptografar(String texto, String senha) throws Exception {
        byte[] vetorInicial = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(vetorInicial);
        SecretKey chaveAes = gerarChaveAES(senha);

        Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cifra.init(Cipher.ENCRYPT_MODE, chaveAes, ivSpec);
        byte[] textoCriptografado = cifra.doFinal(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(textoCriptografado);
    }

// Construtor ou método público: descriptografar
    public static String descriptografar(String textoCriptografado, String senha) throws Exception {
        byte[] vetorInicial = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(vetorInicial);
        SecretKey chaveAes = gerarChaveAES(senha);

        Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cifra.init(Cipher.DECRYPT_MODE, chaveAes, ivSpec);
        byte[] textoDecodificado = Base64.getDecoder().decode(textoCriptografado);
        byte[] textoOriginal = cifra.doFinal(textoDecodificado);
        return new String(textoOriginal, "UTF-8");
    }

// Construtor ou método público: gerarChaveAES
    public static SecretKey gerarChaveAES(String senha) throws Exception {
        byte[] chaveBytes = senha.getBytes("UTF-8");
        return new SecretKeySpec(chaveBytes, 0, 16, "AES");
    }
}

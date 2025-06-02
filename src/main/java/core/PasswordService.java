
package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PasswordService {
    private final Map<String, Conta> senhas = new HashMap<>();

    public void salvarSenha(String servico, String usuario, String senha) {
        String senhaHash = org.mindrot.jbcrypt.BCrypt.hashpw(senha, org.mindrot.jbcrypt.BCrypt.gensalt());

        Conta novaConta = new Conta(usuario, senhaHash);
        senhas.put(servico, novaConta);

        PasswordStorage.salvarEmArquivo(senhas);

        System.out.println("Senha registrada com êxito para o serviço: " + servico);
    }

    public void listarSenhas() {
        System.out.println("Serviços cadastrados:");
        for (Map.Entry<String, Conta> entry : senhas.entrySet()) {
            System.out.println("- " + entry.getKey() + ":");
            System.out.println("    " + entry.getValue());
        }
    }

    public void carregarSenhas() {
        Map<String, Conta> dados = PasswordStorage.carregarDoArquivoSimples();
        if (dados != null) {
            senhas.putAll(dados);
        }
    }
}


package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe principal que representa PasswordService
public class PasswordService {
// Método auxiliar: HashMap<>
    private final Map<String, List<Conta>> senhas = new HashMap<>();

// Método que executa salvarSenha
    public void salvarSenha(String servico, String usuario, String senha) {
        String senhaHash = org.mindrot.jbcrypt.BCrypt.hashpw(senha, org.mindrot.jbcrypt.BCrypt.gensalt());

        Conta novaConta = new Conta(usuario, senhaHash);
        senhas.computeIfAbsent(servico, k -> new ArrayList<>()).add(novaConta);

        PasswordStorage.salvarEmArquivo(senhas);

        System.out.println("Senha registrada com êxito para o serviço: " + servico);
    }

// Método que executa listarSenhas
    public void listarSenhas() {
        System.out.println("Serviços cadastrados:");
        for (String servico : senhas.keySet()) {
            List<Conta> contas = senhas.get(servico);
            System.out.println("- " + servico + ":");
            for (Conta conta : contas) {
                System.out.println("    " + conta);
            }
        }
    }

// Método que executa carregarSenhas
    public void carregarSenhas() {
        Map<String, List<Conta>> dados = PasswordStorage.carregarDoArquivo();
        if (dados != null) {
            senhas.putAll(dados);
        }
    }
}


package core;

// Classe principal que representa Conta
public class Conta {
// Atributo privado
    private String usuario;
// Atributo privado
    private String senhaHash;

// Construtor ou método público: Conta
    public Conta(String usuario, String senhaHash) {
        this.usuario = usuario;
        this.senhaHash = senhaHash;
    }

// Construtor ou método público: getUsuario
    public String getUsuario() {
        return usuario;
    }

// Construtor ou método público: getSenhaHash
    public String getSenhaHash() {
        return senhaHash;
    }

    @Override
// Construtor ou método público: toString
    public String toString() {
        return "Usuário: " + usuario + ", Senha criptografada: " + senhaHash;
    }
}

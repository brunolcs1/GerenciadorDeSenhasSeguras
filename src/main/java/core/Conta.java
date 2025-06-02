
package core;

public class Conta {
    private String usuario;
    private String senhaHash;

    public Conta(String usuario, String senhaHash) {
        this.usuario = usuario;
        this.senhaHash = senhaHash;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    @Override
    public String toString() {
        return "Usuário: " + usuario + ", Senha criptografada: " + senhaHash;
    }
}

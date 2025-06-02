package ui;

import java.util.Scanner;
import core.PasswordService;
import utils.PasswordLeakChecker;
import utils.PasswordGenerator;

public class PasswordManagerApp {

    public static void mostrarMenu() {
        System.out.println("\nGERENCIADOR DE SENHAS SEGURAS");
        System.out.println("1. Cadastrar nova conta");
        System.out.println("2. Ver contas armazenadas");
        System.out.println("3. Gerar senha segura");
        System.out.println("4. Encerrar aplicação");
        System.out.print("Selecione uma alternativa: ");
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        PasswordService passwordService = new PasswordService();
        passwordService.carregarSenhas();

        System.out.println("Acesso ao sistema iniciado.");

        auth.AuthService authService = new auth.AuthService();
        if (!authService.login(inputScanner)) {
            System.out.println("Acesso negado. Encerrando o sistema.");
            return;
        }

        boolean executando = true;
        while (executando) {
            mostrarMenu();
            String opcao = inputScanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Informe o nome do site ou app (ex: Gmail): ");
                    String servico = inputScanner.nextLine();
                    System.out.print("Nome de usuário: ");
                    String usuario = inputScanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = inputScanner.nextLine();

                    PasswordLeakChecker.verificarVazamento(senha);
                    passwordService.salvarSenha(servico, usuario, senha);
                    break;

                case "2":
                    passwordService.listarSenhas();
                    break;

                case "3":
                    String senhaGerada = PasswordGenerator.gerarSenha(16);
                    System.out.println("Senha sugerida: " + senhaGerada);
                    break;

                case "4":
                    System.out.println("Fechando o aplicativo...");
                    executando = false;
                    break;

                default:
                    System.out.println("Escolha não reconhecida.");
            }
        }

        inputScanner.close();
    }
}
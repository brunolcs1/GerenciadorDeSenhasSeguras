# 🔒 Gerenciador de Senhas Seguras
Aplicação em Java que permite o gerenciamento de senhas de forma segura utilizando criptografia, autentificação, geração de senhas aleatórias e verificação de vazamentos.

---

## ✅ Funcionalidades
- Autenticação com chave mestre usando bcrypt.
- Autenticação em duas etapas (2FA) via Google Authenticator.
- Armazenamento seguro de contas com criptografia (AES).
- Geração automática de senhas fortes e únicas.
- Verificação se uma senha foi vazada (HIBP).
- Interface simples em terminal para uso prático.

---

## 🛠️ Tecnologias Utilizadas
- **Java 8+**
- **Google Authenticator**
- **API HaveIBeenPwned**
- **Maven**

---

## 📁 Estrutura do Projeto
```
src/
├── auth/ # Autenticação: chave mestre e 2FA (Google Authenticator)
│ ├── AuthService.java
│ └── TwoFactorAuth.java
│
├── core/ # Lógica principal: contas, serviços e armazenamento
│ ├── Conta.java
│ ├── PasswordService.java
│ └── PasswordStorage.java
│
├── crypto/ # Criptografia AES e hashing com bcrypt
│ ├── AESUtil.java
│ └── HashUtil.java
│
├── ui/ # Interface principal via terminal
│ └── PasswordManagerApp.java
│
├── utils/ # Utilitários: gerador de senhas e verificação HIBP
│ ├── PasswordGenerator.java
│ └── PwnedPasswordChecker.java
```

---

## ▶️ Como Rodar
### Clonar o repositório
```
git clone https://github.com/brunolcs1/GerenciadorDeSenhasSeguras.git
cd GerenciadorDeSenhasSeguras
```
### Requesitos
- Java JDK 8+ instalado
- Maven instalado
### Executar a aplicação
```
java -cp bin ui.PasswordManagerApp
```
### Uso
A aplicação será iniciada no terminal com uma tela de autenticação.
Usuário padrão: **admin**
Chave mestre: **admin123**
Após autenticar, use o menu para navegar.

---

## 🎓 Sobre
Este projeto foi desenvolvido como parte da disciplina Desenvolvimento Seguro, ministrada pelo professor Alexsandro Henrique Batista.

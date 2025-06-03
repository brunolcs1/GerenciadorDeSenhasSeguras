
package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PasswordStorage {
    private static final String ARQUIVO = "passwords.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Type tipoDados = new TypeToken<Map<String, List<Conta>>>() {}.getType();

    public static void salvarEmArquivo(Map<String, List<Conta>> dados) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(dados, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public static Map<String, List<Conta>> carregarDoArquivo() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            return gson.fromJson(reader, tipoDados);
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Falha ao abrir dados. Criando armazenamento novo.");
            return new HashMap<>();
        }
    }
}

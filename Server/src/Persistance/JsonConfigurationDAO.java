package Persistance;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Json configuration dao.
 */
public class JsonConfigurationDAO implements ConfigurationDAO {
    private final int port;
    private final String ip;
    private final String database;
    private final String username;
    private final String password;
    private final int portTCP;

    /**
     * Instantiates a new Json configuration dao that recieves the configuration file.
     *
     * @param path the path
     * @throws IOException the io exception
     */
    public JsonConfigurationDAO(String path) throws IOException {
        Gson gson = new Gson();
        Path path1 = Paths.get(path); // Ens guardem la path on es troba el fitxer de configuraciÃ³

        // Parsejem el fitxer json
        JsonObject json = JsonParser.parseString(Files.readString(path1)).getAsJsonObject();
        this.port = gson.fromJson(json.get("port"), int.class); // obtenciÃ³ de dades segon la key i les guardem
        this.ip = gson.fromJson(json.get("ip"), String.class);
        this.database = gson.fromJson(json.get("database"), String.class);
        this.username = gson.fromJson(json.get("username"), String.class);
        this.password = gson.fromJson(json.get("password"), String.class);
        this.portTCP = gson.fromJson(json.get("portTCP"), int.class);
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public String getDatabase() {
        return this.database;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public int getPortTCP() {
        return this.portTCP;
    }
}

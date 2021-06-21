package seabattle.server.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SocketConfig {

    private String ip = "localhost";
    private String port;
    private String defaultPort = "8000";

    public SocketConfig() throws IOException {
        setProperties();
    }
    private void setProperties() throws IOException {
        MainConfig mainConfig = new MainConfig();
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfig.getSocketConfigPath());
        properties.load(fileReader);

        port = properties.getProperty("port");
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getDefaultPort() {
        return defaultPort;
    }
}

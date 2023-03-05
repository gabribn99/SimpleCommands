package plugin.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.adapters.LocationAdapter;
import plugin.commands.*;
import plugin.entities.LocationBean;
import plugin.entities.TPBean;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    public static final String PATH = "simpleCommands/";
    public static final String FILENAME = "simpleCommands.json";
    public static Map<String, Location> mapHomes = new HashMap<>();
    public static Map<String, TPBean> mapTps = new HashMap<>();

    @Override
    public void onEnable() {
        setCommands();
        loadHomes();
        Bukkit.getConsoleSender().sendMessage("Simple Commands está activado");

    }

    @Override
    public void onDisable() {
        saveHomes();
        Bukkit.getConsoleSender().sendMessage("Simple Commands está desactivado");
    }

    private void setCommands() {
        getCommand("tpa").setExecutor(new TPA());
        getCommand("tpaccept").setExecutor(new TPAccept());
        getCommand("tpdeny").setExecutor(new TPDeny());
        getCommand("home").setExecutor(new Home());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("delhome").setExecutor(new DelHome());
        getCommand("enderchest").setExecutor(new EnderChest());
    }

    private void saveHomes() {
        File file = new File(PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocationBean.class, new LocationAdapter());
        Gson gson = builder.create();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(PATH + FILENAME));
            mapHomes.forEach((playerName, location) -> {
                LocationBean locationBean = new LocationBean(location.getWorld().getName(), playerName, location.getX(), location.getY(), location.getZ());
                pw.println(gson.toJson(locationBean));
            });
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHomes() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocationBean.class, new LocationAdapter());
        Gson gson = builder.create();
        List<LocationBean> locationBeanList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH + FILENAME));
            String jsonString;
            while ((jsonString = br.readLine()) != null) {
                locationBeanList.add(gson.fromJson(jsonString, LocationBean.class));
            }
            locationBeanList.forEach(locationBean -> {
                Location location = new Location(getServer().getWorld(locationBean.getWorldName()), locationBean.getX(), locationBean.getY(), locationBean.getZ());
                mapHomes.put(locationBean.getPlayerName(), location);
            });
        } catch (IOException e) {
            System.out.println("Aun no existe el fichero \"" + FILENAME + "\"");
        }
    }
}

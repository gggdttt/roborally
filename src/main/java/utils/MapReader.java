package utils;

import gui.view.map.TileType;
import model.game.board.map.element.Antenna;
import model.game.board.map.element.CheckPoint;
import model.game.board.map.element.Tile;

import java.io.*;
import java.util.ArrayList;

/**
 *
 */
public class MapReader {

    /**
     * @ PATH_TO_MAP_FILE: The path to the folder storing maps.txt
     */
    private static final String PATH_TO_MAP_FILE = "src/main/resources/maps/";
    private static final String FULLY_QUALIFIED_NAME_OF_ELEMENT = "model.game.board.map.element.";
    private static final String FULLY_QUALIFIED_NAME_OF_CHECKPOINT ="model.game.board.map.element.CheckPoint";
    /**
     * @ readLineToStringArray: Read the content of .txt file to a string array.
     * One line of this txt file -> one string element of this arraylist
     */
    private static ArrayList<String> readLineToStringArray(String mapName) throws IOException {

        ArrayList<String> strList = new ArrayList<>();
        FileReader reader = new FileReader(new File(PATH_TO_MAP_FILE + mapName + ".txt"));
        BufferedReader readerBuf = new BufferedReader(reader);
        String lineStr;
        while ((lineStr = readerBuf.readLine()) != null) {
            strList.add(lineStr);
        }
        reader.close();
        readerBuf.close();
        return strList;
    }

    /**
     * @ readLineToStringArray: Read the content of .txt file to a string array.
     * One line of this txt file -> one string element of this arraylist
     */
    private static Tile createTileInstance(String str, int x, int y) {
        if ("Antenna".equals(str)) return Antenna.getInstance();
        try {
            if (str.startsWith("CheckPoint")) {
                Class<?> clz = Class.forName(FULLY_QUALIFIED_NAME_OF_CHECKPOINT);
                CheckPoint checkPoint = (CheckPoint) clz.getDeclaredConstructor(Integer.class, Integer.class).newInstance(x, y);
                checkPoint.setCheckPointNum(Integer.parseInt("" + str.charAt(str.length()-1)));
                return checkPoint;
            } else {
                Class<?> clz = Class.forName(FULLY_QUALIFIED_NAME_OF_ELEMENT + str);
                return (Tile) clz.getDeclaredConstructor(Integer.class, Integer.class).newInstance(x, y);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @ txtToTileMatrix: input a Map name (like "map1", "map2") and then read the txt file of this map
     * then parse the txt file to a 2D array of Tile
     */
    public static Tile[][] txtToTileMatrix(String mapName) throws IOException {

        Tile[][] result = null;
        ArrayList<String> strList = MapReader.readLineToStringArray(mapName);

        // The number of columns
        String s = strList.get(0);
        int columnNum = s.split(",").length;

        result = new Tile[strList.size()][columnNum];

        // Put data into a 2D array
        int count = 0;
        for (String line : strList) {
            String[] strs = line.split(",");
            for (int i = 0; i < columnNum; i++) {
                result[count][i] = MapReader.createTileInstance(strs[i], count, i);
            }
            count++;
        }
        return result;
    }


    /**
     * @ txtToTileTypeMatrix: input a string of map name (like "map1", “map2") and then read the txt file of this map
     * then parse the txt file to a 2D array of TileType
     * TIP: THIS IS ONLY USED IN UI!!!!!!!!!!!!!!
     */
    public static TileType[][] txtToTileTypeMatrix(String mapName) throws IOException {
        TileType[][] result = null;
        ArrayList<String> strList;
        strList = MapReader.readLineToStringArray(mapName);

        // The number of columns
        String s = strList.get(0);
        int columnNum = s.split(",").length;

        result = new TileType[strList.size()][columnNum];

        // Put data into a 2D array
        int count = 0;
        for (String str : strList) {
            String[] strs = str.split(",");
            for (int i = 0; i < columnNum; i++) {
                result[count][i] = TileType.valueOf(strs[i].toUpperCase());
            }
            count++;
        }

        return result;
    }

    // This is the test of the function
//    public static void main(String[] args) {
//        Tile[][] map = null;
//        try {
//            map = MapReader.txtToTileMatrix("map1");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        assert map != null;
//        for (Tile[] tiles : map) {
//            for (Tile tile : tiles) {
//                System.out.print(tile.getClass().getSimpleName() + ",");
//            }
//            System.out.println();
//        }
//    }
}

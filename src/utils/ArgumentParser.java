package utils;

import java.util.HashMap;

/**
 * @author hacke
 *
 * Basic parser for command line arguments, parsing "-l arg -k arg2 -f file.txt -flag"
 * into a hashmap of argument name to value
 */
public final class ArgumentParser {

    /*
     * HashMaps to hold name of argument to supplied value, to be retrieved later
     */
    private HashMap<String, String> stringArgumentMap;

    private HashMap<String, Integer> integerArgumentMap;

    private HashMap<String, Boolean> flagArgumentMap;

    public ArgumentParser(){
        this.stringArgumentMap = new HashMap<>();
        this.integerArgumentMap = new HashMap<>();
        this.flagArgumentMap = new HashMap<>();
    }

    /**
     * Parses the line provided into the appropriate HashMaps,
     * to later be queried for values
     * @param line a command line string of format "-l string -flag -n 780"
     */
    public void parseArguments(final String line) throws ArgumentParseException{
        if(line == null) throw new IllegalArgumentException("line passed in cannot be null");
        String parsingLine = line.trim();
        String[] splitLine = line.split("\\s+");
        //iterate over split line, parsing as we go
        for (int i = 0; i < splitLine.length; i++) {
            String temp = splitLine[i];
            //test if string is a valid argument
            if(temp.startsWith("-")){
                String argName = temp.replaceFirst("-", "");
                //if there are no more segments
                if(i + 1 == splitLine.length){
                    //must be a flag argument
                    flagArgumentMap.put(argName, true);
                }else{  //otherwise
                    //check if next is a number
                    if(splitLine[i+1].matches("(\\d+)")){
                        integerArgumentMap.put(argName, Integer.parseInt(splitLine[i+1]));
                        i++;    //can skip next segment
                    }else if(splitLine[i+1].startsWith("-")){ //check if a flag by seeing if next is argument
                        flagArgumentMap.put(argName, true);
                    }else{  // otherwise add as a string argument
                        stringArgumentMap.put(argName, splitLine[i+1]);
                        i++;    //can skip next segment
                    }
                }
            }else{
                throw new ArgumentParseException("\"" + splitLine[i] + "\" is an invalid argument key");
            }
        }
    }

    /**
     * Gets an integer from the integerArgumentHashMap, null if no value
     * @param argumentName the character that represents the value
     * @return the value with key provided or null if nonexistent
     */
    public Integer getInteger(final String argumentName){
        if(argumentName == null) throw new IllegalArgumentException("argument name cannot be null");
        return integerArgumentMap.getOrDefault(argumentName, null);
    }

    /**
     * Gets a string from the stringArgumentHashMap, null if no value
     * @param argumentName the character that represents the value
     * @return the value with key provided or null if nonexistent
     */
    public String getString(final String argumentName){
        if(argumentName == null) throw new IllegalArgumentException("argument name cannot be null");
        return stringArgumentMap.getOrDefault(argumentName, null);
    }

    /**
     * Gets a bool from the flagArgumentHashMap, false if no value
     * @param argumentName the character that represents the value
     * @return the value with key provided or false if nonexistent
     */
    public Boolean getFlag(final String argumentName){
        if(argumentName == null) throw new IllegalArgumentException("argument name cannot be null");
        return flagArgumentMap.getOrDefault(argumentName, false);
    }
}


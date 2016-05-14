package acklib.utils.paths;

import java.io.File;
import java.io.IOException;

/**
 * @author hacke
 *
 * Collection of useful utilities for file operations
 */
public final class FileUtils {
    /*
     * Utility class, cannot be instantiated
     */
    private FileUtils(){}

    /**
     * Returns whether or not a filename is valid or not, platform independent
     *
     * @param fileName name of file
     * @return  fileName is a valid filename on the platform
     */
    public static boolean validFilename(final String fileName){
        try{    //ttry to make file and access path
            File file = new File(fileName);
            file.getCanonicalPath();
        }catch (IOException ioe){   //unable to get path of file
            return false;   //so filename is invalid
        }
        return true;    //valid file
    }
}
